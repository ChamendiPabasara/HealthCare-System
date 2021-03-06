package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConnector;
import view.ViewPatient;

public class Patient {

	public String insertPatient(String p_nic, String p_fname, String p_lname, String p_dob, String p_address,
			String p_phone, String p_email, String p_gender, String user_user_id) {
		String output = "";

		try (Connection con = DBConnector.getConnection()) {
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			System.out.println("1111111111");
			// create a prepared statement
			String query = " insert into patient(`patient_id`,`p_nic`,`p_fname`,`p_lname`,`p_dob`,`p_address`,`p_phone`,`p_email`,`p_gender`,`user_user_id`)"
					+ " values (?, ?, ?, ?, ?,?,?,?,?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, p_nic);
			preparedStmt.setString(3, p_fname);
			preparedStmt.setString(4, p_lname);
			preparedStmt.setString(5, p_dob);
			preparedStmt.setString(6, p_address);
			preparedStmt.setString(7, p_phone);
			preparedStmt.setString(8, p_email);
			preparedStmt.setString(9, p_gender);
			preparedStmt.setString(10, user_user_id);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting Patient.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPatients() {
		String output = "";
		try (Connection con = DBConnector.getConnection()) {
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>NIC</th>" + "<th>Fisrst Name</th><th>Last Name</th>"
					+ "<th>Date of Birth</th>" + "<th>Address</th>" + "<th>Phone</th>" + "<th>Email</th>"
					+ "<th>Gender</th>" + "<th>User ID</th>";

			String query = "select * from patient";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set

			while (rs.next()) {

				int patient_id = rs.getInt("patient_id");
				String p_nic = rs.getString("p_nic");
				String p_fname = rs.getString("p_fname");
				String p_lname = rs.getString("p_lname");
				Date p_dob = rs.getDate("p_dob");
				String p_address = rs.getString("p_address");
				String p_phone = rs.getString("p_phone");
				String p_email = rs.getString("p_email");
				String p_gender = rs.getString("p_gender");
				String user_user_id = rs.getString("user_user_id");

				// Add into the html table
				output += "<tr><td>" + p_nic + "</td>";
				output += "<td>" + p_fname + "</td>";
				output += "<td>" + p_lname + "</td>";
				output += "<td>" + p_dob + "</td>";
				output += "<td>" + p_address + "</td>";
				output += "<td>" + p_phone + "</td>";
				output += "<td>" + p_email + "</td>";
				output += "<td>" + p_gender + "</td>";
				output += "<td>" + user_user_id + "</td>";

				// buttons
				/*output += "<td><input name=\"btnUpdate\" " + " type=\"button\" value=\"Update\"></td>"
						+ "<td><form method=\"post\" action=\"index.jsp\">" + "<input name=\"btnRemove\" "
						+ " type=\"submit\" value=\"Remove\">" + "<input name=\"itemID\" type=\"hidden\" " + " value=\""
						+ patient_id + "\">" + "</form></td></tr>";*/
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading Patients.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// show patient by patientid
	public ViewPatient ShowPatientByPatientId(int pid) {
		List<ViewPatient> list = viewPatient(pid);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	public List<ViewPatient> viewPatient(int pid) {

		List<ViewPatient> PatientList = new ArrayList<>();

		try (Connection con = DBConnector.getConnection()) {

			// Connection con = dbObj.connect();
			if (con == null) {

				System.out.println("Error While reading from database");
				return PatientList;
			}

			String query;

			if (pid == 0) {
				query = "SELECT * FROM patient";
			} else {
				query = "SELECT * FROM patient WHERE patient_id=" + pid;
			}
			Statement stmt = con.createStatement();
			ResultSet results = stmt.executeQuery(query);

			while (results.next()) {
				ViewPatient Patient = new ViewPatient(results.getInt("patient_id"), results.getString("p_nic"),
						results.getString("p_fname"), results.getString("p_lname"), results.getString("p_dob"),
						results.getString("p_address"), results.getString("p_phone"), results.getString("p_email"),
						results.getString("p_gender"), results.getInt("user_user_id"));
				PatientList.add(Patient);
				System.out.println("success");
			}
			con.close();
		} catch (Exception e) {
			System.out.println("Error While Reading");
			System.err.println(e.getMessage());
		}

		return PatientList;
	}

	public String updatePatient(int patient_id, String p_nic, String p_fname, String p_lname, String p_dob,
			String p_address, String p_phone, String p_email, String p_gender, String user_user_id) {
		String output = "";
		try (Connection con = DBConnector.getConnection()) {
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE patient SET p_nic=?,p_fname=?,p_lname=?,p_dob=?,p_address=?, p_phone=?,p_email=?,p_gender=?,user_user_id=? WHERE patient_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, p_nic);
			preparedStmt.setString(2, p_fname);
			preparedStmt.setString(3, p_lname);
			preparedStmt.setString(4, p_dob);
			preparedStmt.setString(5, p_address);
			preparedStmt.setString(6, p_phone);
			preparedStmt.setString(7, p_email);
			preparedStmt.setString(8, p_gender);
			preparedStmt.setString(9, user_user_id);
			preparedStmt.setInt(10, patient_id);

// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the patient.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletePatient(String patient_id) {
		String output = "";
		try (Connection con = DBConnector.getConnection()) {
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from patient where patient_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(patient_id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting patient....";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
