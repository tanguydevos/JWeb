<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="sql.SqlManager" %>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>JWeb - Home</title>
	<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
	<%
		SqlManager.init();
	%>	
	<%@ include file="pages/header.jsp" %>
	<div id="page">
		<div class="error">
			<div class="container">
			<p>
			<% 
				String msg = (String)request.getAttribute("error");
				if (msg != null)
					out.print(msg);
			%>
			</p>
			</div>
		</div>
		<div class="container">
		<div class="area">
		<h1>Register</h1>
		<form id="register" method="post" action="inscription">
			<input type="text" class="regular" name="firstname" placeholder="Firstname" required/>
			<input type="text" class="regular" name="lastname" placeholder="Lastname" required/>
			<input type="email" name="email" placeholder="Email" required/>
			<input type="password" class="regular" name="password" placeholder="Password" required/>
			<input type="password" class="regular" name="validPassword" placeholder="Confirm" required/>
			<p id="newsletter">Subscribe to newsletter</p>
			<div class="slideTwo">	
				<input type="checkbox" value="true" id="slideTwo" name="newsletter" />
				<label for="slideTwo"></label>
			</div>
			<input class="submit" type="Submit" value="Submit">
		</form>
		</div>
		</div>
	</div>
	<%@ include file="pages/footer.jsp" %>
</body>
</html>
