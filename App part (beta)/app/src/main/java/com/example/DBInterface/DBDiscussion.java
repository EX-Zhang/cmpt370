package com.example.DBInterface;

import com.example.DataClass.Discussion;

import java.sql.SQLException;

public class DBDiscussion {

    public static Discussion getDiscussion(String discussion_id){
        Discussion result = null;

        String sql="select * from Discussion where discussion_id = \""+discussion_id+"\"";

        DBConnect.DBSocket socket=DBConnect.executeSQL(sql);

        while (true){
            try {
                if (!socket.rs.next()) break;
                String ID=socket.rs.getString("discussion_id");
                String poster = socket.rs.getString("Poster");
                String time = socket.rs.getString("time");
                String content = socket.rs.getString("content");
                result = new Discussion(ID,time,poster,content);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            socket.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBDiscussion.getReplies(result);
        return result;
    }

    public static void getReplies(Discussion d){

        String sql="select * from Discussion where reply_id = \""+d.getID()+"\"";

        DBConnect.DBSocket socket=DBConnect.executeSQL(sql);

        while (true){
            try {
                if (!socket.rs.next()) break;
                String ID=socket.rs.getString("discussion_id");
                String poster = socket.rs.getString("Poster");
                String time = socket.rs.getString("time");
                String content = socket.rs.getString("content");
                Discussion reply = new Discussion(ID,time,poster,content);
                DBDiscussion.getReplies(reply);
                d.addReply(reply);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            socket.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
