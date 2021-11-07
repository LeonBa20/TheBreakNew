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

    /**
     * Holt sich alle Bestellungen des Nutzers.
     */
    public void setOrdersToAllFromUser() { orders = orderManager.getAllOrdersFromUser(this.userMailAddress); }

    /**
     * Holt sich nur die Bestellungen des Nutzers, die noch nicht abgeholt (abgeschlossen) sind.
     */
    public void setOrdersToAllNotPickedUpFromUser() { orders = orderManager.getAllNotPickUpOrdersFromUser(this.userMailAddress); }

}
