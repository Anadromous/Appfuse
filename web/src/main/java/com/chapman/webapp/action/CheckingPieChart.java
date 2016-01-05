/**
 * 
 */
package com.chapman.webapp.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.joda.time.DateTime;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.chapman.Constants;
import com.chapman.model.Category;
import com.chapman.model.LabelValue;
import com.chapman.model.RawBankCheckingData;
import com.chapman.service.RawDataManager;

/**
 * @author PMC
 *
 */

@ManagedBean(name = "checkingPieChart")
public class CheckingPieChart extends BasePage implements Serializable  {

	private static final long serialVersionUID = -1302420214211676326L;
	private PieChartModel model;
	private RawDataManager rawDataManager;
	List<RawBankCheckingData> list = new ArrayList<RawBankCheckingData>();
	private Date toDate;
	private Date fromDate;
	boolean dateRange = false;
	
	@Autowired
    public void setRawDataManager(@Qualifier("rawDataManager") RawDataManager manager) {
        this.rawDataManager = manager;
    }

	public CheckingPieChart() {
	}

	public PieChartModel getModel() {
		model = new PieChartModel();
		List<LabelValue> categories = (List<LabelValue>) getServletContext().getAttribute(Constants.CATEGORIES);
		DateTime d = new DateTime().minusDays(90);
		for(LabelValue category : categories){
			Double sum = rawDataManager.getCheckingCategorySum(Long.valueOf(category.getLabel()).longValue(), d.toDate(), new Date());
			log.debug(".......................Label: "+category.getLabel()+", sum: "+sum);
			model.set(category.getLabel(), (Number)sum);
		}
		model.setTitle("Checking Pie Chart");
		model.setLegendPosition("e");
		model.setFill(true);
		model.setShowDataLabels(true);
		model.setDiameter(150);
		return model;
	}
	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		if(dateRange){
			DateTime d = new DateTime().minusDays(90);
			setFromDate(d.toDate());
		}
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public Date getToDate() {
		if(dateRange){
			setToDate(new Date());
		}
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@SuppressWarnings("unchecked")
	public List<RawBankCheckingData> getRawBankingData() {
		if(!dateRange){
			log.debug("setting default DateRangeData................");
			DateTime d = new DateTime().minusDays(90);
			return (sort(rawDataManager.getDateRangeData(d.toDate(), new Date())));
			
		}else{
			log.debug("setting DateRangeData().....................");
			return (sort(rawDataManager.getDateRangeData(getFromDate(), getToDate())));
		}
    }
	
	public void setRawBankingData(List<RawBankCheckingData> list){
		log.debug("setting RawDataList............................");
		this.list = list;
	}
}
