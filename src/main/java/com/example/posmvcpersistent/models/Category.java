package com.example.posmvcpersistent.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=45)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<VendorInvoice> vendorInvoices;

    @OneToMany
    @JoinColumn(name = "category_id")
    private List<InvItem> invItems = new ArrayList<>();

    @ManyToMany(mappedBy = "categories")
    private List<Vendor> vendors;

    @ManyToMany(mappedBy = "categories")
    private List<Batch> batches;

    @ManyToMany
    private List<ItemType> itemTypes;

    public Category(){}

    public Category(String name) {
        this.name = name;
    }

    public void addShopItem(InvItem item) {invItems.add(item);}

    public void addVendor(Vendor vendor) {vendors.add(vendor);}

    public String getName() {   return name;    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public List<InvItem> getShopItems() {
        return invItems;
    }

    public List<Vendor> getVendors() {
        return vendors;
    }

    public List<ItemType> getItemTypes(){return itemTypes;}

    public List<Batch> getBatches(){return batches;}
}
