package com;

import model.AdminDoc;

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
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return docObj.readDoctors();
	}
	
	@POST 
	@Path("/") 
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

}
