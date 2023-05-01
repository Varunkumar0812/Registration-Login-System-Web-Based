package com.msc.DbFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBMSOperations {
	public static int insertRecord(ArrayList<String> tuple) {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sample1", "root", "Tvarun@0812");
    		PreparedStatement stmt = con.prepareStatement("INSERT INTO Users VALUES (?, ?, ?, ?, ?, ?, ?)");
    		
    		stmt.setString(1, tuple.get(0));
    		stmt.setString(2, tuple.get(1));
    		stmt.setString(3, tuple.get(2));
    		stmt.setString(4, tuple.get(3));
    		stmt.setString(5, tuple.get(4));
    		stmt.setString(6, tuple.get(5));
    		stmt.setString(7, tuple.get(6));
    		
    		
    		
    		int count = stmt.executeUpdate();
    		
    		con.close();
    		return count;
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}
    	return 0;
    }
	
	public static boolean loginValidity(String username, String password) {
		boolean valid = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sample1", "root", "Tvarun@0812");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT username, password FROM Users");
			
			while(rs.next()) {
				
				if(rs.getString(1).equalsIgnoreCase(username) && rs.getString(2).equals(Encryption.encrypt(password))) {
					valid = true;
				}
			}
			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return valid;
	}
	
	 public static boolean forgotValidity(String username, String recEmail) {
	    	boolean valid = false;
	    	
	    	try {
	    		Class.forName("com.mysql.cj.jdbc.Driver");
	    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sample1", "root", "Tvarun@0812");
	    		Statement stmt = con.createStatement();
	    		ResultSet rs = stmt.executeQuery("SELECT username, recovery_email FROM Users where username=\"" + username + "\"");
	    		
	    		while(rs.next()) {
	    			if(username.equals(rs.getString(1)) && recEmail.equals(rs.getString(2))) {
	    				valid = true;
	    			}
	    		}
	    		
	    		con.close();
	     	}
	    	catch(Exception e) {
	    		System.out.println(e);
	    	}
	    	
	    	return valid;
	   }
	 
	 public static int updatePassword(String username, String password) {
		 int count = 0;
		 
		 try {
	    		Class.forName("com.mysql.cj.jdbc.Driver");
	    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sample1", "root", "Tvarun@0812");
	    		PreparedStatement stmt = con.prepareStatement("UPDATE Users SET password=? WHERE username=?");
	    		
	    		stmt.setString(1, Encryption.encrypt(password));
	    		stmt.setString(2, username);
	    		
	    		count = stmt.executeUpdate();
	    			    		
	    		con.close();
	     	}
	    	catch(Exception e) {
	    		System.out.println(e);
	    	}
		 return count;
	 }
}
