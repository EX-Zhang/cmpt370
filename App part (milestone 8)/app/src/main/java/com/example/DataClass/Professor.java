package com.example.DataClass;

import java.util.ArrayList;

public class Professor {

    public String ID;
    public String first_name;
    public String last_name;
    public String university;
    public String username;
    public ArrayList<String> subject_id;
    public ArrayList<Comment> comments;

    public Professor(String ID,String first_name,String last_name,String university, String username){

        this.ID=ID;
        this.first_name=first_name;
        this.last_name=last_name;
        this.university=university;
        this.username=username;

        this.subject_id=new ArrayList<String>();
        this.comments=new ArrayList<Comment>();

    }

    public void addSubjects(String subject_id){
        this.subject_id.add(subject_id);
    }

    public void addComments(Comment comment){
        this.comments.add(comment);
    }

    public String toString() {
        String str="";
        str+="Professor name: "+this.first_name+" "+this.last_name+"\n";
        str+="University: "+this.university+"\n";
        if(!this.subject_id.isEmpty()) str+="Subjects: ";
        for (String s:this.subject_id){
            str+=s+" ";
        }
        if(!this.comments.isEmpty()) str+="Comments:\n";
        for (Comment comment:this.comments){
            str+=comment+"\n";
        }
        return str;
    }
}
