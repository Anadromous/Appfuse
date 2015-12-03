//**
package com.chapman.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.chapman.model.RawBankCheckingData;
import com.chapman.service.impl.RawDataManagerImpl;

/**
 * @author or0189783
 *
 */
public class RawDataDaoTest extends BaseDaoTestCase{
    @Autowired
    private RawDataDao dao;
    private RawDataManagerImpl manager= new RawDataManagerImpl();

    @Test
	public void testFindDataByTransactionId() throws Exception{
		List<RawBankCheckingData> data = dao.findDataByTransactionId("ID21191");
		assertTrue(data.size() >= 0);
    	assertTrue(Boolean.TRUE);
	}
    
    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveData() throws Exception {
    	RawBankCheckingData data = new RawBankCheckingData();
        data.setTransactionId("ID1234");
        data.setAmount(new Double(23.12));
        data = dao.save(data);
        flush();
        data = dao.get(data.getId());
        log.debug("Data from dao:..................."+data.getTransactionId());
        assertEquals("ID1234", data.getTransactionId());
        assertNotNull(data.getId());
        log.debug("removing data...");
        dao.remove(data.getId());
        flush();
        // should throw DataAccessException
        dao.get(data.getId());
    }
    
    //HistoryDownloadTest
    @Test
    public void testLoadCSVList() throws Exception{
    	List<RawBankCheckingData> csv = manager.loadRawCheckingData("C:/chapman/Downloads/HistoryDownloadTest.csv");
    	for(RawBankCheckingData data : csv){
    		log.debug("saving data.................................");
    		dao.save(data);
    		flush();
    	}
    	List<RawBankCheckingData> result = dao.getAll();
    	log.debug("result..........................."+result.size());
    }
}
