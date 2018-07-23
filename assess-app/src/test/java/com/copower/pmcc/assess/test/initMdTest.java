package com.copower.pmcc.assess.test;

import com.copower.pmcc.assess.dal.basis.dao.method.MdMarketCompareDao;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompare;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by kings on 2018-7-23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContextTest.xml"})
public class initMdTest {
    @Autowired
    private MdMarketCompareDao mdMarketCompareDao;

    @org.junit.Test
    public void init(){
        MdMarketCompare mdMarketCompare=new MdMarketCompare();
        mdMarketCompare.setName("abc");
        mdMarketCompareDao.addMarketCompare(mdMarketCompare);
    }
}
