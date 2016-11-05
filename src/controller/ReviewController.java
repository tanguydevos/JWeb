package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Review;

public class ReviewController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	* POST
	* @param request (title, description)
	* @param response (Attribute message)
	* Add a news to database
	* Success = add news, display success message
	* Error = display an error message 
	*/		
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String UserId = request.getParameter("UserId");
		String review = request.getParameter("review");
		String stars = request.getParameter("stars");
		if (Review.createReview(1, UserId, review, stars) == true)
			request.setAttribute("success", "Your review have been accepted.");
		else
			request.setAttribute("error", "Unable to reach database, try again.");			
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/pages/product.jsp");
	    view.forward(request, response);	
	}

	/**
	 * GET
	 * Avoid white page, URL redirection to home
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", "/JWeb/");		
	}

}