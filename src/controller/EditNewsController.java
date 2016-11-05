package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.News;

public class EditNewsController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	* POST
	* @param request (News id, title and description)
	* @param response (Attribute message)
	* Check the id of news to edit and if the id exists in database, replace fields with form data (title and description)
	* Return a success or error message to display
	*/		
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String id = request.getParameter("id");
		if (id == null || id == "-1") {
			request.setAttribute("error", "Unable to edit selected news.");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/manageNews.jsp");
		    view.forward(request, response);	
		}
		else {
			String title = request.getParameter("title");
			String description = request.getParameter("description");				
			News.editNews(Integer.parseInt(id), title, description);
			request.setAttribute("success", "News has been successfully edited.");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/manageNews.jsp");
		    view.forward(request, response);
		}
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