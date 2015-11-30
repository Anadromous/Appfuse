package com.chapman.util;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.chapman.model.RawBankCheckingData;

/**
 * @author ashraf_sarhan
 *
 */
public class CsvFileReader {
	
	//CSV file header
    private static final String [] FILE_HEADER_MAPPING = {"id","firstName","lastName","gender","age"};
	
	//Student attributes
	private static final String STUDENT_ID = "id";
	private static final String STUDENT_FNAME = "firstName";
	private static final String STUDENT_LNAME = "lastName";
	private static final String STUDENT_GENDER = "gender"; 
	private static final String STUDENT_AGE = "age";
	
	public static void readCsvFile(String fileName) {
		FileReader fileReader = null;
		CSVParser csvFileParser = null;
		//Create the CSVFormat object with the header mapping
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);
        List<RawBankCheckingData> checkRecords = new ArrayList<RawBankCheckingData>();
        try {
        	//Create a new list of student to be filled by CSV file data 
        	List<RawBankCheckingData> students = new ArrayList<RawBankCheckingData>();
            //initialize FileReader object
            fileReader = new FileReader(fileName);
            //initialize CSVParser object
            csvFileParser = new CSVParser(fileReader, csvFileFormat);
            //Get a list of CSV file records
            List<CSVRecord> csvRecords = csvFileParser.getRecords(); 
            //Read the CSV file records starting from the second record to skip the header
            for (CSVRecord record : csvRecords) {
				RawBankCheckingData data = new RawBankCheckingData();
				// 10/31/2015 12:00:00 AM
				DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				data.setTransactionDate(df1.parse(record.get("Transaction_Date")));
				// data.setTransactionId((Date)dateUtil.convertToDate(String.class,
				// record.get("Transaction_ID"), "dd/MM/yyyy HH:mm:ss")));
				data.setTransactionId(record.get("Transaction_ID"));
				data.setTransDesc(record.get("TranDesc"));
				data.setExtDesc(record.get("ExtDesc"));
				data.setDescription(record.get("Description"));
				data.setFee(new BigDecimal(record.get("Fee")));
				data.setAmount(new BigDecimal(record.get("Amount")));
				data.setOtherCharges(new BigDecimal(record.get("Other_Charges")));
				data.setBalance(new BigDecimal(record.get("Balance")));
				DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				data.setPostDate(df2.parse(record.get("Post_Date")));
				String checkNumber = record.get("Check_Number");
				data.setCheckNumber(new Long(checkNumber));
				checkRecords.add(data);	
			}
        } 
        catch (Exception e) {
        	System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
                csvFileParser.close();
            } catch (IOException e) {
            	System.out.println("Error while closing fileReader/csvFileParser !!!");
                e.printStackTrace();
            }
        }
	}

}
