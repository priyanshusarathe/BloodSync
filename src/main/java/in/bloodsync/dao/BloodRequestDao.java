package in.bloodsync.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import in.bloodsync.dbutil.DBConnection;
import in.bloodsync.pojo.BloodRequestPojo;

public class BloodRequestDao {
	public static boolean addBloodRequest(BloodRequestPojo request) throws SQLException{
      Connection conn = DBConnection.getConnection();
      String query ="INSERT INTO blood_requests (hospital_name,blood_type,urgency,requested_units) VALUES(?,?,?,?)";
      PreparedStatement ps = conn.prepareStatement(query);
      ps.setString(1, request.getHospitalName());
      ps.setString(2, request.getBloodType());
      ps.setString(3, request.getUrgency());
      ps.setInt(4, request.getRequestedUnits());
      int ans = ps.executeUpdate();
      ps.close();
      return ans >0;
      
}
	public static List<BloodRequestPojo> getAllBloodRequest()throws SQLException{
		Connection conn = DBConnection.getConnection();
		String query = "SELECT * FROM blood_requests";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		List<BloodRequestPojo> request = new ArrayList<>();
		BloodRequestPojo bloodreq;
		
		
		while(rs.next()) {
			bloodreq = new BloodRequestPojo();
			bloodreq.setHospitalName(rs.getString("hospital_name"));
			bloodreq.setRequestedUnits(rs.getInt("requested_units"));
			bloodreq.setRequestId(rs.getInt("request_id"));
			bloodreq.setUrgency(rs.getString("urgency"));
			bloodreq.setStatus(rs.getString("status"));
			bloodreq.setBloodType(rs.getString("blood_type"));
			bloodreq.setRequestDate(rs.getDate("request_date"));
			request.add(bloodreq);
		}
		st.close();
		rs.close();
		return request;
		
		
}
	public static boolean updateRequest(int id,String status) throws SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "UPDATE blood_requests SET status=? where request_id=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1,status);
		ps.setInt(2, id);
		int ans =ps.executeUpdate();
		return ans>0;
		
	}
}
