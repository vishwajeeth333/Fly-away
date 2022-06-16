package com.simplilearn;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.management.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.transform.Transformers;


public class AppMain {
	static AdminTable userObj;
    static Session sessionObj;
    static SessionFactory sessionFactoryObj;
    
	private static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");
 
        // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 
 
        // Creating Hibernate SessionFactory Instance
        sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
        return sessionFactoryObj;
    }
	
	public List checkCredentials(String username, String password) {
		System.out.println(".......Hibernate Maven Example.......\n");
		List fetchedData = null;
        try {

        	Session session = buildSessionFactory().openSession();
            
            SQLQuery query = session.createSQLQuery("select * from AdminDB where username =:username and password =:password");
            query.setParameter("username", username);
            query.setParameter("password", password);
            fetchedData = query.list();
            System.out.println("555555555555555555");
            
        } catch(Exception sqlException) {

        	sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
        
        return fetchedData;
	}
	
	public void changePassword(AdminTable admin, String newPassword) {
		Session session = buildSessionFactory().openSession();
		try {
			
			org.hibernate.Transaction tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery("select * from AdminDB where username =:username");
			AdminTable employee = (AdminTable)session.get(AdminTable.class, admin.getId());
			System.out.println("Employee Table");
			System.out.println(employee.toString());
			employee.setPassword(newPassword);
			System.out.println(employee.toString());
			session.saveOrUpdate(employee);
			tx.commit();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			session.close();
		}
		
	}
	
	public List<Object[]> AirplaneList() {
		Session session = buildSessionFactory().openSession();
		List<Object[]> rows = null;
		try {
			
			org.hibernate.Transaction tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery("select company, flighttype from FlightDetails");
			List fetchedData = query.list();

			// Prep work
			rows = query.list();
			for(Object[] row : rows){
				
				System.out.println(row[0] + " --> " +row[1]);
			}

		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			session.close();
		}
		return rows;
	}
	
	public List<String> processArrival() {
		Session session = buildSessionFactory().openSession();
		List<String> departure = new ArrayList<>();
		List<String> rows = null;
		List<Object[]> rows1 = null;
		
		try {
			
			org.hibernate.Transaction tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery("select acode from FlightDetails");
			List fetchedData = query.list();

			SQLQuery query1 = session.createSQLQuery("select * from LocationCode");
			List fetchedData1 = query.list();
			// Prep work
			rows = query.list();
			rows1 = query1.list();
			
			System.out.println("rows ==->" + rows);
			for(String row : rows){
			
				for(Object[] row1: rows1) {
					String s1 = row;
					String s2 = (String) row1[0];
					System.out.println("s1 " + s1 + " s2 "+ s2 + " row[1] "+ row1[1]);
					if(s1.equals(s2)) {
						departure.add((String)row1[1]);
					}
				}
//				System.out.println(row[0] + " --> " +row[1]);
			}

		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			session.close();
		}
		return departure;
	}
	
	public List<String> processDeparture() {
		Session session = buildSessionFactory().openSession();
		List<String> departure = new ArrayList<>();
		List<String> rows = null;
		List<Object[]> rows1 = null;
		
		try {
			
			org.hibernate.Transaction tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery("select dcode from FlightDetails");
			List fetchedData = query.list();

			SQLQuery query1 = session.createSQLQuery("select * from LocationCode");
			List fetchedData1 = query.list();
			// Prep work
			rows = query.list();
			rows1 = query1.list();
			for(String row : rows){
			
				for(Object[] row1: rows1) {
					String s1 = (String)row;
					String s2 = (String) row1[0];
					
					if(s1.equals(s2)) {
						departure.add((String)row1[1]);
					}
				}
//				System.out.println(row[0] + " --> " +row[1]);
			}

		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			session.close();
		}
		return departure;
	}
	
	public List<FlightDetails> processDetailAirlines() {
		Session session = buildSessionFactory().openSession();
		List<FlightDetails> departure = new ArrayList<>();
		List<Object[]> rows = null;
		
		try {
			
			org.hibernate.Transaction tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery("select * from FlightDetails");
			List fetchedData = query.list();
			
			
			rows = query.list();
			for(Object[] row : rows) {
				System.out.println("row3 ==-==> " + row[3]);
				FlightDetails flight = new FlightDetails();
				flight.setId(Integer.parseInt((row[0].toString())));
				flight.setDeparturecode(row[1].toString());
				flight.setArrivalcode(row[2].toString());
				SimpleDateFormat simple = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
//				String DateToStoreInDataBase= simple.format(row[3].toString());
//				Timestamp ts = Timestamp.valueOf(DateToStoreInDataBase);
				Timestamp ts1 = new Timestamp(Long.valueOf(row[3].toString())); 
				flight.setDtime(ts1);
//				Timestamp.valueOf((row[4].toString()))
				 
				Timestamp ts2 = new Timestamp(Long.valueOf(row[3].toString())); 
				flight.setAtime(ts2);
				flight.setFid(Integer.parseInt(row[5].toString()));
				flight.setCompany(row[6].toString());
				flight.setFlightype(row[7].toString());
				flight.setPrice(Integer.parseInt(row[8].toString()));
				
				departure.add(flight);
			}

		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			session.close();
		}
		return departure;
	}
	
	public List<Object[]> processFlightDetails(String dcode, String acode, Timestamp ts) {
		Session session = buildSessionFactory().openSession();
		List<Object[]> fetchedData = new ArrayList<>();
		try {
			SQLQuery query = session.createSQLQuery("select * from FlightDetails where acode=:acode and dcode=:dcode");
//					+ " and dtime >= :atime");
			System.out.println(acode.toUpperCase());
			System.out.println(dcode.toUpperCase());
//			System.out.println(ts.getTime()/1000);
			query.setParameter("acode", acode.toUpperCase());
            query.setParameter("dcode", dcode.toUpperCase());
//            query.setParameter("atime", new java.sql.Timestamp(ts.getTime()/1000));
            
			fetchedData = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fetchedData;
	}
	
	public List<Object[]> checkAvailableSeat(int id) {
		Session session = buildSessionFactory().openSession();
		List<Object[]> fetchedData = new ArrayList<>();
		try {
			SQLQuery query = session.createSQLQuery("select * from seat_reservation where flight_id=:id");
//					+ " and dtime >= :atime");
			System.out.println(id);
			query.setParameter("id", id);
            
			fetchedData = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fetchedData;
	}
	
	public int personalInfo() {
		Session session = buildSessionFactory().openSession();
		int id1 = 0;
		List<Object[]> fetchedData = new ArrayList<>();
		try {
			SQLQuery query1 = session.createSQLQuery("select * from personal_info");
			fetchedData = query1.list();
			id1 = fetchedData.size();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return id1;
	}
	
	public void ConfirmBooking(PersonalData person, int id, String seatNumber) {
		Session session = buildSessionFactory().openSession();
		List<Object[]> fetchedData = new ArrayList<>();
		try {
			org.hibernate.Transaction tx1 = session.beginTransaction();
			session.save(person);
//			session.getTransaction().commit();
			tx1.commit();
			
			org.hibernate.Transaction tx2 = session.beginTransaction();
			
			int id1 = personalInfo();
			System.out.println("id: ->" +id);
			System.out.println("seat : ->"  +seatNumber);
			
			SQLQuery query = session.createSQLQuery("select * from seat_reservation where flight_id=:id and seat_number=:seatNumber");

			query.setParameter("id", id);
			query.setParameter("seatNumber", seatNumber);
			
			
			fetchedData = query.list();
			System.out.println("fetchedData size : ->"  +fetchedData.size());
			
			Object[] row = fetchedData.get(0);
			
			SeatReservation seat = new SeatReservation();
			seat.setId(Integer.valueOf(row[0].toString()));
			System.out.println("row[0].toString() : ->"  +row[0].toString());
			seat.setSeatNumber(row[1].toString());
			System.out.println("row[1].toString() : ->"  +row[1].toString());
			seat.setFid(Integer.valueOf(row[2].toString()));
			System.out.println("row[2].toString() : ->"  +row[2].toString());
			seat.setBookingID(id1);
			System.out.println("id1 : ->"  +id1);
			
			session.update(seat);
			tx2.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//		return fetchedData;
	}
}
