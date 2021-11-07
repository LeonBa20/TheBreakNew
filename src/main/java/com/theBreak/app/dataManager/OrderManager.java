package com.theBreak.app.dataManager;

import com.theBreak.app.model.order.Order;

import java.util.Collection;

public interface OrderManager {
    /**
     * Fügt der Datenbank eine neue Bestellung hinzu.
     */
    void addOrder (Order order);

    /**
     * Löscht eine Bestellung des Nutzers.
     */
    void deleteOrder (int orderId);

    /**
     * Setzt den Status der Bestellung auf abgeholt.
     */
    void setOrderIsPickedUp(int orderId);

    /**
     * Ändert die Abholzeit der Bestellung.
     */
    void editCollectTime (Order order);

    /**
     * Ändert die Abholzeit der Bestellung, jedoch wird hierbei nicht über die Bestellnummer nach der
     * passenden Bestellung gesucht, sondern über die E-Mail in Verbindung mit dem Abholdatum.
     */
    void editCollectTimeWithBot (Order order);

    /**
     * Zeigt alle Bestellungen des Nutzers.
     */
    Collection<Order> getAllOrdersFromUser(String userMailAddress);

    /**
     * Zeigt alle noch nicht abgeholten (abgeschlossenen) Bestellungen. Grund: Nur bei diesen kann die Abholzeit
     * noch angepasst werden.
     */
    Collection<Order> getAllNotPickUpOrdersFromUser(String userMailAddress);


}
