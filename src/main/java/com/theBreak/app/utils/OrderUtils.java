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
        String ingredientList = "";
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



    public String getSelectedOrderArticle(Order order, int selection){
        if ( selection < order.getRegularOrders().size() ) {
            return order.getRegularOrders().get(selection);
        } else {
            return "";
        }
    }

    public List<String> getOrdersAsList(String order1, String order2, String order3, String order4, String order5, String order6,
                                        String order7, String order8){
        List<String> orders = null;

        if (order1 != ""){
            orders.add(order1);
        }
        if (order2 != ""){
            orders.add(order1);
        }
        if (order3 != ""){
            orders.add(order1);
        }
        if (order4 != ""){
            orders.add(order1);
        }
        if (order5 != ""){
            orders.add(order1);
        }
        if (order6 != ""){
            orders.add(order1);
        }
        if (order7 != ""){
            orders.add(order1);
        }
        if (order8 != ""){
            orders.add(order1);
        }
        return orders;
    }

}
