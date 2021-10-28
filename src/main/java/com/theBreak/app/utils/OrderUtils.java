package com.theBreak.app.utils;

import com.theBreak.app.model.order.Order;

import java.sql.Timestamp;
import java.util.Date;

public class OrderUtils {

    public String getTimestamp(){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date date = ts;
        return date.toString();
    }

    public String configuredBowlsToString(Order order, int configSelect){
        String ingredientList = "";
        if (configSelect == 1) {
            for (int i = 0; i < order.getConfiguredBowl1().size(); i++ ) {
                if (i < order.getConfiguredBowl1().size() - 1) {
                    ingredientList += order.getConfiguredBowl1().get(i) + ", ";
                } else {
                    ingredientList += order.getConfiguredBowl1().get(i);
                }
            }
        }else if (configSelect == 2) {
            for (int i = 0; i < order.getConfiguredBowl2().size(); i++ ) {
                if (i < order.getConfiguredBowl2().size() - 1) {
                    ingredientList += order.getConfiguredBowl2().get(i) + ", ";
                } else {
                    ingredientList += order.getConfiguredBowl2().get(i);
                }
            }
        }else if (configSelect == 3) {
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

    public String getSelectedOrderArticle(Order order, int selection){
        if ( selection < order.getRegularOrders().size() ) {
            return order.getRegularOrders().get(selection);
        } else {
            return "";
        }
    }
    //ordernummer hier erstellen?
}
