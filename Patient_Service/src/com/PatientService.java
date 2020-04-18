package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Patient;
import view.ViewPatient;

@Path("/Patients")
public class PatientService {

	Patient patient = new Patient();

	// get all patients
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)

	public String readPatients() {
		return patient.readPatients();
	}

	// insert new patient
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPatient(@FormParam("p_nic") String p_nic, @FormParam("p_fname") String p_fname,
			@FormParam("p_lname") String p_lname, @FormParam("p_dob") String p_dob,
			@FormParam("p_address") String p_address, @FormParam("p_phone") String p_phone,
			@FormParam("p_email") String p_email, @FormParam("p_gender") String p_gender,
			@FormParam("user_user_id") String user_user_id) {
		String output = patient.insertPatient(p_nic, p_fname, p_lname, p_dob, p_address, p_phone, p_email, p_gender,
				user_user_id);
		return output;
	}

	// View patient by patient id
	@GET
	@Path("/{patient_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ViewPatient ShowPatientByPatientId(@PathParam("patient_id") int pid) {

		return patient.ShowPatientByPatientId(pid);

	}

	//update patient
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updatePatient(String patientData) {

		// Convert the input string to a JSON object
		JsonObject ppp = new JsonParser().parse(patientData).getAsJsonObject();

		// Read the values from the JSON object
		int patient_id = ppp.get("patient_id").getAsInt();
		String p_nic = ppp.get("p_nic").getAsString();
		String p_fname = ppp.get("p_fname").getAsString();
		String p_lname = ppp.get("p_lname").getAsString();
		String p_dob = ppp.get("p_dob").getAsString();
		String p_address = ppp.get("p_address").getAsString();
		String p_phone = ppp.get("p_phone").getAsString();
		String p_email = ppp.get("p_email").getAsString();
		String p_gender = ppp.get("p_gender").getAsString();
		String user_user_id = ppp.get("user_user_id").getAsString();

		String output = patient.updatePatient(patient_id, p_nic, p_fname, p_lname, p_dob, p_address, p_phone, p_email,
				p_gender, user_user_id);
		return output;
	}

	//delete patient
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePatient(String patientData) {
		// Convert the input string to an XML document
		Document doc1 = Jsoup.parse(patientData, "", Parser.xmlParser());
		// Read the value from the element <itemID>
		String patient_id = doc1.select("patient_id").text();

		String output = patient.deletePatient(patient_id);

		return output;
	}

}
