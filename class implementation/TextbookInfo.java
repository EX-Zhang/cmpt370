package com.example.u_daily;

public class TextbookInfo {

    public String name;

    //under which subject
    public Subject sub;

    public int OwnerId;

    public String contactInfo;

    public TextbookInfo(String name, Subject sub, int Owner, String contact){

        this.name = name;
        this.sub = sub;
        this.OwnerId = Owner;
        this.contactInfo = contact;

    }
}
