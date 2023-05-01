<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Success Page</title>
</head>
<body>
<form action="Main.jsp">
	<%  
		String FirstName = "";
		if(request.getAttribute("first_name") != null) {
			FirstName = (String) request.getAttribute("first_name");
		}
		
	%>

	<h1 style="color : green">Congratulation <%= FirstName %>, Registration Successfull :)</h1>
	<button>Go to Home</button>
</form>
</body>
</html>