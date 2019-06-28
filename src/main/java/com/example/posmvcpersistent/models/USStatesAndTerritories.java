package com.example.posmvcpersistent.models;

public enum USStatesAndTerritories {

    AK ("Alaska"), AL ("Alabama"), AR ("Arkansas"), AZ ("Arizona"), CA ("California"), CO ("Colorado"),
    CT ("Connecticut"), DE ("Delaware"), FL ("Florida"), GA("Georgia"), HI ("Hawaii"),
    IA ("Iowa"), ID ("Idaho"), IL ("Illinois"), IN ("Indiana"), KS ("Kansas"), KY ("Kentucky"), LA ("Louisiana"),
    MA ("Massachusetts"), MD ("Maryland"), ME ("Maine"), MI ("Michigan"), MN ("Minnesota"), MO ("Missouri"),
    MS ("Mississippi"), MT ("Montana"), NC ("North Carolina"), ND ("North Dakota"), NE ("Nebraska"),
    NH ("New Hampshire"), NJ ("New Jersey"), NM ("New Mexico"), NV ("Nevada"), NY ("New York"), OH ("Ohio"),
    OK ("Oklahoma"), OR ("Oregon"), PA ("Pennsylvania"), RI ("Rhode Island"), SC ("South Carolina"),
    SD ("South Dakota"), TN ("Tennesee"), TX ("Texas"), UT ("Utah"), VA ("Virginia"), VT ("Vermont"), WA ("Washington"),
    WI ("Wisconsin"), WV ("West Virginia"), WY ("Wyoming"), DC ("District of Columbia"), AS ("American Samoa"),
    CM ("Northern Mariana Isalands"), GU ("Guam"), PR ("Puerto Rico"), VI ("U.S. Virgin Islands");
    private final String name;

    USStatesAndTerritories(String name){
        this.name = name;
    }

}
