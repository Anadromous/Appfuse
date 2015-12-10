package com.chapman.webapp.action;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.chapman.Constants;
import com.chapman.model.LabelValue;
import com.chapman.model.RawBankCheckingData;
import com.chapman.service.RawDataManager;
import com.chapman.util.ConvertUtil;

public class RawDataForm extends BasePage implements Serializable {
	private static final long serialVersionUID = 1L;
	//private GenericManager<RawBankCheckingData, Long> rawDataManager;
	private RawDataManager rawDataManager;
	private Map<String, String> availableCategories;
    private RawBankCheckingData rawData = new RawBankCheckingData();
    private Long id;
 
/*    @Autowired
    public void setRawDataManager(@Qualifier("rawDataManager") GenericManager<RawBankCheckingData, Long> manager) {
    	log.debug("Do we have a manager...................................."+manager.toString());
        this.rawDataManager = manager;
    }*/
    
    public void setRawDataManager(RawDataManager rawDataManager){
    	this.rawDataManager=rawDataManager;
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
    
/*    public String getCategory(){
    	//log.debug("Category from rawData............................"+rawData.getDescription());
    	return getRawData().getCategory().getDescription();
    }
    
    public void setCategory(Long id){
    	getRawData().getCategory().setCategoryId(id);
    }*/
    
    @SuppressWarnings("unchecked")
    public Map<String,String> getAvailableCategories(){
    	if(availableCategories == null){
    		List<LabelValue> categories = (List) getServletContext().getAttribute(Constants.CATEGORIES);
    		availableCategories= ConvertUtil.convertListToMap(categories);
    		
/*            for (LabelValue option : categories) {
                log.debug("======================================================");
                //log.debug("option values: "+option.getLabel()+", "+ option.getValue());
                availableCategories.put(option.getLabel(),option.getValue());
            	//log.debug("map values: "+map.toString());
            	log.debug("======================================================");
            }*/
    	}
    	return availableCategories;
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
        log.debug("id rawDataManager....................... "+rawDataManager.toString());
        log.debug("id from form............................ "+id);
        rawData = rawDataManager.get(id);
 
        return "edit";
    }
 
    public String save() {
    	log.debug("___________________________________________________________");
        boolean isNew = (rawData.getId() == null || rawData.getId() == 0);
        log.debug("iNew: "+isNew);
        rawData = rawDataManager.save(rawData);
        log.debug("rawData: "+rawData.getCategory().getCategoryId());
        //setRawData(rawData);
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
