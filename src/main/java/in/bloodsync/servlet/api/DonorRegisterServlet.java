package in.bloodsync.servlet.api;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.bloodsync.dao.BloodDonorDao;
import in.bloodsync.dbutil.DBConnection;
import in.bloodsync.pojo.BloodDonorPojo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DonorRegisterServlet  extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		String name = req.getParameter("name");
		String Bloodtype = req.getParameter("blood_type");
		String city = req.getParameter("city");
		String ContactNumber = req.getParameter("contact");
		BloodDonorPojo donor = new BloodDonorPojo();
		donor.setName(name);
		donor.setBloodType(Bloodtype);
		donor.setCity(city);
		donor.setContact(ContactNumber);
		RequestDispatcher rd = req.getRequestDispatcher("html/response_handling.html");
		try {
			  boolean ans= BloodDonorDao.AddDonor(donor);
			  if(ans==true) {
				  pw.println("<title>Registeration Successfull</title>");
				  pw.println("<div class=wrapper>");
				  pw.println("<div class=container>");
				  pw.println("<h1 class='green'>Registration Succesfull");
				  pw.println("<p>Thank you for deciding to donate blood ,your Contribution to life </p> ");
				  pw.println("<p> You are now a part of our noble cause</p>");
				  pw.println("<a href='html/index.html' class='btn'>Go to Home</a>");
				  pw.println("</div></div>");
				  
				  
			  }
			  else {
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
			  pw.println("<a href='html/index.html' class='btn'>Please try agian</a>");
			  pw.println("</div></div>");
		}
		finally {
			pw.flush();
			rd.include(req, resp);
		}
	}

}
