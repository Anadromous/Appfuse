package com.chapman.webapp.action;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.chapman.Constants;
import com.chapman.model.Category;
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
    	//log.debug("Category from setRawData............................"+rawData.toString());
        return rawData;
    }
 
    public void setRawData(RawBankCheckingData rawData) {
        this.rawData = rawData;
        setCategory(rawData.getCategory());
        //log.debug("Category from setRawData............................"+rawData.toString());
    }
 
    public void setId(Long id) {
        this.id = id;
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
        rawData = rawDataManager.get(id);
 
        return "edit";
    }
 
    public String save() {
    	log.debug("___________________________________________________________");
        boolean isNew = (rawData.getId() == null || rawData.getId() == 0);
        //log.debug("rawDataForm............................................ "+getCategory().getId());
        log.debug("rawData................................................ "+rawData.toString());
        rawData = rawDataManager.save(rawData);
        //log.debug("rawData: "+rawData.getCategory().getCategoryId());
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
