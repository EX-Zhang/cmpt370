package com.example.DBInterface;

import com.example.u_daily.Myinfo;

import java.sql.SQLException;
import java.util.LinkedList;

/*
    Class of methods of operation with subject
 */
public class DBSubject {

    /*
        Get all the subject ids of a main subject
     */
    public static String[] getSubjectID(String main_subject){

        LinkedList<String> list=new LinkedList<String>();

        String sql ="select * from subject where main_id = \""+main_subject+"\"";

        DBConnect.DBSocket socket = DBConnect.executeSQL(sql);

        while(true){
            try {
                if(!socket.rs.next()) break;
                list.add(socket.rs.getString("subject_id"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            socket.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] subjects = new String[list.size()];

        for(int i=0;i<list.size();i++){
            subjects[i]=list.get(i);
        }

        return subjects;
    }

    /*
        Get all the ids of the main subjects
     */
    public static String[] getMainIDs(){

        LinkedList<String> list=new LinkedList<String>();

        String sql ="select distinct main_id from subject";

        DBConnect.DBSocket socket = DBConnect.executeSQL(sql);

        while(true){
            try {
                if(!socket.rs.next()) break;
                list.add(socket.rs.getString("main_id"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            socket.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] subjects = new String[list.size()];

        for(int i=0;i<list.size();i++){
            subjects[i]=list.get(i);
        }

        return subjects;
    }

    /*
        Get the IDs of professors by the subject
     */
    public static String[] getProfessorIDs(String subject_id){

        LinkedList<String> list =new LinkedList<String>();

        String sql = "select * from subject_relation where subject_id=\""+subject_id+"\"";

        DBConnect.DBSocket socket=DBConnect.executeSQL(sql);

        while(true){
            try {
                if(!socket.rs.next()) break;
                String professor_id=socket.rs.getString("professor_id");
                if(DBProfessor.getUsername(professor_id).equals(Myinfo.username)) continue;
                list.add(professor_id);
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
            result[i]=list.get(i);
        }

        return result;
    }

    /*
        Get the name of a subject by its id
     */
    public static String getName(String subject_id){

        String result="";

        String sql = "select name from subject where subject_id =\""+subject_id+"\"";

        DBConnect.DBSocket socket=DBConnect.executeSQL(sql);

        while (true){
            try {
                if(!socket.rs.next()) break;
                result = socket.rs.getString("name");
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
