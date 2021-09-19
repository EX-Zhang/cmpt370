package com.example.DBInterface;

import com.example.DataClass.Discussion;
import com.example.u_daily.Myinfo;

import java.sql.SQLException;
import java.util.LinkedList;

/*
    Class of methods of discussion
 */
public class DBDiscussion {

    /*
        Get a discussion by its ID
     */
    public static Discussion getDiscussion(String discussion_id){
        Discussion result = null;

        // select * from discussion where discussion_id = [discussion_id]
        String sql="select * from discussion where discussion_id = \""+discussion_id+"\"";

        DBConnect.DBSocket socket=DBConnect.executeSQL(sql);

        while (true){
            try {
                if (!socket.rs.next()) break;
                String ID=socket.rs.getString("discussion_id");
                String poster = socket.rs.getString("Poster");
                String time = socket.rs.getString("time");
                String content = socket.rs.getString("content");
                String reply_id = socket.rs.getString("reply_id");
                String type= socket.rs.getString("type");
                String topic= socket.rs.getString("topic");
                String subject_id= socket.rs.getString("subject_id");
                result = new Discussion(ID,time,poster,content,reply_id,type,topic,subject_id);
                for(String tag:getTags(discussion_id)){
                    result.addTag(tag);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            socket.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
        Get the discussion posted by myself
     */
    public static LinkedList<Discussion> getMyDiscussion(){

        LinkedList<Discussion> list = new LinkedList<Discussion>();

        String sql = "select * from discussion where poster =\""+ Myinfo.username +"\"";

        DBConnect.DBSocket socket=DBConnect.executeSQL(sql);

        while (true){
            try {
                if (!socket.rs.next()) break;
                String ID=socket.rs.getString("discussion_id");
                String poster = socket.rs.getString("Poster");
                String time = socket.rs.getString("time");
                String content = socket.rs.getString("content");
                String reply_id = socket.rs.getString("reply_id");
                String type= socket.rs.getString("type");
                String topic= socket.rs.getString("topic");
                String subject_id= socket.rs.getString("subject_id");
                Discussion result = new Discussion(ID,time,poster,content,reply_id,type,topic,subject_id);
                for(String tag:getTags(ID)){
                    result.addTag(tag);
                }
                list.add(result);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            socket.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    /*
        Get discussions that reply to mine
     */
    public static LinkedList<Discussion> getMyReply(){

        LinkedList<Discussion> list = new LinkedList<Discussion>();

        // select * from discussion where reply_id in (select discussion_id from discussion where poster = [username] )
        String sql = "select * from discussion where reply_id in (select discussion_id from discussion where poster =\""+ Myinfo.username +"\")";

        DBConnect.DBSocket socket=DBConnect.executeSQL(sql);

        while (true){
            try {
                if (!socket.rs.next()) break;
                String ID=socket.rs.getString("discussion_id");
                String poster = socket.rs.getString("Poster");
                String time = socket.rs.getString("time");
                String content = socket.rs.getString("content");
                String reply_id = socket.rs.getString("reply_id");
                String type= socket.rs.getString("type");
                String topic= socket.rs.getString("topic");
                String subject_id= socket.rs.getString("subject_id");
                Discussion result = new Discussion(ID,time,poster,content,reply_id,type,topic,subject_id);
                for(String tag:getTags(ID)){
                    result.addTag(tag);
                }
                list.add(result);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            socket.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    /*
        Get the newest 50 discussions
     */
    public static LinkedList<Discussion> getNewestPost(){

        LinkedList<Discussion> list = new LinkedList<Discussion>();

        String sql = "select * from discussion ORDER BY time DESC limit 0,50";

        DBConnect.DBSocket socket=DBConnect.executeSQL(sql);

        while (true){
            try {
                if (!socket.rs.next()) break;
                String ID=socket.rs.getString("discussion_id");
                String poster = socket.rs.getString("Poster");
                String time = socket.rs.getString("time");
                String content = socket.rs.getString("content");
                String reply_id = socket.rs.getString("reply_id");
                String type= socket.rs.getString("type");
                String topic= socket.rs.getString("topic");
                String subject_id= socket.rs.getString("subject_id");
                Discussion result = new Discussion(ID,time,poster,content,reply_id,type,topic,subject_id);
                for(String tag:getTags(ID)){
                    result.addTag(tag);
                }
                list.add(result);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            socket.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    /*
        Generate the SQL that select discussion with certain tags by recursion
     */
    public static String ReplySelectSQL(String[] tags,int pos){

        // select discussion_id from discussion_tag where tag = [tag] and discussion_id in( select discussion_id from discussion_tag where ... )
        String sql="select discussion_id from discussion_tag where tag =\""+tags[pos]+"\"";

        if(++pos!=tags.length){
            sql+= "and discussion_id in ("+ReplySelectSQL(tags,pos)+")";
        }

        return sql;
    }

    /*
        Get the discussion id that wants to reply
     */
    public static String getReplyID(String[] tags){

        String sql = ReplySelectSQL(tags,0);

        System.out.println(sql);

        DBConnect.DBSocket socket = DBConnect.executeSQL(sql);
        String reply_id="";

        while(true){
            try {
                if(!socket.rs.next()) break;
                reply_id = socket.rs.getString("discussion_id");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        try {
            socket.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reply_id;
    }

    /*
        Get a content of discussion by its ID
     */
    public static String getContent(String discussion_id){

        return getDiscussion(discussion_id).getContent();
    }

    /*
        Get tags of a discussion by its ID
     */
    public static LinkedList<String> getTags(String discussion_id){
        LinkedList<String> tags = new LinkedList<String>();

        String sql="select * from discussion_tag where discussion_id=\""+discussion_id+"\"";

        DBConnect.DBSocket socket=DBConnect.executeSQL(sql);
        while(true){
            try {
                if (!socket.rs.next()) break;
                String tag = socket.rs.getString("tag");
                tags.add(tag);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            socket.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tags;
    }
}