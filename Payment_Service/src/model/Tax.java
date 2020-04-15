package model;

import config.DBConnector;
import java.sql.*;

public class Tax {
	public String addTaxEntry(float amount) {
		try (Connection con = DBConnector.getConnection()) {
			String insertQuery = "insert into tax values (NULL, ?)";
			PreparedStatement pstmt = con.prepareStatement(insertQuery);
			pstmt.setFloat(1, amount);
			pstmt.execute();
			con.close();
			return "Tax entry added successfully....";
		} catch (SQLException e) {
			return "Error occur during adding\n" + e.getMessage();
		}
	}

}
