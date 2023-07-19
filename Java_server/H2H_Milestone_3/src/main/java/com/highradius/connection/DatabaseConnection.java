package com.highradius.connection;

import com.highradius.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

public class DatabaseConnection {
//	List of Invoices
	static ArrayList<Invoice> InvoiceList = new ArrayList<Invoice>();
//	Method getInvoices to return list of invoices
	public static int no_of_org = 10;
	public static Connection connection;
	static {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "amrita");
			System.out.print("Connected");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	static public ArrayList<Invoice> getInvoicesByCust(int cust_no,int limit){
		try {
			InvoiceList.clear();
			String fetch_statement = "select * from h2h_oap where CUSTOMER_ORDER_ID = ? limit ?;";
			PreparedStatement stmt = connection.prepareStatement(fetch_statement);
			stmt.setLong(1, cust_no);
			stmt.setLong(2, limit);
			System.out.println(stmt);
			ResultSet resultset = stmt.executeQuery();
			while (resultset.next()) {
				long SL_NO = resultset.getLong("SL_NO");
				long CUSTOMER_ORDER_ID = resultset.getLong("CUSTOMER_ORDER_ID");
				int SALES_ORG = resultset.getInt("SALES_ORG");
				String DISTRIBUTION_CHANNEL = resultset.getString("DISTRIBUTION_CHANNEL");
				String DIVISION = resultset.getString("DIVISION");
				double RELEASED_CREDIT_VALUE = resultset.getDouble("RELEASED_CREDIT_VALUE");
				String PURCHASE_ORDER_TYPE = resultset.getString("PURCHASE_ORDER_TYPE");
				int COMPANY_CODE = resultset.getInt("COMPANY_CODE");
				String ORDER_CREATION_DATE = resultset.getString("ORDER_CREATION_DATE");
				String ORDER_CREATION_TIME = resultset.getString("ORDER_CREATION_TIME");
				String CREDIT_CONTROL_AREA = resultset.getString("CREDIT_CONTROL_AREA");
				int SOLD_TO_PARTY = resultset.getInt("SOLD_TO_PARTY");
				double ORDER_AMOUNT = resultset.getDouble("ORDER_AMOUNT");
				String REQUESTED_DELIVERY_DATE = resultset.getString("REQUESTED_DELIVERY_DATE");
				String ORDER_CURRENCY = resultset.getString("ORDER_CURRENCY");
				String CREDIT_STATUS = resultset.getString("CREDIT_STATUS");
				int CUSTOMER_NUMBER = resultset.getInt("CUSTOMER_NUMBER");
				double AMOUNT_IN_USD = resultset.getDouble("AMOUNT_IN_USD");
				long UNIQUE_CUST_ID = resultset.getLong("UNIQUE_CUST_ID");
				InvoiceList.add(new Invoice(SL_NO, CUSTOMER_ORDER_ID, SALES_ORG, DISTRIBUTION_CHANNEL, DIVISION,
						RELEASED_CREDIT_VALUE, PURCHASE_ORDER_TYPE, COMPANY_CODE, ORDER_CREATION_DATE,
						ORDER_CREATION_TIME, CREDIT_CONTROL_AREA, SOLD_TO_PARTY, ORDER_AMOUNT, REQUESTED_DELIVERY_DATE,
						ORDER_CURRENCY, CREDIT_STATUS, CUSTOMER_NUMBER, AMOUNT_IN_USD, UNIQUE_CUST_ID));

			}
		} catch (Exception e) {
			System.out.println("Getting Invoice Error:" + e);
		}
		return (ArrayList<Invoice>) InvoiceList.clone();
	}

	@SuppressWarnings("unchecked")
	static public ArrayList<Invoice> getInvoices(int index,int size) {
		try {
			InvoiceList.clear();
			String fetch_statement = "select * from h2h_oap limit ? , ? ;";
			PreparedStatement stmt = connection.prepareStatement(fetch_statement);
			stmt.setLong(1, index);
			stmt.setInt(2, size);
			System.out.println(stmt);
			ResultSet resultset = stmt.executeQuery();
			while (resultset.next()) {
				long SL_NO = resultset.getLong("SL_NO");
				long CUSTOMER_ORDER_ID = resultset.getLong("CUSTOMER_ORDER_ID");
				int SALES_ORG = resultset.getInt("SALES_ORG");
				String DISTRIBUTION_CHANNEL = resultset.getString("DISTRIBUTION_CHANNEL");
				String DIVISION = resultset.getString("DIVISION");
				double RELEASED_CREDIT_VALUE = resultset.getDouble("RELEASED_CREDIT_VALUE");
				String PURCHASE_ORDER_TYPE = resultset.getString("PURCHASE_ORDER_TYPE");
				int COMPANY_CODE = resultset.getInt("COMPANY_CODE");
				String ORDER_CREATION_DATE = resultset.getString("ORDER_CREATION_DATE");
				String ORDER_CREATION_TIME = resultset.getString("ORDER_CREATION_TIME");
				String CREDIT_CONTROL_AREA = resultset.getString("CREDIT_CONTROL_AREA");
				int SOLD_TO_PARTY = resultset.getInt("SOLD_TO_PARTY");
				double ORDER_AMOUNT = resultset.getDouble("ORDER_AMOUNT");
				String REQUESTED_DELIVERY_DATE = resultset.getString("REQUESTED_DELIVERY_DATE");
				String ORDER_CURRENCY = resultset.getString("ORDER_CURRENCY");
				String CREDIT_STATUS = resultset.getString("CREDIT_STATUS");
				int CUSTOMER_NUMBER = resultset.getInt("CUSTOMER_NUMBER");
				double AMOUNT_IN_USD = resultset.getDouble("AMOUNT_IN_USD");
				long UNIQUE_CUST_ID = resultset.getLong("UNIQUE_CUST_ID");
				InvoiceList.add(new Invoice(SL_NO, CUSTOMER_ORDER_ID, SALES_ORG, DISTRIBUTION_CHANNEL, DIVISION,
						RELEASED_CREDIT_VALUE, PURCHASE_ORDER_TYPE, COMPANY_CODE, ORDER_CREATION_DATE,
						ORDER_CREATION_TIME, CREDIT_CONTROL_AREA, SOLD_TO_PARTY, ORDER_AMOUNT, REQUESTED_DELIVERY_DATE,
						ORDER_CURRENCY, CREDIT_STATUS, CUSTOMER_NUMBER, AMOUNT_IN_USD, UNIQUE_CUST_ID));

			}
		} catch (Exception e) {
			System.out.println("Getting Invoice Error:" + e);
		}
		return (ArrayList<Invoice>) InvoiceList.clone();
	}
	
	
	@SuppressWarnings("unchecked")
	static public ArrayList<Invoice> getAdvanceSearchResult(long cust_or_id,long cust_no,long sales_org,long limit) {
		try {
			InvoiceList.clear();
			String fetch_statement;
			PreparedStatement stmt;
			if (cust_or_id < 0 && cust_no < 0 && sales_org > 0) {
				fetch_statement = "select * from h2h_oap where SALES_ORG = ? ;";
				stmt = connection.prepareStatement(fetch_statement);
				stmt.setLong(1, sales_org);
			} else if (cust_or_id < 0 && cust_no > 0 && sales_org > 0) {
				fetch_statement = "select * from h2h_oap where SALES_ORG = ? AND CUSTOMER_NUMBER = ? ;";
				stmt = connection.prepareStatement(fetch_statement);
				stmt.setLong(1, sales_org);
				stmt.setLong(2, cust_no);
			}
			else if (cust_or_id > 0 && cust_no < 0 && sales_org > 0) {
				fetch_statement = "select * from h2h_oap where SALES_ORG = ? AND CUSTOMER_ORDER_ID = ? ;";
				stmt = connection.prepareStatement(fetch_statement);
				stmt.setLong(1, sales_org);
				stmt.setLong(2, cust_or_id);
			}
			
			else if (cust_or_id < 0 && cust_no > 0 && sales_org < 0) {
				fetch_statement = "select * from h2h_oap where CUSTOMER_NO = ? ;";
				stmt = connection.prepareStatement(fetch_statement);
				stmt.setLong(1, cust_no);
			}
			else if (cust_or_id > 0 && cust_no > 0 && sales_org < 0) {
				fetch_statement = "select * from h2h_oap where CUSTOMER_NUMBER = ? AND CUSTOMER_ORDER_ID = ? ;";
				stmt = connection.prepareStatement(fetch_statement);
				stmt.setLong(1, cust_no);
				stmt.setLong(2, cust_or_id);
			}
			
			else if (cust_or_id > 0 && cust_no < 0 && sales_org < 0) {
				fetch_statement = "select * from h2h_oap where CUSTOMER_ORDER_ID = ? ;";
				stmt = connection.prepareStatement(fetch_statement);
				stmt.setLong(1, cust_or_id);
			} else {
				fetch_statement = "select * from h2h_oap where SALES_ORG = ? AND CUSTOMER_NUMBER = ? AND CUSTOMER_ORDER_ID = ? ;";
				stmt = connection.prepareStatement(fetch_statement);
				stmt.setLong(1, sales_org);
				stmt.setLong(2, cust_no);
				stmt.setLong(3, cust_or_id);
			}
			

			System.out.println(stmt);
			ResultSet resultset = stmt.executeQuery();
			while (resultset.next()) {
				long SL_NO = resultset.getLong("SL_NO");
				long CUSTOMER_ORDER_ID = resultset.getLong("CUSTOMER_ORDER_ID");
				int SALES_ORG = resultset.getInt("SALES_ORG");
				String DISTRIBUTION_CHANNEL = resultset.getString("DISTRIBUTION_CHANNEL");
				String DIVISION = resultset.getString("DIVISION");
				double RELEASED_CREDIT_VALUE = resultset.getDouble("RELEASED_CREDIT_VALUE");
				String PURCHASE_ORDER_TYPE = resultset.getString("PURCHASE_ORDER_TYPE");
				int COMPANY_CODE = resultset.getInt("COMPANY_CODE");
				String ORDER_CREATION_DATE = resultset.getString("ORDER_CREATION_DATE");
				String ORDER_CREATION_TIME = resultset.getString("ORDER_CREATION_TIME");
				String CREDIT_CONTROL_AREA = resultset.getString("CREDIT_CONTROL_AREA");
				int SOLD_TO_PARTY = resultset.getInt("SOLD_TO_PARTY");
				double ORDER_AMOUNT = resultset.getDouble("ORDER_AMOUNT");
				String REQUESTED_DELIVERY_DATE = resultset.getString("REQUESTED_DELIVERY_DATE");
				String ORDER_CURRENCY = resultset.getString("ORDER_CURRENCY");
				String CREDIT_STATUS = resultset.getString("CREDIT_STATUS");
				int CUSTOMER_NUMBER = resultset.getInt("CUSTOMER_NUMBER");
				double AMOUNT_IN_USD = resultset.getDouble("AMOUNT_IN_USD");
				long UNIQUE_CUST_ID = resultset.getLong("UNIQUE_CUST_ID");
				InvoiceList.add(new Invoice(SL_NO, CUSTOMER_ORDER_ID, SALES_ORG, DISTRIBUTION_CHANNEL, DIVISION,
						RELEASED_CREDIT_VALUE, PURCHASE_ORDER_TYPE, COMPANY_CODE, ORDER_CREATION_DATE,
						ORDER_CREATION_TIME, CREDIT_CONTROL_AREA, SOLD_TO_PARTY, ORDER_AMOUNT, REQUESTED_DELIVERY_DATE,
						ORDER_CURRENCY, CREDIT_STATUS, CUSTOMER_NUMBER, AMOUNT_IN_USD, UNIQUE_CUST_ID));

			}
		} catch (Exception e) {
			System.out.println("Getting Invoice Error:" + e);
		}
		return (ArrayList<Invoice>) InvoiceList.clone();
	}

//	Method addInvoice to Add Invoice to the list
	static public boolean addInvoice(Invoice invoice) {
		try {
			String fetch_statement = "insert into h2h_oap (SL_NO,CUSTOMER_ORDER_ID,SALES_ORG,DISTRIBUTION_CHANNEL,DIVISION,RELEASED_CREDIT_VALUE,PURCHASE_ORDER_TYPE,COMPANY_CODE,ORDER_CREATION_DATE,ORDER_CREATION_TIME,CREDIT_CONTROL_AREA,SOLD_TO_PARTY,ORDER_AMOUNT,REQUESTED_DELIVERY_DATE,ORDER_CURRENCY,CREDIT_STATUS,CUSTOMER_NUMBER,AMOUNT_IN_USD,UNIQUE_CUST_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement stmt = connection.prepareStatement(fetch_statement);
//			stmt.setInt(1, no_of_org);
			stmt.setLong(1, invoice.getSL_NO());
			stmt.setLong(2, invoice.getCUSTOMER_ORDER_ID());
			stmt.setInt(3, invoice.getSALES_ORG());
			stmt.setString(4, invoice.getDISTRIBUTION_CHANNEL());
			stmt.setString(5, invoice.getDIVISION());
			stmt.setDouble(6, invoice.getRELEASED_CREDIT_VALUE());
			stmt.setString(7, invoice.getPURCHASE_ORDER_TYPE());
			stmt.setInt(8, invoice.getCOMPANY_CODE());
			stmt.setString(9, invoice.getORDER_CREATION_DATE());
			stmt.setString(10, invoice.getORDER_CREATION_TIME());
			stmt.setString(11, invoice.getCREDIT_CONTROL_AREA());
			stmt.setInt(12, invoice.getSOLD_TO_PARTY());
			stmt.setDouble(13, invoice.getORDER_AMOUNT());
			stmt.setString(14, invoice.getREQUESTED_DELIVERY_DATE());
			stmt.setString(15, invoice.getORDER_CURRENCY());
			stmt.setString(16, invoice.getCREDIT_STATUS());
			stmt.setInt(17, invoice.getCUSTOMER_NUMBER());
			stmt.setDouble(18, invoice.getAMOUNT_IN_USD());
			stmt.setFloat(19, invoice.getUNIQUE_CUST_ID());

//			connection.commit();
			InvoiceList.add(invoice);
			return stmt.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.println("Adding Invoice Error:" + e);
			return false;
		}

	}

	static public boolean updateInvoice(HashMap<String, String> updateParam) {
		try {
			StringBuffer arguments = new StringBuffer("");
			for (HashMap.Entry<String, String> param : updateParam.entrySet()) {
				if (param.getKey().equals("SL_NO")) {
					continue;
				}
				arguments.append(param.getKey()+" = ? ,");
			}
			arguments = arguments.deleteCharAt(arguments.length() - 1);
//		UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;
			List<String> strings = Arrays.asList(new String[] {  "DISTRIBUTION_CHANNEL", "DIVISION",
					"PURCHASE_ORDER_TYPE", "ORDER_CREATION_DATE", "ORDER_CREATION_TIME", "CREDIT_CONTROL_AREA",
					"REQUESTED_DELIVERY_DATE", "ORDER_CURRENCY", "CREDIT_STATUS" });
			List<String> ints = Arrays.asList(
					new String[] { "CUSTOMER_ORDER_ID","SALES_ORG","RELEASED_CREDIT_VALUE", "ORDER_AMOUNT", "AMOUNT_IN_USD", "UNIQUE_CUST_ID" });
			
			String fetch_statement = "UPDATE h2h_oap set " + arguments.toString() + " where SL_NO=?;";
			PreparedStatement stmt = connection.prepareStatement(fetch_statement);
			System.out.println(fetch_statement);
			int i = 1;
			for (HashMap.Entry<String, String> param : updateParam.entrySet()) {
				if (param.getKey().equals("SL_NO")) {
					continue;
				}
				if (strings.contains(param.getKey())) {
					stmt.setString(i++, param.getValue());
				} else if (ints.contains(param.getKey())) {
					stmt.setInt(i++, Integer.parseInt(param.getValue()));
				} else {
					stmt.setFloat(i++, Float.parseFloat(param.getValue()));
				}
			}
			stmt.setInt(i, Integer.parseInt(updateParam.get("SL_NO")));
			System.out.println(stmt);
			return stmt.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	static public boolean deleteInvoice(int index) {
		try {
			String delete_statement = "DELETE FROM h2h_oap WHERE SL_NO=?;";
			PreparedStatement stmt = connection.prepareStatement(delete_statement);
			stmt.setInt(1, index);
			return stmt.executeUpdate() > 0;
//			 InvoiceList.set(index, invoice);
		} catch (Exception e) {
			System.out.println("Deleting Invoice Error:" + e);
			return false;
		}
	}
	static public long getSLNO() {
		try {
			String statement = "SELECT MAX(SL_NO) FROM h2h_oap;";
			PreparedStatement stmt = connection.prepareStatement(statement);
			ResultSet resultset = stmt.executeQuery();
			while(resultset.next()) {
				return resultset.getInt(1);								
			}
			return -1;
//			 InvoiceList.set(index, invoice);
		} catch (Exception e) {
			System.out.println("Deleting Invoice Error:" + e);
			return -1;
		} 
	}
	static public HashMap getBarChartData(String dist,int cust_no) {
		try {
			String statement;
			PreparedStatement stmt;
			System.out.println(dist);
			if(cust_no>0 && dist==null) {
				statement = "select sum(AMOUNT_IN_USD) as total,DISTRIBUTION_CHANNEL from h2h_oap where CUSTOMER_NUMBER=? group by DISTRIBUTION_CHANNEL;";
				stmt = connection.prepareStatement(statement);
				stmt.setInt(1, cust_no);
				
			}else if(cust_no<0 && dist!=null) {
				statement = "select sum(AMOUNT_IN_USD) as total,DISTRIBUTION_CHANNEL from h2h_oap where DISTRIBUTION_CHANNEL=?;";
				stmt = connection.prepareStatement(statement);
				stmt.setString(1, dist);
			}else {
				statement = "select sum(AMOUNT_IN_USD) as total,DISTRIBUTION_CHANNEL from h2h_oap where DISTRIBUTION_CHANNEL= ? AND CUSTOMER_NUMBER=? group by DISTRIBUTION_CHANNEL;";
				stmt = connection.prepareStatement(statement);
				stmt.setString(1, dist);
				stmt.setInt(2, cust_no);
			}
			ResultSet resultset = stmt.executeQuery();
			HashMap <String,Double> result=new HashMap<String,Double>();
			while(resultset.next()) {
				result.put(resultset.getString(2),resultset.getDouble(1));
			}
//			System.out.println(result);
			return result;
//			 InvoiceList.set(index, invoice);
		} catch (Exception e) {
			System.out.println("Deleting Invoice Error:" + e);
			return null;
		} 
	}
	//select count(*),IS_OPEN from h2h_oap where CUSTOMER_NUMBER=1210351400 group by IS_OPEN;
	static public float getPieChartData(String dist,int cust_no) {
		try {
			String statement;
			PreparedStatement stmt;
			System.out.println(dist);
			if(cust_no>0 && dist==null) {
				statement = "select count(*) from h2h_oap where CUSTOMER_NUMBER=? group by IS_OPEN;";
				stmt = connection.prepareStatement(statement);
				stmt.setInt(1, cust_no);
				
			}else if(cust_no<0 && dist!=null) {
				statement = "select count(*) from h2h_oap where DISTRIBUTION_CHANNEL=? group by IS_OPEN;";
				stmt = connection.prepareStatement(statement);
				stmt.setString(1, dist);
			}else {
				statement = "select count(*) from h2h_oap where DISTRIBUTION_CHANNEL=? AND CUSTOMER_NUMBER=? group by IS_OPEN;";
				stmt = connection.prepareStatement(statement);
				stmt.setString(1, dist);
				stmt.setInt(2, cust_no);
			}
			System.out.println(stmt);
			ResultSet resultset = stmt.executeQuery();
			float number[]=new float[2];
			int i=0;
			while(resultset.next()) {
				number[i++]=resultset.getFloat(1);
			}
			System.out.println(number[0]);
			System.out.println(number[1]);
			return number[0]/(number[0]+number[1]);
//			 InvoiceList.set(index, invoice);
		} catch (Exception e) {
			System.out.println("Deleting Invoice Error:" + e);
			return 0;
		} 
	}
}