package com.example.DBInterface;
import java.sql.*;
import java.util.LinkedList;

/*
    Class to store the methods of connecting the database server
 */
public class DBConnect{

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  // Driver
    static final String DB_URL = "jdbc:mysql://10.0.2.2/udaily";    // Address

    static final String db_username = "u_daily";    // Username
    static final String db_password = "123456";     // Password
	
	public DBConnect() {
		// TODO Auto-generated constructor stub
	}

	/*
	    Method to execute SQL query that select data from database
	 */
	protected static DBSocket executeSQL(final String SQL) {

        final LinkedList<DBSocket> socket_list=new LinkedList<DBSocket>();

        new Thread(new Runnable(){

            @Override
            public void run() {
                try{
                    Class.forName(JDBC_DRIVER);

                    DBSocket socket = new DBSocket();

                    socket.conn = DriverManager.getConnection(DB_URL,db_username,db_password);

                    socket.stmt = socket.conn.createStatement();

                    socket.rs = socket.stmt.executeQuery(SQL);

                    socket_list.add(socket);

                }catch(SQLException se){
                    se.printStackTrace();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

        while(socket_list.isEmpty());

        return socket_list.get(0);
    }

    /*
        Method to execute SQL query to insert data into database
     */
    protected static DBSocket insertSQL(final String SQL) {

        final LinkedList<DBSocket> socket_list=new LinkedList<DBSocket>();

        new Thread(new Runnable(){
            @Override
            public void run() {

                try{
                    Class.forName(JDBC_DRIVER);

                    DBSocket socket = new DBSocket();

                    socket.conn = DriverManager.getConnection(DB_URL,db_username,db_password);

                    socket.stmt = socket.conn.createStatement();

                    socket.stmt.executeUpdate(SQL);

                    socket_list.add(socket);

                }catch(SQLException se){
                    se.printStackTrace();
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }).start();


        while(socket_list.isEmpty());

        return socket_list.get(0);
    }

    /*
        A protected class to store the socket required for SQL query
     */
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
