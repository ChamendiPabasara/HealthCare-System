package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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

public String getAppointmentByPatient(int id) {
	
	try(Connection con  = DBConnector.getConnection()){
		String getAppQuery = "select a.appoinment_id, p.p_fname, p.p_lname, a.date, a.time, d.doc_fname, d.doc_lname, h.hosp_name from appoinment a \n" +
				"join patient p on a.patient_patient_id = p.patient_id \n" +
				"join payment py on a.appoinment_id = py.appoinment_appoinment_id\n" +
				"join doctor d on a.doctor_doc_id = d.doc_id\n" +
				"join hospital h on a.hospital_hosp_id = h.hosp_id\n" +
				"where patient_id = ?;";
		
		PreparedStatement pstmnt = con.prepareStatement(getAppQuery);
		pstmnt.setInt(1, id);
		
		String output = "<table>" +
				"<tr>" +
				"<th>Payment ID</th>" +
				"<th>Patient Name</th>" +
				"<th>Payment Date</th>" +
				"<th>Amount</th>" +
				"<th>Doctor</th>" +
				"<th>Hospital</th>";
		
		ResultSet rs = pstmnt.executeQuery();
		while(rs.next()) {
			int AppID = rs.getInt("appoinment_id");
			Date day = rs.getDate("date");
			String time = rs.getString("time");
			String patientName = rs.getString("p_fname") + " " + rs.getString("p_lname");
			String doctor = rs.getString("doc_fname") + " " + rs.getString("doc_lname");
			String hospital = rs.getString("hosp_name");


			output += "<tr><td>" + AppID + "</td>";
			output += "<td>" + day + "</td>";
			output += "<td>" + time + "</td>";
			output += "<td>" + patientName + "</td>";
			output += "<td>" + doctor + "</td>";
			output += "<td>" + hospital + "</td>";

		}
		output += "</table>";
		return output;
	}
	catch(SQLException e){
		return "Error occur during retrieving \n" +
				e.getMessage();
	}
	
	
	
}

public String ReadAppointments() {
	
	
	try(Connection con  = DBConnector.getConnection()){
		
		/*String patient = "select p_fname,p_lname from patient where patient_id=?";
		String doctor = "select doc_fname,doc_lname from doctor where doc_id=?";
		String hospital = "select hosp_name	 from hospital where hosp_id =?";
		*/
		
		LocalDate prvPaymentDate = null;
		String readQuery = "select * from appoinment";
		
		 PreparedStatement pstmt = con.prepareStatement(readQuery);
		 
		 String output = "<table border=\"1\"><tr><th>Appointment ID</th>"+
				 		"<th>Appointment Date</th> "+
				 		"<th>Appointment Time</th>"
				 		+ "<th>Patient ID</th>"
				 		+"<th>Doctor ID</th>"
				 		+"<th>Hospital ID</th>"
				 		+ "<th>Update</th><th>Remove</th></tr>"; 
		 
			/*String output = "<table>" +
					"<tr>" +
					"<th>Appointment ID</th>" +
					"<th>Appointment Date</th>" +
					"<th>Appointment Time</th>" +
					"<th>Patient ID</th>" +
					"<th>Doctor ID</th>" +
					"<th>Hospital ID</th>";*/
			
		 ResultSet rs = pstmt.executeQuery(readQuery); 
		
		 
		while(rs.next()) {
			int AppID = rs.getInt("appoinment_id");
		    Date day = rs.getDate("date");
			String time = rs.getString("time");
			int pid = rs.getInt("patient_patient_id");
			int did = rs.getInt("doctor_doc_id");
			int hosID = rs.getInt("hospital_hosp_id");
		
			

			output += "<tr><td>" + AppID + "</td>";
			output += "<td>" + day + "</td>";
			output += "<td>" + time + "</td>";
			output += "<td>" + pid + "</td>";
			output += "<td>" + did + "</td>";
			output += "<td>" + hosID + "</td>";
			

		}
	
	   output += "</table>";
		  return output;
	}
	catch(SQLException e){
		e.printStackTrace();
		return "Error occured during retrieving data";
	}
}

public String UpdateAppointment( Date day, String time,int did,int hosID) {
	
	try(Connection con  = DBConnector.getConnection()){
		
	
		/*String doctor = "select doc_id from doctor where doc_fname =? AND doc_lname=?";
		String hospital = "select hosp_id	 from hospital where hosp_name =?";
		
		
		int did = Integer.parseInt(doctor);
		int hid = Integer.parseInt(hospital);*/
		
		String updateAppQuery =  "UPDATE appoinment SET date=?,time=?,doctor_doc_id=?,hospital_hosp_id=? WHERE appoinment_id=?"; 
		PreparedStatement pstmnt = con.prepareStatement(updateAppQuery);
		pstmnt.setDate(1, day);
		pstmnt.setString(2, time);
		pstmnt.setInt(3, did);
		pstmnt.setInt(4, hosID);
		

		pstmnt.execute();
		return "Payment added successfully...";
	}
	catch(SQLException e){
		return "Error occured during Updating an Appointment\n" + e.getMessage();
	}
	
}
public String DeleteAppointment(int AppID) {
	
	try(Connection con  = DBConnector.getConnection()){
		
		
		// create a prepared statement
		 String Deletequery = "delete from appoinment where appoinment_id=?";
		
		 PreparedStatement pstmnt = con.prepareStatement(Deletequery);
			pstmnt.setInt(1, AppID);
			pstmnt.execute();
			return "Appoinment Deleted successfully...";
		
	}
	catch(SQLException e){
		
		return "Error occurrd during Deleting\n" + e.getMessage();
	}
	
}

}
