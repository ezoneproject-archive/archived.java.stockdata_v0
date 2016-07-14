package stidx.dao;

import java.text.NumberFormat;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ListDTMask {
    static Logger log = LogManager.getLogger(ListDTMask.class);

    private ListDTMask() {}

    /**
     * 숫자 마스크 (숫자에 , 를 찍는다)
     * 
     * @param list 리스트
     * @param propNames 세미콜론(;)으로 구분된 프로퍼티 목록, startsWith로 일치여부 비교
     */
    public static void number(List<Properties> list, String propNames) {
        NumberFormat mask = NumberFormat.getNumberInstance();
        String[] plist = propNames.split(";");

        for (Properties item : list) {
            for (Enumeration<Object> e = item.keys(); e.hasMoreElements();) {
                String key = (String) e.nextElement();

                for (String pname : plist) {
                    if (key.startsWith(pname)) {
                        item.setProperty(key,
                            mask.format(Long.parseLong(item.getProperty(key))));
                    }
                }
            }
        }
    }

}
