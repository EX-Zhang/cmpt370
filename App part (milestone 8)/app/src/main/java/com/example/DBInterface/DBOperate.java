package com.example.DBInterface;

import com.example.DataClass.*;
import java.sql.SQLException;
import java.util.Random;

public class DBOperate {


    public static User getUser(String username, String password){

        User result=null;

        String sql="select * from user where Username=\""+username+"\" and Password=\""+password+"\"";

        DBConnect.DBSocket socket=DBConnect.executeSQL(sql);

        while (true) {
            try {
                if (!socket.rs.next()) break;
                String first_name=socket.rs.getString("Firstname");
                String last_name=socket.rs.getString("Lastname");
                String email=socket.rs.getString("Email");
                String university=socket.rs.getString("University");
                String identity=socket.rs.getString("Identity");
                result=new User(username,first_name,last_name,email,university,identity);
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

    public static String getCurrentDate(){
        java.util.Date javatime=new java.util.Date();
        java.sql.Date date=new java.sql.Date(javatime.getTime());
        return date.toString();
    }

    public static void Insert(String sql){
        DBConnect.DBSocket socket = DBConnect.insertSQL(sql);
        try {
            socket.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String getName(String ID){

        String result = "";

        String sql="select Firstname,Lastname from professor where professor_id = \""+ID+"\"";

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

        sql="select Firstname,Lastname from user where Username = \""+ID+"\"";

        socket=DBConnect.executeSQL(sql);

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
}
