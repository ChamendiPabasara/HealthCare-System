package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DBConnector;

public class Payment {

	public String addPayment(String cardType, int cardNumber, String nameOnCard, int cvc, Date expireDate,
			String status, double subAmount, Date paymentDate, int taxId, int appointmentId) {

		try (Connection con = DBConnector.getConnection()) {
			String insertQuery = " insert into payment values (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?)";
			PreparedStatement pstmnt = con.prepareStatement(insertQuery);

			pstmnt.setString(1, cardType);
			pstmnt.setInt(2, cardNumber);
			pstmnt.setString(3, nameOnCard);
			pstmnt.setInt(4, cvc);
			pstmnt.setDate(5, expireDate);
			pstmnt.setString(6, status);
			pstmnt.setDouble(7, subAmount);
			pstmnt.setDate(8, paymentDate);
			pstmnt.setInt(9, taxId);
			pstmnt.setInt(10, appointmentId);

			pstmnt.execute();
			return "Payment added successfully...";
		} catch (SQLException e) {
			return "Error occur during adding\n" + e.getMessage();
		}
	}

	public String getPaymentByPatient(int id) {
		try (Connection con = DBConnector.getConnection()) {
			String getQuery = "select py.payment_id, p.p_fname, p.p_lname, py.date, py.sub_amount, d.doc_fname, d.doc_lname, h.hosp_name from appoinment a \n"
					+ "join patient p on a.patient_patient_id = p.patient_id \n"
					+ "join payment py on a.appoinment_id = py.appoinment_appoinment_id\n"
					+ "join doctor d on a.doctor_doc_id = d.doc_id\n"
					+ "join hospital h on a.hospital_hosp_id = h.hosp_id\n" + "where patient_id = ?;";
			PreparedStatement pstmnt = con.prepareStatement(getQuery);
			pstmnt.setInt(1, id);
			String output = "<table>" 
					+ "<tr>" 
					+ "<th>Payment ID</th>" 
					+ "<th>Patient Name</th>"
					+ "<th>Payment Date</th>" 
					+ "<th>Amount</th>" 
					+ "<th>Doctor</th>" 
					+ "<th>Hospital</th>";
			ResultSet rs = pstmnt.executeQuery();
			
			while (rs.next()) {
				int payId = rs.getInt("payment_id");
				String patientName = rs.getString("p_fname") + " " + rs.getString("p_lname");
				Date paymentDate = rs.getDate("date");
				double subAmount = rs.getDouble("sub_amount");
				String doctorName = rs.getString("doc_fname") + " " + rs.getString("doc_lname");
				String hospitalName = rs.getString("hosp_name");

				output += "<tr><td>" + payId + "</td>";
				output += "<td>" + patientName + "</td>";
				output += "<td>" + paymentDate + "</td>";
				output += "<td>" + subAmount + "</td>";
				output += "<td>" + doctorName + "</td>";
				output += "<td>" + hospitalName + "</td>";

			}
			output += "</table>";
			return output;
		} catch (SQLException e) {
			return "Error occur during retrieving \n" + e.getMessage();
		}
	}
	
	public double calculateSubAmount(int appointmentId) {
		double subAmount = 0;
		try(Connection con  = DBConnector.getConnection()){
			String getQuery = "select h.hosp_charge,d.doc_charge\n" + 
					"from appoinment a\n" + 
					"join doctor d on d.doc_id = a.doctor_doc_id \n" + 
					"join hospital h on h.hosp_id = a.hospital_hosp_id \n" + 
					"where a.appoinment_id = ?;" ;
			PreparedStatement pstmt = con.prepareStatement(getQuery);
			ResultSet rs = pstmt.executeQuery(getQuery);
			
			float docCharge = 0;
			float hospCharge = 0;
			while(rs.next()) {
				docCharge = rs.getFloat("doc_charge");
				hospCharge = rs.getFloat("hosp_charge");
			}
			
			subAmount = docCharge + hospCharge;
		} 
		catch (SQLException e) {
			e.printStackTrace();
	
		}
		return subAmount;	
		
	}

}
