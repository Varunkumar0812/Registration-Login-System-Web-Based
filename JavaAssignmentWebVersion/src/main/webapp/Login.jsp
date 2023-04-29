<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Comme&family=Noto+Sans:wght@500&family=Roboto+Slab&family=Slabo+27px&display=swap" rel="stylesheet">
<link rel="stylesheet" href="LoginStyles.css">
</head>
<body>

<div class="center-box">

    <div class="center-left">
        <div id="login-title">Login</div>

        <div id="go-to-home"><a href="Main.jsp">Go to Home</a></div>
        <div id="forgot-password"><a href="ForgotPassword.jsp">Forgot Password</a></div>

        <div id="question"><a>Doesn't have an account ? </a><a id="sign-up-link" href="Register.jsp">Sign Up</a></div>
    </div>

    <div class="center-right">

        <div class="center-right-in">
            <form action="LoginServlet" method="post">
                <div>
                    <div id="uname-text">Username : </div>
                    <input type="text" name ="uname" id="uname"/>
                </div>
            
                <div>
                    <div id="pword-text">Password : </div>
                    <input type="password" name="pword" id="pword"/>
                </div>
                
                <div>
                    <input type="submit" name="SUBMIT" id="submit-but" />
                    <input type="reset" name="RESET" id="reset-but"/>
                </div>
            </form>

        </div>

        
    </div>

</div>

</body>
</html>