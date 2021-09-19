package com.example.u_daily.main_class;

import java.util.ArrayList;

public class Professor extends User{

    public int rate;

    public ArrayList<Subject> subjects;

    public ArrayList<String> comments;

    public Professor(String name)
    {
        super(name);
    }

    public int checkMyRate(){
        return this.rate;
    }

    public ArrayList<String> checkComments()
    {
        return this.comments;
    }

    public void addComments(String c){
        comments.add(c);
    }

    public void addSubject(Subject s){
        subjects.add(s);
    }

    public void deleteSubject(Subject s){
        subjects.remove(s);
    }
}
