package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

public class Inscription extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	* POST
	* @param request (firstname, lastname, email, password, validPassword, newsletter)
	* @param response (Attribute message or redirection)
	* Add a new user to database, check existing email before
	* Success = user is registered and redirected to logged page
	* Error = display an error message 
	*/		
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String validPassword = request.getParameter("validPassword");
		String newsletter = request.getParameter("newsletter");
				
		if (newsletter == null)
			newsletter = "false";
		if (firstname == "" || lastname == "" || email == "" || password == "" || password.equals(validPassword) == false) {
			request.setAttribute("error", "Registration form isn't valid");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/index.jsp");
		    view.forward(request, response); 
		}
		if (User.checkUserMail(email) == true) {
			User.createUser(firstname, lastname, email, password, newsletter);
			int id = User.connectUser(email, password);
			if (id != -1) {
				HttpSession session = request.getSession();
				session.setAttribute("idUser", id);
				session.setAttribute("admin", false);
			} else {
				request.setAttribute("error", "Impossible to connect user to database");
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/index.jsp");
			    view.forward(request, response); 
			}
			response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
	        response.setHeader("Location", "/JWeb/logged");
		} else {
			request.setAttribute("error", "Email already exists");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/index.jsp");
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