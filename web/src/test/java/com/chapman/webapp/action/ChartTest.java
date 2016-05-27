/**
 * 
 */
package com.chapman.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.chapman.Constants;
import com.chapman.model.LabelValue;
import com.chapman.service.RawDataManager;

/**
 * @author or0189783
 *
 */
public class ChartTest extends BasePageTestCase {

	/**
	 * 
	 */
	public ChartTest() {
		// TODO Auto-generated constructor stub
	}
	
	private CheckingPieChart bean;
    @Autowired @Qualifier("rawDataManager")
    private RawDataManager rawDataManager;// = new RawDataManagerImpl();
 
    @Override
    @Before
    public void onSetUp() {
        super.onSetUp();
        bean = new CheckingPieChart();
        bean.setRawDataManager(rawDataManager);
        List<LabelValue> list = new ArrayList<LabelValue>();
        list.add(new LabelValue("1","Food"));
        list.add(new LabelValue("2","Gas"));
        list.add(new LabelValue("3","Entertainment"));
        list.add(new LabelValue("4","Eating Out"));
        list.add(new LabelValue("5","Household"));
        list.add(new LabelValue("6","Utilities"));
        list.add(new LabelValue("7","Clothes"));
        list.add(new LabelValue("8","Overdraft"));
        ServletContext c = servletContext;
        c.setAttribute(Constants.CATEGORIES, list);
        session.setServletContext(c);
    }
    
    @Override
    @After
    public void onTearDown() {
        super.onTearDown();
        bean = null;
    }
    
    @Test
    public void testGetModel() throws Exception{
    	PieChartModel model = bean.getModel();
    	List<Stats> stats = bean.getStats();
    	log.debug("stats size:............... "+stats.size());
    	assertTrue(stats.size() > 0);
    }
    
}
