//**
package com.chapman.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.chapman.model.Category;
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
	public void testGetNonAssignedData() throws Exception{
		List<RawBankCheckingData> data = dao.getUnassighnedData();
		assertTrue(data.size() >= 0);
    	assertTrue(Boolean.TRUE);
	}
    
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
        data.setCategory(new Category(1L,"Food"));
        log.debug("Category from data:..................."+data.getCategory().getDescription());
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
    		data.setCategory(new Category(1L,"Food"));
    		dao.save(data);
    		flush();
    	}
    	List<RawBankCheckingData> result = dao.getAll();
    	log.debug("result..........................."+result.size());
    }
    
    @Test
    public void testSaveAndUpdateAllCategories() throws Exception{
    	RawBankCheckingData data = new RawBankCheckingData();
        data.setTransactionId("ID1234");
        data.setAmount(new Double(23.12));
        data.setCategory(new Category(1L,"Food"));
        data.setDescription("AVA ROASTERIA BEAVERTON ORUS");
        data.setExtDesc("AVA ROASTERIA");
        log.debug("Category from data:..................."+data.getCategory().getDescription());
        data = dao.save(data);
        flush();
        dao.saveAndUpdateAllCategories(data);
        RawBankCheckingData test = dao.findDataByTransactionId("ID21191").get(0);
        //log.debug("testSaveAndUpdateAllCategories category: EatingOut: "+test.getCategory().getDescription());
        
    }
    
    @Test
    public void testDateRangeSearch() throws Exception{
    	DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	String from = "2015-11-17";
    	log.debug("date from:.................................. "+from);
    	String to = "2015-11-19";
    	log.debug("date to:.................................... "+to);
    	List<RawBankCheckingData> data = dao.getDateRangeData(from, to);
    	assertTrue(data.size() > 0);
    }
    
    @Test
    public void getRawDataSum() throws Exception{
    	Double d = dao.getCheckingCategorySum(1L, "2015-11-01", "2015-11-30");
    	log.debug("Double amount:................................. "+d);
    }
}
