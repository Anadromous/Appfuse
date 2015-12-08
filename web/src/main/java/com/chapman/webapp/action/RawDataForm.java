package com.chapman.webapp.action;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.chapman.model.RawBankCheckingData;
import com.chapman.service.GenericManager;

@Scope("request")
@Component("rawDataForm")
public class RawDataForm extends BasePage implements Serializable {
	private static final long serialVersionUID = 1L;
	private GenericManager<RawBankCheckingData, Long> rawDataManager;
    private RawBankCheckingData rawData = new RawBankCheckingData();
    private Long id;
 
    @Autowired
    public void setRawDataManager(@Qualifier("rawDataManager") GenericManager<RawBankCheckingData, Long> manager) {
        this.rawDataManager = manager;
    }
 
    public RawBankCheckingData getRawData() {
        return rawData;
    }
 
    public void setRawData(RawBankCheckingData rawData) {
        this.rawData = rawData;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String delete() {
        rawDataManager.remove(rawData.getId());
        addMessage("rawData.deleted");
 
        return "list";
    }
 
    public String edit() {
        if (id == null) {
            id = new Long(getParameter("id"));
        }
        rawData = rawDataManager.get(id);
 
        return "edit";
    }
 
    public String save() {
        boolean isNew = (rawData.getId() == null || rawData.getId() == 0);
        rawDataManager.save(rawData);
 
        String key = (isNew) ? "rawData.added" : "rawData.updated";
        addMessage(key);
 
        if (isNew) {
            return "list";
        } else {
            return "edit";
        }
    }
}
