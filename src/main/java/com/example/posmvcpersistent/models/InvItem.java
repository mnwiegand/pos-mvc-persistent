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
    private String itemCode;

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
    private Date dateChanged;

    @NotNull
    @ManyToOne
    private Category category;

    @ManyToOne
    private VendorInvoice vendorInvoice;

    @ManyToMany(mappedBy = "shopItems")
    private List<Registry> registries;

    @ManyToMany(mappedBy = "shopItems")
    private List<SalesSlip> salesSlips;

    public InvItem(String itemCode, String description, int quantityInStock, long wholesaleCost, long retailCost, ItemType itemType,
                   Vendor vendor, String vendorPN, String vendorDescription, Date dateChanged, Category category,
                   VendorInvoice vendorInvoice) {
        this.itemCode = itemCode;
        this.description = description;
        this.quantityInStock = quantityInStock;
        this.wholesaleCost = wholesaleCost;
        this.retailCost = retailCost;
        this.itemType = itemType;
        this.vendor = vendor;
        this.vendorPN = vendorPN;
        this.vendorDescription = vendorDescription;
        this.dateChanged = dateChanged;
        this.category = category;
        this.vendorInvoice = vendorInvoice;
    }

    public InvItem() { }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public long getWholesaleCost() {
        return wholesaleCost;
    }

    public void setWholesaleCost(long wholesaleCost) {
        this.wholesaleCost = wholesaleCost;
    }

    public long getRetailCost() {
        return retailCost;
    }

    public void setRetailCost(long retailCost) {
        this.retailCost = retailCost;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getVendorPN() {
        return vendorPN;
    }

    public void setVendorPN(String vendorPN) {
        this.vendorPN = vendorPN;
    }

    public String getVendorDescription() {
        return vendorDescription;
    }

    public void setVendorDescription(String vendorDescription) {
        this.vendorDescription = vendorDescription;
    }

    public Date getDateChanged() {
        return dateChanged;
    }

    public void setDateChanged(Date dateChanged) {
        this.dateChanged = dateChanged;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public VendorInvoice getVendorInvoice() {
        return vendorInvoice;
    }

    public void setVendorInvoice(VendorInvoice vendorInvoice) {
        this.vendorInvoice = vendorInvoice;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
}