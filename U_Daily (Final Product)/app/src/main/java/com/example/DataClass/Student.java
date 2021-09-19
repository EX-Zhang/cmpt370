package com.example.DataClass;

import java.util.ArrayList;

/*
    A class that is never be used.
 */
public class Student extends User {

    private String nickname;
    private ArrayList<String> subject_ids;
    private ArrayList<Sell> sells;

    public Student(String username, String first_name, String last_name, String email, String Univrsity, String Identity,String nickname) {
        super(username, first_name, last_name, email, Univrsity, Identity);

        this.nickname=nickname;
        this.subject_ids = new ArrayList<String>();
        this.sells = new ArrayList<Sell>();
    }

    public void addSells(Sell sell){
        sells.add(sell);
    }

}
