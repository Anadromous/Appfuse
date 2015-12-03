/**
 * 
 */
package com.chapman.service.impl;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.chapman.dao.RawDataDao;
import com.chapman.model.RawBankCheckingData;

/**
 * @author or0189783
 *
 */
public class RawDataManagerImplTest extends BaseManagerMockTestCase {

	@InjectMocks
	private RawDataManagerImpl manager;

	@Mock
	private RawDataDao dao;

	@Test
	public void testRawData() throws Exception {
		log.debug("testing testRawData getAll()..."); // given final
		List<RawBankCheckingData> rawData = new ArrayList<RawBankCheckingData>();
		given(dao.getAll()).willReturn(rawData); // when
		List<RawBankCheckingData> result = manager.getAll();
		log.debug("result size..................................."+ result.size());
		// then
		assertSame(rawData, result);
	}

	@Test
	public void testReadCsvFileApache() throws Exception {
		log.debug("testing got the records...................");
		// given(util.readCsvFileApache()).willReturn(records); //when
		List<RawBankCheckingData> result = manager.loadRawCheckingData("C:/chapman/Downloads/HistoryDownload.csv");
		log.debug("result size..................." + result.size());
		assertTrue(result.size() >= 0);
	}

	@Test
	public void testSavePerson() throws Exception {
		log.debug("testing save...");
		// given
		final RawBankCheckingData d = new RawBankCheckingData();
		d.setTransactionId("ID1234");
		d.setAmount(new Double(23.12));
		given(dao.save(d)).willReturn(d);
		// when
		RawBankCheckingData p = (RawBankCheckingData) manager.save(d);
		log.debug("RawBankCheckingData from db:................."+ p.getTransactionId());
		// then
		verify(dao).save(d);
	}

	@Test
	public void testSaveCheckingData() throws Exception { 
		// given final
		RawBankCheckingData data = new RawBankCheckingData();
		data.setId(new Long(1));
		given(dao.get(1L)).willReturn(data);
		data.setTransactionId("ID1234");
		given(dao.save(data)).willReturn(data); // when
		RawBankCheckingData returned = manager.save(data); // then
		assertTrue(returned.getTransactionId().equals("ID1234"));
	}

	@Test
	public void testSaveRawBankCheckingData() throws Exception {
		log.debug("testing save...");
		// given
		final RawBankCheckingData d = new RawBankCheckingData();
		d.setTransactionId("ID1234");
		d.setAmount(new Double(23.12));
		given(dao.save(d)).willReturn(d);
		// when
		manager.save(d);
		// then
		verify(dao).save(d);
	}
	
/*	@Test
	public void testInsertRawCheckingData() throws Exception{
		RawBankCheckingData data = new RawBankCheckingData();
        data.setTransactionId("ID1234");
        data.setAmount(new Double(23.12));
        data = dao.save(data);
		//for(RawBankCheckingData data : result){
        data = dao.get(data.getId());
        log.debug("saved data from testInsertRawCheckingData.............."+data.getTransactionId());
		//}
		List<RawBankCheckingData> list = manager.getAll();
		//ID21187
		log.debug("insertRawCheckingData result size..................." + list.size());
		List<RawBankCheckingData> found = manager.findDataByTransactionId("ID1234");
		//log.debug("insertRawCheckingData amount..................." + d.get(0).getAmount());
	}*/

}
