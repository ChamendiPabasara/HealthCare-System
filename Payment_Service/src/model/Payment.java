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
			String output = "<table>" + "<tr>" + "<th>Payment ID</th>" + "<th>Patient Name</th>"
					+ "<th>Payment Date</th>" + "<th>Amount</th>" + "<th>Doctor</th>" + "<th>Hospital</th>";
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
	
	public String getAllPayment(){
        try(Connection con  = DBConnector.getConnection()) {
            String getQuery = "select * from payment";
            PreparedStatement pstmt = con.prepareStatement(getQuery);

            String output = "<table border=\"1\">" +
                    "<tr>" +
					"<th>Payment Id</th>" +
                    "<th>Card Type</th>" +
					"<th>Card Number</th>" +
					"<th>Name On Card</th>" +
					"<th>cvc</th>" +
					"<th>Expire Date</th>" +
					"<th>Status</th>" +
					"<th>Sub Amount</th>" +
                    "<th>Payment Date</th>" +
					"<th>Tax Id</th>" +
					"<th>Appointment Id</th>" ;
					
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
				int paymentId = rs.getInt("payment_id");
				String cardType = rs.getString("card_type");
                int cardNumber = rs.getInt("card_number");
				String nameOnCard = rs.getString("name_on_card");
				int cvc = rs.getInt("cvc");
				Date expireDate = rs.getDate("expire_date");
				String status = rs.getString("status");
				float subAmount = rs.getFloat("sub_amount");
				Date paymentDate = rs.getDate("date");
				int taxId = rs.getInt("tax_tax_id");
                int appointmentId = rs.getInt("appoinment_appoinment_id");

                output += "<tr><td>" + paymentId + "</td>";
                output += "<td>" + cardType + "</td>";
                output += "<td>" + cardNumber + "</td>";
				output += "<td>" + nameOnCard + "</td>";
				output += "<td>" + cvc + "</td>";
				output += "<td>" + expireDate + "</td>";
				output += "<td>" + status + "</td>";
				output += "<td>" + subAmount + "</td>";
				output += "<td>" + paymentDate + "</td>";
				output += "<td>" + taxId + "</td>";
				output += "<td>" + appointmentId + "</td>";

            }
            output += "</table>";
            con.close();
            return output;
        }
        catch (SQLException e){
            return "Error occur during retrieving \n" +
                    e.getMessage();
        }
    }


	public double calculateSubAmount(int appointmentId) {
		double subAmount = 0;
		try (Connection con = DBConnector.getConnection()) {
			String getQuery = "select h.hosp_charge,d.doc_charge\n" + "from appoinment a\n"
					+ "join doctor d on d.doc_id = a.doctor_doc_id \n"
					+ "join hospital h on h.hosp_id = a.hospital_hosp_id \n" + "where a.appoinment_id = ?;";
			PreparedStatement pstmt = con.prepareStatement(getQuery);
			ResultSet rs = pstmt.executeQuery(getQuery);

			float docCharge = 0;
			float hospCharge = 0;
			while (rs.next()) {
				docCharge = rs.getFloat("doc_charge");
				hospCharge = rs.getFloat("hosp_charge");
			}

			subAmount = docCharge + hospCharge;
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return subAmount;

	}

	public String updatePayment(String cardType,
								int cardNumber,
								String nameOnCard,
								int cvc,
								Date expireDate,
								double subAmount,
								Date paymentDate,
								int taxId,
								int appointmenetId){
			
			try(Connection con = DBConnector.getConnection()) {
			String updateQuery = "update payment set cardType =?, cardNumber =?, nameOnCard =?, cvc =?, expireDate =?, subAmount =?, paymentDate =?, taxId =?, appointmenetId =?" +
						 "where appointmenetId =?" ;
						
			PreparedStatement pstmt = con.prepareStatement(updateQuery);
			pstmt.setString(1,cardType);
			pstmt.setInt(2,cardNumber);
			pstmt.setString(3,nameOnCard);
			pstmt.setInt(4,cvc);
			pstmt.setDate(5,expireDate);
			pstmt.setDouble(6,subAmount);
			pstmt.setDate(7,paymentDate);
			pstmt.setInt(8,taxId);
			pstmt.setInt(9,appointmenetId);
			pstmt.executeUpdate();
			con.close();
	
			return "Payment updated successfully....";
			}
			catch(SQLException e){
				return "Error occur during updating \n" +
						e.getMessage();
			}

	}

}
