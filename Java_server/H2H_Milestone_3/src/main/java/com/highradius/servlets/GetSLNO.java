package com.highradius.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.highradius.implementation.InvoiceDaoImpl;

/**
 * Servlet implementation class GetSLNO
 */
@WebServlet("/getslno")
public class GetSLNO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("got req");
			setAccessControlHeaders(response);
			long payload = new InvoiceDaoImpl().getSLNO();
			String message="Error";
			if(payload>0)
				message="success";
			response.getWriter().append("{ \"message\": \"" + message + "\", \"payload\" : " +payload+ " }");
		} catch (Exception e) {
			System.out.println(e);
			response.getWriter().append("{ \"error\" : \"Please request in proper format\" }");
		}
	}

}
