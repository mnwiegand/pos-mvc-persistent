package com.example.posmvcpersistent.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Vendor {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=4)
    @Column(unique=true)
    private String vendorCode;

    @NotNull
    @Size(min=3, max=45)
    private String studioName;

    @NotNull
    @Size(min=3, max=45)
    private String firstName;

    @NotNull
    @Size(min=3, max=45)
    private String lastName;

    /*
    @NotNull
    private Integer streetNumber;

    @NotNull
    @Size(min=3, max = 45)
    private String street;

    @NotNull
    @Size(min=3, max = 45)
    private String city;

    @NotNull
    private USState state;

    @NotNull
    private Integer zipCode;
    */

    @OneToOne
    private Address address;

    private String website;

    private String instagramHandle;

    private String fbHandle;

    private String twitterHandle;

    @OneToMany
    @JoinColumn(name = "vendor_id")
    private List<InvItem> shopItems = new ArrayList<>();

    @ManyToMany
    private List<Category> categories;

    public Vendor(){}

    public Vendor(String vendorCode, String studioName, String firstName, String lastName,
                  /*Integer streetNumber, String street, USState state, Integer zipCode,*/ String website,
                  String instagramHandle, String fbHandle, String twitterHandle) {
        this.vendorCode = vendorCode;
        this.studioName = studioName;
        this.firstName = firstName;
        this.lastName = lastName;
        /*this.streetNumber = streetNumber;
        this.street = street;
        this.state = state;
        this.zipCode = zipCode;
        this.website = website;*/
        this.instagramHandle = instagramHandle;
        this.fbHandle= fbHandle;
        this.twitterHandle = twitterHandle;
    }

    public void addCategory(Category category){
        categories.add(category);
    }


    public int getId() {
        return id;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    /*
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

    public USState getState() {
        return state;
    }

    public void setState(USState state) {
        this.state = state;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }
*/
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

    public List<InvItem> getShopItems() {
        return shopItems;
    }

    public void setShopItems(List<InvItem> shopItems) {
        this.shopItems = shopItems;
    }

    public void addShopItem(InvItem item) {shopItems.add(item);}


    public List<Category> getCategories() {
        return categories;
    }
}
