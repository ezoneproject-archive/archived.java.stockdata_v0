package stidx.controllers.validator;

import org.apache.commons.validator.DateValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import stidx.controllers.form.UploadCommand;
import stidx.util.DateUtil;

public class UploadValidator implements Validator {
    Logger log = LogManager.getLogger(UploadValidator.class);

    @Override
    public boolean supports(@SuppressWarnings("rawtypes") Class clazz) {
        if (UploadCommand.class.isAssignableFrom(clazz))
            return true;
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        int errCnt = 0;
        int fatalCnt = 0;
        UploadCommand param = (UploadCommand) target;

        // 파일이 업로드 되었는지 체크 (최우선 체크사항)
        MultipartFile uploadFile = param.getUploadFile();
        if (uploadFile == null || uploadFile.isEmpty()) {
            errors.rejectValue("uploadFile", "upload.err");
            fatalCnt++;
        }

        // 자료일자 포맷 체크
        if (!DateValidator.getInstance().isValid(param.getGsdate(),
            "yyyy-MM-dd", true)) {
            errors.rejectValue("gsdate", "upload.err.format");
            fatalCnt++;
        }

        if (fatalCnt > 0) {
            return;
        }

        // 검증 생략 체크박스가 선택되어 있으면 검증작업을 생략하고 진행 
        if (param.getValidCheck().equals("skip"))
            return;

        if (!DateUtil.getCurrentFormat("yyyy-MM-dd").equals(param.getGsdate())) {
            errors.rejectValue("gsdate", "upload.err");
            errCnt++;
        }

        // 자료시각 비교
        if (param.getFileType().equals("inv")
                || param.getFileType().equals("fgn")
                || param.getFileType().equals("fgninv")) {
            if (param.getTimeType().equals("0920")
                    || param.getTimeType().equals("0950")
                    || param.getTimeType().equals("1100")
                    || param.getTimeType().equals("1320")
                    || param.getTimeType().equals("1505")) {
                ; // next sentence
            }
            else {
                // 자료시각과 선택한 시각이 다름
                errCnt++;
                errors.rejectValue("timeType", "upload.err");
            }
        }
        else if (param.getFileType().equals("strmov") || param.getFileType().equals("tr20")) {
            if (param.getTimeType().equals("0930")
                    || param.getTimeType().equals("1000")
                    || param.getTimeType().equals("1100")
                    || param.getTimeType().equals("1200")
                    || param.getTimeType().equals("1300")
                    || param.getTimeType().equals("1400")
                    || param.getTimeType().equals("1530")) {
                ; // next sentence
            }
            else {
                // 자료시각과 선택한 시각이 다름
                errCnt++;
                errors.rejectValue("timeType", "upload.err");
            }
        }

        if (errCnt > 0)
            errors.rejectValue("uploadFile", "upload.err.notice");
    }
}
