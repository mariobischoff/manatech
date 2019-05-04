package com.fatece.manatech.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvert {
    public static String getDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
