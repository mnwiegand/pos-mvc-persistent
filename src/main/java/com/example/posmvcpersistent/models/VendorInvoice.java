package com.example.posmvcpersistent.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class VendorInvoice {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(max=45,  message = "Description must be between 3 and 45 characters")
    private String title;

    @NotNull
    private long subTotal;

    @NotNull
    private long shipNHandling;

    @NotNull
    private long invoiceTotal;

    @ManyToMany(mappedBy = "invoices")
    private List<ItemType> itemTypes;

    @ManyToOne
    private Vendor vendor;

    @NotNull
    private Date dateAdded;

    @ManyToMany
    private List<Category> categories;

    @OneToMany
    @JoinColumn(name = "invoice_id")
    private List<InvItem> invItems = new ArrayList<>();

    public VendorInvoice(String title, long subTotal, long shipNHandling, long invoiceTotal, List itemTypes,
                         Vendor vendor, Date dateAdded, List categories, List invItems) {
        this.title = title;
        this.subTotal = subTotal;
        this.shipNHandling = shipNHandling;
        this.invoiceTotal = invoiceTotal;
        this.itemTypes = itemTypes;
        this.categories = categories;
        this.invItems = invItems;
    }

    public VendorInvoice(List<ItemType> itemTypes) {
        this.itemTypes = itemTypes;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(long subTotal) {
        this.subTotal = subTotal;
    }

    public long getShipNHandling() {
        return shipNHandling;
    }

    public void setShipNHandling(long shipNHandling) {
        this.shipNHandling = shipNHandling;
    }

    public long getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(long invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public List<ItemType> getItemTypes() {
        return itemTypes;
    }

    public void setItemTypes(List<ItemType> itemTypes) {
        this.itemTypes = itemTypes;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<InvItem> getInvItems() {
        return invItems;
    }

    public void setInvItems(List<InvItem> invItems) {
        this.invItems = invItems;
    }
}