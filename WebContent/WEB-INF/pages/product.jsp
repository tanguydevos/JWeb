<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Product"%>
<html>
<head>
<meta charset="UTF-8">
<title>JWeb - The SUPER product !</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
	<%@ include file="header.jsp"%>
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
						Product copy = Product.getProduct(1);
						if (copy == null) {
							out.println("<p>No product in database</p>");
						}
					%>
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
			<h1>The SUPER Product !</h1>
				<%
					if (copy != null) {
						String	name = copy.getName();
						String 	description = copy.getDescription();
						int		price = copy.getPrice();
						String	picture = copy.getPhoto();
						out.println("<img id=\"product\" src=\"" + picture + "\"\">");
						out.println("<h2 id=\"product-name\">" + name + "</h2>");
						out.println("<p id=\"price\">" + price + " € only</p>");
						out.println("<p>" + description + "</p>");
					}
				%>
				<%@ include file="review.jsp"%>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>