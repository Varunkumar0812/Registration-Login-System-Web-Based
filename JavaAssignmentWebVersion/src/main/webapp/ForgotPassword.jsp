<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forgot Password Page</title>

<link rel="stylesheet" href="ForgotPasswordStyles.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Comme&family=Noto+Sans:wght@500&family=Roboto+Slab&family=Slabo+27px&display=swap" rel="stylesheet">
</head>
<body>

<form action="ForgotPasswordServlet" method="post">
<div class="center-box"> 
    <div class="center-top">
        <div id="heading">Forgot Password</div>
    </div>
    <div class="center-middle">
        <div id="uname-text">Username</div>
        <input type="text" name="uname" id="uname"/>
        
        <div id="uname-text">Recovery email</div>
        <input type="text" name="rec_email" id="uname"/>
        
        <div id="uname-text">New Password</div>
        <input type="password" name="pword" id="uname" style="font-family : Verdana;letter-spacing : 0.125em;"/>
    </div>
        
    <div class="center-bottom">
        <div style="text-align : center; padding-top : 25px;">
            <input type="submit" name="SUBMIT" id="submit-but"/>
        </div>
    </div>
</div>


</form>

</body>
</html>