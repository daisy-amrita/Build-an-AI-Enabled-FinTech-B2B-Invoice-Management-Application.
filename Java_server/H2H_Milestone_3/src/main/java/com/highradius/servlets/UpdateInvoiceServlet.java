package com.highradius.servlets;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.highradius.implementation.InvoiceDaoImpl;

/**
 * Servlet implementation class UpdateInvoiceServlet
 */

@WebServlet("/updateinvoice")
public class UpdateInvoiceServlet extends HttpServlet {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setAccessControlHeaders(response);
		try {
			System.out.println(request);
			String payload="Please request in proper format";
			Enumeration<String> params=request.getParameterNames();
			HashMap<String,String> updateParam=new HashMap<String,String>();
			while(params.hasMoreElements()) {
				String name=params.nextElement();
				System.out.println(name+"What");
				updateParam.put(name,request.getParameter(name));
			}
			if(new InvoiceDaoImpl().updateInvoice(updateParam)) {
				payload="Invoice updated";
			}
				
			response.getWriter().append("{ \"message\": \"" + payload + "\" }");
		} catch (Exception e) {
			response.getWriter().append("{ \"error\" : \"Please request in proper format\" }");
		}
	}

}
