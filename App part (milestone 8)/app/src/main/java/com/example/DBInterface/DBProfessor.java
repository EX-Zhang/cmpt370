package com.example.DBInterface;

import com.example.DataClass.Comment;
import com.example.DataClass.Professor;

import java.sql.SQLException;
import java.util.ArrayList;

public class DBProfessor {

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

    public static String getProfessorID(String[] name){

        String result=null;

        System.out.println(name);
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
