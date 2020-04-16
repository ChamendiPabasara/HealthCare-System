package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Patient;

@Path("/Patients")
public class PatientService {
	
Patient pp = new Patient();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String readPatients()
	{
	return pp.readPatients();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPatient(@FormParam("p_nic") String p_nic, @FormParam("p_fname") String p_fname,@FormParam("p_lname") String p_lname, @FormParam("p_dob") String p_dob,@FormParam("p_address") String p_address,@FormParam("p_phone") String p_phone,@FormParam("p_email") String p_email,@FormParam("p_gender") String p_gender,@FormParam("user_user_id")String user_user_id)
			{
		String output = pp.insertPatient(p_nic, p_fname,p_lname,p_dob,p_address, p_phone,p_email,p_gender,user_user_id);
		return output;
	}
	

	

}
