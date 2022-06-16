package com.simplilearn;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ChangePasswordServlet
 */
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String sessionUsername = (String)session.getAttribute("userName");
		System.out.println("sessionUsername --> "+sessionUsername);
		if(sessionUsername != null && !sessionUsername.isEmpty()) {
			PrintWriter writer = response.getWriter();
			// build HTML code
	        String htmlRespone = "<html>";
	        htmlRespone += "<form action='ChangePassword' method='post'>";
	        htmlRespone += "<label for='newpassword'>Enter New Password:</label>";
	        htmlRespone += "<input type='password' id='newpassword' name='newpassword'><br>";
	        htmlRespone += "<input type='submit' value='Submit'>";
	        htmlRespone += "</html>";
	        writer.println(htmlRespone);
		}
	}

}
