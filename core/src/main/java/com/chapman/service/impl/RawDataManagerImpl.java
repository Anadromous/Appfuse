/**
 * 
 */
package com.chapman.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.chapman.dao.RawDataDao;
import com.chapman.dao.hibernate.RawDataDaoHibernate;
import com.chapman.model.RawBankCheckingData;
import com.chapman.service.RawDataManager;
import com.chapman.util.CsvFileReaderUtil;

/**
 * @author or0189783
 *
 */
public class RawDataManagerImpl extends	GenericManagerImpl<RawBankCheckingData, Long> implements RawDataManager {

	private RawDataDao dao;// = new RawDataDaoHibernate();
	public RawDataManagerImpl() {
	}
	
	@Autowired
	public RawDataManagerImpl(RawDataDao dao) {
		super(dao);
        this.dao = dao;
	}
	
/*	@Override
    @Autowired
    public void setRawDataDao(RawDataDao dao) {
		super(dao);
        this.dao = dao;
    }*/

	@Override
	public List<RawBankCheckingData> findDataByTransactionId(String transactionId) {
		return dao.findDataByTransactionId(transactionId);
	}

	@Override
	public List<RawBankCheckingData> loadRawCheckingData(String file) {
		CsvFileReaderUtil util = new CsvFileReaderUtil();
		List<RawBankCheckingData> result = new ArrayList<RawBankCheckingData>();
		result = util.readCsvFile(file);
		return result;
	}

	@Override
	public RawBankCheckingData saveData(final RawBankCheckingData data)	throws Exception {
		return dao.saveData(data);
	}
	
	@Override
	public List<RawBankCheckingData> getAllData(){
		return dao.getAllData();
	}

}
