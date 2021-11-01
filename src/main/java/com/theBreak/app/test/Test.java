package com.theBreak.app.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test  {

    public static void main(String[] args)
    {
       /* List<String> list = Arrays.asList("NYC", "New Delhi");

        String[] array = list.toArray(new String[0]);
        System.out.println(Arrays.toString(array));

        ArrayList<String> test = new ArrayList<>();

        test.add("hallo");
        test.add("test");

        System.out.println(String.join(", ", test));*/

        String commaSeparated = "[Hallo, Hallo, Hallo]";
        //using String split function
        if (commaSeparated != null) {
        List<String> words = Arrays.asList(commaSeparated.split(","));

        System.out.println(words.toString());
    }
    }
}
