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
 * Servlet implementation class AdvancedSearch
 */
@WebServlet("/advancesearch")
public class AdvancedSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Got here");
		setAccessControlHeaders(resp);
		resp.setStatus(HttpServletResponse.SC_OK);
	}

	private void setAccessControlHeaders(HttpServletResponse resp) {
		resp.setHeader("Access-Control-Allow-Origin", "*");
//	    resp.setHeader("Access-Control-Allow-Methods", "GET");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			System.out.println("got req");
			setAccessControlHeaders(response);
			long cust_or_id =-1;
			long cust_no =-1;
			long sales_org = -1;
			long limit = 1000;
			if(request.getParameter("CUST_ORD_ID")!=null)
			cust_or_id=Long.parseLong(request.getParameter("CUST_ORD_ID"));
			if(request.getParameter("CUST_NO")!=null)
			cust_no =Long.parseLong(request.getParameter("CUST_NO"));
			if(request.getParameter("SALE_ORG")!=null)
			sales_org =Long.parseLong(request.getParameter("SALE_ORG"));
			if(request.getParameter("LIMIT")!=null)
			limit = Long.parseLong(request.getParameter("LIMIT"));
			Gson gson = new Gson();
			String message = "No thing found";

			ArrayList<Invoice> payload = new InvoiceDaoImpl().getAdvanceSearchResult(cust_or_id,cust_no,sales_org, limit);
			if (payload.size() < 1)
				message = "No invoice availabale in range";
			response.getWriter()
					.append("{ \"message\": \"" + message + "\", \"payload\" : " + gson.toJson(payload) + " }");
		} catch (Exception e) {
			System.out.println(e);
			response.getWriter().append("{ \"error\" : \"Please request in proper format\" }");
		}
	}

}
