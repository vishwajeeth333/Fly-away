package com.simplilearn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookingConfirmed
 */
public class BookingConfirmed extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingConfirmed() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		int id = Integer.valueOf(request.getParameter("id"));
//		String fid = request.getParameter("fid");
		String fname = request.getParameter("first_name");
		String lname = request.getParameter("surname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		System.out.println("id: "+id);
		System.out.println("fname: "+fname);
		
		PrintWriter writer = response.getWriter();
 		// build HTML code
         String htmlRespone = "<html>";
         htmlRespone += "<form action='BookingSuccess' method='post'>";
		AppMain appMain = new AppMain();
		List<Object[]> list = appMain.checkAvailableSeat(id);
		int count = 0;
		if(list.size()!=0) {
			for(int i=0; i< list.size(); i++) {
				Object[] row = list.get(i);
				if(Integer.valueOf(row[3].toString()) == 0) {
					if(count == 0)
						htmlRespone += "Please select seat for booking in airline<br><br>";
					count++;
					
					htmlRespone += "<input type='radio' id='seatNumber' name='seatNumber' value="+row[1];
					
					if(count == 1)
						htmlRespone += " checked>";
					else
						htmlRespone += " >";
					htmlRespone += "<label for='Time'>" + row[1] + "</label><br>";
				}
			}
		}
		
		if(count == 0 ) {
			htmlRespone += "<h1>No Seat Number Available";
		} else {
			htmlRespone += "<input type='hidden' name='fid' id='fid' value="+id+" />";
			htmlRespone += "<input type='hidden' name='fname' id='fname' value="+fname+" />";
			htmlRespone += "<input type='hidden' name='lname' id='lname' value="+lname+" />";
			htmlRespone += "<input type='hidden' name='email' id='email' value="+email+" />";
			htmlRespone += "<input type='hidden' name='phone' id='phone' value="+phone+" />";
			htmlRespone += "<br><input type='submit' value='Go To Seat Selection and Payment Gateway'>";	
		}
        
		
//		htmlRespone += "<input type='radio' id="+row[0] +"name='id' value="+row[0];
		
		
		htmlRespone += "</html>";
		writer.println(htmlRespone);
	}
}
