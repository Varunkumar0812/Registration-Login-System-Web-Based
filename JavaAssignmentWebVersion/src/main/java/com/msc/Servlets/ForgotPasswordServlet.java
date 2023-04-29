package com.msc.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ForgotPasswordServlet
 */
@WebServlet("/ForgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected boolean forgotValidity(String username, String recEmail) {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("uname");
		String recEmail = request.getParameter("rec_email");
		String password = request.getParameter("pword");
		
		PrintWriter out = response.getWriter(); 
		
		out.print("<html> <head> <title> Forgot Password Result Page </title> </head> <body>");
				
		if(forgotValidity(username, recEmail) && RegisterServlet.passwordValidity(password) == 9) {
			try {
	    		Class.forName("com.mysql.cj.jdbc.Driver");
	    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sample1", "root", "Tvarun@0812");
	    		PreparedStatement stmt = con.prepareStatement("UPDATE Users SET password=? WHERE username=?");
	    		
	    		stmt.setString(1, Encryption.encrypt(password));
	    		stmt.setString(2, username);
	    		
	    		int count = stmt.executeUpdate();
	    		
	    		if(count >= 1) {
	    			out.print("<h1 style=\"color : green\"> Password updated successfully :)</h1>");
	    		}
	    		else {
	    			out.print("<h1 style=\"color : red\"> Password updation failed :(</h1>");
	    		}
	    		
	    		con.close();
	     	}
	    	catch(Exception e) {
	    		System.out.println(e);
	    	}
			
			out.print("</body> </html>");

		}
		else {
			out.print("<h1 style=\"color : red\"> Password updation failed :(</h1>");
		}
		
		out.print("<form action=\"Main.jsp\"> <button type=\"submit\">Go to Home</button></form>");
		out.flush();
		out.close();
	}

}
