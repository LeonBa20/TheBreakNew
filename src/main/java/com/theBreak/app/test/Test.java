package com.theBreak.app.test;

import java.sql.Timestamp;
import java.util.Date;

public class Test {

    public static void main(String[] args){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date date = ts;
        System.out.println(date);

    }
}
