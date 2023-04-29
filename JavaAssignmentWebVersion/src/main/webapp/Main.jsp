<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Main Page</title>

<link rel="stylesheet" href="MainStyles.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Roboto+Slab&family=Slabo+27px&display=swap" rel="stylesheet"></head>
<body>

<div id="center-box">
    
    <div id="heading"> Registration and Login Page Simulation</div>
    <div id="heading-small">- A Java Project -</div>
	<form action="Register.jsp">
        <div id="sign-up-out" style="margin-top : 50px">
            <button type="submit" id="sign-up-button">Sign Up</button>
        </div>
	</form>
	
	<form action="Login.jsp">
        <div id="sign-in-out">
            <button type="submit" id="sign-in-button">Sign In</button>
        </div>
	</form>

</div>

</body>
</html>