package com.example.posmvcpersistent.models;

import javax.persistence.*;
import javax.validation.constraints.*;
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

    @ManyToMany
    private List<Category> categories;

    /*@NotNull
    @Min(value = 1)
    private double subTotal;

    @NotNull
    private double shipNHandling;

    @NotNull
    private double invoiceTotal;

    @ManyToMany(mappedBy = "invoices")
    private List<ItemType> itemTypes;

    @ManyToOne
    private Vendor vendor;

    @NotNull
    private Date dateAdded;


    @OneToMany
    @JoinColumn(name = "invoice_id")
    private List<InvItem> invItems = new ArrayList<>();

    public VendorInvoice(){}

    public VendorInvoice(String title, double subTotal, double shipNHandling, double invoiceTotal,
                         List<ItemType> itemTypes, Vendor vendor, Date dateAdded, List<Category> categories,
                         List<InvItem> invItems) {
        this.title = title;
        this.subTotal = subTotal;
        this.shipNHandling = shipNHandling;
        this.invoiceTotal = invoiceTotal;
        this.itemTypes = itemTypes;
        this.vendor = vendor;
        this.dateAdded = dateAdded;
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

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(long subTotal) {
        this.subTotal = subTotal;
    }

    public double getShipNHandling() {
        return shipNHandling;
    }

    public void setShipNHandling(long shipNHandling) {
        this.shipNHandling = shipNHandling;
    }

    public double getInvoiceTotal() {
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
*/
}