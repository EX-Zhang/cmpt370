package com.example.DBInterface;
import java.sql.*;


public class DBConnect {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://10.0.2.2/udaily";

    static final String db_username = "u_daily";
    static final String db_password = "123456";
	
	public DBConnect() {
		// TODO Auto-generated constructor stub
	}

	protected static DBSocket executeSQL(String SQL) {

        DBSocket socket = new DBSocket();

        try{
            Class.forName(JDBC_DRIVER);

            socket.conn = DriverManager.getConnection(DB_URL,db_username,db_password);

            socket.stmt = socket.conn.createStatement();

            socket.rs = socket.stmt.executeQuery(SQL);

        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return socket;
    }

    protected static DBSocket insertSQL(String SQL) {

        DBSocket socket = new DBSocket();

        try{
            Class.forName(JDBC_DRIVER);

            socket.conn = DriverManager.getConnection(DB_URL,db_username,db_password);

            socket.stmt = socket.conn.createStatement();

            socket.stmt.executeUpdate(SQL);

        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return socket;
    }

    protected static class DBSocket{
	    ResultSet rs=null;
        Connection conn=null;
        Statement stmt=null;

        public DBSocket(){

        }

        public void close() throws SQLException {
            if(rs!=null) rs.close();
            stmt.close();
            conn.close();
        }
    }
}
