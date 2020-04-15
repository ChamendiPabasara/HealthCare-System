package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import config.DBConnector;

public class Appointment {

public String addAppointment( Date day, String time, int pid,int did,int hosID) {
		
		try(Connection con  = DBConnector.getConnection()){
			
			/*String patient = "select patient_id from patient where p_fname =? AND p_lname=?";
			String doctor = "select doc_id from doctor where doc_fname =? AND doc_lname=?";
			String hospital = "select hosp_id	 from hospital where hosp_name =?";
			
			int pid = Integer.parseInt(patient);
			int did = Integer.parseInt(doctor);
			int hid = Integer.parseInt(hospital);*/
			
			String insertAppQuery = " insert into appoinment values (?,?,?,?,?)";
			PreparedStatement pstmnt = con.prepareStatement(insertAppQuery);
			pstmnt.setDate(1,day);
			pstmnt.setString(2,time);
			pstmnt.setInt(3,pid);
			pstmnt.setInt(4,did);
			pstmnt.setInt(5,hosID);
			
			

			pstmnt.execute();
			return "Payment added successfully...";
		}
		catch(SQLException e){
			return "Error occured during adding an Appoinment\n" + e.getMessage();
		}
		
	}
}
