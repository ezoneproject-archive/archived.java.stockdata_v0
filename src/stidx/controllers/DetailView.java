package stidx.controllers;

import java.util.Collections;
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
import stidx.dao.ListDTMask;
import stidx.dao.ListDTOrder;
import stidx.util.DateUtil;

public class DetailView extends AbstractController {
    Logger log = LogManager.getLogger(DetailView.class);
    ListDTDao listDT = null;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("detail");

        String stcode = request.getParameter("stcode");
        String frdate = request.getParameter("frdate");
        String todate = request.getParameter("todate");

        mav.addObject("menu_detail", "active");

        log.debug("stcode [" + stcode + "]");
        log.debug("frdate [" + frdate + "]");
        log.debug("todate [" + todate + "]");
        
        // 선택된 종목이 없을 경우 종목 선택 요청
        if (stcode == null || frdate == null || todate == null) {
            return mav;
        }

        mav.addObject("stcode", stcode);
        mav.addObject("frdate", frdate);
        mav.addObject("todate", todate);


        // 자료일자 포맷 체크
        if (!DateValidator.getInstance().isValid(frdate, "yyyy-MM-dd", true)) {
            // error
            //BindException errors = new BindException(null);
        }
        if (!DateValidator.getInstance().isValid(todate, "yyyy-MM-dd", true)) {
            // error
            //BindException errors = new BindException(null);
        }

        // 날짜의 "-"를 제거
        frdate = frdate.replaceAll("-", "");
        todate = todate.replaceAll("-", "");

        /*

        List<Properties> rlist = null;

        Comparator<Properties> ord = null;

        if (fileType.equals("inv")) {
            rlist = listDT.listCommon(gsdate2, "InvestTrans");
            ord = new ListDTOrder("SubtotInvest_");
        }
        else if (fileType.equals("fgn")) {
            rlist = listDT.listCommon(gsdate2, "ForeignTrans");
            ord = new ListDTOrder("SubtotForeign_");
        }
        else if (fileType.equals("fgninv")) {
            rlist = listDT.listCommon(gsdate2, "ForeignInvestTrans");
            ord = new ListDTOrder("Sum_");
        }
        else {
            // 지원하지 않는 모듈명
            throw new Exception("upload.jsp:fileType - " + fileType);
        }

        if (order != null && order.indexOf("_") > 0) {
            int idx = order.indexOf("_");
            ord = new ListDTOrder(order.substring(0, idx + 1),
                order.substring(idx + 1));

            mav.addObject(order, "D"); // descending
        }

        Collections.sort(rlist, ord);
        ListDTMask.number(rlist,
            "ChangeForeign_;SubtotForeign_;ChangeInvest_;SubtotInvest_;Sum_");

        mav.addObject("lview", rlist);

        mav.addObject(fileType, "checked");
        mav.addObject("gsdate", gsdate);
            */

        return mav;
    }

    public void setListDT(ListDTDao listDT) {
        this.listDT = listDT;
    }

}
