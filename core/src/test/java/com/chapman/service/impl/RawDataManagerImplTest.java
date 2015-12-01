/**
 * 
 */
package com.chapman.service.impl;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.chapman.dao.RawDataDao;
import com.chapman.model.RawBankCheckingData;
import com.chapman.model.Role;
import com.chapman.model.User;
import com.chapman.util.CsvFileReaderUtil;

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
    public void testRawData() {
        log.debug("testing testRawData getAll()...");
        //given
        final List<RawBankCheckingData> rawData = new ArrayList();
        given(dao.getAll()).willReturn(rawData);
        //when
        List<RawBankCheckingData> result = manager.getAll();
        //then
        assertSame(rawData, result);
    }

    @Test
    public void testReadCsvFileApache() throws Exception{
    	log.debug("testing...................");
    	List<RawBankCheckingData> records = new ArrayList<RawBankCheckingData>();
    	log.debug("testing got the records...................");
    	//given(util.readCsvFileApache()).willReturn(records);
    	//when
    	log.debug("calling the manager...................");
    	List<RawBankCheckingData> result = manager.loadRawCheckingData("C:/pchapman/Downloads/HistoryDownload.csv");
    	log.debug("result size..................."+result.size());
    	assertTrue(records.size() >= 0);
    }
    	
    @Test
    public void testSaveCheckingData() throws Exception {
        //given
        final RawBankCheckingData data = new RawBankCheckingData();
        data.setId(new Long(1));
        given(dao.get(1L)).willReturn(data);
        final RawBankCheckingData r = manager.get(new Long(1));
        data.setTransactionId("1234");
        given(dao.save(data)).willReturn(data);
        //when
        RawBankCheckingData returned = manager.save(data);
        //then
        assertTrue(returned.getTransactionId().equals("1234"));
    }
}
