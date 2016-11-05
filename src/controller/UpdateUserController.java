package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

public class UpdateUserController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	* POST
	* @param request (newsletter, admin, delete)
	* @param response (Attribute message)
	* Update users details like newsletter, admin privileges or deletion
	* Success = do the right action ordered by admin and display a success message
	* Error = Do nothing
	*/	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String[] listNewsletter = request.getParameterValues("newsletter");
		String[] listAdmin = request.getParameterValues("admin");
		String[] listUserDelete = request.getParameterValues("delete");
		
		User.userUnsetNewsletter();
		User.userUnsetAdmin();
		
		if (listNewsletter != null){
			for (int i = 0; i < listNewsletter.length; i++) {
				Integer id = Integer.valueOf(listNewsletter[i]);
				User.userSetNewsletter(id);
			}
		}
		
		if (listAdmin != null){
			for (int i = 0; i < listAdmin.length; i++) {
				Integer id = Integer.valueOf(listAdmin[i]);
				User.userSetAdmin(id);
			}
		}
		
		if (listUserDelete != null){
			for (int i = 0; i < listUserDelete.length; i++) {
				Integer id = Integer.valueOf(listUserDelete[i]);
				User.userDelete(id);
			}
		}
		
		request.setAttribute("success", "Your changes have been successfully saved.");
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/user.jsp");
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