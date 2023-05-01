<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Failure Page</title>
</head>
<body>
<% 
	String errMes = "hello<br>hello<br>";
	if(request.getAttribute("Error messages") != null) {
		errMes = (String) request.getAttribute("Error messages");
	}
%>
<form action="Main.jsp">
	<h1><%= errMes %></h1>
	
	<h1 style="color : red">Registration failed :(</h1>
	<button>Go to Home</button>
</form>


</body>
</html>