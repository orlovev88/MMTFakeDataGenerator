package com.mmt.fakedatalibrary.util;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class HourISO8601 {

    public static String timestampMsToHourIso8601(long timestamp)
    {
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        sdf.setTimeZone(TimeZone.getTimeZone("CET"));
        String hourISO8601 = sdf.format(timestamp);

        return hourISO8601;
    }
}
