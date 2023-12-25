package com.velocity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil
{
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	  {
		  //load the driver class
		  Class.forName("com.mysql.jdbc.Driver");
		  
		  String url="jdbc:mysql://localhost:3306/javaquiz?characterEncoding=latin1";
		//istablish the connection
		  Connection con=DriverManager.getConnection(url, "root", "1234@Sai");
		  
		  return con;
	  }


}
