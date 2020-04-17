package com;

import model.Hospital;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Hospitals")
public class HospitalService {
	Hospital hospObj = new Hospital();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readHospitals() {
		return hospObj.readHospital();
	}
	
	
	@POST()
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertHospitals(@FormParam("hosp_name") String hospName,
			@FormParam("hosp_address") String hospAddress, @FormParam("hosp_email") String hospEmail,
			@FormParam("hosp_phone") String hospPhone, @FormParam("hosp_reg_date") String hospRegDate,
			@FormParam("hosp_charge") String hospCharge) {

		String output = hospObj.insertHospital(hospName, hospAddress, hospEmail, hospPhone, hospRegDate, hospCharge);
		return output;
	}
}
