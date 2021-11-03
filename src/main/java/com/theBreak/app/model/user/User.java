package com.theBreak.app.model.user;

public class User {

    private String firstName;
    private String lastName;
    private String streetAndNr;
    private String city;
    private String postcode;
    private String userMailAddress;
    private String password;

    public User(){};

    public User(String firstName, String lastName, String streetAndNr, String city, String postcode, String userMailAddress, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAndNr = streetAndNr;
        this.city = city;
        this.postcode = postcode;
        this.userMailAddress = userMailAddress;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getUserMailAddress() {
        return userMailAddress;
    }

    public void setUserMailAddress(String userMailAddress) {
        this.userMailAddress = userMailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}


