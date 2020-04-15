package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Time;

import config.DBConnector;

public class Doctor {
	
	public String assignDocHospital(int did, int hid, String date, Time time)  
	 {   
		 String output = ""; 
	 
		 try(Connection con  = DBConnector.getConnection())   
		 {    
			 
	 		 if (con == null)    
			 {return "Error while connecting to the database for inserting."; } 
	 
			 // create a prepared statement    
			 String query = "INSERT INTO doctor_has_hospital(`doctor_doc_id`, `hospital_hosp_id`, `date`, `time`)" + "VALUES (?,?,?,?)"; 
	 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			 // binding values    
			 preparedStmt.setInt(1, did);    
			 preparedStmt.setInt(2, hid);    
			 preparedStmt.setString(3, date);    
			 preparedStmt.setTime(4, time);
			 
			 // execute the statement    
			 preparedStmt.execute();    
			 con.close(); 
			 
			 output = "Inserted successfully";   
		 }   
		 catch (Exception e)   
		 {    
			 output = "Error while assigning doctor to hospital.";    
			 System.err.println(e.getMessage());   
		 }
			 
		 return output;  
	} 
	
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


}
