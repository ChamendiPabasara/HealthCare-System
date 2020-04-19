package view;


import java.sql.Time;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@XmlRootElement

public class DoctorAppointment {
	
	int DID;
	private int appointment_Id;
	private String date;
	private Time time;
	private int patient_Id;
	private int hospital_Id;
	
	
	public DoctorAppointment() {}
	
	
	public DoctorAppointment(int appointment_Id, String date, Time time, int patient_Id, int hospital_Id) {
	
		this.appointment_Id = appointment_Id;
		this.date = date;
		this.time = time;
		this.patient_Id = patient_Id;
		this.hospital_Id= hospital_Id;
	}

	
	public int getAppointment_Id() {
		return appointment_Id;
	}


	public void setAppointment_Id(int appointment_Id) {
		this.appointment_Id = appointment_Id;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public Time getTime() {
		return time;
	}


	public void setTime(Time time) {
		this.time = time;
	}


	public int getPatient_Id() {
		return patient_Id;
	}


	public void setPatient_Id(int patient_Id) {
		this.patient_Id = patient_Id;
	}


	public int getHospital_Id() {
		return hospital_Id;
	}


	public void setHospital_Id(int hospital_Id) {
		this.hospital_Id = hospital_Id;
	}

		
}



