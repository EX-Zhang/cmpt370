package com.example.DataClass;

import com.example.DBInterface.DBOperate;


/*
    Class that store the comments of the professors
 */

public class Comment {

    private String ID;
    private String poster;
    private String time;
    private String content;
    private int Rate;

    public Comment(String ID,String poster, String time, String content, int rate){
        this.ID=ID; // ID to store the comment in the database
        this.poster=poster; // ID of the poster
        this.time=time; // Time of submitting
        this.content= content;  // Content of the comment
        this.Rate=rate; // Rate of the comment
    }

    public int getRate(){
        return this.Rate;
    }

    public String getTime(){
        return this.time;
    }

    public String getContent(){
        return this.content;
    }

    public String getID(){
        return this.ID;
    }

    public String toString(){
        String str="";

        str+="Time: "+this.time+"\nPoster: "+ DBOperate.getName(this.poster) +"\nRate: "+Integer.toString(this.Rate)+"\n"+this.content;

        return str;
    }

    /*
        Function to insert the comment into the database
     */
    public static void InsertComment(String ID,String poster, String time, String content, int rate){

        String str ="insert into comments values('"+DBOperate.getRandomString(0)+"','"+ID+"','"+poster+"','"+time+"','"+content+"','"+Integer.toString(rate)+"')";

        System.out.println(str);

        DBOperate.Insert(str);

    }

}
