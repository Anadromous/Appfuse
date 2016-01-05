package com.chapman.webapp.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.chapman.model.RawBankCheckingData;
import com.chapman.service.RawDataManager;

public class RawDataList extends BasePage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private RawDataManager rawDataManager;
	List<RawBankCheckingData> list = new ArrayList<RawBankCheckingData>();
	private Date fromDate;
	private Date toDate;
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
	
	public String update() {
    	log.debug("___________________________________________________________");
    	log.debug("fromDate............................ "+getFromDate());
    	log.debug("toDate.............................. "+getToDate());
        setRawBankingData(rawDataManager.getDateRangeData(getFromDate(), getToDate()));
        //addMessage("rawData.updated");
        dateRange=true;
        log.debug("___________________________________________________________");
        return "update";
        
    }
}
