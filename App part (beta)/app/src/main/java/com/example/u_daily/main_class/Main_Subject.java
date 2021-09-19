package com.example.u_daily.main_class;

import java.util.ArrayList;

public class Main_Subject {

    public String name;

    public Manager manager;

    public ArrayList<Subject> sub_list;

    public Main_Subject(String name){
        this.name = name;
    }

    public void setManeger(Manager m){
        this.manager = m;
    }

    public void addSubject(Subject s){
        sub_list.add(s);
    }

}
