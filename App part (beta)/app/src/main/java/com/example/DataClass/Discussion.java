package com.example.DataClass;

import com.example.DBInterface.DBOperate;

import java.util.ArrayList;

public class Discussion {

    private String ID;
    private String time;
    private String poster;
    private String content;
    private ArrayList<Discussion> replies;

    public Discussion(String id, String time, String poster, String content){
        this.ID=id;
        this.time=time;
        this.poster=poster;
        this.content=content;
        this.replies=new ArrayList<Discussion>();
    }

    public String getID(){
        return this.ID;
    }

    public void addReply(Discussion reply){
        this.replies.add(reply);
    }

    public String toString(int n){
        String str ="";

        str+=time+" "+ DBOperate.getName(poster) +": "+content+"\n";
        for (Discussion discussion: this.replies){

            for (int i=0;i<n;i++){
                str+="   ";
            }

            str+="---"+discussion.toString(n+1);
        }

        return str;
    }

    public String toString(){
        return this.toString(0);
    }

    public static void insertDiscussion(String type, String topic, String[] tags, String content){

        String ID = DBOperate.getRandomString(20);

        String sql = "insert into discussion values('"+ID+"',NULL,'TestUser','"
                +DBOperate.getCurrentDate()+"','CMPT-370','"+type+"','"+topic+"','"+content+"')";

        DBOperate.Insert(sql);

        for(String s: tags){
            sql = "insert into discussion_tag values('"+s+"','"+ID+"')";
            DBOperate.Insert(sql);
        }

    }
}
