package com.velocity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountCheck extends InsertScoreCount
{
	boolean flag = false;
	String uname1;
	String pass1;

	public void getUserInformation() throws SQLException {

		System.out.println(" Fill Your Information");

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Your First Name");
		String fname = scanner.next();
		System.out.println("Enter Your Last Name ");
		String lname = scanner.next();
		System.out.println("Enter Your Email ");
		String email = scanner.next();
	    System.out.println("Create Username");
		String uname = scanner.next();
		System.out.println("Create password");
		String pass = scanner.next();
		System.out.println("Enter your city");
		String city=scanner.next();
		System.out.println("Enter your mobile number");
		String mo_no=scanner.next();

		

		try {
			
			Connection con=DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"insert into student (firstname,lastname,Email,username,password,city,mo_no)" + "values(?,?,?,?,?,?,?)");
			// set the value
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, email);
			ps.setString(4, uname);
			ps.setString(5, pass);
			ps.setString(6, city);
			ps.setString(7, mo_no);
			int a = ps.executeUpdate();
			System.out.println(a);
			System.out.println(" Congratulation  Your Registration Successfully Completed....!!  ");
			System.out.println("**********************************************************");

			AccountCheck accountCheck = new AccountCheck();
			accountCheck.getStudentData();
			con.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

///////////////////////////////////StudentData////////////////////////////////////////////////////	
	public void getStudentData() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Login Your Account");
		System.out.println("Enter Your Username ");
		uname1 = scanner.next();
		System.out.println("Enter Your Password");
		pass1 = scanner.next();
		
		ResultSet rs = null;
		try {
			Connection con=DBUtil.getConnection();
			
			PreparedStatement ps = con.prepareStatement("select username, password from student");
			rs = ps.executeQuery();

			while (rs.next()) {

				if (uname1.equals(rs.getString(1).toString()) && pass1.equals(rs.getString(2).toString())) {
					flag = true;
				}

			}
			if (flag) {
				System.out.println("**********************************************");
				System.out.println("Your Exam Is Start..!");
				AccountCheck accountCheck = new AccountCheck();
				accountCheck.getQuestionData(uname1);

			} else {
				System.out.println("Please Register Your Account...");
				AccountCheck accountCheck = new AccountCheck();
				accountCheck.getUserInformation();
			}
			con.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

///////////////////////////////////QuestionData//////////////////////////////////////////////////////
	int scoreCount = 0;

	// Design the method which return the connection object
	public Connection getConnectionDetails() throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/javaquiz?characterEncoding=latin1";
			con = DriverManager.getConnection(url, "root", "1234@Sai");
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public void getQuestionData(String uname) throws ClassNotFoundException, SQLException {
		
		ResultSet rs = null;
		try {
			AccountCheck accountCheck = new AccountCheck();

			Connection conn = accountCheck.getConnectionDetails();
			PreparedStatement ps = conn.prepareStatement("select *from mcq order by rand() limit 10");

			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("Question:- " + rs.getString(1));
				System.out.println("1) " + rs.getString(2));
				System.out.println("2) " + rs.getString(3));
				System.out.println("3) " + rs.getString(4));
				System.out.println("4) " + rs.getString(5));
				Scanner scanner = new Scanner(System.in);
				System.out.println("              Submit Your Option");
				String answer = scanner.next();

				String a = rs.getString(6).toString();

				if (a.contains(answer)) {
					scoreCount++;

				} else {
				}

			}

			System.out.println(" Your Score is " + scoreCount + " out Of 10 \n");

			if (scoreCount >= 8) {
				System.out.println(" Congratulations....! Your Grade is : Class A");
			} else if (scoreCount == 7) {
				System.out.println("  Your  Grade  is: Class B");
			} else if (scoreCount == 6 || scoreCount == 5) {
				System.out.println(" Your Grade is : Class C");

			} else {
				System.out.println("Fail");

			}
			AccountCheck accountCheck1 = new AccountCheck();
			accountCheck1.getScoreData(uname, scoreCount);
			conn.close();
			ps.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public static void main(String[] args) throws SQLException {

	}


}
