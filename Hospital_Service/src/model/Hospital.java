package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

	
}
