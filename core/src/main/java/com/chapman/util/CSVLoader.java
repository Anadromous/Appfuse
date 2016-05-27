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
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		CSVLoader loader = new CSVLoader();
		loader.loadCsvData("C:/chapman/temp/HistoryDownload-Sept-Nov.csv", connection);

	}

	private void loadCsvData(String file, Connection conn) {
		System.out.println("File: " + file);
		CsvFileReaderUtil util = new CsvFileReaderUtil();
		List<RawBankCheckingData> list = new ArrayList<RawBankCheckingData>();
		PreparedStatement pst = null;
		String sql = "insert into raw_data (amount, balance, check_number, description, ext_desc, fee, other_charges, post_date, trans_desc, trans_date, trans_id) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?);";
		
		try {
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			list = util.readCsvFile(file);
			DateTime dt; 
			for (RawBankCheckingData record : list) {
				log.debug("setting "+record.getTransactionId());
				pst.setDouble(1, record.getAmount());
				pst.setDouble(2, record.getBalance());
				if(record.getCheckNumber() != null)
					pst.setLong(3, record.getCheckNumber());
				else
					pst.setLong(3, 0);
				pst.setString(4, record.getDescription());
				if(!StringUtils.isEmpty(record.getExtDesc()))
					pst.setString(5, record.getExtDesc());
				else
					pst.setString(5, " ");
				if(record.getFee() != null)
					pst.setDouble(6, record.getFee());
				else
					pst.setDouble(6,  0.00);
				if(record.getOtherCharges() != null)
					pst.setDouble(7, record.getOtherCharges());
				else
					pst.setDouble(7, 0.00);
				dt = new DateTime(record.getTransactionDate());
				log.debug(".....................date: "+dt+", "+new java.sql.Date(record.getTransactionDate().getTime()));
				pst.setDate(8, new java.sql.Date(record.getPostDate().getTime()));
				pst.setString(9, record.getTransDesc());
				pst.setDate(10, new java.sql.Date(record.getTransactionDate().getTime()));
				pst.setString(11, record.getTransactionId());
				// pst.setLong(12, NULL);
				log.debug("......"+pst.toString());
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
