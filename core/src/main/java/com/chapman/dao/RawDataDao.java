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
	
	public List<RawBankCheckingData> findDataByTransactionId(String transactionId);

}
