package com.highradius.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.highradius.implementation.InvoiceDaoImpl;
import com.highradius.model.Invoice;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/searchinvoice")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		System.out.println("Got here");
	    setAccessControlHeaders(resp);
	    resp.setStatus(HttpServletResponse.SC_OK);
	}

	private void setAccessControlHeaders(HttpServletResponse resp) {
	    resp.setHeader("Access-Control-Allow-Origin", "*");
//	    resp.setHeader("Access-Control-Allow-Methods", "GET");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		System.out.println("got req");
		setAccessControlHeaders(response);
		int cust_no=Integer.parseInt(request.getParameter("CUST_ORD_ID"));
		int limit=Integer.parseInt(request.getParameter("LIMIT"));
		Gson gson=new Gson();
		String message="No thing found";
		
		ArrayList<Invoice> payload = new InvoiceDaoImpl().getInvoiceByCust(cust_no,limit);
		if(payload.size()<1)
			message="No invoice availabale in range";
		response.getWriter().append("{ \"message\": \"" + message + "\", \"payload\" : " + gson.toJson(payload) + " }");
	} catch (Exception e) {
		System.out.println(e);
		response.getWriter().append("{ \"error\" : \"Please request in proper format\" }");
	}
	}


}
