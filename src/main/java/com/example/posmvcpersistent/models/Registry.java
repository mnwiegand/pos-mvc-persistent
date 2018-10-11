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
    @Size(min=3, max=35)
    private String name;

    @ManyToMany
    private List<InvItem> shopItems;

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

    public List<InvItem> getShopItems() {
        return shopItems;
    }

    public void addItem(InvItem item){
        this.shopItems.add(item);
    }

    public Registry(){}

    public Registry(String name) {
        this.name = name;
    }
}
