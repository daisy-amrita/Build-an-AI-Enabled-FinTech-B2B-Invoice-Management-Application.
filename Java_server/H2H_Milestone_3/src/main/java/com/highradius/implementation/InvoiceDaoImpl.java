package com.highradius.implementation;

import java.util.ArrayList;
import java.util.HashMap;

import com.highradius.connection.*;
import com.highradius.model.Invoice;

public class InvoiceDaoImpl implements InvoiceDao {

	// DatabaseConnection DC=new DatabaseConnection();
	// Method getInvoice should call DatabaseConnection getInvoices method
	@Override
	public ArrayList<Invoice> getInvoice(int index,int size) {
		return DatabaseConnection.getInvoices(index,size);
	}

	// Method insertInvoice should call DatabaseConnection addInvoice method
	@Override
	public boolean insertInvoice(Invoice invoice) {
		return DatabaseConnection.addInvoice(invoice);
	}

	@Override
	public boolean updateInvoice(HashMap<String, String> updateParam) {
		return DatabaseConnection.updateInvoice(updateParam);
	}

	@Override
	public boolean deleteInvoice(int id) {
		return DatabaseConnection.deleteInvoice(id);
	}
	@Override
	public ArrayList<Invoice> getInvoiceByCust(int cust_no,int limit){
		return DatabaseConnection.getInvoicesByCust(cust_no,limit);
	}
	@Override
	public ArrayList<Invoice> getAdvanceSearchResult(long cust_or_id,long cust_no,long sales_org,long limit){
		return DatabaseConnection.getAdvanceSearchResult(cust_or_id,cust_no,sales_org,limit);
	}
	
	@Override
	public long getSLNO() {
		return DatabaseConnection.getSLNO();
	}
}
