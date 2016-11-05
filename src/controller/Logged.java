package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logged extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * GET
	 * @param request (get.Session of idUser)
	 * @param response (url where user is redirected)
	 * Simple URL redirection to protect get access to this page.
	 * idUser exists : redirection to logged page
	 * idUser doesn't exists : redirection to visitor's home.
	 */	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		Object idUser = session.getAttribute("idUser");
		if (idUser == null) {
			response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
	        response.setHeader("Location", "/JWeb/");		
		}
		else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/pages/logged.jsp");
		    view.forward(request, response);
		}
	}
}
