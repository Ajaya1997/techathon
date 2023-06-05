package com.walmart.techathon.util;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;

public class RewardUtility {

    public static long getEpochTime(String date) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.parse(date).getTime();
    }

    public static String getDateFromEpoch(long epochTime) {
        Date date = new Date(epochTime);
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        return format.format(date);
    }

    public static long addTime(long epochTime, int days){
         return Instant.ofEpochMilli(epochTime).plus(days, ChronoUnit.DAYS).toEpochMilli();
    }
}
