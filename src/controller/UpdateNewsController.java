package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.News;

public class UpdateNewsController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	* POST
	* @param request (newsid, id, title, description)
	* @param response (Attribute message or redirection)
	* Select a news by id to display it in an edit page
	* Success = get all news infos and send them to the edit page
	* Error = display an error message 
	*/		
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String id = request.getParameter("newsid");
		if (id == null || id == "-1") {
			request.setAttribute("error", "Unable to find selected news");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/manageNews.jsp");
		    view.forward(request, response);	
		}
		else {
			String [] newsArray = News.getNewsById(Integer.parseInt(id));			
			request.setAttribute("id", newsArray[0]);
			request.setAttribute("title", newsArray[1]);
			request.setAttribute("description", newsArray[2]);
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/updateNews.jsp");
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