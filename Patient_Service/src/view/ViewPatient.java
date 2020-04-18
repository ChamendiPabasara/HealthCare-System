package view;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@XmlRootElement
public class ViewPatient {
	
	private int patient_id;
	private String p_nic;
	private String p_fname;
	private String p_lname;
	private String p_dob;
	private String p_address;
	private String p_phone;
	private String p_email;
	private String p_gender;
	
	private int user_user_id;
	
	public ViewPatient() {}

	public ViewPatient(int patient_id, String p_nic, String p_fname, String p_lname, String p_dob, String p_address,
			String p_phone, String p_email, String p_gender, int user_user_id) {
		this.patient_id = patient_id;
		this.p_nic = p_nic;
		this.p_fname = p_fname;
		this.p_lname = p_lname;
		this.p_dob = p_dob;
		this.p_address = p_address;
		this.p_phone = p_phone;
		this.p_email = p_email;
		this.p_gender = p_gender;
		this.user_user_id = user_user_id;
	}

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	public String getP_nic() {
		return p_nic;
	}

	public void setP_nic(String p_nic) {
		this.p_nic = p_nic;
	}

	public String getP_fname() {
		return p_fname;
	}

	public void setP_fname(String p_fname) {
		this.p_fname = p_fname;
	}

	public String getP_lname() {
		return p_lname;
	}

	public void setP_lname(String p_lname) {
		this.p_lname = p_lname;
	}

	public String getP_dob() {
		return p_dob;
	}

	public void setP_dob(String p_dob) {
		this.p_dob = p_dob;
	}

	public String getP_address() {
		return p_address;
	}

	public void setP_address(String p_address) {
		this.p_address = p_address;
	}

	public String getP_phone() {
		return p_phone;
	}

	public void setP_phone(String p_phone) {
		this.p_phone = p_phone;
	}

	public String getP_email() {
		return p_email;
	}

	public void setP_email(String p_email) {
		this.p_email = p_email;
	}

	public String getP_gender() {
		return p_gender;
	}

	public void setP_gender(String p_gender) {
		this.p_gender = p_gender;
	}

	public int getUser_user_id() {
		return user_user_id;
	}

	public void setUser_user_id(int user_user_id) {
		this.user_user_id = user_user_id;
	}
	

}
