package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResetController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * GET
	 * Reset the database for a clean start
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		try {
			String auth = request.getParameter("auth");
			if (auth.equals("42") == true) {
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/reset.jsp");
				view.forward(request, response);
			}
			else {
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/index.jsp");
				view.forward(request, response);
			}
		}
		catch (NullPointerException e) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/index.jsp");
			view.forward(request, response);
		}
	}
}
