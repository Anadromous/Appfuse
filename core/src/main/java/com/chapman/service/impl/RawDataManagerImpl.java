/**
 * 
 */
package com.chapman.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

	private RawDataDao dao = new RawDataDaoHibernate();
	public RawDataManagerImpl() {
	}
	
	@Autowired
	public RawDataManagerImpl(RawDataDao dao) {
		super(dao);
        this.dao = dao;
	}
	
/*    @Autowired
    public void setRawDataDao(RawDataDao dao) {
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
		log.debug("dao");
		return dao.saveData(data);
	}
	
	@Override
	public List<RawBankCheckingData> getAllData(){
		return dao.getAllData();
	}
	
	@Override
	public List<RawBankCheckingData> getUnassighnedData(){
		log.debug("getUnassighnedData from RawDataManager....................................");
		return dao.getUnassighnedData();
	}
	
	@Override
	public int saveAndUpdateAllCategories(RawBankCheckingData b){
		return dao.saveAndUpdateAllCategories(b);
	}
	
	@Override
	public List<RawBankCheckingData> getDateRangeData(Date from, Date to){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		log.debug("fromDate: "+df.format(from)+", toDate: "+df.format(to));
		return dao.getDateRangeData(df.format(from), df.format(to));
	}

	@Override
	public Double getCheckingCategorySum(Long categoryId, Date from, Date to){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return dao.getCheckingCategorySum(categoryId, df.format(from), df.format(to));
	}

	@Override
	public List<RawBankCheckingData> getDataByCategory(Long categoryId, Date from, Date to) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return dao.getDataByCategory(categoryId, df.format(from), df.format(to));
	}
}
