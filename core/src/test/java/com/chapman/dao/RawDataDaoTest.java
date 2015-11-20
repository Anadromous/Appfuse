/**
 * 
 */
package com.chapman.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chapman.model.RawBankCheckingData;

/**
 * @author or0189783
 *
 */
public class RawDataDaoTest extends BaseDaoTestCase{
    @Autowired
    private RawDataDao rawDataDao;

    @Test
	public void testFindDataByTransactionId() throws Exception{
		List<RawBankCheckingData> data = rawDataDao.findDataByTransactionId("ID21191");
		assertTrue(data.size() >= 0);
    	assertTrue(Boolean.TRUE);
	}
	
}
