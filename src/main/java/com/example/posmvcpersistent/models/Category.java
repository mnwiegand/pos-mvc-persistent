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
    @Size(min=3, max=15)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Category(){}

    public Category(String name) {
        this.name = name;
    }

    //use the category_id column of the cheese table
    @OneToMany
    @JoinColumn(name = "category_id")
    private List<ShopItem> shopItems = new ArrayList<>();

}
