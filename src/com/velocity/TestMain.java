package com.velocity;

import java.sql.SQLException;
import java.util.Scanner;

public class TestMain 
{
	public static void main(String[] args) throws SQLException 
	{
		 AccountCheck accountCheck=new AccountCheck();

		System.out.println("\r\n       *WELCOME To Java Online Exam* \n");
		System.out.println("--------------------------------------------------");
		System.out.println(" ENTER 1 : For Registration");
		System.out.println(" ENTER 2 : For Login ");
		System.out.println(" ENTER 3 : Check Your Name In Merit List");
		System.out.println(" ENTER 4 : Check Your Record ");
		System.out.println("--------------------------------------------------");
		
		
		Scanner scanner=new Scanner(System.in);
		int  choice =scanner.nextInt();
		
		
		switch(choice) 
		{
		case 1:accountCheck.getUserInformation();
	           break;
		
		case 2:accountCheck.getStudentData();
		       break;
		
		case 3:accountCheck.getDisplayRecord();
			   break;
		
		case 4:accountCheck.getParticularRecord();
			   break;
		
		
		       
		default:System.out.println("Please enter correct choice");
		
		}
	}



}
