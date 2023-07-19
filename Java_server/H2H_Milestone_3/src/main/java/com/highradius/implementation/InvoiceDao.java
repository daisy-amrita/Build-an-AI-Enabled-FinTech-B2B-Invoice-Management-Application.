package com.highradius.implementation;
import com.highradius.model.Invoice;
import java.util.ArrayList;
import java.util.HashMap;
//It is an interface
public interface InvoiceDao{
	//Method getInvoice return list of invoices
	ArrayList<Invoice> getInvoice(int index,int size);
	
	ArrayList<Invoice> getInvoiceByCust(int cust_no,int limit);
	//Method insertInvoice takes  invoice object
	boolean insertInvoice(Invoice invoice);
	//Method updateInvoice takes id, and invoice object to update
	boolean updateInvoice(HashMap<String,String> updateParam);
	//Method deleteInvoice takes id to be deleted for invoice
	boolean deleteInvoice(int id);
	ArrayList<Invoice> getAdvanceSearchResult(long cust_or_id,long cust_no,long sales_org,long limit);
	long getSLNO();
	
}
