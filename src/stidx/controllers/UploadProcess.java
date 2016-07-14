package stidx.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;
import stidx.controllers.form.UploadCommand;
import stidx.dao.BulkInsertModule;

public class UploadProcess extends AbstractCommandController {
    Logger log = LogManager.getLogger(UploadProcess.class);

    @Override
    protected ModelAndView handle(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception {
        ApplicationContext context = getApplicationContext();
        UploadCommand param = (UploadCommand) command;

        // 검증 오류가 발생하면 이전 폼으로 되돌린다
        if (errors.hasErrors()) {
            ModelAndView mave = new ModelAndView("upload", errors.getModel());
            mave.addObject("gsdate", param.getGsdate()); // 이걸 일일히 셋업하지 않고 자동으로 set 할 방법이 있을텐데...

            mave.addObject("menu_upload", "active");
            mave.addObject("upload_error", "y");
            return mave;
        }

        // 업로드한 파일 분석
        String fileType = param.getFileType(); // 파일종류
        String gsdate = param.getGsdate(); // 일자
        String timeType = param.getTimeType(); // 기준시각
        MultipartFile uploadFile = param.getUploadFile();

        gsdate = gsdate.replaceAll("-", "");
        log.debug("fileType: " + fileType);
        log.debug("GsDate: " + gsdate);
        log.debug("TimeType: " + timeType);

        BufferedReader br = new BufferedReader(new InputStreamReader(
            uploadFile.getInputStream(), "EUC-KR" ));

        BulkInsertModule insertModule = null;
        if (fileType.equals("inv")) {
            insertModule = (BulkInsertModule) getApplicationContext().getBean(
                "bulkInsertInvest");
        }
        else if (fileType.equals("fgn")) {
            insertModule = (BulkInsertModule) getApplicationContext().getBean(
                "bulkInsertForeign");
        }
        else if (fileType.equals("fgninv")) {
            insertModule = (BulkInsertModule) getApplicationContext().getBean(
                "bulkInsertForeignInvest");
        }

        else if (fileType.equals("strmov")) {
            insertModule = (BulkInsertModule) getApplicationContext().getBean(
                "bulkInsertStrMov");
        }
        else if (fileType.equals("tr20")) {
            insertModule = (BulkInsertModule) getApplicationContext().getBean(
                "bulkInsertTR20");
        }

        else {
            // 지원하지 않는 모듈명
            throw new Exception("upload.jsp:fileType - " + fileType);
        }

        int lineCount = 0;
        String lineData = br.readLine();

        try {
            // 첫째열은 행
            insertModule.checkHeader(lineData);

            // 데이터 입력 시작
            lineData = br.readLine();
            while (lineData != null) {
                lineCount++;
                insertModule.insertBulkRaw(gsdate, timeType, lineData);

                //                if (lineCount == 0) {
                //                    throw new FormatErrorException("사용자 에러");
                //                }

                lineData = br.readLine();
            }
        }
        catch (Exception e) {
            log.error("Data error line: " + lineCount + "[" + lineData + "]");
            throw e;
        }
        finally {
            IOUtils.closeQuietly(br);
        }

        // 입력 성공
        ModelAndView mav = new ModelAndView("uploadfin");

        String fileTypeNm = context.getMessage("upload.filetype." + fileType,
            new Object[0], null);
        mav.addObject("upload_fileType", fileTypeNm);
        mav.addObject("upload_timeType", param.getTimeType());

        mav.addObject("menu_upload", "active");
        return mav;
    }

}
