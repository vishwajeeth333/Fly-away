package com.simplilearn;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProcessAirlinesTicket
 */
public class ProcessAirlinesTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessAirlinesTicket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String dcode = request.getParameter("from");
		String acode = request.getParameter("toward");
		String date = request.getParameter("dateOfJourney");
//		System.out.println(date);
//		 Date date1 = new Date(Long.parseLong(date));
		java.sql.Timestamp ts = null;
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
         try {
        	 System.out.println(date);
        	 ts = new Timestamp(((java.util.Date)df.parse(date)).getTime());
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
         
         PrintWriter writer = response.getWriter();
 		// build HTML code
         String htmlRespone = "<html>";
         htmlRespone += "<form action='BookingConfirmed' method='post'>";
		AppMain appMain = new AppMain();
		List<Object[]> list = appMain.processFlightDetails(dcode, acode, ts);
		System.out.println("list::size"+ list.size());
		int count = 0;
		if(list.size()!=0) {
			for(int i=0; i< list.size(); i++) {
				Object[] row = list.get(i);
				if(Long.valueOf(row[3].toString()) >= ts.getTime()/1000) {
					count++;
					
					htmlRespone += "<label for='Time'>Flight Time/Price</label><br>";
					htmlRespone += "<input type='radio' id='id' name='id' value="+row[0];
					if(i == 0)
						htmlRespone += " checked>";
					else
						htmlRespone += " >";
						
						 Date date1 = new Date(ts.getTime()); 
						 String pattern = "dd-MM-yyyy hh:mm:ss";
						 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
						 String date2 = simpleDateFormat.format(date1);
			                System.out.println(date2);                     
						htmlRespone +="<label for='Time'>"+ date2 + "/"+ row[8] +"</label><br>";
						htmlRespone += "<input type='hidden' name='fid' id='orderNumber' value="+row[5]+"/>";
							
				}
			}
		}
		
		if(count > 0) {
			htmlRespone += "<br><label for='Time'>First Name:</label>";
			htmlRespone += "<input type='text' id='first_name' name='first_name'><br>";
			htmlRespone += "<label for='Time'>Surname:</label>";
			htmlRespone += "<input type='text' id='surname' name='surname'><br>";
			htmlRespone += "<label for='Time'>Email:</label>";
			htmlRespone += "<input type='text' id='email' name='email'><br>";
			htmlRespone += "<label for='Time'>Phone:</label>";
			htmlRespone += "<input type='text' id='phone' name='phone'><br><br>";
			htmlRespone += "<input type='submit' value='Submit'>";
		} else {
			htmlRespone += "<h1> Sorry, No Flight Found for given Date and Time.";
		}
		
		htmlRespone += "</html>";
        writer.println(htmlRespone);
	}

}
