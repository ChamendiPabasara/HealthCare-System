package model;

import view.DoctorAppointment;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

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
	
	
	//show the type by DocID
	public DoctorAppointment ShowAppointmentByDocId(int id) {
	List<DoctorAppointment> list = viewAppointments(id);
		if(!list.isEmpty()) {
			return	list.get(0);
		}
		return null;
	}
	
	//view method
		public List<DoctorAppointment> viewAppointments(int id) {
			
				List <DoctorAppointment> AppointmentList = new ArrayList<>();
				
			try(Connection con  = DBConnector.getConnection())   
			{
				//Connection con = dbObj.connect();
				if (con == null) {
					
					System.out.println("Error While reading from database");
					return AppointmentList;
				}

				String query;
				
				if(id==0) {
				query = "SELECT `appoinment_id`, `date`, `time`, `patient_patient_id`, `hospital_hosp_id` FROM appoinment";
				}
				else {
					query = "SELECT `appoinment_id`, `date`, `time`, `patient_patient_id`, `hospital_hosp_id` FROM appoinment WHERE doctor_doc_id="+id;	
				}
				Statement stmt = con.createStatement();
				ResultSet results = stmt.executeQuery(query);

				while (results.next()) {
					DoctorAppointment DocApp = new DoctorAppointment(
											results.getInt("appoinment_Id"),
											results.getString("date"),
											results.getString("time"),
											results.getInt("patient_patient_id"),
											results.getInt("hospital_hosp_id")
										);
					AppointmentList.add(DocApp);
				}
				con.close();
			}
			catch (Exception e) {
				System.out.println("Error While Reading");
				System.err.println(e.getMessage());
			}
			
			return AppointmentList;
		}
		

}
