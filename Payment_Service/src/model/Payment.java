package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DBConnector;

public class Payment {

	public String addPayment(String cardType, 
							int cardNumber, String nameOnCard, int cvc, Date expireDate,
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

}
