/**
 * 
 */
package com.chapman.dao;

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
	List<RawBankCheckingData> getDateRangeData(String from, String to);
	Double getCheckingCategorySum(Long categoryId, String from, String to);
	List<RawBankCheckingData> getDataByCategory(Long categoryId, String from, String to);
}
