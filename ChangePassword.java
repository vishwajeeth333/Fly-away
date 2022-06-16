package com.simplilearn;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ChangePassword
 */
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String sessionUsername = (String)session.getAttribute("userName");
		System.out.println("getter -->"+session.getAttribute("id"));
		int id = (Integer)session.getAttribute("id");
		String name = (String)session.getAttribute("name");
		String password = request.getParameter("newpassword");
		
		AdminTable admin = new AdminTable();
		admin.setId(id);
		admin.setName(name);
		admin.setPassword(password);
		admin.setUsername(sessionUsername);
		
		if(sessionUsername != null && !sessionUsername.isEmpty()) {
			AppMain appMain = new AppMain();
			appMain.changePassword(admin, password);
			PrintWriter writer = response.getWriter();
			// build HTML code
	        String htmlRespone = "<html>";
	        
	        htmlRespone += "Password Changed Successfull";
	        htmlRespone += "<h3><a href='ChangePasswordServlet'>Change Password</a><br/>";
	        htmlRespone += "<h3><a href='ProcessPlacesList'>List of Source annd Destination</a><br/>";
	        htmlRespone += "<h3><a href='AirplaneList'>List of Airlines</a><br/>";
	        htmlRespone += "<h3><a href='AirplaneDetailList'>List of Airlines with details</a><br/>";
	        htmlRespone += "</html>";
	        writer.println(htmlRespone);
		}
	}

}
