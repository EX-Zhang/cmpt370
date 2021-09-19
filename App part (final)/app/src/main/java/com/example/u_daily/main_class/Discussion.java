package com.example.u_daily.main_class;

import java.util.ArrayList;

public class Discussion {

    public User owner;

    public int star ;

    public String info ;

    public Subject sub ;

    public ArrayList<String> tags ;

    public ArrayList<Discussion_Follower> followers;

    public Discussion()
    {
        this.owner = null;

        this.star = 0;

        this.info = null;

        this.sub = null;

        this.tags = new ArrayList<>();
    }

    public void setOwner(User u){
        this.owner = u;
    }

    public void addContents(String contents){
        this.info = contents;
    }

    public void changeSubject(Subject sbj){
        this.sub = sbj;
    }

    public void starIncrease(){
        this.star++;
    }

    public void addTag(ArrayList<String> tag_list){
        for(String t: tag_list)
        {
            this.tags.add(t);
        }
    }

    public void addFollowers(Discussion_Follower d){
        this.followers.add(d);
    }

    public String toString(){
        String str="";

        str+=this.owner+": ";

        for(String tag:this.tags){
            str+=tag+", ";
        }

        str+="\n"+this.info+"\nReplies:";


        return str;
    }
}
