package com.chapman.webapp.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
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
	String creditOrDebit = "<";
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

	/**
	 * @return the creditOrDebit
	 */
	public String getCreditOrDebit() {
		return creditOrDebit;
	}

	/**
	 * @param creditOrDebit the creditOrDebit to set
	 */
	public void setCreditOrDebit(String creditOrDebit) {
		this.creditOrDebit = creditOrDebit;
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
		if(list.size() == 0 || list.isEmpty()){
			DateTime d = new DateTime().minusDays(90);
			return (sort(rawDataManager.getDateRangeData(d.toDate(), new Date(), getCreditOrDebit())));
			
		}
		return list;
    }
	
	public void setRawBankingData(List<RawBankCheckingData> list){
		this.list = list;
	}
	
	public String credit(){
		setCreditOrDebit(">");
		update();
		return "credit";
	}
	
	public String debit(){
		setCreditOrDebit("<");
		update();
		return "debit";
	}
	
	@SuppressWarnings("unchecked")
	public String byCategory(){
		log.debug("___________________________________________________________");
		Enumeration<String> e = getRequest().getParameterNames();
		while(e.hasMoreElements()){
			log.debug(e.nextElement());
		}
		log.debug("getAttribute rawData: "+getRequest().getAttribute("rawData"));
		log.debug("getAttribute byCategory: "+getRequest().getAttribute("byCategory"));
		log.debug("request rawData: "+getRequest().getParameter("rawData"));
		log.debug("request byCategory: "+getRequest().getParameter("byCategory"));
		log.debug("request category: "+getRequest().getParameter("category"));
		if(getFromDate() == null){
			DateTime d = new DateTime().minusDays(90);
			setFromDate(d.toDate());
		}
		if(getToDate() == null){
			setToDate(new Date());
		}
		if(getCategory() != null){
			log.debug("Category: "+getCategory().getId());
			setRawBankingData(sort(rawDataManager.getDataByCategory(getCategory().getId(), getFromDate(), getToDate(), getCreditOrDebit())));
		}else{
			setRawBankingData(sort(rawDataManager.getDateRangeData(getFromDate(), getToDate(), getCreditOrDebit())));
		}
		log.debug("From Date: "+getFromDate());
		log.debug("To Date: "+getToDate());
		
		log.debug("___________________________________________________________");
		return "byCategory";
		
	}
	
	@SuppressWarnings("unchecked")
	public String update() {
    	log.debug("___________________________________________________________");
    	if(getFromDate() == null){
			DateTime d = new DateTime().minusDays(90);
			setFromDate(d.toDate());
		}
		if(getToDate() == null){
			setToDate(new Date());
		}
    	log.debug("fromDate............................ "+getFromDate());
    	log.debug("toDate.............................. "+getToDate());
    	Enumeration<String> e = getRequest().getParameterNames();
    	while(e.hasMoreElements()){
			log.debug(e.nextElement());
		}
    	log.debug("getAttribute rawData: "+getRequest().getAttribute("rawData"));
		log.debug("getAttribute update: "+getRequest().getAttribute("update"));
		log.debug("request rawData: "+getRequest().getParameter("rawData"));
		log.debug("request update: "+getRequest().getParameter("update"));
		log.debug("request category: "+getRequest().getParameter("category"));
        setRawBankingData(sort(rawDataManager.getDateRangeData(getFromDate(), getToDate(), getCreditOrDebit())));
        dateRange=true;
        log.debug("___________________________________________________________");
        return "update";
        
    }
}
