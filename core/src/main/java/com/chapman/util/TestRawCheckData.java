package com.chapman.util;

import java.util.List;
import java.util.Map;

import com.chapman.dao.RawDataDao;
import com.chapman.dao.SearchException;
import com.chapman.dao.hibernate.GenericDaoHibernate;
import com.chapman.dao.hibernate.RawDataDaoHibernate;
import com.chapman.model.RawBankCheckingData;
import com.chapman.service.RawDataManager;
import com.chapman.service.impl.RawDataManagerImpl;

public class TestRawCheckData extends GenericDaoHibernate<RawBankCheckingData, Long> implements RawDataDao, RawDataManager {
	public TestRawCheckData() {
		super(RawBankCheckingData.class);
	}
	
	public static void main(String[] args) {
		RawDataManager manager = new RawDataManagerImpl();
		RawDataDao dao = new RawDataDaoHibernate();
		try {
			RawBankCheckingData data = new RawBankCheckingData();
			data.setTransactionId("ID1234");
			data.setAmount(new Double(23.12));
			data = dao.save(data);
			List<RawBankCheckingData> list = dao.findDataByTransactionId("ID1234");
			System.out.println(
					"saved data from testInsertRawCheckingData.............." + list.get(0).getTransactionId());
			System.out.println("insertRawCheckingData result size..................." + list.size());
			List<RawBankCheckingData> found = manager.findDataByTransactionId("ID1234");
			System.out.println("insertRawCheckingData amount..................." + found.get(0).getAmount());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<RawBankCheckingData> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RawBankCheckingData> getAllDistinct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RawBankCheckingData> search(String searchTerm) throws SearchException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RawBankCheckingData get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RawBankCheckingData save(RawBankCheckingData object) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reindex() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reindexAll(boolean async) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<RawBankCheckingData> search(String searchTerm, Class clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RawBankCheckingData> loadRawCheckingData(String file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RawBankCheckingData insertRawCheckingData(RawBankCheckingData data) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RawBankCheckingData> findDataByTransactionId(String transactionId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void remove(RawBankCheckingData object) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List findByNamedQuery(String queryName, Map queryParams) {
		// TODO Auto-generated method stub
		return null;
	}
}