package stidx.dao;

import java.util.Comparator;
import java.util.Properties;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ListDTOrder implements Comparator<Properties> {
    Logger log = LogManager.getLogger(ListDTOrder.class);

    private String ordTime = null;
    private String ordPropNm = null;

    public ListDTOrder(String ordPropNm) {
        this.ordPropNm = ordPropNm;
    }

    public ListDTOrder(String ordPropNm, String ordTime) {
        this.ordPropNm = ordPropNm;
        this.ordTime = ordTime;
    }

    /**
     * 소계 큰->작음 순서로 정렬, 소계는 시간이 정해지지 않았으면 마지막 시간의 소계를 기준으로 한다
     */
    @Override
    public int compare(Properties o1, Properties o2) {

        String lastTime1 = "";
        String lastTime2 = "";

        if (ordTime != null && ordTime.length() > 0) {
            lastTime1 = ordTime;
            lastTime2 = ordTime;
        }
        else {
            if (o1.getProperty("TimeList") != null) {
                String[] tlist1 = o1.getProperty("TimeList").split(",");
                lastTime1 = tlist1[tlist1.length - 1];
            }

            if (o2.getProperty("TimeList") != null) {
                String[] tlist2 = o2.getProperty("TimeList").split(",");
                lastTime2 = tlist2[tlist2.length - 1];
            }
        }

        long sum1 = Long.parseLong(o1.getProperty(ordPropNm + lastTime1, "0"));
        long sum2 = Long.parseLong(o2.getProperty(ordPropNm + lastTime2, "0"));

        int cmp = (int) (sum2 - sum1);
        if (cmp != 0)
            return cmp;

        // 소계가 동일하면 종목코드순
        return o1.getProperty("StockCode").compareTo(
            o2.getProperty("StockCode"));

    }

}
