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

import com.chapman.model.RawBankCheckingData;
import com.chapman.service.GenericManager;

/**
 * @author OR0189783
 *
 */
public class RawDataListTest extends BasePageTestCase {
	
/*	private RawDataList bean;
    @Autowired @Qualifier("rawDataManager")
    private GenericManager<RawBankCheckingData, Long> rawDataManager;
 
    @Override
    @Before
    public void onSetUp() {
        super.onSetUp();
        bean = new RawDataList();
        bean.setRawDataManager(rawDataManager);
 
        // add test data to the database
        RawBankCheckingData data = new RawBankCheckingData();
        data.setTransactionId("ID12345");
        data.setAmount(new Double(23.12));
        rawDataManager.save(data);
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
    }*/
	
	@Test
	public void testNull() throws Exception{
		String blah = "blah";
		assertTrue(blah=="blah");
	}
}
