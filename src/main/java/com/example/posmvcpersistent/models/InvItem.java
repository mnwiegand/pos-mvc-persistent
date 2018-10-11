package com.example.posmvcpersistent.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
public class InvItem {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=45,  message = "Description must be between 3 and 45 characters")
    private String invObjectType;

    @NotNull
    @Size(min=1, max=200, message = "Description must not be empty")
    private String description;

    @NotNull
    private int quantityInStock;

    @NotNull
    private long wholesaleCost;

    @NotNull
    private long retailCost;

    @ManyToOne
    @NotNull
    private ItemType itemType;
    //Hibernate will create the column item_type_id (based in the field name)

    @ManyToOne
    @NotNull
    private Vendor vendor;

    @NotNull
    @Size(max=45,  message = "Description must be fewer than 45 characters")
    private String vendorPN;

    @NotNull
    @Size(max=200,  message = "Description must be fewer than 200 characters")
    private String vendorDescription;

    @NotNull
    private Date dateAdded;

    @NotNull
    @ManyToOne
    private Category category;

    @ManyToOne
    private VendorInvoice vendorInvoice;

    @ManyToMany(mappedBy = "shopItems")
    private List<Registry> registries;

    @ManyToMany(mappedBy = "shopItems")
    private List<SalesSlip> salesSlips;

    public InvItem(String invObjectType, String description) {
        this.invObjectType = invObjectType;
        this.description = description;
    }

    public InvItem() { }

    public int getId() {
        return id;
    }

    public String getName() {
        return invObjectType;
    }

    public void setName(String name) {
        this.invObjectType = invObjectType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getShopItemCat() {
        return category;
    }

    public void setShopItemCat(Category shopItemCat) {
        this.category = shopItemCat;
    }
}