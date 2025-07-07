package in.bloodsync.servlet.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import in.bloodsync.dao.BloodRequestDao;
import in.bloodsync.pojo.BloodDonorPojo;
import in.bloodsync.pojo.BloodRequestPojo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HospitalRequestServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		String hospitalname = req.getParameter("hospital");
		String bloodtype = req.getParameter("required");
		String strUnits = req.getParameter("Blood");
		int bloodunits = Integer.parseInt(strUnits);
		String Urgency = req.getParameter("Urgency");
		BloodRequestPojo request = new BloodRequestPojo();
		request.setBloodType(bloodtype);
		request.setHospitalName(hospitalname);
		request.setRequestedUnits(bloodunits);
		request.setUrgency(Urgency);
		RequestDispatcher rd = req.getRequestDispatcher("html/response_handling.html");
		
		try {
		boolean ans=BloodRequestDao.addBloodRequest(request);
		if(ans==true) {
			 pw.println("<title>Registeration Successfull</title>");
			  pw.println("<div class=wrapper>");
			  pw.println("<div class=container>");
			  pw.println("<h1 class='green'>Registration Succesfull");
			  pw.println("<p>Your blood request has been successfully registered. </p> ");
			  pw.println("<p> We apperciate your support in saving lives</p>");
			  pw.println("<a href='html/index.html' class='btn'>Go to Home</a>");
			  pw.println("</div></div>");
		}else {
			pw.println("<title>Registeration Fail!</title>");
			  pw.println("<div class=wrapper>");
			  pw.println("<div class=container>");
			  pw.println("<h1 class='green'>Registration Fail!");
			  pw.println("<p>Something went wrong try agian later</p> ");
			  pw.println("<p> if the probelm persists,contact support</p>");
			  pw.println("<a href='html/index.html' class='btn'>Go to Home</a>");
			  pw.println("</div></div>");
		}
		}catch(SQLException ex) {
			pw.println("<title>Server Error!</title>");
			  pw.println("<div class=wrapper>");
			  pw.println("<div class=container>");
			  pw.println("<h1 class='green'>Server Error!");
			  pw.println("<p>Exception error</p> ");
			  pw.println("<p>"+ex.getMessage()+"</p>");
			  pw.println("<a href='html/hospital_request.html' class='btn'>Please try agian</a>");
			  pw.println("</div></div>");
		}
		finally {
			pw.flush();
			rd.include(req, resp);
			
		}
		
		
		
		
	}

}
