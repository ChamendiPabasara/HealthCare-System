package view;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@XmlRootElement

public class ViewDoctor {
	
	int DID;
	private int doc_id;
	private String nic;
	private String fname;
	private String lname;
	private String email;
	private String gender;
	private String liscen;
	private String special;
	private int phone;
	private float charge;
	private int userId;
	
	
	public ViewDoctor() {}
	
	
	public ViewDoctor(int doc_id, String nic, String fname, String lname, String email, String gender, String liscen, String special, int phone, float charge, int userId) 
	{
		
		this.doc_id = doc_id;
		this.nic = nic;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.gender = gender;
		this.liscen = liscen;
		this.special = special;
		this.phone = phone;
		this.charge = charge;
		this.userId =userId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getDoc_id() {
		return doc_id;
	}


	public void setDoc_id(int doc_id) {
		this.doc_id = doc_id;
	}


	public String getNic() {
		return nic;
	}


	public void setNic(String nic) {
		this.nic = nic;
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getLname() {
		return lname;
	}


	public void setLname(String lname) {
		this.lname = lname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getLiscen() {
		return liscen;
	}


	public void setLiscen(String liscen) {
		this.liscen = liscen;
	}


	public String getSpecial() {
		return special;
	}


	public void setSpecial(String special) {
		this.special = special;
	}


	public int getPhone() {
		return phone;
	}


	public void setPhone(int phone) {
		this.phone = phone;
	}


	public float getCharge() {
		return charge;
	}


	public void setCharge(float charge) {
		this.charge = charge;
	}
	
	

}
