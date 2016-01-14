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
import com.chapman.model.LabelValue;
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
	List<Stats> stats = new ArrayList<Stats>();
	Double income = new Double(0.00);
	Double spent = new Double(0.00);
	private Date toDate;
	private Date fromDate;
	boolean dateRange = false;
	
	@Autowired
    public void setRawDataManager(@Qualifier("rawDataManager") RawDataManager manager) {
        this.rawDataManager = manager;
    }

	public CheckingPieChart() {
	}

	@SuppressWarnings("unchecked")
	public PieChartModel getModel() {
		model = new PieChartModel();
		if(getFromDate() == null){
			DateTime d = new DateTime().minusDays(90);
			setFromDate(d.toDate());
		}
		if(!dateRange){
			setToDate(new Date());
		}
		List<LabelValue> categories = (List<LabelValue>) getServletContext().getAttribute(Constants.CATEGORIES);
		log.debug("category list size:............................................................ "+categories.size());
		for(LabelValue category : categories){
			Double sum = rawDataManager.getCheckingCategorySum(Long.valueOf(category.getLabel()).longValue(), getFromDate(), getToDate());
			log.debug(".......................Label: "+category.getLabel()+", sum: "+sum);
			if(sum == null)
				sum = new Double(0.00);
			if(sum < 0)
				spent=spent+sum;
			if(sum > 0)
				income = income+sum;
			stats.add(new Stats(category.getValue(), sum));
			model.set(category.getValue(), (Number)sum);
		}
		setStats(stats);
		model.setTitle("Checking Pie Chart");
		model.setLegendPosition("e");
		model.setFill(true);
		model.setShowDataLabels(true);
		model.setDiameter(350);
		return model;
	}
	
	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
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
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	/**
	 * @return the stats
	 */
	public List<Stats> getStats() {
		return stats;
	}

	/**
	 * @param stats the stats to set
	 */
	public void setStats(List<Stats> stats) {
		this.stats = stats;
	}

	public String update(){
		log.debug("______________________________________________________________________________________");
		dateRange=true;
		getModel();
		log.debug("______________________________________________________________________________________");
		return "update";
	}
}
