package stidx.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.validator.DateValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import stidx.dao.ListDTDao;
import stidx.dao.ListDTOrder;
import stidx.dao.StockCodeDao;
import stidx.util.DateUtil;

public class DiffView extends AbstractController {
    Logger log = LogManager.getLogger(DiffView.class);
    ListDTDao listDT = null;
    StockCodeDao stockDT = null;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("diff");

        mav.addObject("menu_diff", "active");

        String fileType = request.getParameter("fileType");
        String gsdate = request.getParameter("gsdate");
        String order = request.getParameter("ord");

        if (fileType == null)
            fileType = "strmov";
        if (gsdate == null)
            gsdate = DateUtil.getCurrentFormat("yyyy-MM-dd");

        log.debug("fileType [" + fileType + "]");
        log.debug("gsdate [" + gsdate + "]");

        // 자료일자 포맷 체크
        if (!DateValidator.getInstance().isValid(gsdate, "yyyy-MM-dd", true)) {
            // error
            //BindException errors = new BindException(null);
        }

        // 날짜의 "-"를 제거
        String gsdate2 = gsdate.replaceAll("-", "");

        List<Properties> timelist = null; // 해당일자 시간 전체 목록
        List<Properties> stocklist = null; // 시간대별 종목코드 목록


        if (fileType.equals("strmov")) {
            timelist = listDT.listTimeGroup(gsdate2, "StrMov");
            stocklist = listDT.listTimeStockGroup(gsdate2, "StrMov");
        }
        else if (fileType.equals("tr20")) {
            timelist = listDT.listTimeGroup(gsdate2, "TR20");
            stocklist = listDT.listTimeStockGroup(gsdate2, "TR20");
        }
        else {
            // 지원하지 않는 모듈명
            throw new Exception("upload.jsp:fileType - " + fileType);
        }


        mav.addObject("timelist", timelist);
        mav.addObject("stocklist", stocklist);

        mav.addObject(fileType, "checked");
        mav.addObject("gsdate", gsdate);

        return mav;
    }

    public void setListDT(ListDTDao listDT) {
        this.listDT = listDT;
    }

    public void setStockDT(StockCodeDao stockDT) {
        this.stockDT = stockDT;
    }

}
