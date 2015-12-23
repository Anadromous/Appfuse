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
		log.debug("RawDataDaoHibernate getUnassighnedData....................................................");
		Query qry = getSession().createQuery("from RawBankCheckingData u where u.category = NULL order by upper(u.transDesc)");
        return qry.list();
	}
	
	@Override
	public int saveAndUpdateAllCategories(RawBankCheckingData b){
		log.debug("RawDataDaoHibernate saveAndUpdateAllCategories....................................................");
		Query qry = getSession().createQuery("update RawBankCheckingData u set u.category = :category where u.description like :extDesc");
		qry.setParameter("category", b.getCategory());
		qry.setParameter("extDesc", "%"+b.getExtDesc()+"%");
		int result = qry.executeUpdate();
		log.debug("rows updated........................ "+result);
		return result;
	}

}
