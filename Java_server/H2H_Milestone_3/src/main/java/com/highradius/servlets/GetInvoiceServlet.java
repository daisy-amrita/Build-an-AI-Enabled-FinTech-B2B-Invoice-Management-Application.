package com.highradius.servlets;

import com.google.gson.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.highradius.implementation.InvoiceDaoImpl;
import com.highradius.model.Invoice;

/**
 * Servlet implementation class getInvoiceServlet
 */
@WebServlet("/getinvoice")

public class GetInvoiceServlet extends HttpServlet {
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
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			System.out.println("got req");
			setAccessControlHeaders(response);
//		    response.addHeader("Access-Control-Allow-Origin", "*");
//		    response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
//		    response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
//		    response.addHeader("Access-Control-Max-Age", "1728000");
//			String clientOrigin = request.getHeader("origin");            
			int index=1;
			int size=10;
			Gson gson=new Gson();
			if (request.getParameter("START") != null&&request.getParameter("SIZE") != null) {
				index = Integer.parseInt(request.getParameter("START"));
				size=Integer.parseInt(request.getParameter("SIZE"));
			}else if (request.getParameter("START") != null&&request.getParameter("SIZE") == null) {
				index = Integer.parseInt(request.getParameter("START"));
				size = 1;
			}else if (request.getParameter("START") == null&&request.getParameter("SIZE") != null) {
				size = Integer.parseInt(request.getParameter("SIZE"));
				index = 1;
			}
			
			String message = "Invoices from "+index+" to "+((int)index+(int)size-1);
			ArrayList<Invoice> payload = new InvoiceDaoImpl().getInvoice(index,size);
			if(payload.size()<1)
				message="No invoice availabale in range";
			response.getWriter().append("{ \"message\": \"" + message + "\", \"payload\" : " + gson.toJson(payload) + " }");
		} catch (Exception e) {
			System.out.println(e);
			response.getWriter().append("{ \"error\" : \"Please request in proper format\" }");
		}
	}
}
