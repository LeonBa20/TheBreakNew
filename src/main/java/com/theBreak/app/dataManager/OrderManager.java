package com.theBreak.app.dataManager;

import com.theBreak.app.model.order.Order;

import java.util.Collection;

public interface OrderManager {
    void addOrder (Order order);
    void editCollectTime (Order order);
    void editCollectTimeWithBot (Order order);
    Collection<Order> getAllUnpaidOrders(String userMailAddress);
    Collection<Order> getAllPaidOrders(String userMailAddress);
}
