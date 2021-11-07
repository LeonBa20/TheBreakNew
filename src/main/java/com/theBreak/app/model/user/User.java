package com.theBreak.app.model.user;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Nutzerdatenklasse
 */
@Schema(description = "Nutzer")
public class User {
    @Schema(description = "Max")
    private String firstName;
    @Schema(description = "Mustermann")
    private String lastName;
    @Schema(description = "Beispielstraße 5")
    private String streetAndNr;
    @Schema(description = "Beispielstadt")
    private String city;
    @Schema(description = "00000")
    private String postcode;
    @Schema(description = "max@mustermann.de")
    private String userMailAddress;
    @Schema(description = "Passwort123")
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


