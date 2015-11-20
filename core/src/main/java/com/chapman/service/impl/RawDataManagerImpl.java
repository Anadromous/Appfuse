/**
 * 
 */
package com.chapman.service.impl;

import java.util.List;

import com.chapman.dao.RawDataDao;
import com.chapman.model.RawBankCheckingData;
import com.chapman.service.RawDataManager;
import static org.junit.Assert.assertTrue;;

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

}
