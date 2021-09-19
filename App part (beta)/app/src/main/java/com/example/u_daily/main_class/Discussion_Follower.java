package com.example.u_daily.main_class;

public class Discussion_Follower {

    public User owner;

    public String info ;

    public Discussion main_Discussion;

    public Discussion_Follower(String reply, User o, Discussion main){
        this.info = reply;
        this.owner = o;
        this.main_Discussion = main;
    }
}
