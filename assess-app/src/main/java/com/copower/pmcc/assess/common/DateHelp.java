package com.copower.pmcc.assess.common;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public Date parse(String str, String model) {
        SimpleDateFormat sdf = null;
        if (StringUtils.isEmpty(model)){
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }else {
            sdf = new SimpleDateFormat(model);
        }
        try {
            if (!StringUtils.isEmpty(str)) {
                Date date = sdf.parse(str);
                return date;
            }
        } catch (ParseException e) {

        }
        return null;
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
