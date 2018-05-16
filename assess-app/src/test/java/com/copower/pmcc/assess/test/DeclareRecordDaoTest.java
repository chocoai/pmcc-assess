package com.copower.pmcc.assess.test;

import com.copower.pmcc.assess.dal.dao.DeclareRecordDao;
import com.copower.pmcc.assess.dal.entity.DeclareRecord;
import com.copower.pmcc.assess.service.project.DeclareRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by 13426 on 2018/5/15.
 */
public class DeclareRecordDaoTest {
    private static Logger logger = LoggerFactory.getLogger(DeclareRecordDaoTest.class);
    private static ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    private static DeclareRecordDao dao;
    private static DeclareRecordService service;
    public static void main(String[] args) {
        before();
        queryProvinceAll();
    }

    public static void queryProvinceAll(){
        List<DeclareRecord> declareRecords = service.queryNotSelect();
        declareRecords.parallelStream().forEach(declareRecord -> {
            logger.debug("---------------------------------->"+declareRecord);
        });
    }

    public static void before(){
        dao = context.getBean(DeclareRecordDao.class);
        service = context.getBean(DeclareRecordService.class);
    }
}
