package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.dal.dao.HousePriceIndexDao;
import com.copower.pmcc.assess.dal.entity.HousePriceIndex;
import com.copower.pmcc.assess.service.data.HousePriceIndexService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HousePriceIndexServiceTest {
    private final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    private HousePriceIndexDao housePriceIndexDao;
    private HousePriceIndexService housePriceIndexService;

    @Test
    public void add()throws Exception{
        HousePriceIndex housePriceIndex = new HousePriceIndex();
        housePriceIndex.setCreator("bob");
        housePriceIndex.setIndexCalendar("24");
        housePriceIndex.setGmtCreated(new Date());
        housePriceIndex.setYearMonthCalendar(new Date());
        housePriceIndexService.addHousePriceIndex(housePriceIndex);
    }

    @Test
    public void update()throws Exception{
        HousePriceIndex housePriceIndex = housePriceIndexService.get(1);
        System.out.println(housePriceIndex);
        housePriceIndex.setIndexCalendar("77");
        housePriceIndex.setYearMonthCalendar(new Date());
        housePriceIndexService.update(housePriceIndex);
    }

    @Test
    public void remove()throws Exception{
        housePriceIndexService.removeHousePriceIndeX(2);
    }

    @Test
    public void select()throws Exception{
//        List<HousePriceIndex> housePriceIndices = housePriceIndexDao.list(null);
//        housePriceIndices.forEach(housePriceIndex -> {
//            System.out.println("---------------------------> "+housePriceIndex);
//        });

        Date start = new Date();
        Date end = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        start = format.parse("1896-02-29");
        end = format.parse("2018-02-08");
        List<HousePriceIndex> housePriceIndices = housePriceIndexService.list(null,null);
        housePriceIndices.forEach(housePriceIndex -> {
            System.out.println("---------------------------> "+housePriceIndex);
        });
    }

    @Before
    public void before(){
        housePriceIndexDao = context.getBean(HousePriceIndexDao.class);
        housePriceIndexService = context.getBean(HousePriceIndexService.class);
    }
}
