package stidx.util;

import java.util.Calendar;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {
    public static Log log = LogFactory.getLog(DateUtil.class);

    public static String getCurrentFormat(String format) {
        return DateFormatUtils.format(System.currentTimeMillis(), format);
    }

    public static String getCurrentDate8() {
        return getCurrentFormat("yyyyMMdd");
    }

    public static String getCurrentTime6() {
        return getCurrentFormat("HHmmss");
    }

    public static String nextMinute(String time, int minute) {
        int tm = Integer.parseInt(time);
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.HOUR_OF_DAY, tm / 10000);
        cal.set(Calendar.MINUTE, (tm - (tm / 10000 * 10000)) / 100);
        cal.set(Calendar.SECOND, 0);

        cal.add(Calendar.MINUTE, minute);

        String ndate = DateFormatUtils.format(cal.getTimeInMillis(), "HHmmss");
        //if (ndate.startsWith("00"))
        //    ndate = "24" + ndate.substring(2);
        return ndate;
    }
}
