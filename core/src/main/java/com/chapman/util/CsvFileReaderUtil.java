package com.chapman.util;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.chapman.model.RawBankCheckingData;

/**
 * @author ashraf_sarhan
 *
 */
public class CsvFileReaderUtil {
	
	protected final Log log = LogFactory.getLog(getClass());

    private static final String [] FILE_HEADER_MAPPING = {"Transaction_Date","Transaction_ID","TranDesc","ExtDesc","Description","Fee","Amount","Other_Charges","Balance","Post_Date","Check_Number"};
	
	public List<RawBankCheckingData> readCsvFile(String fileName) {
		FileReader fileReader = null;
		CSVParser csvFileParser = null;
		//Create the CSVFormat object with the header mapping
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader();
        List<RawBankCheckingData> checkRecords = new ArrayList<RawBankCheckingData>();
        log.debug("entering the try............");
        try {
            //initialize FileReader object
            fileReader = new FileReader(fileName);
            //initialize CSVParser object
            csvFileParser = new CSVParser(fileReader, csvFileFormat);
            //Get a list of CSV file records
            List<CSVRecord> csvRecords = csvFileParser.getRecords(); 
            CurrencyConverter c = new CurrencyConverter();
            DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            log.debug("csvRecords............ "+csvRecords.size());
            for (CSVRecord record : csvRecords) {
/*            	String checkNumber = record.get("Check_Number");
				if(!StringUtils.isBlank(checkNumber)){
					checkNumber = "0";
				}
				RawBankCheckingData data = new RawBankCheckingData(df1.parse(record.get("Transaction_Date")), 
						record.get("Transaction_ID"),
						record.get("TranDesc"),
						record.get("ExtDesc"),
						record.get("Description"), 
						(Double)c.convert(Double.class, record.get("Fee")),
						(Double)c.convert(Double.class, record.get("Amount")), 
						(Double)c.convert(Double.class, record.get("Other_Charges")), 
						(Double)c.convert(Double.class, record.get("Balance")),
						df1.parse(record.get("Post_Date")),
						new Long(checkNumber));*/
            	
            	RawBankCheckingData data = new RawBankCheckingData();
				data.setTransactionDate(df1.parse(record.get("Transaction_Date")));
				data.setTransactionId(record.get("Transaction_ID"));
				data.setTransDesc(record.get("TranDesc"));
				data.setExtDesc(record.get("ExtDesc"));
				data.setDescription(record.get("Description"));
				data.setFee((Double)c.convert(Double.class, record.get("Fee")));
				data.setAmount((Double)c.convert(Double.class, record.get("Amount")));
				data.setOtherCharges((Double)c.convert(Double.class, record.get("Other_Charges")));
				data.setBalance((Double)c.convert(Double.class, record.get("Balance")));
				data.setPostDate(df1.parse(record.get("Post_Date")));
				String checkNumber = record.get("Check_Number");
				if(!StringUtils.isBlank(checkNumber))
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
        return checkRecords;
	}

}
