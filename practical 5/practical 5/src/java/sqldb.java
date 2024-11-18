/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MISAREE
 */
import java.sql.*;

public class sqldb {

    static Connection conn; //Connection Object
    static Statement st; //Statement Object
    // Connect method establish the connection to the database
    static void connect()
    {
        String url = "jdbc:mysql://localhost:3306/";
        String driver = "com.mysql.jdbc.Driver";
        String dbName = "JavaTest";
        String userName = "root"; 
        String password = "";
        try {
                Class.forName(driver); //Register JDBC Driver
                //Class.forName(driver).newInstance(); //Register JDBC Driver
                conn = DriverManager.getConnection(url+dbName,userName,password); //create a connection object
                System.out.println("Connection Established");
                st = conn.createStatement();
            }
        catch (Exception e) {
		  e.printStackTrace();
            }
    }
    static void connclose()
    {
        try{
        conn.close();
        }
        catch(Exception e)
        {  e.printStackTrace();
        }
    }
    
    static int iud_data(String str) // Used for insert, update, delete query
    {   int r=0;
        try {
        r=st.executeUpdate(str);
        }
        catch(Exception e)
        {
        e.printStackTrace();
        }
        return r;
    }
    
    static int iud_prepare(String... str) // Used for insert, update, delete query
    {   int r=0;
        try {
        PreparedStatement prst=conn.prepareStatement(str[0]);  
        prst.setString(1,str[1]);
        prst.setString(2,str[2]);
        prst.setInt(3,Integer.parseInt(str[3]));
        r=prst.executeUpdate();
        }
        catch(NumberFormatException | SQLException e)
        {
            e.printStackTrace();
        }
        return r;
    }
    
    static ResultSet fetchdata(String str) throws SQLException // Used for select query
    { 
        return st.executeQuery(str);
    }
    
}
