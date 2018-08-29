package com.copower.pmcc.assess.common;

import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: zch
 * @Date: 2018/8/29 17:04
 * @Description:date处理
 */
public class DateHelp {
    private static DateHelp dateHelp = new DateHelp();

    public String printDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String str = String.format("%s%s%s",
                String.format("%d%s", calendar.get(Calendar.YEAR), "年"),
                String.format("%d%s", calendar.get(Calendar.MONTH) + 1, "月"),
                String.format("%d%s", calendar.get(Calendar.DATE), "日"));
        return str;
    }

    private DateHelp() {
    }

    public static DateHelp getDateHelp() {
        if (dateHelp == null) {
            dateHelp = new DateHelp();
        }
        return dateHelp;
    }
}
