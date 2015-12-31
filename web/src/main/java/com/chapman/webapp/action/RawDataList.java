package com.chapman.webapp.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.chapman.model.RawBankCheckingData;
import com.chapman.service.RawDataManager;

public class RawDataList extends BasePage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private RawDataManager rawDataManager;
	List<RawBankCheckingData> list = new ArrayList<RawBankCheckingData>();
	private Date fromDate = new Date();
	private Date toDate = new Date();
 
    @Autowired
    public void setRawDataManager(@Qualifier("rawDataManager") RawDataManager manager) {
        this.rawDataManager = manager;
    }
 
    public RawDataList() {
        setSortColumn("id"); // sets the default sort column
    }
     
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
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
		if(getSession().getAttribute("messages") == null){
			list = sort(rawDataManager.getAllData());
		}
        return list;
    }
	
	public void setRawBankingData(List<RawBankCheckingData> list){
		this.list = list;
	}
	
	public String update() {
    	log.debug("___________________________________________________________");
        setRawBankingData(rawDataManager.getDateRangeData(getFromDate(), getToDate()));
        log.debug("message.............................. "+getSession().getAttribute("messages"));
        addMessage("rawData.updated");
        log.debug("message.............................. "+getSession().getAttribute("messages"));
        log.debug("___________________________________________________________");
        return "update";
        
    }
}
