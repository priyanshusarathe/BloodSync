package in.bloodsync.servlet.apis;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import in.bloodsync.dao.BloodStockDao;
import in.bloodsync.pojo.BloodStockPojo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminBloodStockServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setContentType("application/json");
	PrintWriter pw = resp.getWriter();
     Gson g = new Gson();
	
	try {
	List<BloodStockPojo> bloodst = BloodStockDao.getAllBoodStock();
	  String BloodstockJson =  g.toJson(bloodst);
	  pw.print(BloodstockJson);
	  
	}catch(SQLException ex) {
		String errorJson = g.toJson(Map .of("status", "failed", "message", ex.getMessage()));
		pw.print(errorJson);

	}finally {
		pw.close();
		}
	
}
}
