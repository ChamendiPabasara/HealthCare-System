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


}
