package com.highradius.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.highradius.implementation.InvoiceDaoImpl;
//import com.highradius.model.Invoice;

/**
 * Servlet implementation class DeleteInvoiceServlet
 */
@WebServlet("/deleteinvoice")
public class DeleteInvoiceServlet extends HttpServlet {

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
	    resp.setHeader("Access-Control-Allow-Methods", "DELETE");
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setAccessControlHeaders(response);
		
		try {
			String payload;
			int number = Integer.parseInt(request.getParameter("SL_NO"));

			if (new InvoiceDaoImpl().deleteInvoice(number))
				payload = "Invoice deleted";
			else
				payload = "Invoice not present";
			response.getWriter().append("{ \"message\": \"" + payload + "\" }");
		} catch (Exception e) {
			System.out.println(e);
			response.getWriter().append("{ \"error\" : \"Please request in proper format\" }");
		}
	}

}
