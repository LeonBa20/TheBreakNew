package com.theBreak.app.model.order;

import java.util.List;

public class Order {

    private String firstname;
    private String name;
    private String userMailAddress;
    private String streetAndNr;
    private String city;
    private String postcode;
    private List<String> regularOrders;
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
                 String city, String postcode, List<String> regularOrders, List<String> configuredBowl1,
                 List<String> configuredBowl2, List<String> configuredBowl3, double sum,
                 boolean orderPaid, String pickUpDate, String pickupTime, String orderTime, int orderId){
        super();
        this.firstname = firstname;
        this.name = name;
        this.userMailAddress = userMailAddress;
        this.streetAndNr = streetAndNr;
        this.city = city;
        this.postcode = postcode;
        this.regularOrders = regularOrders;
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

    public List<String> getRegularOrders() {
        return regularOrders;
    }

    public void setRegularOrders(List<String> regularOrders) {
        this.regularOrders = regularOrders;
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







}