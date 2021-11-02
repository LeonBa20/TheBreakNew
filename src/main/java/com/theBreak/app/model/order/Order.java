package com.theBreak.app.model.order;

import java.util.List;

public class Order {

    private String firstname;
    private String name;
    private String userMailAddress;
    private String streetAndNr;
    private String city;
    private String postcode;
    private String orderedArticle1;
    private String orderedArticle2;
    private String orderedArticle3;
    private String orderedArticle4;
    private String orderedArticle5;
    private String orderedArticle6;
    private String orderedArticle7;
    private String orderedArticle8;
    private List<String> configuredBowl1;
    private List<String> configuredBowl2;
    private List<String> configuredBowl3;
    private double sum;
    private boolean orderPaid;
    private String pickUpDate;
    private String pickupTime;
    private String orderTime;
    private int orderId;

    public Order() {}

    public Order(String firstname, String name, String userMailAddress, String streetAndNr,
                 String city, String postcode, String orderedArticle1, String orderedArticle2,
                 String orderedArticle3, String orderedArticle4, String orderedArticle5, String orderedArticle6,
                 String orderedArticle7, String orderedArticle8, List<String> configuredBowl1,
                 List<String> configuredBowl2, List<String> configuredBowl3, double sum,
                 boolean orderPaid, String pickUpDate, String pickupTime, String orderTime, int orderId){
        super();
        this.firstname = firstname;
        this.name = name;
        this.userMailAddress = userMailAddress;
        this.streetAndNr = streetAndNr;
        this.city = city;
        this.postcode = postcode;
        this.orderedArticle1 = orderedArticle1;
        this.orderedArticle2 = orderedArticle2;
        this.orderedArticle3 = orderedArticle3;
        this.orderedArticle4 = orderedArticle4;
        this.orderedArticle5 = orderedArticle5;
        this.orderedArticle6 = orderedArticle6;
        this.orderedArticle7 = orderedArticle7;
        this.orderedArticle8 = orderedArticle8;
        this.configuredBowl1 = configuredBowl1;
        this.configuredBowl2 = configuredBowl2;
        this.configuredBowl3 = configuredBowl3;
        this.sum = sum;
        this.orderPaid = orderPaid;
        this.pickUpDate = pickUpDate;
        this.pickupTime = pickupTime;
        this.orderTime = orderTime;
        this.orderId = orderId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserMailAddress() {
        return userMailAddress;
    }

    public void setUserMailAddress(String userMailAddress) {
        this.userMailAddress = userMailAddress;
    }

    public String getStreetAndNr() {
        return streetAndNr;
    }

    public void setStreetAndNr(String streetAndNr) {
        this.streetAndNr = streetAndNr;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public boolean isOrderPaid() {
        return orderPaid;
    }

    public void setOrderPaid(boolean orderPaid) {
        this.orderPaid = orderPaid;
    }

    public String getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(String pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public List<String> getConfiguredBowl1() {
        return configuredBowl1;
    }

    public void setConfiguredBowl1(List<String> configuredBowl1) {
        this.configuredBowl1 = configuredBowl1;
    }

    public List<String> getConfiguredBowl2() {
        return configuredBowl2;
    }

    public void setConfiguredBowl2(List<String> configuredBowl2) {
        this.configuredBowl2 = configuredBowl2;
    }

    public List<String> getConfiguredBowl3() {
        return configuredBowl3;
    }

    public void setConfiguredBowl3(List<String> configuredBowl3) {
        this.configuredBowl3 = configuredBowl3;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getOrderedArticle1() {
        return orderedArticle1;
    }

    public void setOrderedArticle1(String orderedArticle1) {
        this.orderedArticle1 = orderedArticle1;
    }

    public String getOrderedArticle2() {
        return orderedArticle2;
    }

    public void setOrderedArticle2(String orderedArticle2) {
        this.orderedArticle2 = orderedArticle2;
    }

    public String getOrderedArticle3() {
        return orderedArticle3;
    }

    public void setOrderedArticle3(String orderedArticle3) {
        this.orderedArticle3 = orderedArticle3;
    }

    public String getOrderedArticle4() {
        return orderedArticle4;
    }

    public void setOrderedArticle4(String orderedArticle4) {
        this.orderedArticle4 = orderedArticle4;
    }

    public String getOrderedArticle5() {
        return orderedArticle5;
    }

    public void setOrderedArticle5(String orderedArticle5) {
        this.orderedArticle5 = orderedArticle5;
    }

    public String getOrderedArticle6() {
        return orderedArticle6;
    }

    public void setOrderedArticle6(String orderedArticle6) {
        this.orderedArticle6 = orderedArticle6;
    }

    public String getOrderedArticle7() {
        return orderedArticle7;
    }

    public void setOrderedArticle7(String orderedArticle7) {
        this.orderedArticle7 = orderedArticle7;
    }

    public String getOrderedArticle8() {
        return orderedArticle8;
    }

    public void setOrderedArticle8(String orderedArticle8) {
        this.orderedArticle8 = orderedArticle8;
    }
}