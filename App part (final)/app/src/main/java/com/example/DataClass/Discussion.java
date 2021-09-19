package com.example.DataClass;

import com.example.DBInterface.DBDiscussion;
import com.example.DBInterface.DBOperate;

import java.util.ArrayList;
import java.util.LinkedList;


/*
    Class for store the discussion
 */
public class Discussion {

    private String ID;  // Primary key in the database
    private String time;
    private String poster;
    private String content;
    private String replie_id;
    private String type;
    private String topic;
    private String subject_id;
    private LinkedList<String> tags;

    public Discussion(String id, String time, String poster, String content,String replie_id,String type,String topic,String subject_id){
        this.ID=id;
        this.time=time;
        this.poster=poster;
        this.content=content;
        this.replie_id=replie_id;
        this.type=type;
        this.topic=topic;
        this.subject_id=subject_id;
        this.tags=new LinkedList<String>();
    }

    public String getID(){
        return this.ID;
    }

    public String getContent(){
        return this.content;
    }

    public void addTag(String tag){
        this.tags.add(tag);
    }

    public String[] getString(){
        String[] strs;
        if(this.replie_id!=null){
            strs = new String[6];
        }
        else{
            strs = new String[4];
        }

        strs[0]="Type: "+this.type+"\nTopic: "+this.topic+"\nPoster: "+DBOperate.getName(this.poster);
        strs[1]="Time: "+time;
        strs[2]="Tags: ";
        for(String tag:this.tags){
            strs[2]+=tag+" ";
        }
        strs[3]=this.content;
        if(this.replie_id!=null){
            strs[4]="------Reply------";
            strs[5]=DBDiscussion.getContent(this.replie_id);
        }

        return strs;
    }

    /*
        Function to insert discussion into database
     */
    public static void insertDiscussion(String type, String topic, String[] tags, String content,String subject_id,String user){

        String ID = DBOperate.getRandomString(1);

        String replyid = DBDiscussion.getReplyID(tags);

        String sql="";

        if(replyid.equals("")){
            sql = "insert into discussion values('"+ID+"',NULL,'"+user+"','"
                    +DBOperate.getCurrentDate()+"','"+subject_id+"','"+type+"','"+topic+"','"+content+"')";
        }
        else{
            sql = "insert into discussion values('"+ID+"','"+replyid+"','"+user+"','"
                    +DBOperate.getCurrentDate()+"','"+subject_id+"','"+type+"','"+topic+"','"+content+"')";
        }

        DBOperate.Insert(sql);

        for(String s: tags){
            sql = "insert into discussion_tag values('"+s+"','"+ID+"')";
            DBOperate.Insert(sql);
        }

    }
}
