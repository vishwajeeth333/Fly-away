package com.simplilearn;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AdminDB")
public class FlightDetails {
	@Id
    @Column(name = "id")
    private int id;
	
	@Column(name = "dcode")
    private String dcode;
 
    @Column(name = "acode")
    private String acode;
 
    @Column(name = "dtime")
    private java.sql.Timestamp dtime;
    
    @Column(name = "atime")
    private java.sql.Timestamp atime;
    
    @Column(name = "fid")
    private int fid;
    
    @Column(name = "company")
    private String company;
    
    @Column(name = "flighttype")
    private String flighttype;
    
    @Column(name = "price")
    private int price;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeparturecode() {
		return dcode;
	}

	public void setDeparturecode(String departurecode) {
		this.dcode = departurecode;
	}

	public String getArrivalcode() {
		return acode;
	}

	public void setArrivalcode(String arrivalcode) {
		this.acode = arrivalcode;
	}

	public java.sql.Timestamp getDtime() {
		return dtime;
	}

	public void setDtime(java.sql.Timestamp dtime) {
		this.dtime = dtime;
	}

	public java.sql.Timestamp getAtime() {
		return atime;
	}

	public void setAtime(java.sql.Timestamp atime) {
		this.atime = atime;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getFlightype() {
		return flighttype;
	}

	public void setFlightype(String flightype) {
		this.flighttype = flightype;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	
}
