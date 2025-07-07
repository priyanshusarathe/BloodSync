package in.bloodsync.servlet.api;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminloginServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  resp.setContentType("text/html");
		  PrintWriter pw = resp.getWriter();
		String emailid = req.getParameter("emailId");
		String password = req.getParameter("pwd");
	
		ServletConfig cfc = super.getServletConfig();
		String email = cfc.getInitParameter("emailId");
		String pwd = cfc.getInitParameter("password");
		if(emailid.equalsIgnoreCase(email) && password.equalsIgnoreCase(pwd)) {
			resp.sendRedirect("html/admin/admin_dashboard.html");

			
		}else {
			RequestDispatcher rd = req.getRequestDispatcher("html/response_handling.html");
			  pw.println("<title>Login Denied!</title>");
			  pw.println("<div class=wrapper>");
			  pw.println("<div class=container>");
			  pw.println("<h1 class='green'>Login Denied!");
			  pw.println("<p>Invalid Username or password</p> ");
			  pw.println("<a href='html/admin/admin_login.html' class='btn'>Try again Later</a>");
			  pw.println("</div></div>");
		}
		
	}

}
