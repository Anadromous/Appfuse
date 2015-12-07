/**
 * 
 */
package com.chapman.webapp.action;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.chapman.model.Category;
import com.chapman.model.RawBankCheckingData;
import com.chapman.service.GenericManager;
import com.chapman.service.RawDataManager;
import com.chapman.service.impl.RawDataManagerImpl;

/**
 * @author OR0189783
 *
 */
public class RawDataListTest extends BasePageTestCase {
	
	private RawDataList bean;
    /*@Autowired @Qualifier("rawDataManager")
    private GenericManager<RawBankCheckingData, Long> rawDataManager;*/
    //@Autowired
    private RawDataManager manager;// = new RawDataManagerImpl();
 
    @Override
    @Before
    public void onSetUp() {
        super.onSetUp();
        bean = new RawDataList();
        bean.setRawDataManager(manager);
        /*// add test data to the database
        log.debug("setting up data.................................");
        Category c = new Category();
        c.setCategoryId(2L);
        c.setCategory("Food");
        RawBankCheckingData data = new RawBankCheckingData();
        data.setTransactionId("ID12345");
        data.setAmount(new Double(23.12));
        data.setCategory(c);
        log.debug("----------------------------------"+data.getTransactionId());
        if(rawDataManager == null){
        	log.debug("WE HAVE A NULL MANAGER...................");
        }else{
        	log.debug("poiupoiupoiupoupoiupoiupoiupoiupoiu");
        }
        try {
			rawDataManager.save(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    }
 
    @Override
    @After
    public void onTearDown() {
        super.onTearDown();
        bean = null;
    }
 
    @Test
    public void testSearch() throws Exception {
        assertTrue(bean.getRawBankingData().size() >= 1);
        assertFalse(bean.hasErrors());
    }
	
	@Test
	public void testNull() throws Exception{
		String blah = "blah";
		assertTrue(blah=="blah");
	}
}
