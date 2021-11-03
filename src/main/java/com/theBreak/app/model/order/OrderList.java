package com.theBreak.app.model.order;

import com.theBreak.app.dataManager.OrderManager;
import com.theBreak.app.dataManagerImpl.PostgresOrderManagerImpl;

import java.util.Collection;

public class OrderList {

    private String userMailAddress;
    private Collection<Order> orders;
    OrderManager orderManager = PostgresOrderManagerImpl.getPostgresOrderManagerImpl();

    public OrderList() {
    }

    public OrderList(String userMailAddress) {
        this.userMailAddress = userMailAddress;
    }

    public String getUserMailAddress() {
        return this.userMailAddress;
    }

    public void setUserMailAddress(String userMailAddress) {
        this.userMailAddress = userMailAddress;
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    public void setUnpaidOrders() {
        orders = orderManager.getAllUnpaidOrders(this.userMailAddress);
    }

    public void setPaidOrders() {
        orders = orderManager.getAllPaidOrders(this.userMailAddress);
    }
}
