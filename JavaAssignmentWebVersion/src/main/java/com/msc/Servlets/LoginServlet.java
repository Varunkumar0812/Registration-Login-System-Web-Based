package com.msc.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String UserName = request.getParameter("uname");
		String PassWord = request.getParameter("pword");
		
		boolean valid = false;
		
		PrintWriter out = response.getWriter();
		out.print("<html> <head> <title> Login Result Page </title> </head> <body>");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sample1", "root", "Tvarun@0812");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT username, password FROM Users");
			
			while(rs.next()) {
				
				if(rs.getString(1).equalsIgnoreCase(UserName) && rs.getString(2).equals(Encryption.encrypt(PassWord))) {
					valid = true;
				}
			}
			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		if(valid) {
			out.print("<h1 style=\"color : green\"> Login Successful :)</h1></body></html>");
		}
		else {
			out.print("<h1 style=\"color : red\"> Login Failed :(</h1></body></html>");
		}
		
		out.print("<form action=\"Main.jsp\"> <button type=\"submit\">Go to Home</button></form>");
		
		out.flush();
		out.close();
	}

}
