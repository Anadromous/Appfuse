/**
 * 
 */
package com.chapman.dao.hibernate;

import java.util.Date;
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
		log.debug("RawDataDaoHibernate saveData: " +data.toString());
		//log.debug("data category description: " + data.getCategory().toString());
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
	public List<RawBankCheckingData> getAllData(){
		Query qry = getSession().createQuery("from RawBankCheckingData");
        return qry.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RawBankCheckingData> getUnassighnedData(){
		Query qry = getSession().createQuery("from RawBankCheckingData u where u.category = NULL order by upper(u.transDesc)");
        return qry.list();
	}
	
	@Override
	public int saveAndUpdateAllCategories(RawBankCheckingData b){
		Query qry = getSession().createQuery("update RawBankCheckingData u set u.category = :category where u.description like :extDesc");
		qry.setParameter("category", b.getCategory());
		qry.setParameter("extDesc", "%"+b.getExtDesc()+"%");
		int result = qry.executeUpdate();
		log.debug("rows updated........................ "+result);
		return result;
	}
	
	@Override
	public List<RawBankCheckingData> getDateRangeData(String from, String to){
		log.debug("....................................dates: from "+from+", to "+to);
		Query qry = getSession().createQuery("from RawBankCheckingData u where u.transactionDate between '"+from+"' and '"+to+"' order by u.transactionDate");
		return qry.list();
	}

	@Override
	public Double getCheckingCategorySum(Long categoryId, String from, String to){
		Query qry = getSession().createQuery("select sum(u.amount) from RawBankCheckingData u where u.category.id = :category and u.transactionDate between '"+from+"' and '"+to+"'");
		qry.setParameter("category", categoryId);
		List<Double> amount = qry.list();
		return amount.get(0);
	}
}
