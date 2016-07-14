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

public class ListView extends AbstractController {
    Logger log = LogManager.getLogger(ListView.class);
    ListDTDao listDT = null;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        String fileType = request.getParameter("fileType");
        String gsdate = request.getParameter("gsdate");
        String order = request.getParameter("ord");

        if (fileType == null)
            fileType = "inv";
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

        ModelAndView mav = new ModelAndView("listview"); // 나중에 DB구조 달라지면 분리
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

        mav.addObject("menu_view", "active");
        return mav;
    }

    public void setListDT(ListDTDao listDT) {
        this.listDT = listDT;
    }

}
