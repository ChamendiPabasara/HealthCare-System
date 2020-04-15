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
	
	 public String getAllTaxEntry(){
        try(Connection con  = DBConnector.getConnection()) {
            String getQuery = "select * from tax";
            PreparedStatement pstmt = con.prepareStatement(getQuery);

            String output = "<table border=\"1\">" +
                    "<tr>" +
                    "<th>Tax ID</th>" +
                    "<th>Tax amount</th>";
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                int taxId = rs.getInt("tax_id");
                float taxAmount = rs.getFloat("tax_amount");

                output += "<tr><td>" + taxId + "</td>";
                output += "<td>" + taxAmount + "</td>";

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
	
	 public String getTaxEntryById(int id){
        try(Connection con  = DBConnector.getConnection()) {
            String getQuery = "select * from tax where tax_id = ?";
            PreparedStatement pstmt = con.prepareStatement(getQuery);
            pstmt.setInt(1, id);
            String output = "<table border=\"1\">" +
                    "<tr>" +
                    "<th>Tax ID</th>" +
                    "<th>Tax amount</th>";
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                int taxId = rs.getInt("tax_id");
                float taxAmount = rs.getFloat("tax_amount");

                output += "<tr><td>" + taxId + "</td>";
                output += "<td>" + taxAmount + "</td>";

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
	
	 public String updateTaxEntryById(int id, float amount){
        try(Connection con  = DBConnector.getConnection()) {
            String updateQuery = "update tax set tax_amount = ? where tax_id = ?";
            PreparedStatement pstmt = con.prepareStatement(updateQuery);
            pstmt.setInt(2, id);
            pstmt.setFloat(1, amount);
            pstmt.executeUpdate();
            con.close();
            return "Tax entry updated successfully....";
        }
        catch (SQLException e){
            return "Error occur during updating \n" +
                    e.getMessage();
        }
    }
	
	  public boolean checkTaxEntryUsed(Connection con, int id){
	        boolean flag = true;
	        try {
	            String checkQuery = "select exists(select * from payment where tax_tax_id = ?);";
	            PreparedStatement pstmt = con.prepareStatement(checkQuery);
	            pstmt.setInt(1, id);
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()){
	                if (rs.getInt(1) > 0)
	                    flag = true;
	                else
	                    flag = false;
	            }
	        }
	        catch (SQLException e){
	            flag = true;
	        }
	        return flag;
	    }

}
