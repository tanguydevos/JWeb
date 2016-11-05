<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.News"%>
<%@page import="org.json.*"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>JWeb - Manage news</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
	<%@ include file="../pages/header.jsp"%>
	<div id="page">
		<div class="success">
			<div class="container">
				<p>
					<% 
						String success = (String)request.getAttribute("success");
						if (success != null)
							out.print(success);
					%>
				</p>
			</div>
		</div>
		<div class="error">
			<div class="container">
				<p>
					<% 
						String error = (String)request.getAttribute("error");
						if (error != null)
							out.print(error);
					%>
				</p>
			</div>
		</div>
		<div class="container">
			<div class="area">
				<h1>Add news</h1>
				<form id="sendnews" method="post" action="news">
					<label>Title</label>
					<input class="newstitle" type="text" name="title" required/>
					<label>Content</label>
					<textarea name="description" required></textarea>
					<input class="submit" type="submit" value="Send">
				</form>
				</div>
				<h1>Update or delete news</h1>
				<form id="modifynews" method="post" action="updateNews">
					<select name="newsid">
					<option value="-1" selected disabled>Choose a news</option>
					<%
					 	String jsonObject = News.getNews();
						JSONArray obj = new JSONArray(jsonObject);
					    for (int id = 0; id < obj.length(); id++)
						{
					    	String title = obj.getJSONObject(id).getString("title");
					    	String newsId = obj.getJSONObject(id).getString("id");
						    out.println("<option value='"+ newsId +"'>" + title + "</option>");
						}
					%>				
					</select>
					<input type="submit" class="submit" value="Select"/>
				</form>
		</div>
	</div>
	<%@ include file="../pages/footer.jsp" %>
</body>
</html>