package com.chapman.webapp.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.chapman.Constants;
import com.chapman.model.Category;
import com.chapman.model.LabelValue;
import com.chapman.model.RawBankCheckingData;
import com.chapman.service.RawDataManager;
import com.chapman.util.ConvertUtil;

/**
 * @author PMC
 *
 */

@Scope("request")
@Component("nonAssignedRawDataForm")
public class NonAssignedRawDataForm extends BasePage implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private RawDataManager rawDataManager;
	private Map<String, String> availableCategories;
	private Date fromDate;
	private Date toDate;
	String creditOrDebit = "<";
    private RawBankCheckingData rawData = new RawBankCheckingData();
    List<RawBankCheckingData> list = new ArrayList<RawBankCheckingData>();
	
    @Autowired
    public void setRawDataManager(@Qualifier("rawDataManager") RawDataManager manager) {
        this.rawDataManager = manager;
    }
    
	public List<RawBankCheckingData> getNonAssignedRawData() {
		if(list.size() == 0 || list.isEmpty()){
			log.debug("list is empty........................................................");
			DateTime d = new DateTime().minusDays(90);
			return (rawDataManager.getUnassighnedData(d.toDate(), new Date(), getCreditOrDebit()));
			
		}
        return list;
    }
 
    public void setNonAssignedRawData(List<RawBankCheckingData> list) {
        this.list = list;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
    
    public RawBankCheckingData getRawData() {
		return rawData;
	}

	public void setRawData(RawBankCheckingData rawData) {
		this.rawData = rawData;
		setCategory(rawData.getCategory());
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

	public Category getCategory(){
    	return rawData.getCategory();
    }
    
    public void setCategory(Category category){
    	rawData.setCategory(category);
    	log.debug("Category from getCategory............................"+rawData.getCategory().toString());
    }
    
    @SuppressWarnings("unchecked")
    public Map<String,String> getAvailableCategories(){
    	if(availableCategories == null){
    		List<LabelValue> categories = (List<LabelValue>) getServletContext().getAttribute(Constants.CATEGORIES);
    		availableCategories= ConvertUtil.convertListToMap(categories);
    	}
    	return availableCategories;
    }
    
	public String credit(){
		setCreditOrDebit(">");
		update();
		return "update";
	}
	
	public String debit(){
		setCreditOrDebit("<");
		update();
		return "update";
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
        log.debug("id from NonAssignedRawDataForm.................. "+id);
        setRawData(rawDataManager.get(id));
        log.debug("NonAssignedRawData Description.................. "+getRawData().getMemo());
        return "edit";
    }
    
	public String update() {
    	log.debug("___________________________________________________________");
    	if(getFromDate() == null){
			DateTime d = new DateTime().minusDays(90);
			setFromDate(d.toDate());
		}
		if(getToDate() == null){
			setToDate(new Date());
		}
		setNonAssignedRawData(rawDataManager.getUnassighnedData(getFromDate(), getToDate(), getCreditOrDebit()));
        log.debug("___________________________________________________________");
        return "update";
        
    }
 
    public String save() {
    	log.debug("___________________________________________________________");
        boolean isNew = (rawData.getId() == null || rawData.getId() == 0);
        log.debug("rawData................................................ "+list.size());
        for(RawBankCheckingData data : list){
        	if(data.getCategory() != null){
        		log.debug("rawData... "+data.toString());
        		rawDataManager.saveAndUpdateAllCategories(data);
        	}
        }
        String key = (isNew) ? "rawData.added" : "rawData.updated";
        addMessage(key);
        log.debug("___________________________________________________________");
        if (isNew) {
            return "list";
        } else {
            return "edit";
        }
        
    }

}
