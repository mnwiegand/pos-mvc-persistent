package com.example.posmvcpersistent.models;

public enum USState{

    AK ("Alaska"), AL ("Alabama"), AR ("Arkansas"), AZ ("Arizone"), CA ("California"), CO ("Colorado"),
    CT ("Connecticut"), DE ("Delaware"), FL ("Florida"), GA("Georgia"), HI ("Hawaii"), MO ("Missouri"),
    IL ("Illinois");

    private final String name;

    USState(String name){
        this.name = name;
    }

}
