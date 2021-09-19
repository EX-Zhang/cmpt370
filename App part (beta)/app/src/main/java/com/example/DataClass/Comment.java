package com.example.DataClass;

import com.example.DBInterface.DBOperate;

public class Comment {

    private String ID;
    private String poster;
    private String time;
    private String content;
    private int Rate;

    public Comment(String ID,String poster, String time, String content, int rate){
        this.ID=ID;
        this.poster=poster;
        this.time=time;
        this.content= content;
        this.Rate=rate;
    }

    public String getID(){
        return this.ID;
    }

    public String toString(){
        String str="";

        str+="Time: "+this.time+"\nPoster: "+ DBOperate.getName(this.poster) +"\nRate: "+Integer.toString(this.Rate)+"\n"+this.content;

        return str;
    }

    public static void InsertComment(String ID,String poster, String time, String content, int rate){

        String str ="insert into comments values('"+DBOperate.getRandomString(10)+"','"+ID+"','"+poster+"','"+time+"','"+content+"','"+Integer.toString(rate)+"')";

        System.out.println(str);

        DBOperate.Insert(str);

    }

}
