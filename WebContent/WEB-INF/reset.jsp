<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="sql.SqlManager" %>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>JWeb - Reset project</title>
	<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
	<% 
		SqlManager.init();
		SqlManager.resetDatabase(); 
	%>
	<div id="page">
		<div class="container">
			<div class="area">
				<h1>JWeb project has been reset.</h1>
				<a href="/JWeb/" title="Home">Go to home page</a>
			</div>
		</div>
	</div>
	<%@ include file="pages/footer.jsp" %>
</body>
</html>