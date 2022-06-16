package com.simplilearn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "personal_info")
public class PersonalData {

	@Id
    @Column(name = "id")
    private int id;
	
	@Column(name = "first_name")
    private String firstName;
 
    @Column(name = "surname")
    private String surname;
 
    @Column(name = "email")
    private String email;
    
    @Column(name = "phone")
    private int phone;
    
    @Column(name = "fid")
    private int fid;
    
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	
}
