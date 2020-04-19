package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import config.DBConnector;

public class Hospital {

	public String insertHospital(String hname, String hadd, String hmail, String hphone, String hdate, String hchage) {

		String output = null;

		try (Connection con = DBConnector.getConnection()){
			if (con == null) {
				return "Error while connecting to the database";
			} else {
				String query = "INSERT INTO hospital (`hosp_id`,`hosp_name`, `hosp_address`, `hosp_email`, `hosp_phone`, `hosp_reg_date`, `hosp_charge`) VALUES (?,?,?,?,?,?,?)";

				PreparedStatement preparedStmt = con.prepareStatement(query);

				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, hname);
				preparedStmt.setString(3, hadd);
				preparedStmt.setString(4, hmail);
				preparedStmt.setString(5, hphone);

				java.util.Date date = new SimpleDateFormat("dd-MM-yyyy").parse(hdate);
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());

				preparedStmt.setDate(6, sqlDate);
				preparedStmt.setFloat(7, Float.parseFloat(hchage));

				preparedStmt.execute();
				con.close();

				output = "Inserted Successfully";
			}
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String updateHospitals(String hID,String hname, String hadd, String hmail, String hphone, String hdate, String hchage) {
		String output = "";
		
		try (Connection con = DBConnector.getConnection()){
			if(con == null) {
				return "Error while connecting to the database";
			} else {
				String query = "update hospital set hosp_name=?,hosp_address=?,hosp_email=?,hosp_phone=?,hosp_reg_date=?,hosp_charge=? where hosp_id=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				preparedStmt.setString(1, hname);
				preparedStmt.setString(2, hadd);
				preparedStmt.setString(3, hmail);
				preparedStmt.setString(4, hphone);

				java.util.Date date = new SimpleDateFormat("dd-MM-yyyy").parse(hdate);
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());

				preparedStmt.setDate(5, sqlDate);
				preparedStmt.setFloat(6, Float.parseFloat(hchage));
				preparedStmt.setInt(7, Integer.parseInt(hID));
				preparedStmt.execute();
				con.close();
				
				output = "Updated Successfully";
			}
		} catch(Exception e) {
			output = "Error while updating the hosital";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String deleteHospital(String hospID) {
		String output = null;
			try (Connection con = DBConnector.getConnection()){
				if(con == null) {
					return "Error while connecting to the database";
				} else {
					String query = "delete from hospital where hosp_id=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					preparedStmt.setInt(1,Integer.parseInt(hospID));
					
					preparedStmt.execute();
					con.close();
					output = "Deleted Successfully";
				}
			}catch(Exception e) {
				output = "Error while deleting hospital";
				System.err.println(e.getMessage());
			}
		return output;
	}
	
	public String readHospital() {

		String output = "";

		try (Connection con = DBConnector.getConnection()){
			if (con == null) {
				return "Error while connecting to the database";
			} else {

				String query = "select * from hospital";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				output = "<table border=\"1\">"
						+ "<tr><th>Hospital Name</th><th>Hospital Address</th><th>Hospital Email</th>"
						+ "<th>Hospital Phone</th><th>Hospital Registered date</th><th>Hospital Charge</th><th>Update</th><th>Remove</th></tr>";

				while (rs.next()) {
					String hospID = Integer.toString(rs.getInt("hosp_id"));
					String hospName = rs.getString("hosp_name");
					String hospAddress = rs.getString("hosp_address");
					String hospEmail = rs.getString("hosp_email");
					String hospPhone = rs.getString("hosp_phone");
					String hospRegDate = rs.getString("hosp_reg_date");
					String hospCharge = Float.toString(rs.getFloat("hosp_charge"));
					
					
					// buttons
					output += "<tr><td><input id=\"hidHospIDUpdate\" name=\"hidHospIDUpdate\" type=\"hidden\" value=\"" + hospID + "\">" + hospName + "</td>";
					output += "<td>" + hospAddress + "</td>";
					output += "<td>" + hospEmail + "</td>";
					output += "<td>" + hospPhone + "</td>";
					output += "<td>" + hospRegDate + "</td>";
					output += "<td>" + hospCharge + "</td>";
					
					// buttons
					output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\" btnUpdate btn btn-secondary\">"
							+ "</td><td><form method=\"post\" action=\"hospitals.jsp\">"
							+ "<input name=\"btnRemove\" type=\"submit\"value=\"Remove\" class=\"btn btn-danger\">"
							+ "<input name=\"hidHospIDDelete\" type=\"hidden\"value=\""
							+ hospID + "\">" + "</form></td></tr>";
					
				}
				con.close();
				output += "</table>";
			}
		} catch (Exception e) {
			output = "Error while reading";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String getHospitalDetailsByID(String hospID) {
		String output = "";
		
		try (Connection con = DBConnector.getConnection()){
			
				if(con ==  null) {
					return "Error while connecting to the database";
				} else {
					String query = "select * from hospital where hosp_id=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					preparedStmt.setInt(1, Integer.parseInt(hospID));
					
					output = "<table border=\"1\">"
							+ "<tr><th>Hospital Name</th><th>Hospital Address</th><th>Hospital Email</th>"
							+ "<th>Hospital Phone</th><th>Hospital Registered date</th><th>Hospital Charge</th></tr>";
					
					
					ResultSet rs = preparedStmt.executeQuery();
					
					while(rs.next()) {
						String hospName = rs.getString("hosp_name");
						String hospAddress = rs.getString("hosp_address");
						String hospEmail = rs.getString("hosp_email");
						String hospPhone = rs.getString("hosp_phone");
						String hospRegDate = rs.getString("hosp_reg_date");
						String hospCharge = Float.toString(rs.getFloat("hosp_charge"));

						output += "<td>" + hospName + "</td>";
						output += "<td>" + hospAddress + "</td>";
						output += "<td>" + hospEmail + "</td>";
						output += "<td>" + hospPhone + "</td>";
						output += "<td>" + hospRegDate + "</td>";
						output += "<td>" + hospCharge + "</td>";
					}
					con.close();
					output += ("</table>");
				}
			} catch(Exception e) {
				output ="Error while getting hospital details";
				System.err.println(e.getMessage());	
			}
		
		return output;
	}

	public String getDoctorsHospitalWise() {
		String output = "";
		
		try(Connection con = DBConnector.getConnection()){
				if(con == null) {
					return "Error while connecting to the database";
				}else {
					String query = "select h.hosp_name,d.doc_fname,d.doc_lname from doctor_has_hospital r,doctor d,hospital h where r.doctor_doc_id=d.doc_id and r.hospital_hosp_id=h.hosp_id group by h.hosp_name,d.doc_fname,d.doc_lname";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					
					output = "<table border=\"1\">"
							+ "<tr><th>Hospital Name</th><th>Doctor firstname</th><th>Doctor lastname</th></tr>";

					while (rs.next()) {
						String hospName = rs.getString("hosp_name");
						String docFname = rs.getString("doc_fname");
						String docLname = rs.getString("doc_lname");
						
						// buttons
						output += "<tr>";
						output += "<td>" + hospName + "</td>";
						output += "<td>" + docFname + "</td>";
						output += "<td>" + docLname + "</td>";
						
						// buttons
						output +="</tr>";
						
					}
					con.close();
					output += "</table>";
				}
				
			}catch(Exception e) {
				output = "Read successfull";
				System.err.println(e.getMessage());
			}
		
		
		return output;
	}
	
}
