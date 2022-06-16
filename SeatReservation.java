package com.simplilearn;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "seat_reservation")
public class SeatReservation {
	@Id
    @Column(name = "id")
    private int id;
	
	@Column(name = "seat_number")
    private String seatNumber;
 
    @Column(name = "flight_id")
    private int fid;
 
    @Column(name = "booking_id")
    private int bookingID;
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}
}
