package com.msc.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.msc.DbFiles.DBMSOperations;

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
		
		RequestDispatcher obj = null;
				
		if(DBMSOperations.loginValidity(UserName, PassWord)) {
			obj = request.getRequestDispatcher("/Result Pages/LoginSuccess.jsp");
			request.setAttribute("UNAME", UserName);
			obj.forward(request, response);
		}
		else {
			obj = request.getRequestDispatcher("/Result Pages/LoginFailure.jsp");
		}	
	}
}
