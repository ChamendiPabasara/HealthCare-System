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
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateHospital(String hospitalData) {
		// Convert the input string to a JSON object
		JsonObject hospitalObject = new JsonParser().parse(hospitalData).getAsJsonObject();
		// Read the values from the JSON object
		String hospId = hospitalObject.get("hosp_id").getAsString();
		String hospName = hospitalObject.get("hosp_name").getAsString();
		String hospAddress = hospitalObject.get("hosp_address").getAsString();
		String hospEmail = hospitalObject.get("hosp_email").getAsString();
		String hospPhone = hospitalObject.get("hosp_phone").getAsString();
		String hospRegDate = hospitalObject.get("hosp_reg_date").getAsString();
		String HospCharge = hospitalObject.get("hosp_charge").getAsString();

		String output = hospObj.updateHospitals(hospId, hospName, hospAddress, hospEmail, hospPhone, hospRegDate,
				HospCharge);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String hospData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(hospData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String hospID = doc.select("hosp_id").text();
	 String output = hospObj.deleteHospital(hospID);
	return output;
	}
}
