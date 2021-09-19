package com.example.DataClass;

import java.util.ArrayList;


/*
    A class designed to store the info of a subject, never be used.
    The final product uses different approaches.
 */
public class Subject {

    private String ID;
    private String name;
    private String professor_id;
    private ArrayList<Subject> sub_subjects;
    private ArrayList<Discussion> discussions;
    private ArrayList<Textbook> textbooks;

    public Subject(String ID, String name){
        this.ID=ID;
        this.name=name;

        this.sub_subjects = new ArrayList<Subject>();
    }

    public Subject(String ID, String name,String professor_id){
        this.ID=ID;
        this.name=name;
        this.professor_id=professor_id;
        this.discussions=new ArrayList<Discussion>();
        this.textbooks=new ArrayList<Textbook>();
    }
}
