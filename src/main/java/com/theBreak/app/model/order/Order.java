package com.theBreak.app.model.order;

import java.sql.Timestamp;
import java.util.Date;

public class Order {

    private String firstname;
    private String name;
    private String user_mail_address;
    private String billing_mail_address;
    private String street_and_nr;
    private String city;
    private String postcode;
    private int[] regularOrders;
    private int[] congiguredOrders;
    private double[] prices;
    private double sum;
    private boolean order_paid;
    private String pick_up_date;
    private Date ordertime;

    public Order() {}

    public Order(String firstname, String name, String user_mail_address, String street_and_nr,
                 String city, String postcode, int[] regularOrders, boolean order_paid, String pick_up_date){
        this.firstname = firstname;
        this.name = name;
        this.user_mail_address = user_mail_address;
        this.street_and_nr = street_and_nr;
        this.city = city;
        this.postcode = postcode;
        this.regularOrders = regularOrders;
        this.sum = this.calcSum();
        this.order_paid = order_paid;
        this.pick_up_date = pick_up_date;
        this.ordertime = this.getTimestamp();
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

    public String getUser_mail_address() {
        return user_mail_address;
    }

    public void setUser_mail_address(String user_mail_address) {
        this.user_mail_address = user_mail_address;
    }

    public String getBilling_mail_address() {
        return billing_mail_address;
    }

    public void setBilling_mail_address(String billing_mail_address) {
        this.billing_mail_address = billing_mail_address;
    }

    public String getStreet_and_nr() {
        return street_and_nr;
    }

    public void setStreet_and_nr(String street_and_nr) {
        this.street_and_nr = street_and_nr;
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

    public int[] getRegularOrders() {
        return regularOrders;
    }

    public void setRegularOrders(int[] regularOrders) {
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

    public boolean isOrder_paid() {
        return order_paid;
    }

    public void setOrder_paid(boolean order_paid) {
        this.order_paid = order_paid;
    }

    public String getPick_up_date() {
        return pick_up_date;
    }

    public void setPick_up_date(String pick_up_date) {
        this.pick_up_date = pick_up_date;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public int[] getCongiguredOrders() {
        return congiguredOrders;
    }

    public void setCongiguredOrders(int[] congiguredOrders) {
        this.congiguredOrders = congiguredOrders;
    }

    public double calcSum(){
        double subtotal = 0;
        for(double prices : this.prices ){
            subtotal += prices;
        }
        return subtotal;
    }
     public Date getTimestamp(){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date date = new Date(ts.getTime());
        return date;
     }

     public int getSelectedOrderArticle(int selection){
        if (selection > 9) {
            return 0;
         } else {
            return this.regularOrders[selection];
        }
     }

}