<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>HTML5 Login Form with validation Example</title>
<style type="text/css">
<%@
include file="css/style.css" 
%>
</style>
</head>
<body>
	<p>
	<h3 style="color: 8B0000;"">Logged Out Succesfully!!!</h3>
	</p>

	<!-- partial:index.partial.html -->
	<div id="login-form-wrap">
		<h2>Learner's Academy Login</h2>
		<form id="login-form" method="post"
			action="<%=request.getContextPath()%>/login">
			<p>
				<input type="email" id="emailId" name="emailId"
					placeholder="Email Address" required=""><i
					class="validation"><span></span><span></span></i>
			</p>
			<p>
				<input type="text" id="password" name="password"
					placeholder="password" required=""><i class="validation"><span></span><span></span></i>
			</p>
			<p>
				<input type="submit" id="login" value="Login">
			</p>
		</form>
		<div id="create-account-wrap">
			<p>Access only for the admin!!</p>
		</div>
		<!--create-account-wrap-->
	</div>
	<!--login-form-wrap-->
	<!-- partial -->



</body>
</html>