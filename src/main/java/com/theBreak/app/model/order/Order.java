package com.theBreak.app.model.order;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private String firstname;
    private String name;
    private String userMailAddress;
    private String streetAndNr;
    private String city;
    private String postcode;
    private ArrayList<String> regularOrders;
    private ArrayList<String> configuredBowl1;
    private ArrayList<String> configuredBowl2;
    private ArrayList<String> configuredBowl3;
    private double[] prices;
    private double sum;
    private boolean orderPaid;
    private String pickUpDate;
    private String pickupTime;
    private String ordertime;
    private int orderId;

    public Order() {}

    public Order(String firstname, String name, String user_mail_address, String street_and_nr,
                 String city, String postcode, ArrayList<String> regularOrders, ArrayList<String> configuredBowl1,
                 boolean order_paid, String pick_up_date, String pickupTime){
        super();
        this.firstname = firstname;
        this.name = "hallo";
        this.userMailAddress = user_mail_address;
        this.streetAndNr = street_and_nr;
        this.city = city;
        this.postcode = postcode;
        this.regularOrders = regularOrders;
        this.configuredBowl1 = configuredBowl1;
        this.orderPaid = order_paid;
        this.pickUpDate = pick_up_date;
        this.pickupTime = pickupTime;
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

    public void setRegularOrders(ArrayList<String> regularOrders) {
        this.regularOrders = regularOrders;
    }

    public double[] getPrices() {
        return prices;
    }

    public void setPrices(double[] prices) {
        this.prices = prices;
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

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public List<String> getConfiguredBowl1() {
        return configuredBowl1;
    }

    public void setConfiguredBowl1(ArrayList<String> configuredBowl1) {
        this.configuredBowl1 = configuredBowl1;
    }

    public ArrayList<String> getConfiguredBowl2() {
        return configuredBowl2;
    }

    public void setConfiguredBowl2(ArrayList<String> configuredBowl2) {
        this.configuredBowl2 = configuredBowl2;
    }

    public ArrayList<String> getConfiguredBowl3() {
        return configuredBowl3;
    }

    public void setConfiguredBowl3(ArrayList<String> configuredBowl3) {
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

    public double calcSum(){
        double subtotal = 0;
        for(double prices : this.prices ){
            subtotal += prices;
        }
        return subtotal;
    }





}