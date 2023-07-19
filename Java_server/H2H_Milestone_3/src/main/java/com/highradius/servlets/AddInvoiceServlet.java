package com.highradius.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.highradius.implementation.InvoiceDaoImpl;
import com.highradius.model.Invoice;

/**
 * Servlet implementation class AddInvoiceServlet
 */
@WebServlet("/addinvoice")
public class AddInvoiceServlet extends HttpServlet {
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
		
		String payload = "Invoice added";
		try {
			long SL_NO = Long.parseLong(request.getParameter("SL_NO"));
			long CUSTOMER_ORDER_ID = Long.parseLong(request.getParameter("CUSTOMER_ORDER_ID"));
			int SALES_ORG = Integer.parseInt(request.getParameter("SALES_ORG"));
			String DISTRIBUTION_CHANNEL = request.getParameter("DISTRIBUTION_CHANNEL");
			String DIVISION = request.getParameter("DIVISION");
			double RELEASED_CREDIT_VALUE = Double.parseDouble(request.getParameter("RELEASED_CREDIT_VALUE"));
			String PURCHASE_ORDER_TYPE = request.getParameter("PURCHASE_ORDER_TYPE");
			int COMPANY_CODE = Integer.parseInt(request.getParameter("COMPANY_CODE"));
			String ORDER_CREATION_DATE = request.getParameter("ORDER_CREATION_DATE");
			String ORDER_CREATION_TIME = request.getParameter("ORDER_CREATION_TIME");
			String CREDIT_CONTROL_AREA = request.getParameter("CREDIT_CONTROL_AREA");
			int SOLD_TO_PARTY = Integer.parseInt(request.getParameter("SOLD_TO_PARTY"));
			double ORDER_AMOUNT = Double.parseDouble(request.getParameter("ORDER_AMOUNT"));
			String REQUESTED_DELIVERY_DATE = request.getParameter("REQUESTED_DELIVERY_DATE");
			String ORDER_CURRENCY = request.getParameter("ORDER_CURRENCY");
			String CREDIT_STATUS = request.getParameter("CREDIT_STATUS");
			int CUSTOMER_NUMBER = Integer.parseInt(request.getParameter("CUSTOMER_NUMBER"));
			double AMOUNT_IN_USD = Double.parseDouble(request.getParameter("AMOUNT_IN_USD"));
			long UNIQUE_CUST_ID = Long.parseLong(request.getParameter("UNIQUE_CUST_ID"));
			Invoice newInvoice=new Invoice(SL_NO, CUSTOMER_ORDER_ID, SALES_ORG,
					DISTRIBUTION_CHANNEL, DIVISION, RELEASED_CREDIT_VALUE, PURCHASE_ORDER_TYPE, COMPANY_CODE,
					ORDER_CREATION_DATE, ORDER_CREATION_TIME, CREDIT_CONTROL_AREA, SOLD_TO_PARTY, ORDER_AMOUNT,
					REQUESTED_DELIVERY_DATE, ORDER_CURRENCY, CREDIT_STATUS, CUSTOMER_NUMBER, AMOUNT_IN_USD,
					UNIQUE_CUST_ID);
			if (!new InvoiceDaoImpl().insertInvoice(newInvoice)) {
				payload = "Invalide invoice please recheck the details.";
			}
			response.getWriter().append("{ \"message\": \"" + payload + "\" }");
		} catch (Exception e) {
			System.out.println(e);
			response.getWriter().append("{ \"error\" : \"Please request in proper format\" }");
		}
	}

}