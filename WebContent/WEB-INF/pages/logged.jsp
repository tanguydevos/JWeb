<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@page import="model.News"%>
<%@page import="org.json.*"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/style.css" />
<title>JWeb - Welcome !</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div id="page">
		<div class="container">
			<div class="area">
				<h1>Welcome !</h1>
				<%
				 	String jsonObject = News.getNews();
					JSONArray obj = new JSONArray(jsonObject);
				    for (int id = 0; id < obj.length(); id++)
					{
				    	String title = obj.getJSONObject(id).getString("title");
					    String date = obj.getJSONObject(id).getString("date");
					    String description = obj.getJSONObject(id).getString("description");
					    if (id % 2 == 0)
					    	out.println("<div class=\"area-list\">");
					    else
					    	out.println("<div class=\"area-list altern\">");
					    out.println("<h2>" + title + "</h2>");
					    out.println("<p class=\"date\">Posté le : " + date + "</p>");
					    out.println("<p class=\"description\">" + description + "</p>");
					    out.println("</div>");
					}
				%>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>