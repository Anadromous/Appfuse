package com.chapman.webapp.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.chapman.Constants;
import com.chapman.model.Category;
import com.chapman.model.LabelValue;
import com.chapman.model.RawBankCheckingData;
import com.chapman.service.RawDataManager;
import com.chapman.util.ConvertUtil;

public class RawDataList extends BasePage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private RawDataManager rawDataManager;
	List<RawBankCheckingData> list = new ArrayList<RawBankCheckingData>();
	private Date fromDate;
	private Date toDate;
	private Category category;
	private Map<String, String> availableCategories;
	boolean dateRange = false;
 
    @Autowired
    public void setRawDataManager(@Qualifier("rawDataManager") RawDataManager manager) {
        this.rawDataManager = manager;
    }
 
    public RawDataList() {
    	ascending=false;
        setSortColumn("transactionDate"); // sets the default sort column
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	@SuppressWarnings("unchecked")
    public Map<String,String> getAvailableCategories(){
    	if(availableCategories == null){
    		List<LabelValue> categories = (List<LabelValue>) getServletContext().getAttribute(Constants.CATEGORIES);
    		availableCategories= ConvertUtil.convertListToMap(categories);
    	}
    	return availableCategories;
    }

	@SuppressWarnings("unchecked")
	public List<RawBankCheckingData> getRawBankingData() {
		if(!dateRange){
			DateTime d = new DateTime().minusDays(90);
			return (sort(rawDataManager.getDateRangeData(d.toDate(), new Date())));
			
		}else{
			return (sort(rawDataManager.getDateRangeData(getFromDate(), getToDate())));
		}
    }
	
	public void setRawBankingData(List<RawBankCheckingData> list){
		log.debug("setting RawDataList............................");
		this.list = list;
	}
	
	public String byCategory(){
		setRawBankingData(rawDataManager.getDataByCategory(getCategory().getId(), getFromDate(), getToDate()));
		return "category";
	}
	
	public String update() {
    	log.debug("___________________________________________________________");
    	log.debug("fromDate............................ "+getFromDate());
    	log.debug("toDate.............................. "+getToDate());
        setRawBankingData(rawDataManager.getDateRangeData(getFromDate(), getToDate()));
        dateRange=true;
        log.debug("___________________________________________________________");
        return "update";
        
    }
}
