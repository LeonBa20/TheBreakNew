package com.theBreak.app.test;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {

    public static void main(String[] args){

        ArrayList<Integer> test1 = new ArrayList<Integer>();
        test1.add(11111);
        test1.add(123211);
        test1.add(13311);
        test1.add(14411);
        test1.add(15511);


        for(int i = 0; i < test1.size(); i++){
            System.out.println(test1.get(i));
            i++;
        }

    }
}
