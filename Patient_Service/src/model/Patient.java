package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import config.DBConnector;

public class Patient {
	
	public String insertPatient(String p_nic, String p_fname, String p_lname, Date p_dob, String p_address,
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
			System.out.println("22222222222");

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			System.out.println("999999999");
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, p_nic);
			preparedStmt.setString(3, p_fname);
			preparedStmt.setString(4, p_lname);
			preparedStmt.setDate(5, p_dob);
			preparedStmt.setString(6, p_address);
			preparedStmt.setString(7, p_phone);
			preparedStmt.setString(8, p_email);
			preparedStmt.setString(9, p_gender);
			preparedStmt.setString(10, user_user_id);

			System.out.println("3333333333333");

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	

}
