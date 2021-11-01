package com.theBreak.app.dataManager;

import com.theBreak.app.model.order.Order;

import java.util.Collection;

public interface OrderManager {
    void addOrder (Order order);
    Collection<Order> getAllOrders(String userMailAddress);

}
