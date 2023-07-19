package com.highradius.servlets;

import com.google.gson.Gson;
import com.highradius.connection.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap; 
/**
 * Servlet implementation class GetChartData
 */

//select sum(AMOUNT_IN_USD) as total,DISTRIBUTION_CHANNEL from h2h_oap where CUSTOMER_NUMBER=1210351400 group by DISTRIBUTION_CHANNEL;

@WebServlet("/chartdata")
public class GetChartData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
	    setAccessControlHeaders(resp);
	    resp.setStatus(HttpServletResponse.SC_OK);
	}

	private void setAccessControlHeaders(HttpServletResponse resp) {
	    resp.setHeader("Access-Control-Allow-Origin", "*");
//	    resp.setHeader("Access-Control-Allow-Methods", "GET");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{setAccessControlHeaders(response);
		
		String dist=request.getParameter("DIST");
		int cust_no=-1;
		if(request.getParameter("CUST_NO")!=null)
			cust_no=Integer.parseInt(request.getParameter("CUST_NO"));
		
		Gson gson=new Gson();
			response.getWriter().append("{ \"message\": \"" + "Hello" + "\", \"barData\" : " + gson.toJson(DatabaseConnection.getBarChartData(dist,cust_no)) + ", \"pieData\" : " + gson.toJson(DatabaseConnection.getPieChartData(dist,cust_no)) + " }");
		}
		catch(Exception e) {
			System.out.println(e);
			response.getWriter().append("{ \"error\" : \"Please request in proper format\" }");
		}
	}

}
