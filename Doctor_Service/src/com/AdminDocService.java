package com;

import model.AdminDoc;

import view.ViewDoctor;

import java.sql.Time;

//For REST Service 
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON 
import com.google.gson.*;


//For XML 
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/AdminDoc")

public class AdminDocService {
	
AdminDoc docObj = new AdminDoc();
	
	@GET
	@Path("/getDoc")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return docObj.readDoctors();
	}
	
	@POST 
	@Path("/insertDoc") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertDoctor(@FormParam("doc_nic") String nic, 
							@FormParam("doc_fname") String fname, 
							@FormParam("doc_lname") String lname, 
							@FormParam("doc_email") String email,
							@FormParam("doc_gender") String gender,
							@FormParam("liscen_no") String liscen,
							@FormParam("specialization") String special,
							@FormParam("phone") String phone,
							@FormParam("doc_charge") String charge,
							@FormParam("user_user_id") String userId) 
	{  
		String output = docObj.insertDoctor(nic, fname, lname, email, gender, liscen, special, phone, charge, userId); 
		return output; 
	}
	
	@DELETE
	@Path("/deleteDoc")
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteDoctor(String docData) 
	{  
		//Convert the input string to an XML document  
		Document doc = Jsoup.parse(docData, "", Parser.xmlParser());    
		
		//Read the value from the element <itemID>  
		String d_id = doc.select("doc_id").text(); 
		 
		String output = docObj.deleteDoctor(d_id); 
		 
		return output; 
	} 
	
	@POST 
	@Path("/assignDoc") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String assignDocHospital(@FormParam("doctor_doc_id") int did, 
							@FormParam("hospital_hosp_id") int hid, 
							@FormParam("date") String date, 
							@FormParam("time") Time time) 
	{  
		String output = docObj.assignDocHospital(did, hid, date, time); 
		return output; 
	}
	
	// View appointment by doctor id
	@GET
	@Path("/{doc_id}")
	// @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ViewDoctor ShowDoctortByDocId(@PathParam("doc_id") int id) {
					
		return docObj.ShowDoctorByDocId(id);
	
	}
	
	

}
