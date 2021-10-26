package com.theBreak.app.model.order;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Order {

    private String firstname;
    private String name;
    private String userMailAddress;
    private String billingMailAddress;
    private String streetAndNr;
    private String city;
    private String postcode;
    private List<Integer> regularOrders = null;
    private List<Integer> congiguredOrders = null;
    private double[] prices;
    private double sum;
    private boolean orderPaid;
    private String pickUpDate;
    private String ordertime;

    public Order() {}

    public Order(String firstname, String name, String user_mail_address, String street_and_nr,
                 String city, String postcode, List<Integer> regularOrders, boolean order_paid, String pick_up_date){
        super();
        this.firstname = firstname;
        this.name = name;
        this.userMailAddress = user_mail_address;
        this.streetAndNr = street_and_nr;
        this.city = city;
        this.postcode = postcode;
        this.regularOrders = regularOrders;
        this.sum = this.calcSum();
        this.orderPaid = order_paid;
        this.pickUpDate = pick_up_date;
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

    public String getBillingMailAddress() {
        return billingMailAddress;
    }

    public void setBillingMailAddress(String billingMailAddress) {
        this.billingMailAddress = billingMailAddress;
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

    public List<Integer> getRegularOrders() {
        return regularOrders;
    }

    public void setRegularOrders(List<Integer> regularOrders) {
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

    public List<Integer> getCongiguredOrders() {
        return congiguredOrders;
    }

    public void setCongiguredOrders(List<Integer> congiguredOrders) {
        this.congiguredOrders = congiguredOrders;
    }

    public double calcSum(){
        double subtotal = 0;
        for(double prices : this.prices ){
            subtotal += prices;
        }
        return subtotal;
    }
     public void getTimestamp(){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date date = ts;
        this.ordertime = date.toString();
     }

     public int getSelectedOrderArticle(int selection){
        if (selection > 9) {
            return 0;
         } else {
            return this.regularOrders.get(selection);
        }
     }

}