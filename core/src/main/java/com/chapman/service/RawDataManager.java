/**
 * 
 */
package com.chapman.service;

import java.util.List;

import com.chapman.model.RawBankCheckingData;

/**
 * @author or0189783
 *
 */
public interface RawDataManager extends GenericManager<RawBankCheckingData, Long> {

	public List<RawBankCheckingData> findDataByTransactionId(String transactionId);

}
