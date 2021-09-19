package com.example.u_daily;

import java.util.ArrayList;

public class Student extends User {

    public int ID;

    public ArrayList<Subject> subjects;

    public ArrayList<TextbookInfo> textInfos;

    public Student(String name)
    {
        super(name);
    }

    public void editMyInfo(String newName){
        this.name = newName;
    }

    /**
     * add subject to my list, so I can view it quickly
     * @param sub subject that users wanna view quickly
     */
    public void addSubject(Subject sub){
        subjects.add(sub);
    }

    /**
     * delete subject from my list
     * @param sub subject that users wanna delete from his list
     */
    public void deleteSubject(Subject sub){
        subjects.remove(sub);
    }

    /**
     * showing all subject I interested in
     * @return a string representation of the subject
     */
    public String showSubject(){
        String allSubjects = "";

        for(Subject i: this.subjects)
        {
            allSubjects += i.name;
            allSubjects += '\n';
        }

        return allSubjects;
    }

    /**
     * post the information of the textbook that users wanna sell
     * @param title the title of the post
     * @param sub post to which subject
     * @param contact the contact info of the user
     * @return a new Textbook information form
     */
    public TextbookInfo postTextbookInfo(String title, Subject sub, String contact){

        TextbookInfo tex_info = new TextbookInfo(title, sub, this.ID, contact);

        //add to my selling list
        this.textInfos.add(tex_info);

        //add the selling info under the corresponding subject
        sub.addTextBookInfo(tex_info);

        return tex_info;
    }

    /**
     * delete the textbook information users posted
     * @param t textbook information
     */
    public void deleteTextInfo(TextbookInfo t){
        this.textInfos.remove(t);
        t.sub.allBooks.remove(t);
    }

    /**
     * view all posting textbook information based on the subject
     * @param s subject
     * @return a list of textbook information
     */
    public ArrayList<TextbookInfo> viewTextInfo(Subject s){
        return s.allBooks;
    }

    public void rateProfessor(Professor p){
        p.rate++;
    }

    public void commentProfessor(Professor p, String comment){
        p.addComments(comment);
    }
}
