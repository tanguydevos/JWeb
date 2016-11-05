<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Review"%>
<%@page import="model.User"%>
<%@page import="org.json.*"%>
<h2 class="h2-reviews">Reviews</h2>
<div id="reviews">
	<%
	 	String jsonObject = Review.getReviewByProductId(1);
		JSONArray obj = new JSONArray(jsonObject);
	    for (int id = 0; id < obj.length(); id++)
		{
	    	int ReviewId = obj.getJSONObject(id).getInt("id");
	    	int UserId = obj.getJSONObject(id).getInt("idUser");
	    	int stars = obj.getJSONObject(id).getInt("stars");
	    	String review = obj.getJSONObject(id).getString("review");
		    String date = obj.getJSONObject(id).getString("date");
		    if (id % 2 == 0)
		    	out.println("<div class=\"area-list\">");
		    else
		    	out.println("<div class=\"area-list altern\">");
		    out.println("<h2>" + User.getUserNameById(UserId) + "</h2>");
		    out.println("<p class=\"date\">Posté le : " + date + "</p>");
		    out.println("<p class=\"date\">" + stars + " stars</p>");
		    out.println("<p class=\"description\">" + review + "</p>");
		    out.println("</div>");
		}
	%>
	<h2 class="h2-reviews">Add a review</h2>
	<form id="sendreviews" method="post" action="review">
		<input type="hidden" value="<% out.println(session.getAttribute("idUser")); %>" name="UserId">
		<label>Product note </label>
		<select name="stars">
			<option value="1">1 - It sucks !</option>
			<option value="2">2 - Can be better</option>
			<option value="3">3 - Not bad</option>
			<option value="4">4 - Good</option>
			<option selected value="5">5 - Excellent</option>
		</select>
		<label>Your review</label>
		<textarea name="review" required></textarea>
		<input class="submit" type="submit" value="Send">
	</form>
</div>