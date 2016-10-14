/**
 * 
 */
package com.chapman.webapp.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.chapman.model.Category;
import com.chapman.model.LabelValue;
import com.chapman.model.RawBankCheckingData;
import com.chapman.service.RawDataManager;
import com.chapman.util.ConvertUtil;

/**
 * @author <a href="mailto:pchapman@easystreet.net">Peter Chapman</a>
 *
 */
public class RawDataFormTest extends BasePageTestCase {
	
	private RawDataForm bean;
    @Autowired @Qualifier("rawDataManager")
    private RawDataManager rawDataManager;
    //private RawDataManager rawDataManager = new RawDataManagerImpl(new RawDataDaoHibernate());
 
    @Override
    @Before
    public void onSetUp() {
        super.onSetUp();
        bean = new RawDataForm();
        bean.setRawDataManager(rawDataManager);
        // add test data to the database
        Category c = new Category();
        c.setId(2L);
        c.setDescription("Food");
        RawBankCheckingData data = new RawBankCheckingData();
        data.setTransactionId("IT54321");
        data.setAmount(new Double(23.12));
        data.setReferenceNumber("123456");
        data.setCategory(c);
        log.debug("----------------------------------"+data.getTransactionId());
        log.debug("----------------------------------"+data.getCategory().getId());
        log.debug("----------------------------------"+data.getCategory().getDescription());
		rawDataManager.save(data);
    }
    
    @Override
    @After
    public void onTearDown() {
        super.onTearDown();
        bean = null;
    }
    
    @Test
    public void testAdd() throws Exception {
        // set required fields
    	Category c = new Category();
        c.setId(2L);
        c.setDescription("Food");
        RawBankCheckingData data = new RawBankCheckingData();
        data.setTransactionId("IT54322");
        data.setAmount(new Double(23.12));
        data.setReferenceNumber("123456");
        data.setCategory(c);
        bean.setRawData(data);
        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }
 
    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        bean.setId(-1L);
 
        assertEquals("edit", bean.edit());
        assertNotNull(bean.getRawData());
        assertFalse(bean.hasErrors());
    }
    
    @Test
    public void testAvailableCategories() throws Exception {
    	log.debug("...................availableCategories:...............................");
    	List<LabelValue> list = new ArrayList<LabelValue>();//(List)session.getServletContext().getAttribute(Constants.AVAILABLE_ROLES);
    	list.add(new LabelValue("1", "Food"));
    	list.add(new LabelValue("2", "Gas"));
    	list.add(new LabelValue("3", "Entertainment"));
    	Map availableCategories= ConvertUtil.convertListToMap(list);
    	log.debug("...................availableCategories: "+list.toString());
    	log.debug("...................availableCategories: "+availableCategories.values());
    }
	
	@Test
	public void testBlah() throws Exception{
		String blah = "blah";
		assertTrue(blah=="blah");
	}
}
