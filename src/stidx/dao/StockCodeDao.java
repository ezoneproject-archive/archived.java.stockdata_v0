package stidx.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import stidx.tables.CommonTrans;
import stidx.tables.StockCode;

public class StockCodeDao extends HibernateDaoSupport {
    Logger log = LogManager.getLogger(StockCodeDao.class);

    public StockCode select(String stockCode) {
        return (StockCode) getHibernateTemplate().get(StockCode.class,
            stockCode);
    }

    public void insert(StockCode stockCode) {
        getHibernateTemplate().save(stockCode);
    }

    public void update(StockCode stockCode) {
        getHibernateTemplate().update(stockCode);
    }

    /**
     * 특정 일자의 종목코드 전체를 가져온다.
     * 
     * @param gsdate
     * @param tblName
     * @return List<Properties> "StockCode", "StockName"
     */
    public List<Properties> stockCodeList(String gsdate, String tblName) {
        @SuppressWarnings("unchecked")
        List<CommonTrans> dtstockcd = getHibernateTemplate().find(
            "from "
                    + tblName
                    + " as dt where dt.gsDate=? group by dt.stockCode order by dt.stockCode",
            gsdate);

        // 응답할 표준리스트 모델
        List<Properties> result = new ArrayList<Properties>();

        // 가공작업
        Properties prop = null;
        for (CommonTrans item : dtstockcd) {
            prop = new Properties();

            // 종목코드 및 종목명 셋
            StockCode stockCodeB = select(item.getStockCode());
            prop.setProperty("StockCode", item.getStockCode());
            prop.setProperty("StockName", (stockCodeB == null ? "?"
                    : stockCodeB.getStockName()));

            result.add(prop);
        }

        return result;
    }

}
