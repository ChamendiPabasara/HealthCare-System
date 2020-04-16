package com;

import java.sql.Date;

import java.sql.Date;
import java.text.SimpleDateFormat;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import model.Appointment;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Appointment;


@Path("/Appointments")
public class AppointmentService {
	
	Appointment App1 = new Appointment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String ReadAppointments() {
		
		return App1.ReadAppointments();
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addAppointment(@FormParam("date") Date day, @FormParam("time") String time,
			@FormParam("patient_patient_id") int pid, 
			@FormParam("doctor_doc_id") int did,
			@FormParam("hospital_hosp_id") int hosID) 
	{
		String output = App1.addAppointment(day, time, pid, did, hosID);
		return output;
	}
	
	@PUT
	@Path("/update") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String UpdateAppointment(@FormParam("date") Date day, @FormParam("time") String time,
			@FormParam("doctor_doc_id") int did,
			@FormParam("hospital_hosp_id") int hosID) 
	{
		String output = App1.UpdateAppointment(day, time, did, hosID);
		return output;
	}
	
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String DeleteAppointment(@FormParam ("appoinment_id") Integer AppID )
	{
	
	//Read the value from the element <AppID>
		String output = App1.DeleteAppointment(AppID);
        return output;

	
	
	}
	

}
