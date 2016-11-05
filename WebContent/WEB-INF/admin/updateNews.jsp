<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.News"%>
<%@page import="org.json.*"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>JWeb - Edit news</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
	<%@ include file="../pages/header.jsp"%>
	<div id="page">
		<div class="container">
			<div class="area">
				<h1>Edit news</h1>
				<form id="sendnews" method="post" action="editNews">
					<label>Title</label>
					<input class="newstitle" type="text" name="title" value="<% out.print((String)request.getAttribute("title"));%>" required/>
					<label>Content</label>
					<textarea name="description" required><% out.print((String)request.getAttribute("description"));%></textarea>
					<input type="hidden" name="id" value="<% out.print((String)request.getAttribute("id"));%>" />
					<input class="submit" type="submit" value="Save">
				</form>
				<h2 class="delete-h2">Delete this news ?</h2>
				<form class="delete-form" method="post" action="deleteNews">
				<input type="hidden" name="id" value="<% out.print((String)request.getAttribute("id"));%>" />
				<input class="submit" type="submit" value="Delete">
				</form>
			</div>
		</div>
	</div>
	<%@ include file="../pages/footer.jsp" %>
</body>
</html>