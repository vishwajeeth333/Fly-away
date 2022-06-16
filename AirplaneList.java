package com.simplilearn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AirplaneList
 */
public class AirplaneList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AirplaneList() {
        super();
        // TODO Auto-generated constructor stub
    }


    /**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AppMain appmain = new AppMain();
		List<Object[]> rows = appmain.AirplaneList();
		 String htmlRespone = "<html>";
	        
		 PrintWriter writer = response.getWriter();
		 htmlRespone += "<table><tr>";
	        htmlRespone += "<th>Company</th><th>Flight Type</th></tr>";
		 for(Object[] row : rows){
			 htmlRespone += "<tr>";
	        	htmlRespone += "<td>" + row[0] + "</td>";
	        	htmlRespone += "<td>" + row[1] + "</td>";
			 htmlRespone += "</tr>";
		 }
		 
		 htmlRespone += "</tr></table>";
		 	htmlRespone += "<h3><a href='ChangePasswordServlet'>Change Password</a><br/>";
	        htmlRespone += "<h3><a href='ProcessPlacesList'>List of Source annd Destination</a><br/>";
	        htmlRespone += "<h3><a href='AirplaneList'>List of Airlines</a><br/>";
	        htmlRespone += "<h3><a href='AirplaneDetailList'>List of Airlines with details</a><br/>";
	        htmlRespone += "</html>";
	        writer.println(htmlRespone);
	}

}
