package stidx.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import stidx.controllers.validator.FormatErrorException;
import stidx.tables.StockCode;
import stidx.tables.StrMov;

public class BulkInsertStrMov extends HibernateDaoSupport implements
        BulkInsertModule {
    Logger log = LogManager.getLogger(BulkInsertStrMov.class);
    StockCodeDao stockCodeDao = null;

    String[] headerList = { "종목명", "외인당일", "기관당일", "전일대비율", "전일거래량 대비율",
            "외인잠정", "기관잠정", "외인전일", "기관전일", "유보율(분기)", "EV/EBITDA", "종목코드",
            "재무시점(분기)", "소속업종" };

    @Override
    public void checkHeader(String header) throws Exception {
        String[] hitem = header.split("\t");
        if (hitem.length != headerList.length)
            throw new FormatErrorException("Header count check error. (Fixed:"
                    + headerList.length + ", Input:" + hitem.length + ")");

        for (int i = 0; i < headerList.length; i++) {
            if (!headerList[i].equals(hitem[i])) {
                throw new FormatErrorException("Header error. No." + (i + 1)
                        + " '" + headerList[i] + "' != '" + hitem[i] + "'.");
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertBulkRaw(String date, String time, String data)
            throws Exception {
        String[] item = data.split("\t");

        // 종목명 ~ 종목코드까지는 모두 필수항목, 소속업종은 선택항목
        if (item.length < 12)
            throw new FormatErrorException("Data count check error. (Fixed:"
                    + headerList.length + ", Input:" + item.length + ")");

        // 종목코드 확인
        if (stockCodeDao.select(item[11]) == null) {
            log.debug("StockCode [" + item[11] + "] not found.");
            StockCode stockCode = new StockCode();
            stockCode.setStockCode(item[11]);
            stockCode.setStockName(item[0]);
            if (item.length > 13)
                stockCode.setCategoryName(item[13]);
            stockCodeDao.insert(stockCode);
        }

        StrMov tr = new StrMov();
        tr.setGsDate(date);
        tr.setGsTime(time);
        tr.setStockCode(item[11]);

        tr.setTodayForeign(Integer.parseInt(item[1].replaceAll(",", "")));
        tr.setTodayInvest(Integer.parseInt(item[2].replaceAll(",", "")));
        tr.setYestRate(Float.parseFloat(item[3].replaceAll(",", "").replaceAll(
            "%", "")));
        tr.setYestTransRate(Float.parseFloat(item[4].replaceAll(",", "").replaceAll(
            "%", "")));
        tr.setSubtotForeign(Integer.parseInt(item[5].replaceAll(",", "")));
        tr.setSubtotInvest(Integer.parseInt(item[6].replaceAll(",", "")));
        tr.setYestForeign(Integer.parseInt(item[7].replaceAll(",", "")));
        tr.setYestInvest(Integer.parseInt(item[8].replaceAll(",", "")));
        tr.setRateReserve(Float.parseFloat(item[9].replaceAll(",", "").replaceAll(
            "%", "")));
        tr.setEV_EBITDA(Float.parseFloat(item[10].replaceAll(",", "").replaceAll(
            "%", "")));

        insertOrUpdate(tr);
    }

    public void insertOrUpdate(StrMov investTrans) {
        getHibernateTemplate().saveOrUpdate(investTrans);
    }

    public void delete(StrMov investTrans) {
        getHibernateTemplate().delete(investTrans);
    }

    public void setStockCodeDao(StockCodeDao stockCodeDao) {
        this.stockCodeDao = stockCodeDao;
    }
}
