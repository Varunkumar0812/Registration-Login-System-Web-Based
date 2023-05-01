package com.msc.Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.msc.DbFiles.DBMSOperations;
import com.msc.DbFiles.Encryption;
import com.msc.DbFiles.Validation;

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
		
		boolean valid = true;
		
		ArrayList<String> tuple = new ArrayList<String>();
		String errMes = "";
		
		if(!Validation.usernameValidity(UserName)) {
			valid = false;
			errMes += "The username has invalid characters, please check<br>";
		}
		if(!Validation.usernameExists(UserName)) {
			valid = false;
			errMes += "The username already exists, try a different one<br>";
		}
		if(!Validation.nameValidity(FirstName)) {
			valid = false;
			errMes += "The First name has invalid characters, please check<br>";
		}
		if(!Validation.nameValidity(LastName)) {
			valid = false;
			errMes += "Last name has invalid characters, please check<br>";
		}
		if(Validation.passwordValidity(PassWord) != 9) {
			valid = false;
			errMes += "Password is weak, try a strong one with uppercase, symbols and numbers.<br>";
		}
		if(!PassWord.equals(CPassword)) {
			valid = false;
			errMes += "The Confirm password is different from the original one<br>";
		}
		if(Validation.usernameExists(Rec_Email)) {
			valid = false;
			errMes += "Recovery email is invalid, try a different one<br>";
		}
		if(!Validation.dateValidity(DOB)) {
			valid = false;
			errMes += "The given date is not a valid date<br>";
		}
		if(!("MALE".equals(gender.toUpperCase()) || "FEMALE".equals(gender.toUpperCase()))) {
			valid = false;
			errMes += "!! Invalid gender !!<br>";
		}
		
		RequestDispatcher obj = null;
		
		if(valid) {
			tuple.add(UserName);
			tuple.add(FirstName);
			tuple.add(LastName);
			tuple.add(Encryption.encrypt(PassWord));
			tuple.add(Rec_Email);
			tuple.add(DOB);
			tuple.add(gender);
			
			int res = DBMSOperations.insertRecord(tuple);
			
			if(res > 0) {
				obj = request.getRequestDispatcher("/Result Pages/RegisterSuccess.jsp");
				request.setAttribute("first_name", FirstName);
				obj.forward(request, response);
			}
		}
		else {
				obj = request.getRequestDispatcher("/Result Pages/RegisterFailure.jsp");
				request.setAttribute("Error messages", errMes);
				obj.forward(request, response);
		}
	}
}
