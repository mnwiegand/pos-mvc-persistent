package com.example.posmvcpersistent.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Address {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private Integer streetNumber;

    @NotNull
    @Size(min=3, max = 45)
    private String street;

    @NotNull
    @Size(min=3, max = 45)
    private String city;

    @NotNull
    private USStatesAndTerritories state;

    @NotNull
    private Integer zipCode;

    /*private String website;

    private String instagramHandle;

    private String fbHandle;

    private String twitterHandle;*/

    @OneToOne(mappedBy = "address")
    private Vendor vendor;


    public Address(){}

    public Address(Integer streetNumber, String street, USStatesAndTerritories state, Integer zipCode
    /* , String website, String instagramHandle, String fbHandle, String twitterHandle*/) {
        this.streetNumber = streetNumber;
        this.street = street;
        this.state = state;
        this.zipCode = zipCode;
        /*
        this.website = website;
        this.instagramHandle = instagramHandle;
        this.fbHandle= fbHandle;
        this.twitterHandle = twitterHandle;
        */
    }

    public int getId() {
        return id;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public USStatesAndTerritories getState() {
        return state;
    }

    public void setState(USStatesAndTerritories state) {
        this.state = state;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    /*
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getInstagramHandle() {
        return instagramHandle;
    }

    public void setInstagramHandle(String instagramHandle) {
        this.instagramHandle = instagramHandle;
    }

    public String getFbHandle() {
        return fbHandle;
    }

    public void setFbHandle(String fbHandle) {
        this.fbHandle = fbHandle;
    }

    public String getTwitterHandle() {
        return twitterHandle;
    }

    public void setTwitterHandle(String twitterHandle) {
        this.twitterHandle = twitterHandle;
    }
*/



}
