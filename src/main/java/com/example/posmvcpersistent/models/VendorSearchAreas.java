package com.example.posmvcpersistent.models;

public enum VendorSearchAreas {

    searchByAll ("all search areas"),  searchByStudio ("studio name"), searchByPerson ("person's name"),
    searchByAddress ("address");

    private final String name;

    VendorSearchAreas(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
