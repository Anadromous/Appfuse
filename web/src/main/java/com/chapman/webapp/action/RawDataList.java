package com.chapman.webapp.action;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.chapman.model.RawBankCheckingData;
import com.chapman.service.GenericManager;
import com.chapman.service.RawDataManager;
import com.chapman.service.impl.RawDataManagerImpl;

@Scope("request")
@Component("rawDataList")
public class RawDataList extends BasePage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//private GenericManager<RawBankCheckingData, Long> rawDataManager;
	private RawDataManager rawDataManager;
 
/*    @Autowired
    public void setRawDataManager(@Qualifier("rawDataManager") RawDataManager rawDataManager) {
        this.rawDataManager = rawDataManager;
    }*/
	
    //@Autowired
    public void setRawDataManager(RawDataManager rawDataManager) {
        this.rawDataManager = rawDataManager;
    }
 
    public RawDataList() {
        setSortColumn("id"); // sets the default sort column
    }
 
    @SuppressWarnings("unchecked")
	public List<RawBankCheckingData> getRawBankingData() {
    	log.debug("is the rawDataManager null............."+rawDataManager.toString());
        return sort(rawDataManager.getAllData());
    }
}
