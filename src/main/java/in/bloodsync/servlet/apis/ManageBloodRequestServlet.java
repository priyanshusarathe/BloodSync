package in.bloodsync.servlet.apis;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import in.bloodsync.dao.BloodRequestDao;
import in.bloodsync.pojo.BloodRequestPojo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ManageBloodRequestServlet extends HttpServlet{
 @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setContentType("application/json");
	PrintWriter pw = resp.getWriter();
	Gson gson = new Gson();
	
     try {
    	List<BloodRequestPojo>requestsList= BloodRequestDao.getAllBloodRequest();
    	String jsonData = gson.toJson(requestsList);
    	pw.print(jsonData);
    	
     }catch(SQLException ex) {
    	 String errorJson = gson.toJson(Map.of("status","failed","message",ex.getMessage()));
    	 pw.print(errorJson);
     }finally {
    	 pw.close();
     }
 }
 @Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		PrintWriter pw = resp.getWriter();
		Gson gson = new Gson();
		String Strid = req.getParameter("id");
		int id = Integer.parseInt(Strid);
		String status = req.getParameter("status");
		try {
			boolean request = BloodRequestDao.updateRequest(id,status);
			if(request) {
				String jsonData = gson.toJson(Map.of("status","success","message","request approved succesfully"));
				pw.print(jsonData);
			}else {
				String jsonData = gson.toJson(Map.of("status","failed","message","request rejected"));
				pw.print(jsonData);
			}
		}catch(SQLException ex) {
			String jsonData = gson.toJson(Map.of("status","error","message",ex.getMessage()));
			pw.print(jsonData);
		}finally{
			pw.close();
		}
	}
	
}
