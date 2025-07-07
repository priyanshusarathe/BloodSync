package in.bloodsync.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import in.bloodsync.dbutil.DBConnection;
import in.bloodsync.pojo.BloodDonorPojo;

public class BloodDonorDao {
	
	public static boolean AddDonor(BloodDonorPojo donorPojo)throws SQLException {
		String BloodType =donorPojo.getBloodType();
		String name = donorPojo.getName();
		String city = donorPojo.getCity();
        String Contact = donorPojo.getContact();
        int bloodunit = donorPojo.getBloodUnit();
        int donorId = donorPojo.getDonorId();
        Connection conn = DBConnection.getConnection();
        String query = "INSERT INTO blood_donors (name,blood_type,city,contact) VALUES(?,?,?,?)";
        
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, BloodType);
        ps.setString(3,city);
        ps.setString(4, Contact);
        
        int  result = ps.executeUpdate();
        ps.close();
        if(result==1) {
        	return true;
        }else {
        	return false;
        }
		}
	 
	public static List<BloodDonorPojo>  getAllDonors() throws SQLException{
		  Connection conn = DBConnection.getConnection();
		  String query = "select * from blood_donors";
		  Statement st = conn.createStatement();
		  ResultSet rs = st.executeQuery(query);
		  List<BloodDonorPojo> donors = new ArrayList<>();
		  BloodDonorPojo donor;
		  while(rs.next()) {
			  donor = new BloodDonorPojo();
			  donor.setDonorId(rs.getInt("donors_id"));
			  donor.setName(rs.getString("name"));
			  donor.setCity(rs.getString("city"));
			  donor.setBloodType(rs.getString("blood_type"));
			  donor.setContact(rs.getString("contact"));
              donor.setBloodUnit(rs.getInt("blood_unit"));
              donors.add(donor);
		  }
		  st.close();
		  rs.close();
		  return donors;
		  
		
	}
	public static boolean deleteDonor(int id)throws SQLException{
		  Connection conn = DBConnection.getConnection();
	        String query = "Delete from blood_donors where  donors_id=?";
	        
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1,id);
	     int executeUpdate=   ps.executeUpdate();
	     return executeUpdate>0;
		
	}
	
	public static boolean updateDonor(int id,int units)throws SQLException{
		Connection conn = DBConnection.getConnection();
		String query = "UPDATE blood_donors SET blood_unit=? WHERE donors_id=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, units);
		ps.setInt(2, id);
		conn.setAutoCommit(false);
		int ans = ps.executeUpdate();
		int updateStock =BloodStockDao.updateStock(id,units);
		if(ans>0 && updateStock>0) {
			conn.setAutoCommit(true);
			return true;
		}else {
			conn.rollback();
			return false;
		}
		
	}
}
