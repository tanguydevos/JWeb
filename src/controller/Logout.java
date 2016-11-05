package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * GET.
	 * Remove idUser from session when user logout
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			HttpSession session = request.getSession();
			session.removeAttribute("idUser");
			response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
	        response.setHeader("Location", "/JWeb/");
	}
}