package com.theBreak.app.dataManagerImpl;

import com.theBreak.app.dataManager.OrderManager;
import com.theBreak.app.model.order.Order;
import com.theBreak.app.utils.OrderUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

public class PropertyFileOrderManagerImpl implements OrderManager {

    String fileName;
    OrderUtils oUtils = new OrderUtils();


    static PropertyFileOrderManagerImpl PropertyFileOrderManager = null;

    private PropertyFileOrderManagerImpl(String fileName) {
        this.fileName = fileName;
    }

    static public PropertyFileOrderManagerImpl getPropertyFileOrderManagerImpl(String fileName) {
        if (PropertyFileOrderManager == null)
            PropertyFileOrderManager = new PropertyFileOrderManagerImpl(fileName);
        return PropertyFileOrderManager;
    }

    @Override
    public void addOrder(Order order) {

        // I am ignoring the student and storing all tasks to the file

        Properties properties = new Properties();
        final AtomicLong counter = new AtomicLong();
        counter.set(0);

        properties.setProperty("Order." + counter.incrementAndGet() + ".firstname", order.getFirstname());
        properties.setProperty("Order." + counter.get() + ".name", order.getName());
        properties.setProperty("Order." + counter.get() + ".usermail-address", "" + order.getUserMailAddress());
        properties.setProperty("Order." + counter.get() + ".street and housenumber", "" + order.getStreetAndNr());
        properties.setProperty("Order." + counter.get() + ".city", "" + order.getCity());
        properties.setProperty("Order." + counter.get() + ".postcode", "" + order.getPostcode());
        properties.setProperty("Order." + counter.get() + ".orders", "" + oUtils.getSelectedOrderArticle(order,0));
        properties.setProperty("Order." + counter.get() + ".orders2", "" + oUtils.getSelectedOrderArticle(order,1));
        properties.setProperty("Order." + counter.get() + ".orders3", "" + oUtils.getSelectedOrderArticle(order,2));
        properties.setProperty("Order." + counter.get() + ".configuredOrders1", "" + oUtils.configuredBowlsToString(order,1));
        properties.setProperty("Order." + counter.get() + ".sum", "" + order.getSum());
        properties.setProperty("Order." + counter.get() + ".order paid", "" + order.isOrderPaid());
        properties.setProperty("Order." + counter.get() + ".pick-up date", "" + order.getPickUpDate());
        properties.setProperty("Order." + counter.get() + ".ordertime", "" + oUtils.getTimestamp() );




        try {
            properties.store(new FileOutputStream(fileName), null);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
