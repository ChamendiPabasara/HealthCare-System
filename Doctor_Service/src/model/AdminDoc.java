package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import config.DBConnector;
import view.ViewDoctor;

public class AdminDoc {
	
	public String readDoctors() {
		String output = "";

		try (Connection con = DBConnector.getConnection()) {

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>D_ID</th><th>D_NIC</th><th>D_fname</th><th>D_lanme</th><th>D_email</th><th>D_gender</th><th>Liscen_No</th><th>D_specialization</th><th>D_phone</th><th>D_charge</th><th>User_ID</th><th>Update</th><th>Remove</th></tr>";

			String query = "SELECT * FROM doctor";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("doc_id"));
				String nic = rs.getString("doc_nic");
				String fname = rs.getString("doc_fname");
				String lname = rs.getString("doc_lname");
				String email = rs.getString("doc_email");
				String gender = rs.getString("doc_gender");
				String liscen = rs.getString("liscen_No");
				String special = rs.getString("specialization");
				String phone = rs.getString("phone");
				String charge = Double.toString(rs.getDouble("doc_charge"));
				String uid = rs.getString("user_user_id");

				// Add into the html table
				output += "<tr><td>" + id + "</td>";
				output += "<td>" + nic + "</td>";
				output += "<td>" + fname + "</td>";
				output += "<td>" + lname + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + liscen + "</td>";
				output += "<td>" + special + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + charge + "</td>";
				output += "<td>" + uid + "</td>";

				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\"  class=\" btnUpdate btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"index.jsp\"> "
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\"> "
						+ "<input name=\"hidItemIDDelete\" type=\"hidden\" value=\"" +  id + "\">" + "</form></td></tr>";
			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the doctors.";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	public String insertDoctor(String nic, String fname, String lname, String email, String gender, String liscen,
			String special, String phone, String charge, String userId) {
		String output = "";

		try (Connection con = DBConnector.getConnection()) {

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " INSERT INTO doctor(`doc_id`, `doc_nic`, `doc_fname`, `doc_lname`, `doc_email`, `doc_gender`, `liscen_no`, `specialization`, `phone`, `doc_charge`, `user_user_id`)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, nic);
			preparedStmt.setString(3, fname);
			preparedStmt.setString(4, lname);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, gender);
			preparedStmt.setString(7, liscen);
			preparedStmt.setString(8, special);
			preparedStmt.setInt(9, Integer.parseInt(phone));
			preparedStmt.setDouble(10, Double.parseDouble(charge));
			preparedStmt.setString(11, userId);

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the doctor.";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	public String deleteDoctor(String d_id) {
		String output = "";

		try (Connection con = DBConnector.getConnection()) {

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from doctor where doc_id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(d_id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the doctor.";
			System.err.println(e.getMessage());
		}

		return output;

	}
	
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

	//show the type by DocID
			public ViewDoctor ShowDoctortByDocId(int id) {
			List<ViewDoctor> list = viewDoctor(id);
				if(!list.isEmpty()) {
					return	list.get(0);
				}
				return null;
			}
			
			//view method
				public List<ViewDoctor> viewDoctor(int id) {
					
						List <ViewDoctor> DoctorList = new ArrayList<>();
						
					try(Connection con  = DBConnector.getConnection())   
					{
						//Connection con = dbObj.connect();
						if (con == null) {
							
							System.out.println("Error While reading from database");
							return DoctorList;
						}

						String query;
						
						if(id==0) {
						query = "SELECT * FROM doctor";
						}
						else {
							query = "SELECT * FROM doctor WHERE doc_id="+id;	
						}
						Statement stmt = con.createStatement();
						ResultSet results = stmt.executeQuery(query);

						while (results.next()) {
							ViewDoctor Doctor = new ViewDoctor(
													results.getInt("doc_id"),
													results.getString("doc_nic"),
													results.getString("doc_fname"),
													results.getString("doc_lname"),
													results.getString("doc_email"),
													results.getString("doc_gender"),
													results.getString("liscen_no"),
													results.getString("specialization"),
													results.getInt("phone"),
													results.getFloat("doc_charge"),
													results.getInt("user_user_id")
												);
							DoctorList.add(Doctor);
						}
						con.close();
					}
					catch (Exception e) {
						System.out.println("Error While Reading");
						System.err.println(e.getMessage());
					}
					
					return DoctorList;
				}

	

}
