package com.simplilearn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AirplaneDetailList
 */
public class AirplaneDetailList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AirplaneDetailList() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AppMain appMain = new AppMain();
		List<FlightDetails> list = appMain.processDetailAirlines();
		
		PrintWriter writer = response.getWriter();
		// build HTML code
        String htmlRespone = "<html>";
        htmlRespone += "<table><tr>";
        htmlRespone += "<th>id</th><th>Departure</th><th>Arrival</th><th>Departure Time</th><th>Arrival Time</th>";
        htmlRespone += "<th>Company</th><th>Flight Type</th><th>Price</th></tr>";
        for(int i=0; i < list.size(); i++) {
        	FlightDetails flight = list.get(i);
        	htmlRespone += "<tr>";
        	htmlRespone += "<td>" + flight.getId() + "</td>";
        	htmlRespone += "<td>" + flight.getDeparturecode() + "</td>";
        	htmlRespone += "<td>" + flight.getArrivalcode() + "</td>";
        	java.util.Date date = new java.util.Date(flight.getDtime().getTime());
        	String dateStr = new java.text.SimpleDateFormat("yyyy-MM-dd H:m:s").format(date);
        	java.util.Date date1 = new java.util.Date(flight.getDtime().getTime());
        	String dateStr1 = new java.text.SimpleDateFormat("yyyy-MM-dd H:m:s").format(date);
        	htmlRespone += "<td>" + dateStr + "</td>";
        	htmlRespone += "<td>" + dateStr1 + "</td>";
        	htmlRespone += "<td>" + flight.getCompany() + "</td>";
        	htmlRespone += "<td>" + flight.getFlightype() + "</td>";
        	htmlRespone += "<td>" + flight.getPrice() + "</td>";
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
