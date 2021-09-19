package com.example.u_daily.main_class;

import java.util.ArrayList;

public class Subject {

    public String name;

    public Main_Subject main;

    public ArrayList<TextbookInfo> allBooks;

    public ArrayList<Discussion> allDiscussion;

    public Subject(String name, Main_Subject m){
        this.name = name;
        this.main = m;
    }

    protected void addTextBookInfo(TextbookInfo t){
        allBooks.add(t);
    }

    protected void addDiscussion(Discussion d){
        allDiscussion.add(d);
    }

    protected void deleteDiscussion(Discussion d){
        allDiscussion.remove(d);
    }
}
