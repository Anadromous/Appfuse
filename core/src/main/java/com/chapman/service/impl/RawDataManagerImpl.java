/**
 * 
 */
package com.chapman.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.chapman.dao.RawDataDao;
import com.chapman.dao.hibernate.RawDataDaoHibernate;
import com.chapman.model.RawBankCheckingData;
import com.chapman.service.RawDataManager;
import com.chapman.util.CsvFileReaderUtil;;

/**
 * @author or0189783
 *
 */
public class RawDataManagerImpl extends GenericManagerImpl<RawBankCheckingData, Long> implements RawDataManager {

	private RawDataDao dao;
	public RawDataManagerImpl() {}

	@Override
	public List<RawBankCheckingData> findDataByTransactionId(String transactionId) {
		return dao.findDataByTransactionId(transactionId);
	}
	
	@Override
	public RawBankCheckingData insertRawCheckingData(final RawBankCheckingData data){

		try{
			log.debug("..................................................................");
			RawBankCheckingData d = dao.save(data);
			log.debug("insertRawCheckingData data...................... "+d.getTransDesc());
			return d;
		}catch(final Exception e){
			e.printStackTrace();
            log.warn(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<RawBankCheckingData> loadRawCheckingData(String file){
		CsvFileReaderUtil util = new CsvFileReaderUtil();
		List<RawBankCheckingData> result = new ArrayList<RawBankCheckingData>();
		result= util.readCsvFile(file);
		return result;
	}

}
