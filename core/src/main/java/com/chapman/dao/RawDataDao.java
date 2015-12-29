/**
 * 
 */
package com.chapman.dao;

import java.util.Date;
import java.util.List;

import com.chapman.model.RawBankCheckingData;

/**
 * @author or0189783
 *
 */
public interface RawDataDao extends GenericDao<RawBankCheckingData, Long> {
	
	List<RawBankCheckingData> findDataByTransactionId(String transactionId);
	public RawBankCheckingData saveData(final RawBankCheckingData data) throws Exception;
	List<RawBankCheckingData> getAllData();
	List<RawBankCheckingData> getUnassighnedData();
	int saveAndUpdateAllCategories(RawBankCheckingData b);
	List<RawBankCheckingData> getDateRangeData(Date from, Date to);
}
