package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
		
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
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/user.jsp");
			    view.forward(request, response);	
			}
	}

}