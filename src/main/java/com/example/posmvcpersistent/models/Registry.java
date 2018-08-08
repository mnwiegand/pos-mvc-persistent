package com.example.posmvcpersistent.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Registry {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    //this holds the menu items
    //Hibernate populates this automatically
    //based upon relationships in the controller
    @ManyToMany
    private List<ShopItem> shopItems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public Integer getId() {
        return id;
    }

    //no Id setter, because that is a generated value

    public List<ShopItem> getShopItems() {
        return shopItems;
    }

    public void addItem(ShopItem item){
        this.shopItems.add(item);
    }

    public Registry(){}

    public Registry(String name) {
        this.name = name;
    }
}
