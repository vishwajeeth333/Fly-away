package com.simplilearn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProcessPlacesList
 */
public class ProcessPlacesList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessPlacesList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AppMain appMain = new AppMain();
		
		PrintWriter writer = response.getWriter();
		// build HTML code
        String htmlRespone = "<html>";
        htmlRespone += "<h2>List Of Arrival Places";
        List<String> list = appMain.processArrival();
        for(int i=0; i < list.size(); i++) {
        	htmlRespone += "<h3>"+list.get(i) + "<br>";
        }
        
        htmlRespone += "<h2>List Of Departure Places";
        list = appMain.processDeparture();
        for(int i=0; i < list.size(); i++) {
        	htmlRespone += "<h3>"+list.get(i) + "<br>";
        }
        
        htmlRespone += "<h3><a href='ChangePasswordServlet'>Change Password</a><br/>";
        htmlRespone += "<h3><a href='ProcessPlacesList'>List of Source annd Destination</a><br/>";
        htmlRespone += "<h3><a href='AirplaneList'>List of Airlines</a><br/>";
        htmlRespone += "<h3><a href='AirplaneDetailList'>List of Airlines with details</a><br/>";
        htmlRespone += "</html>";
        writer.println(htmlRespone);
		
		
	}

}
