package stidx.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import stidx.tables.CommonTrans;
import stidx.tables.InvestTrans;
import stidx.tables.StockCode;

public class ListDTDao extends HibernateDaoSupport {
    Logger log = LogManager.getLogger(ListDTDao.class);
    StockCodeDao stockCodeDao = null;

    public List<Properties> listCommon(String gsdate, String tblName) {

        @SuppressWarnings("unchecked")
        List<InvestTrans> dtlist = getHibernateTemplate().find(
            "from "
                    + tblName
                    + " as dt where dt.gsDate=? order by dt.stockCode, dt.gsTime",
            gsdate);

        // 응답할 표준리스트 모델
        List<Properties> result = new ArrayList<Properties>();

        // 가공작업
        String lastStockCode = null;
        String timeList = "";
        CommonTrans pitem = null;
        Properties prop = null;
        for (CommonTrans item : dtlist) {
            if (!item.getStockCode().equals(lastStockCode)) {
                // 종목 최초 자료일 경우
                prop = new Properties();
                result.add(prop);
                lastStockCode = item.getStockCode();

                // 종목코드 및 종목명 셋
                StockCode stockCodeB = stockCodeDao.select(item.getStockCode());
                prop.setProperty("StockCode", item.getStockCode());
                prop.setProperty("StockName", (stockCodeB == null ? "?"
                        : stockCodeB.getStockName()));

                timeList = "";

                // 증감 계산시 최초자료는 없으므로...
                pitem = item;
            }

            int foreignVal = item.getSubtotForeign();
            int investVal = item.getSubtotInvest();

            // 2015-01-15 변경
            // 15시 05분 최종 데이터는 마감 확정자료이므로 subtot이 아니라 today로 대신 가져온다
            if (item.getGsTime().equals("1505")) {
                foreignVal = item.getTodayForeign();
                investVal = item.getTodayInvest();
            }

            // 외인 증감
            prop.setProperty("ChangeForeign_" + item.getGsTime(),
                String.valueOf(foreignVal - pitem.getSubtotForeign()));
            // 외인 계
            prop.setProperty("SubtotForeign_" + item.getGsTime(),
                String.valueOf(foreignVal));
            // 기관 증감
            prop.setProperty("ChangeInvest_" + item.getGsTime(),
                String.valueOf(investVal - pitem.getSubtotInvest()));
            // 기관 계
            prop.setProperty("SubtotInvest_" + item.getGsTime(),
                String.valueOf(investVal));

            // 소계
            prop.setProperty("Sum_" + item.getGsTime(),
                String.valueOf(foreignVal + investVal));

            timeList += item.getGsTime() + ",";
            prop.setProperty("TimeList", timeList);

            pitem = item;
        }

        return result;
    }

    /**
     * 해당일자 시간별 종목 목록
     * 
     * @param gsdate
     * @param tblName
     * @return
     */
    public List<Properties> listTimeStockGroup(String gsdate, String tblName) {

        @SuppressWarnings("unchecked")
        List<CommonTrans> dtlist = getHibernateTemplate().find(
            "from "
                    + tblName
                    + " as dt where dt.gsDate=? group by dt.gsTime, dt.stockCode order by dt.gsTime, dt.stockCode",
            gsdate);

        // 종목 리스트
        @SuppressWarnings("unchecked")
        List<CommonTrans> stocklist = getHibernateTemplate().find(
            "from "
                    + tblName
                    + " as dt where dt.gsDate=? group by dt.stockCode order by dt.stockCode",
            gsdate);

        // 응답할 표준리스트 모델
        List<Properties> result = new ArrayList<Properties>();

        // 가공작업

        // 1. 전체 종목 목록 편집
        Properties stockListP = new Properties();
        stockListP.setProperty("MAX", "" + stocklist.size());
        for (int i = 0; i<stocklist.size(); i++) {
            stockListP.setProperty("" + (i+1), stocklist.get(i).getStockCode());
        }
        result.add(stockListP);

        // 시간별 종목 목록
        for (CommonTrans item : dtlist) {
            Properties prop = new Properties();

            // 종목코드 및 종목명 셋
            StockCode stockCodeB = stockCodeDao.select(item.getStockCode());
            prop.setProperty("StockCode", item.getStockCode());
            prop.setProperty("StockName", (stockCodeB == null ? "?"
                    : stockCodeB.getStockName()));

            prop.setProperty("Time", item.getGsTime());

            result.add(prop);
        }

        return result;
    }

    /**
     * 해당일자 등록된 시간 목록
     * 
     * @param gsdate
     * @param tblName
     * @return
     */
    public List<Properties> listTimeGroup(String gsdate, String tblName) {

        @SuppressWarnings("unchecked")
        List<InvestTrans> dtlist = getHibernateTemplate().find(
            "from "
                    + tblName
                    + " as dt where dt.gsDate=? group by dt.gsTime order by dt.gsTime",
            gsdate);

        // 응답할 표준리스트 모델
        List<Properties> result = new ArrayList<Properties>();

        // 가공작업
        for (CommonTrans item : dtlist) {
            Properties prop = new Properties();
            prop.setProperty("Time", item.getGsTime());

            result.add(prop);
        }

        return result;
    }

    public void setStockCodeDao(StockCodeDao stockCodeDao) {
        this.stockCodeDao = stockCodeDao;
    }

}
