package com.copower.pmcc.assess.test;

import com.copower.pmcc.assess.dal.entity.DataBuildingNewRate;
import com.copower.pmcc.assess.service.data.DataBuildingNewRateService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class DataBuildingNewRateServiceTest {
    private  DataBuildingNewRateService dataBuildingNewRateService;
    private final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void select(){
        List<DataBuildingNewRate> dataBuildingNewRates = dataBuildingNewRateService.getDataBuildingNewRateList("简易结构");
        dataBuildingNewRates.forEach(dataBuildingNewRate -> {
            System.out.println(dataBuildingNewRate);
        });
    }

    @Before
    public void before(){
        dataBuildingNewRateService = context.getBean(DataBuildingNewRateService.class);
    }
}
