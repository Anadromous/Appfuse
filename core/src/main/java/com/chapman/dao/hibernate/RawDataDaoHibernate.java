/**
 * 
 */
package com.chapman.dao.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
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


}
