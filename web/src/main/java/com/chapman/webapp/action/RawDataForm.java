package com.chapman.webapp.action;

import java.io.Serializable;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.chapman.Constants;
import com.chapman.model.Category;
import com.chapman.model.RawBankCheckingData;
import com.chapman.service.RawDataManager;
import com.chapman.util.ConvertUtil;

public class RawDataForm extends BasePage implements Serializable {
	private static final long serialVersionUID = 1L;
	//private GenericManager<RawBankCheckingData, Long> rawDataManager;
	private RawDataManager rawDataManager;
	private Map<String,String> availableCategories;
    private RawBankCheckingData rawData = new RawBankCheckingData();
    private Long id;
    private String categoryId;
 
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
    
	/**
	 * @return the categoryId
	 */
	public String getCategoryId() {
		/*log.debug("getCategoryId() from rawData1............................"+categoryId);
		log.debug("getCategoryId() from rawData2............................"+getRequest().getAttribute("rawDataForm").toString());
		for (Enumeration<String> keys = getRequest().getAttributeNames(); keys.hasMoreElements();) {
            log.debug(keys.nextElement());
		}*/
		//return String.valueOf(getRawData().getCategory().getCategoryId());
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		if(getCategoryId()==null){
			setCategoryId("0");
		}else{
			setCategoryId(String.valueOf(getRawData().getCategory().getCategoryId()));
		}
		log.debug("setCategoryId() from rawData............................"+categoryId);
	}

	public Category getCategory(){
    	log.debug("Getting Category from rawData............................"+getRawData().getCategory().getCategoryId()+", "+getRawData().getCategory().getDescription());
    	return getRawData().getCategory();
    }
    
    public void setCategory(Category category){
    	getRawData().setCategory(category);
    	log.debug("Setting Category from rawData............................"+getRawData().getCategory().getCategoryId()+", "+getRawData().getCategory().getDescription());
    }
    
    @SuppressWarnings("unchecked")
    public Map<String,String> getAvailableCategories(){
    	if(availableCategories == null){
    		List<Category> categoryList = (List) getServletContext().getAttribute(Constants.CATEGORIES);
    		availableCategories= ConvertUtil.convertList(categoryList);
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
        log.debug("Category from save....................: "+categoryId);
        setCategory(new Category(2L,"Gas"));
        List<Category> categoryList = (List) getServletContext().getAttribute(Constants.CATEGORIES);
        Iterator<Category> iterator = categoryList.iterator();
        while(iterator.hasNext()) {
        	Category object = iterator.next();
        	log.debug("Here is the object from CategoryConverter.save() 1: "+object.toString());
        	if(String.valueOf(object.getDescription()).equals(categoryId)) {
            	log.debug("Here is the object from CategoryConverter.save() 2: "+object.toString());
                setCategory(object);
            }
        }
        rawDataManager.save(rawData);
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
