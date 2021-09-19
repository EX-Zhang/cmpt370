package com.example.DBInterface;

import com.example.DataClass.*;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Random;

/*
    Class of methods that is needed for database operation
 */
public class DBOperate {

    /*
        Get the string of current date (date and time)
     */
    public static String getCurrentDate(){
        java.util.Date javatime=new java.util.Date();
        java.sql.Timestamp time=new java.sql.Timestamp(javatime.getTime());
        return time.toString();
    }

    /*
        Execute a insertion SQL
     */
    public static void Insert(String sql){
        DBConnect.DBSocket socket = DBConnect.insertSQL(sql);
        try {
            socket.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
        Generate a random String for the Primary Key.
        It cannot check that the Key has been used, we don't have time to fix the bug of that.
     */
    public static String getRandomString(int num){

        String[] tables = {"comments","discussion","reply","sell","subject_relation"};
        String[] key = {"comments_id","discussion_id","reply_id","item_id","relation_id"};
        int[] len = {10,20,50,50,20};

        boolean new_id = false;
        StringBuffer sb=null;

        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        sb=new StringBuffer();
        for(int i=0;i<len[num];i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /*
        Get the name of a user by its username
     */
    public static String getName(String ID){

        String result = "";

        String sql="select Firstname,Lastname from user where Username = \""+ID+"\"";

        DBConnect.DBSocket socket=DBConnect.executeSQL(sql);

        while(true) {
            try {
                if (!socket.rs.next()) break;
                String first_name = socket.rs.getString("Firstname");
                String last_name = socket.rs.getString("Lastname");
                result += first_name+" "+last_name;
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
        Get the names of textbooks used by a subject by the subject id
     */
    public static String[] getTextbookName(String subject_id){

        LinkedList<String> list=new LinkedList<>();

        String sql = "select * from textbook where subject_id = \""+subject_id+"\"";

        DBConnect.DBSocket socket = DBConnect.executeSQL(sql);

        while(true){
            try {
                if(!socket.rs.next()) break;
                list.add(socket.rs.getString("name"));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        try {
            socket.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String[] result = new String[list.size()];

        for(int i=0;i<list.size();i++){
            result[i] = list.get(i);
        }

        return result;
    }
}
