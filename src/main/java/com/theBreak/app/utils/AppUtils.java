package com.theBreak.app.utils;

import java.sql.Timestamp;
import java.util.Date;

public class AppUtils {

    public String getTimestamp(){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date date = ts;
        return date.toString();
    }

    //ordernummer hier erstellen?
}
