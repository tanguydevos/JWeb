package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

public class Connexion extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * POST
	 * @param request (email and password from post form)
	 * @param response (url where user is redirected)
	 * Identify and connect the user to website
	 * Success : connected and redirected to logged home page
	 * Fail : not connected and redirected to home for visitors
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String email = request.getParameter("login");
		String password = request.getParameter("password");

		int id = User.connectUser(email, password);
		if (id != -1) {
			HttpSession session = request.getSession();
			session.setAttribute("idUser", id);
			session.setAttribute("admin", false);
			if (User.userIsAdmin(id) == true) {
				session.setAttribute("admin", true);
				response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
		        response.setHeader("Location", "/JWeb/logged");
			} else {
		        response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
		        response.setHeader("Location", "/JWeb/logged");
				
			}
		} else {
			request.setAttribute("error", "Error : Unknown user");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/index.jsp");
		    view.forward(request, response);	
		}
	}

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
	        response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
	        response.setHeader("Location", "/JWeb/logged");
		}
	}
}