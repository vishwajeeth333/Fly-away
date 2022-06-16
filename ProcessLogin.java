package com.simplilearn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ProcessLogin
 */
public class ProcessLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ProcessLogin() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username + " : " + password);
		
		HttpSession session = request.getSession(true);
		AppMain appmain = new AppMain();
		String sessionUsername = (String)session.getAttribute("userName");
		System.out.println("sessionUsername : "+ sessionUsername);
		List fetchedData = appmain.checkCredentials(username, password);
		
		if(sessionUsername == null) {
		
			if(fetchedData.size() == 0) {
				System.out.println("login failed");
				request.setAttribute("errorMessage", "login Failed due to invalid credentials");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			} else {
				System.out.println("login success");
				session.setAttribute("userName", username);
				sessionUsername = session.getAttribute("userName").toString();
			}
		}
		
		if(sessionUsername != null && !sessionUsername.isEmpty()) {
			System.out.println("alkhjsdad;lm  v");
			System.out.println(Arrays.toString(fetchedData.toArray()));
			if(fetchedData.size() != 0) {
			Object[] manager = (Object[]) fetchedData.get(0);
			System.out.println(Arrays.toString(manager));
			
//			String Name = fetchedData.get(0).getName();
			String Name =(String) manager[1];
			session.setAttribute("name", Name);
			session.setAttribute("id", manager[0]);
			session.setAttribute("password", manager[3]);
			PrintWriter writer = response.getWriter();
			// build HTML code
	        String htmlRespone = "<html>";
	        htmlRespone += "<h2>Your username is: " + Name + "<br/>";
	        htmlRespone += "<h3><a href='ChangePasswordServlet'>Change Password</a><br/>";
	        htmlRespone += "<h3><a href='ProcessPlacesList'>List of Source annd Destination</a><br/>";
	        htmlRespone += "<h3><a href='AirplaneList'>List of Airlines</a><br/>";
	        htmlRespone += "<h3><a href='AirplaneDetailList'>List of Airlines with details</a><br/>";
	        htmlRespone += "</html>";
	         
	        // return response
	        writer.println(htmlRespone);
			}
		}
			
	}
		
		

}
