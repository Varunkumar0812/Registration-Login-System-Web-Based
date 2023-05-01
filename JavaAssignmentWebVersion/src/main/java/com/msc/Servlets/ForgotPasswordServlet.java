package com.msc.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.msc.DbFiles.DBMSOperations;
import com.msc.DbFiles.Validation;

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
    
   

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("uname");
		String recEmail = request.getParameter("rec_email");
		String password = request.getParameter("pword");
		
		int count = 0;
		
		RequestDispatcher obj = null;
		
		if(DBMSOperations.forgotValidity(username, recEmail) && Validation.passwordValidity(password) == 9) {
			count = DBMSOperations.updatePassword(username, password);
			
			if(count >= 1) {
				obj = request.getRequestDispatcher("/Result Pages/ForgotPasswordSuccess.jsp");
				request.setAttribute("Uname", username);
				obj.forward(request, response);
			}
		}
		else {
			obj = request.getRequestDispatcher("/Result Pages/ForgotPasswordFailure");
			request.setAttribute("Uname", username);
			obj.forward(request, response);
		}

	}

}
