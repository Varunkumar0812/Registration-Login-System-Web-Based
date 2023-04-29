package com.msc.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected int insertRecord(ArrayList<String> tuple) {
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
    
    protected boolean usernameValidity(String username) {
    	return Pattern.compile("^[a-z0-9_]+$").matcher(username).matches();
    }

    protected boolean nameValidity(String name) {
    	return Pattern.compile("^[a-zA-Z0-9_]+$").matcher(name).matches();
    }
    
    protected boolean dateValidity(String date) {
    	try {
    		LocalDate.parse(date);
    	}
    	catch(Exception e) {
    		return false;
    	}
    	return true;
    }
    
    protected static int passwordValidity(String password) {
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
    
    protected boolean passwordMatch(String password, String cPassword) {
    	return password.equals(cPassword);
    }
    
    
    protected boolean usernameExists(String username) {
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
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String UserName  = request.getParameter("uname");
		String FirstName = request.getParameter("first_name");
		String LastName  = request.getParameter("last_name");
		String PassWord  = request.getParameter("pword");
		String CPassword = request.getParameter("cpword");
		String Rec_Email = request.getParameter("rec_email");
		String DOB       = request.getParameter("dob");
		String gender    = request.getParameter("gender");
		
		PrintWriter out = response.getWriter();
		
		boolean valid = true;
		
		ArrayList<String> tuple = new ArrayList<String>();
		
		out.print("<html> <head> <title> Register Result Page </title> </head> <body>");
		
		if(!usernameValidity(UserName)) {
			valid = false;
			out.print("<h1> The username has invalid characters, please check</h1>");
		}
		if(!usernameExists(UserName)) {
			valid = false;
			out.print("<h1> The username already exists, try a different one</h1>");
		}
		if(!nameValidity(FirstName)) {
			valid = false;
			out.print("<h1> The First name has invalid characters, please check</h1>");
		}
		if(!nameValidity(LastName)) {
			valid = false;
			out.print("<h1> The Last name has invalid characters, please check</h1>");
		}
		if(passwordValidity(PassWord) != 9) {
			valid = false;
			out.print("<h1> The Password is weak, try a strong one with uppercase, symbols and numbers.</h1>");
		}
		if(!PassWord.equals(CPassword)) {
			valid = false;
			out.print("<h1> The Confirm password is different from the original one</h1>");
		}
		if(usernameExists(Rec_Email)) {
			valid = false;
			out.print("<h1> The recovery email is invalid, try a different one</h1>");
		}
		if(!dateValidity(DOB)) {
			valid = false;
			out.print("<h1> The given date is not a valid date</h1>");
		}
		if(!("MALE".equals(gender.toUpperCase()) || "FEMALE".equals(gender.toUpperCase()))) {
			valid = false;
			out.print("<h1> !! Invalid gender !! </h1>");
		}
		
		if(valid) {
			tuple.add(UserName);
			tuple.add(FirstName);
			tuple.add(LastName);
			tuple.add(Encryption.encrypt(PassWord));
			tuple.add(Rec_Email);
			tuple.add(DOB);
			tuple.add(gender);
			
			int res = insertRecord(tuple);
			
			if(res > 0) {
				out.print("<h1 style=\"color : green\"> Registration Successfull :) </h1>");
			}
		}
		else {
			out.print("<h1 style=\"color : red\"> Registration Failed :( </h1>");
		}
		
		out.print("<form action=\"Main.jsp\"> <button type=\"submit\">Go to Home</button></form>");
		
		out.print("</body> </html>");
		
		out.flush();
		out.close();
	}

}
