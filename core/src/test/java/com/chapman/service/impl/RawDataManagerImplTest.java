/**
 * 
 */
package com.chapman.service.impl;

import static org.junit.Assert.assertSame;
import static org.mockito.BDDMockito.given;

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
}
