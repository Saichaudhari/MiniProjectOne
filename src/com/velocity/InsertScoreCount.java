package com.velocity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertScoreCount 
{
	private static String uname;
	private static String pass;
	private static int scoreCount;

	public Connection getConnectionDetails() throws SQLException, ClassNotFoundException  {
		Connection con=null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/javaquiz?characterEncoding=latin1";
			 con =DriverManager.getConnection(url,"root","1234@Sai");
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return con;
		}
	 public void getScoreData(String uname, int scoreCount) throws ClassNotFoundException, SQLException {
			
		 
		try
		{
			InsertScoreCount insertScoreCount =new InsertScoreCount();
			Connection conn=insertScoreCount.getConnectionDetails();
			PreparedStatement ps= conn.prepareStatement("insert into scorecard(firstname,score)values(?,?)");
			ps.setString(1, uname);
			ps.setInt(2, scoreCount);
			   
			int a= ps.executeUpdate();
			System.out.println(a);
			System.out.println("---------------------------------------------------");
			System.out.println("Exam Successfully Done \n");
			System.out.println(" \r\n   Thank You...! \n");
			
            conn.close();
			ps.close();
		
			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
			
			
	}
///////////////////////////////////Display The Record  /////////////////////////////////////////////////////
	 public void getDisplayRecord() throws SQLException
	 {
		 
		 
	   try 
	   {
				
		         Connection con=DBUtil.getConnection();	
				 PreparedStatement ps=con.prepareStatement("select *from scorecard order by score desc");
				
				 ResultSet rs= ps.executeQuery();
				 System.out.println("Students record   \n");
				 while(rs.next())
				 {
					System.out.println("Student id;- "+rs.getInt(1));
					System.out.println("Student Name:-"+rs.getString(2));
					System.out.println("Obtained Score:"+rs.getString(3)+"\n");
					System.out.println("----------------------------------------------------------------------------------");
					
				}
				con.close();
				ps.close();
				rs.close();
			}
	        catch(Exception e)
	        {
				e.printStackTrace();
			}
			
		}
	 //////////////////////////////////Particular Record//////////////////////////////////////////////////////////////////////////////////////////
	 public void getParticularRecord() throws SQLException {
		 System.out.println("Enter Student id");
			
			Scanner scanner=new Scanner(System.in);
			 int studid= scanner.nextInt();
			
			//Connection con=null;

			//PreparedStatement ps=null;
			//con.close();
				
			try {
				Connection con=DBUtil.getConnection();
				
				 PreparedStatement ps= con.prepareStatement("select studentid,firstname,lastname,Email from student where studentid=?");
				 ps.setInt(1, studid);
		         boolean flag1=false;

		         ResultSet rs  = ps.executeQuery();
				
				while(rs.next())
				{
					if (flag1=true)
					{

					System.out.println("Student Id: "+rs.getInt(1));
					System.out.println("First Name: "+rs.getString(2));
					System.out.println("Last Name: "+rs.getString(3));
					System.out.println("Email Address: "+rs.getString(4));
					}
				}
				if (flag1)
				{
					System.out.println("Record  fount ");
				}else 
				{
					System.out.println("Record not fount ");

					
				}
				con.close();
				ps.close();
				rs.close();
				
			}
			catch(Exception e) 
			{
				
				e.printStackTrace();
			}
		
			
			
		}
		
	 
	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		
		
	}
	


}
