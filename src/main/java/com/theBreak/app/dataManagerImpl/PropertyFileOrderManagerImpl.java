package com.theBreak.app.dataManagerImpl;

import com.theBreak.app.dataManager.OrderManager;
import com.theBreak.app.model.order.Order;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

public class PropertyFileOrderManagerImpl implements OrderManager {

    String fileName;

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
        properties.setProperty("Order." + counter.get() + ".usermail-address", "" + order.getUser_mail_address());
        properties.setProperty("Order." + counter.get() + ".street and housenumber", "" + order.getStreet_and_nr());
        properties.setProperty("Order." + counter.get() + ".city", "" + order.getCity());
        properties.setProperty("Order." + counter.get() + ".postcode", "" + order.getPostcode());
        for(int i = 0; i <= order.getOrders().length; i++){
            properties.setProperty("Order." + counter.get() + ".orders", "" + order.getSelectedOrderArticle(i));
            i++;
        }
        properties.setProperty("Order." + counter.get() + ".sum", "" + order.getSum());
        properties.setProperty("Order." + counter.get() + ".order paid", "" + order.isOrder_paid());
        properties.setProperty("Order." + counter.get() + ".pick-up date", "" + order.getPick_up_date());
        properties.setProperty("Order." + counter.get() + ".ordertime", "" + order.getOrdertime());


        try {
            properties.store(new FileOutputStream(fileName), null);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
