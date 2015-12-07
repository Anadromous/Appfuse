package com.chapman.webapp.action;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.chapman.model.RawBankCheckingData;
import com.chapman.service.GenericManager;

@Scope("request")
@Component("rawDataList")
public class RawDataList extends BasePage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private GenericManager<RawBankCheckingData, Long> rawDataManager;
 
    @Autowired
    public void setRawDataManager(@Qualifier("rawDataManager") GenericManager<RawBankCheckingData, Long> rawDataManager) {
        this.rawDataManager = rawDataManager;
    }
 
    public RawDataList() {
        setSortColumn("id"); // sets the default sort column
    }
 
    @SuppressWarnings("unchecked")
	public List<RawBankCheckingData> getRawBankingData() {
        return sort(rawDataManager.getAll());
    }
}
