package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

import config.DBConnector;

public class Doctor {
	
	
	
	public String updateDoctor(int d_id, String nic, String fname, String lname, String email, String gender, String liscen, String special, String phone, String charge, String userId) 
	{   
		String output = ""; 
	 
		try(Connection con  = DBConnector.getConnection())   
		{    
					 
	        if (con == null)    
	        {return "Error while connecting to the database for updating."; } 
	 
	        // create a prepared statement    
	        String updatequery = "UPDATE doctor SET doc_nic =?, doc_fname =?, doc_lname =?, doc_email=?, doc_gender=?, liscen_no=?, specialization=?, phone=?, doc_charge=?, user_user_id=? WHERE doc_id=?"; 
	 
	        PreparedStatement preparedStmt = con.prepareStatement(updatequery); 
	 
	        // binding values
	        
	        preparedStmt.setString(1, nic);    
	        preparedStmt.setString(2, fname);
	        preparedStmt.setString(3, lname);
	        preparedStmt.setString(4, email);
	        preparedStmt.setString(5, gender);
	        preparedStmt.setString(6, liscen);
	        preparedStmt.setString(7, special);
	        preparedStmt.setString(8,phone);
	        preparedStmt.setDouble(9, Double.parseDouble(charge));
	        preparedStmt.setInt(10, Integer.parseInt(userId));
	        preparedStmt.setInt(11, d_id);
	        
	         
	 
	        // execute the statement    
	        preparedStmt.execute();    
	        con.close(); 
	 
	        output = "Updated successfully";   
	    }   
		catch (Exception e)   
		{    
			output = "Error while updating the Doctor";    
			System.err.println(e.getMessage());   
		} 
	 
	    return output;  
	}
	
	public String readDocAppointments(String did) {
		String output = "";

		try (Connection con = DBConnector.getConnection()) {

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Appointment_ID</th><th>Date</th><th>Time</th><th>Patient ID</th><th>Hospital ID</th>";

			String query = "SELECT `appoinment_id`, `date`, `time`, `patient_patient_id`, `hospital_hosp_id` FROM appoinment WHERE doctor_doc_id=" +did;
			
			//PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			//preparedStmt.setInt(1, Integer.parseInt(did));
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				
				int aid = rs.getInt("appoinment_id");
				String date = rs.getString("date");
				String time = rs.getString("time");
				int pid = rs.getInt("patient_patient_id");
				int hid = rs.getInt("hospital_hosp_id");
				

				// Add into the html table
				output += "<tr><td>" + aid + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + time + "</td>";
				output += "<td>" + pid + "</td>";
				output += "<td>" + hid + "</td>";
				
			}
			//preparedStmt.execute();
			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while Reading Doctor's Appointments.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
