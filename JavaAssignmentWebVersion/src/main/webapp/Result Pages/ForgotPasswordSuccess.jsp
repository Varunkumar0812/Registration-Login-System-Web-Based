<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forgot Password Success</title>
</head>
<body>
	<form action="Main.jsp">
		<%
			String username = "";
			if(request.getAttribute("Uname") != null) {
				username = (String) request.getAttribute("Uname");
			}
		%>
		<h1 style="color : green">Congratulations <%= username %>, password updation successfull :)</h1>
		<button>Go to Home</button>
	</form>
</body>
</html>