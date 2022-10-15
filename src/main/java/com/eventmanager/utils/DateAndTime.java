package com.eventmanager.utils;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateAndTime {
    public static String getDateAndTime(){
      return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault()).format(new Date().toInstant());
    }
}
