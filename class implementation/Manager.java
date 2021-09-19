package com.example.u_daily;

import java.util.ArrayList;

public class Manager extends User{

    public Main_Subject subFor;

    public ArrayList<Discussion> report_list;

    public Manager(String name){
        super(name);
    }

    public void addReport(Discussion d){
        report_list.add(d);
    }

    public ArrayList<Discussion> handdleReport(){
        return report_list;
    }

    public void deleteDiscussion(Discussion d){
        //get the subject
        Subject s = d.sub;
        s.deleteDiscussion(d);
        this.report_list.remove(d);
    }

    public void deleteReport(Discussion d){
        this.report_list.remove(d);
    }

    public void setMain(Main_Subject m){
        this.subFor = m;
    }
}
