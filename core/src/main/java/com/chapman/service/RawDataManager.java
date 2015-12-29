/**
 * 
 */
package com.chapman.service;

import java.util.Date;
import java.util.List;

import com.chapman.dao.RawDataDao;
import com.chapman.model.RawBankCheckingData;

/**
 * @author or0189783
 *
 */
public interface RawDataManager extends GenericManager<RawBankCheckingData, Long> {

	List<RawBankCheckingData> findDataByTransactionId(String transactionId);
	List<RawBankCheckingData> loadRawCheckingData(String file);
	//RawBankCheckingData insertRawCheckingData(RawBankCheckingData data) throws Exception;
	RawBankCheckingData saveData(RawBankCheckingData data) throws Exception;
	List<RawBankCheckingData> getAllData();
	List<RawBankCheckingData> getUnassighnedData();
	int saveAndUpdateAllCategories(RawBankCheckingData b);
	List<RawBankCheckingData> getDateRangeData(Date from, Date to);
	/**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
	//void setRawDataDao(RawDataDao dao);
}
