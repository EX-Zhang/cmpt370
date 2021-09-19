package com.example.u_daily;

import java.util.ArrayList;

public abstract class User {

    public String name;

    // store all discussion that I interested in
    public ArrayList<Discussion> myFollows;

    public User(String name)
    {
        this.name = name;
        myFollows = new ArrayList<Discussion>();
    }

    public void anserDiscussion (Discussion d, String reply, User u){
        Discussion_Follower f = new Discussion_Follower(reply, u, d);
        d.addFollowers(f);
    }

    public void reportDiscussion (Discussion d){

        //get the manager that is responsible for the corresponding subject
        Manager m = d.sub.main.manager;
        m.addReport(d);
    }

    public Discussion postDiscussion(String contents,Subject sub, User u, ArrayList<String> tag){

        Discussion newD = new Discussion();
        newD.setOwner(u);
        newD.changeSubject(sub);
        newD.addTag(tag);
        newD.addContents(contents);
        return newD;
    }

    /**
     * view all posting discussion information based on the subject
     * @param s subject
     * @return a list of discussion
     */
    public ArrayList<Discussion> viewDiscussion(Subject s){
        return s.allDiscussion;
    }

    public void followDiscussion(Discussion d){
        this.myFollows.add(d);
    }

    public void rateDiscussion(Discussion d){
        d.starIncrease();
    }
}
