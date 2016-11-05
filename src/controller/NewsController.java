package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.News;

public class NewsController extends HttpServlet {

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
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		if (News.createNews(title, description) == true)
			request.setAttribute("success", "Your news have been accepted.");
		else
			request.setAttribute("error", "Unable to reach database, try again.");			
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/manageNews.jsp");
	    view.forward(request, response);	
	}

	/**
	 * GET
	 * Check if user have admin privileges before display the page
	 * Success : display manageNews page
	 * Fail : redirection to visitor's home
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			HttpSession session = request.getSession();
			Boolean admin = ((Boolean) session.getAttribute("admin")).booleanValue();
			if (admin == false) {
				response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
		        response.setHeader("Location", "/JWeb/");
			}
			else {
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/manageNews.jsp");
			    view.forward(request, response);	
			}
	}

}