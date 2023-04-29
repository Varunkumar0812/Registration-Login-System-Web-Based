<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration page</title>

<link rel="stylesheet" href="RegisterStyles.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Comme&family=Noto+Sans:wght@500&family=Roboto+Slab&family=Slabo+27px&display=swap" rel="stylesheet">

</head>
<body>

<form action="RegisterServlet" method="post">

<div class="center-box">
	<div class="heading-out">
		<div id="heading">Registration</div>

		<div class="question"><a id="question-text">Already have an account ? </a><a id="question-link" href="Login.jsp">Sign In</a></div>
	</div>

	<div class="center-box-in">
			<div>
				<div id="uname-text">Username : </div>
				<input type="text" name="uname" id="uname"/>
			</div>
			
			<div>
				<div id="uname-text">First Name : </div>
				<input type="text" name="first_name" id="uname"/>
			</div>
			
			<div>
				<div id="uname-text">Last Name : </div>
				<input type="text" name="last_name" id="uname"/>
			</div>
			
			<div>
				<div id="uname-text">Password : </div>
				<input type="password" name="pword" id="pword"/>
			</div>
			
			<div>
				<div id="uname-text">Confirm Password : </div>
				<input type="password" name="cpword" id="pword"/>
			</div>
			
			<div>
				<div id="uname-text">Recovery email : </div>
				<input type="text" name="rec_email" id="uname"/>
			</div>
			
			<div>
				<div id="uname-text">DOB (yyyy-mm-dd) : </div>
				<input type="text" name="dob" id="uname"/>
			</div>
			
			<div>
				<div id="uname-text">Gender : </div>
				<input type="text" name="gender" id="uname"/>
			</div>
		</div>

		<div id="bottom-box">
			<div id="buttons-out">
				<input type="reset" name="RESET" id="reset-but"/>
				<input type="submit" name="SUBMIT" id="submit-but"/>
			</div>	
		</div>
</div>

</form>

</body>
</html>