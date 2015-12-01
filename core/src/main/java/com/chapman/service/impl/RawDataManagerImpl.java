/**
 * 
 */
package com.chapman.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.chapman.dao.RawDataDao;
import com.chapman.model.RawBankCheckingData;
import com.chapman.service.RawDataManager;
import com.chapman.util.CsvFileReaderUtil;;

/**
 * @author or0189783
 *
 */
public class RawDataManagerImpl extends GenericManagerImpl<RawBankCheckingData, Long> implements RawDataManager {

	RawDataDao rawDataDao;
	
	public RawDataManagerImpl() {}

	@Override
	public List<RawBankCheckingData> findDataByTransactionId(String transactionId) {
		return rawDataDao.findDataByTransactionId(transactionId);
	}
	
	public void insertRawCheckingData(String file){
		List<RawBankCheckingData> data = loadRawCheckingData(file);
		for(RawBankCheckingData record : data){
			log.debug("saving "+record.getTransactionId()+" to the database.........................");
			rawDataDao.save(record);
		}
	}
	
	public List<RawBankCheckingData> loadRawCheckingData(String file){
		CsvFileReaderUtil util = new CsvFileReaderUtil();
		List<RawBankCheckingData> result = new ArrayList<RawBankCheckingData>();
		result= util.readCsvFile(file);
		return result;
	}

}
