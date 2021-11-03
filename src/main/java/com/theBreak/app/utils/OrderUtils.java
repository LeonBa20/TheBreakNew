package com.theBreak.app.utils;

import com.theBreak.app.model.order.Order;

import java.sql.Timestamp;
import java.util.*;

public class OrderUtils {

    public String getTimestamp(){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date date = ts;
        return date.toString();
    }

    public String configuredBowlsToString(Order order, int configSelect){
        String ingredientList = null;
        if (configSelect == 1 && order.getConfiguredBowl1() != null) {
            for (int i = 0; i < order.getConfiguredBowl1().size(); i++ ) {
                if (i < order.getConfiguredBowl1().size() - 1) {
                    ingredientList += order.getConfiguredBowl1().get(i) + ", ";
                } else {
                    ingredientList += order.getConfiguredBowl1().get(i);
                }
            }
        }else if (configSelect == 2 && order.getConfiguredBowl2() != null) {
            for (int i = 0; i < order.getConfiguredBowl2().size(); i++ ) {
                if (i < order.getConfiguredBowl2().size() - 1) {
                    ingredientList += order.getConfiguredBowl2().get(i) + ", ";
                } else {
                    ingredientList += order.getConfiguredBowl2().get(i);
                }
            }
        }else if (configSelect == 3 && order.getConfiguredBowl3() != null) {
            for (int i = 0; i < order.getConfiguredBowl3().size(); i++ ) {
                if (i < order.getConfiguredBowl3().size() - 1) {
                    ingredientList += order.getConfiguredBowl3().get(i) + ", ";
                } else {
                    ingredientList += order.getConfiguredBowl3().get(i);
                }
            }
        }
        return ingredientList;
    }

    public List<String> configuredBowlsToList(String bowl) {
        List<String> configuredBowl = null;
        if (bowl != null) {
           configuredBowl = Arrays.asList(bowl.split(","));
        }
        return configuredBowl;
    }
}
