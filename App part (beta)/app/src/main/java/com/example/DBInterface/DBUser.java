package com.example.DBInterface;

import com.example.DataClass.User;

import java.sql.SQLException;

public class DBUser {

    public static boolean userExist(String username){

        User result=null;

        String sql="select * from user where Username=\""+username+"\"";

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


        return result!=null;
    }

    public static String getPassword(String username){

        String rs="";

        String sql="select * from user where Username=\""+username+"\"";

        DBConnect.DBSocket socket=DBConnect.executeSQL(sql);

        while (true) {
            try {
                if (!socket.rs.next()) break;
                rs=socket.rs.getString("Password");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        try {
            socket.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public static void register(String username, String password){

        String sql = "insert into user(Username,Password) values(\""+username+"\",\""+password+"\")";

        DBConnect.insertSQL(sql);

    }

}
