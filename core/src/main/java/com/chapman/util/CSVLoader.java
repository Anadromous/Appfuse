/**
 * 
 */
package com.chapman.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.joda.time.DateTime;

import com.chapman.dao.RawDataDao;
import com.chapman.dao.hibernate.RawDataDaoHibernate;
import com.chapman.model.RawBankCheckingData;
import com.chapman.service.RawDataManager;
import com.chapman.service.impl.RawDataManagerImpl;

/**
 * @author OR0189783
 *
 */
public class CSVLoader {

	public CSVLoader() {
		// TODO Auto-generated constructor stub
	}

	static final Log log = LogFactory.getLog(CSVLoader.class);
	RawDataManager manager = new RawDataManagerImpl();
	RawDataDao dao = new RawDataDaoHibernate();
	private static SessionFactory sessionFactory;
	private static Configuration configuration = new Configuration()
			.configure();
	CurrencyConverter c = new CurrencyConverter();
	DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		log.debug("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/appfuse", "root", "Redside01");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			System.out.println("Connection established....");
		} else {
			System.out.println("Failed to make connection!");
		}
		CSVLoader loader = new CSVLoader();
		loader.loadCsvData("C:/chapman/Downloads/ExportedTransactions_7-16.csv", connection);

	}

	private void loadCsvData(String file, Connection conn) {
		System.out.println("File: " + file);
		CsvFileReaderUtil util = new CsvFileReaderUtil();
		List<RawBankCheckingData> list = new ArrayList<RawBankCheckingData>();
		PreparedStatement pst = null;
		String sql = "insert into raw_data (trans_id, posting_date, effective_date, trans_type, "
				+ "amount, check_number, ref_number, payee, "
				+ "memo, trans_category, type, balance) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?);";
		
		try {
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			list = util.readCsvFile(file);
			DateTime dt;
			for (RawBankCheckingData record : list) {
				//Transaction ID	Posting Date	Effective Date	Transaction Type	
				//Amount	Check Number	Reference Number	Payee	Memo	Transaction Category	Type	Balance
				log.debug("setting "+record.getTransactionId());
				pst.setString(1, record.getTransactionId());
				pst.setDate(2, new java.sql.Date(record.getPostingDate().getTime()));
				pst.setDate(3, new java.sql.Date(record.getEffectiveDate().getTime()));
				pst.setString(4, record.getTransactionType());
				pst.setDouble(5, record.getAmount());
				if(record.getCheckNumber() != null)
					pst.setLong(6, record.getCheckNumber());
				else
					pst.setLong(6, 0);
				pst.setString(7, record.getReferenceNumber());
				if(!StringUtils.isEmpty(record.getPayee()))
					pst.setString(8, record.getPayee());
				else
					pst.setString(8, " ");
				pst.setString(9, record.getMemo());
				pst.setString(10, record.getTransactionCategory());
				pst.setString(11, record.getType());
				pst.setDouble(12, record.getBalance());
				pst.executeUpdate();
				//conn.commit();
			}
		} catch (Exception e) {
			System.out.println("Error in CsvFileLoader !!!");
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
