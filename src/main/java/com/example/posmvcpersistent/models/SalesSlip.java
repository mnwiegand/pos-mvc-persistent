package com.example.posmvcpersistent.models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class SalesSlip {

    @Id
    @GeneratedValue
    private int id;

    private String saleDate(){
        Date dateObject = new Date();
        String dateString = dateObject.toString();
        return dateString;
    }

    //this holds the sales slip items
    //Hibernate populates this automatically
    //based upon relationships in the controller
    @ManyToMany
    private List<InvItem> shopItems;

    @ManyToMany
    private List<Customer> customer;

    @NotNull
    public Integer getId() {
        return id;
    }

    //no Id setter, because that is a generated value

    public List<InvItem> getShopItems() {
        return shopItems;
    }

    public void addItem(InvItem item){
        this.shopItems.add(item);
    }

    public SalesSlip(){}

    public SalesSlip(Integer id) {
        this.id = id;
    }

}
