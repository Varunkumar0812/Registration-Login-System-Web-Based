<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Success Page</title>
</head>
<body>
<%
	String username = "";
	if(request.getAttribute("UNAME") != null) {
		username = (String) request.getAttribute("UNAME");
	}
%>

	<h1 style="color:green"> Welcome, <%= username %>. You have sucessfully logged in.</h1>
	
	<form action="Main.jsp">
		<button>Go to Home</button>
	</form>
</body>
</html>