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
import com.chapman.service.RawDataManager;

/**
 * @author OR0189783
 *
 */
public class RawDataListTest extends BasePageTestCase {

	public RawDataListTest() {
	}
	private RawDataList bean;
    @Autowired @Qualifier("rawDataManager")
    private RawDataManager rawDataManager;// = new RawDataManagerImpl();
 
    @Override
    @Before
    public void onSetUp() {
        super.onSetUp();
        bean = new RawDataList();
        bean.setRawDataManager(rawDataManager);
        // add a test object to the database
        Category c = new Category();
        c.setId(2L);
        c.setDescription("Food");
        RawBankCheckingData data = new RawBankCheckingData();
        data.setTransactionId("ID12346");
        data.setAmount(new Double(23.12));
        data.setCategory(c);
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
    }
}
