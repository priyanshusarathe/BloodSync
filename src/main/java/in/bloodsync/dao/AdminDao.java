package in.bloodsync.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import in.bloodsync.dbutil.DBConnection;

public class AdminDao {
    public static Map<String,Integer> getAllBloodBankCount() throws SQLException{
    	Connection conn = DBConnection.getConnection();
    	String query = "select count(*) from blood_donors";
    	String query2 = "select sum(available_units) from blood_stock";
    	
    	Statement st = conn.createStatement();
    	ResultSet rs = st.executeQuery(query);
    	Map <String,Integer> counts = new HashMap<>();
    	if(rs.next()) {
    		int totalDonors = rs.getInt(1);
    		counts.put("totalDonors",totalDonors);
    		
    	}
    	 st = conn.createStatement();
    	 rs = st.executeQuery(query2);
    	 if(rs.next()) {
    		 int Bloodstock = rs.getInt(1);
    		 counts.put("Bloodstock", Bloodstock);
    	 }
    	 st = conn.createStatement();
    	 rs = st.executeQuery("SELECT COUNT(*) FROM blood_requests WHERE status='pending'");
    	 if(rs.next()) {
    		 int totalRequests = rs.getInt(1);
    		 counts.put("totalRequests",totalRequests);
    		 
    	 }
    	 return counts;
    }
}

