package com.example.DBInterface;

import com.example.DataClass.Comment;
import com.example.DataClass.Professor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

/*
    Class of methods of operation related to the professor
 */
public class DBProfessor {

    /*
        Get a professor from database by its id
     */
    public static Professor getProfessor(String professor_id){
        Professor result=null;

        String sql="select * from professor where professor_id = \""+professor_id+"\"";

        DBConnect.DBSocket socket=DBConnect.executeSQL(sql);

        while(true) {
            try {
                if (!socket.rs.next()) break;
                String ID = socket.rs.getString("professor_id");
                String first_name = socket.rs.getString("Firstname");
                String last_name = socket.rs.getString("Lastname");
                String university = socket.rs.getString("University");
                String username = socket.rs.getString("username");
                result = new Professor(ID, first_name, last_name, university, username);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            socket.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(result!=null){
            DBProfessor.addComments(result);
            DBProfessor.addSubjects(result);
        }

        return result;
    }

    /*
        Get the name of a professor from the database by the id
     */
    public static String getName(String ID) {

        String result = "";

        String sql = "select Firstname,Lastname from professor where professor_id = \"" + ID + "\"";

        DBConnect.DBSocket socket = DBConnect.executeSQL(sql);

        while (true) {
            try {
                if (!socket.rs.next()) break;
                String first_name = socket.rs.getString("Firstname");
                String last_name = socket.rs.getString("Lastname");
                result += first_name + " " + last_name;

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
        Get the comments of a professor by the ID
     */
    public static LinkedList<Comment> checkCommentID(String professor_id){

        LinkedList<Comment> comments = new LinkedList<Comment>();

        String sql = "select * from comments where professor_id=\""+professor_id+"\"";

        DBConnect.DBSocket socket = DBConnect.executeSQL(sql);

        while(true){
            try {
                if(!socket.rs.next()) break;

                String id = socket.rs.getString("comments_id");
                String poster = socket.rs.getString("username");
                String time = socket.rs.getString("time");
                String comment = socket.rs.getString("comment");
                int rate = Integer.parseInt(socket.rs.getString("rate"));
                comments.add(new Comment(id,poster,time,comment,rate));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            socket.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    /*
        If user is a professor, he/she can check the comments by the username
     */
    public static LinkedList<Comment> checkComment(String username){

        String sql = "select professor_id from professor where username = \""+username+"\"";

        DBConnect.DBSocket socket = DBConnect.executeSQL(sql);

        String professor_id="";

        while(true){
            try {
                if(!socket.rs.next()) break;

                professor_id = socket.rs.getString("professor_id");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return checkCommentID(professor_id);
    }

    /*
        Get the username of a professor by the ID
     */
    public static String getUsername(String ID){

        String result = "";

        String sql = "select username from professor where professor_id = \""+ID+"\"";

        DBConnect.DBSocket socket = DBConnect.executeSQL(sql);

        while(true){
            try {
                if(!socket.rs.next()) break;
                result = socket.rs.getString("username");

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
        Get the professor ID by the name
     */
    public static String getProfessorID(String[] name){

        String result=null;

        String sql="select * from professor where Firstname = \""+name[0]+"\" and Lastname = \""+name[1]+"\"";

        DBConnect.DBSocket socket=DBConnect.executeSQL(sql);

        while(true) {
            try {
                if (!socket.rs.next()) break;
                String ID = socket.rs.getString("professor_id");

                result = ID;
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
        Add a subject to a professor object
     */
    public static void addSubjects(Professor professor){

        String sql="select * from subject_relation where username_or_id = \""+professor.ID+"\"";

        DBConnect.DBSocket socket=DBConnect.executeSQL(sql);

        while(true) {
            try {
                if (!socket.rs.next()) break;
                String subject_id=socket.rs.getString("subject_id");
                professor.addSubjects(subject_id);
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

    /*
        Add comments to a professor's object
     */
    public static void addComments(Professor professor){
        String sql="select * from comments where professor_id = \""+professor.ID+"\"";

        DBConnect.DBSocket socket=DBConnect.executeSQL(sql);

        while(true) {
            try {
                if (!socket.rs.next()) break;
                String id = socket.rs.getString("comments_id");
                String username = socket.rs.getString("username");
                String time = socket.rs.getString("time");
                String comment = socket.rs.getString("comment");
                int rate = Integer.parseInt(socket.rs.getString("rate"));
                professor.addComments(new Comment(id,username,time,comment,rate));
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

    /*
        Get all the professors from the database
     */
    public static ArrayList<Professor> getProfessorList(){

        ArrayList<Professor> list= new ArrayList<Professor>();

        String sql="select * from professor";

        DBConnect.DBSocket socket=DBConnect.executeSQL(sql);

        while(true) {
            try {
                if (!socket.rs.next()) break;
                String ID = socket.rs.getString("professor_id");
                String first_name = socket.rs.getString("Firstname");
                String last_name = socket.rs.getString("Lastname");
                String university = socket.rs.getString("University");
                String username = socket.rs.getString("username");
                list.add(new Professor(ID, first_name, last_name, university, username));
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


}
