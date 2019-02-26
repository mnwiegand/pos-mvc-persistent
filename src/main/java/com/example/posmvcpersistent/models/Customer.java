package com.example.posmvcpersistent.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String lastName;

    @NotNull
    @Size(min=3, max=15)
    private String firstName;

    private Integer streetNumber;

    private String street;

    @Size(max=2)
    private String state;

    @Size(min=5, max=10)
    private Integer zipCode;

    @ManyToMany(mappedBy = "shopItems")
    private List<Registry> registries;

    @ManyToMany(mappedBy = "shopItems")
    private List<SalesSlip> salesSlips;

    public int getId() {
        return id;
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

}