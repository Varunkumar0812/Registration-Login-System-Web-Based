package com.msc.DbFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class Validation {

    public static boolean usernameValidity(String username) {
    	return Pattern.compile("^[a-z0-9_]+$").matcher(username).matches();
    }

    public static boolean nameValidity(String name) {
    	return Pattern.compile("^[a-zA-Z0-9_]+$").matcher(name).matches();
    }
    
    public static boolean dateValidity(String date) {
    	try {
    		LocalDate.parse(date);
    	}
    	catch(Exception e) {
    		return false;
    	}
    	return true;
    }
    
    public static int passwordValidity(String password) {
    	int uppercase = 0;
		int symbols   = 0;
		int numbers   = 0;
			
		String numberSet = "0123456789";
		String symbolSet = "`~!@#$%^&*()_-+=/'|[]{};:\\\"";
		
		for(int i = 0;i < password.length();i++) {
			if(Character.isUpperCase(password.charAt(i))) {
				uppercase += 1;
			}
			else if(numberSet.contains(String.valueOf(password.charAt(i)))) {
				numbers += 1;
			}
			else if(symbolSet.contains(String.valueOf(password.charAt(i)))) {
				symbols += 1;
			}
		}
				
		if (password.length() <= 8) {
			return 1;
		}
		else if(uppercase == 0 && numbers == 0 && symbols == 0) {
			return 2;
		}
		else if(uppercase == 0 && numbers == 0 && symbols != 0) {
			return 3;
		}
		else if(uppercase == 0 && symbols == 0 && numbers != 0) {
			return 4;
		}
		else if(numbers == 0 && symbols == 0 && uppercase != 0) {
			return 5;
		}
		else if(uppercase == 0 && numbers != 0 && symbols != 0) {
			return 6;
		}
		else if(numbers == 0 && uppercase != 0 && symbols != 0) {
			return 7;
		}
		else if(symbols == 0 && uppercase != 0 && numbers != 0) {
			return 8;
		}
		else {
			return 9;
		}
    }
    
    public static boolean passwordMatch(String password, String cPassword) {
    	return password.equals(cPassword);
    }
    
    
    public static boolean usernameExists(String username) {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sample1", "root", "Tvarun@0812");
    		Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT username FROM Users");  
			while(rs.next())  {
				if(rs.getString(1).equals(username)) {
					return false;
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return true;
    }
}
