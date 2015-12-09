package com.chapman.webapp.action;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.chapman.Constants;
import com.chapman.model.Category;
import com.chapman.model.LabelValue;
import com.chapman.model.RawBankCheckingData;
import com.chapman.service.RawDataManager;
import com.chapman.service.impl.RawDataManagerImpl;
import com.chapman.util.ConvertUtil;

public class RawDataForm extends BasePage implements Serializable {
	private static final long serialVersionUID = 1L;
	//private GenericManager<RawBankCheckingData, Long> rawDataManager;
	private RawDataManager rawDataManager = new RawDataManagerImpl();
	private Category category = new Category();
	private Map<String, String> availableCategories;
    private RawBankCheckingData rawData = new RawBankCheckingData();
    private Long id;
 
/*    @Autowired
    public void setRawDataManager(@Qualifier("rawDataManager") GenericManager<RawBankCheckingData, Long> manager) {
    	log.debug("Do we have a manager...................................."+manager.toString());
        this.rawDataManager = manager;
    }*/
 
    public RawBankCheckingData getRawData() {
        return rawData;
    }
 
    public void setRawData(RawBankCheckingData rawData) {
        this.rawData = rawData;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCategory(){
    	return category.getDescription();
    }
    
    public void setCategory(Long id){
    	category.setCategoryId(id);
    }
    
    @SuppressWarnings("unchecked")
    public Map<String,String> getAvailableCategories(){
    	if(availableCategories == null){
    		List<LabelValue> categories = (List) getServletContext().getAttribute(Constants.CATEGORIES);
    		availableCategories= ConvertUtil.convertListToMap(categories);
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
