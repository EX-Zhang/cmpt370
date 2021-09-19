package com.example.DBInterface;

import com.example.DataClass.User;

import java.sql.SQLException;

/*
    Class of methods related to the user
 */
public class DBUser {

    /*
        Check whether a username exists.
     */
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

    /*
        Get the password by the username
     */
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

    /*
        Get the identity of a user by the username
     */
    public static String getIdentity(String username){
        String sql = "select Identity from user where Username = \""+username+"\"";

        String Identity="";

        DBConnect.DBSocket socket = DBConnect.executeSQL(sql);

        while(true){
            try {
                if(!socket.rs.next()) break;
                Identity = socket.rs.getString("Identity");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        try {
            socket.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Identity;
    }

    /*
        Register a user ( Insert the info into the database)
     */
    public static void register(String username, String password,String name,String email,String university){

        String[] name_arr = name.split(" ");

        String sql = "insert into user(Username,Password,Firstname,Lastname,Email,University,Identity) values(\""+username+"\",\""+password+
                "\",\""+name_arr[0]+"\",\""+name_arr[1]+"\",\""+email+"\",\""+university+"\",\"Student\")";

        DBConnect.insertSQL(sql);

    }

}
