/**
 * 
 */
package com.chapman.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.chapman.dao.RawDataDao;
import com.chapman.model.RawBankCheckingData;

/**
 * @author or0189783
 *
 */
@Repository("rawDataDao")
public class RawDataDaoHibernate extends GenericDaoHibernate<RawBankCheckingData, Long> implements RawDataDao{

	/**
	 * @param persistentClass
	 */
	public RawDataDaoHibernate() {
		super(RawBankCheckingData.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RawBankCheckingData> findDataByTransactionId(String transactionId) {
		return getSession().createCriteria(RawBankCheckingData.class).add(Restrictions.eq("transactionId", transactionId)).list();
	}
	
	public RawBankCheckingData saveData(RawBankCheckingData data){
        getSession().saveOrUpdate(data);
        // necessary to throw a DataIntegrityViolation and catch it in Manager
        getSession().flush();
        List<RawBankCheckingData> list = findDataByTransactionId(data.getTransactionId());
        return list.get(0);
	}
	
	@Override
	public RawBankCheckingData save(RawBankCheckingData b){
		return this.saveData(b);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RawBankCheckingData> getAllData(String greaterOrLess){
		Query qry = getSession().createQuery("from RawBankCheckingData u where u.amount < 0");
        return qry.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RawBankCheckingData> getUnassighnedData(String from, String to, String greaterOrLess){
		Query qry = getSession().createQuery("from RawBankCheckingData u where u.category = NULL and u.amount "+greaterOrLess+" 0 and u.postingDate between '"+from+"' and '"+to+"' order by upper(u.memo)");
        return qry.list();
	}
	
	@Override
	public int saveAndUpdateAllCategories(RawBankCheckingData b){
		Query qry = getSession().createQuery("update RawBankCheckingData u set u.category = :category where u.memo like :memo");
		qry.setParameter("category", b.getCategory());
		qry.setParameter("memo", "%"+b.getMemo()+"%");
		int result = qry.executeUpdate();
		log.debug("rows updated........................ "+result);
		return result;
	}
	
	@Override
	public List<RawBankCheckingData> getDateRangeData(String from, String to, String greaterOrLess){
		Query qry = getSession().createQuery("from RawBankCheckingData u where u.amount "+greaterOrLess+" 0 and u.postingDate between '"+from+"' and '"+to+"' order by u.postingDate");
		return qry.list();
	}

	@Override
	public Double getCheckingCategorySum(Long categoryId, String from, String to, String greaterOrLess){
		Query qry = getSession().createQuery("select sum(u.amount) from RawBankCheckingData u where u.amount "+greaterOrLess+" 0 and u.category.id = :category and u.postingDate between '"+from+"' and '"+to+"'");
		qry.setParameter("category", categoryId);
		List<Double> amount = qry.list();
		return amount.get(0);
	}

	@Override
	public List<RawBankCheckingData> getDataByCategory(Long categoryId, String from, String to, String greaterOrLess) {
		log.debug("getDataByCategory.........."+categoryId+", "+from+", "+to);
		Query qry = getSession().createQuery("from RawBankCheckingData u where u.amount "+greaterOrLess+" 0 and u.category.id = :category and u.postingDate between '"+from+"' and '"+to+"'");
		qry.setParameter("category", categoryId);
		return qry.list();
	}
	
	
}
