package com.chapman.webapp.action;

import java.io.Serializable;
import java.util.Date;
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
	private Map<String,String> avalableCategories;
    private RawBankCheckingData rawData = new RawBankCheckingData();
    private Long id;
    private Date transactionDate;
 
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
	 * @return the transactionDate
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Category getCategory(){
    	//log.debug("Category from rawData............................"+rawData.getDescription());
    	return getRawData().getCategory();
    }
    
    public void setCategory(Category category){
    	getRawData().setCategory(category);
    }
    
    @SuppressWarnings("unchecked")
    public Map<String,String> getAvailableCategories(){
    	if(avalableCategories == null){
    		List<Category> categoryList = (List) getServletContext().getAttribute(Constants.CATEGORIES);
    		avalableCategories= ConvertUtil.convertList(categoryList);
    	}
    	return avalableCategories;
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
        log.debug("rawDataManager: "+rawDataManager.toString());
        rawData = rawDataManager.save(rawData);
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
