package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.dal.dao.EvaluationMethodFieldDao;
import com.copower.pmcc.assess.dto.input.data.EvaluationMethodDto;
import com.copower.pmcc.assess.service.data.EvaluationMethodService;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by 13426 on 2018/4/24.
 */
public class EvaluationMethodServiceTest {
    private final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    private EvaluationMethodService evaluationMethodService = null;
    private EvaluationMethodFieldDao evaluationMethodFieldDao = null;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void add() {
        EvaluationMethodDto evaluationMethodDto = new EvaluationMethodDto();
        evaluationMethodDto.setCreator("root");
        evaluationMethodDto.setApplicableReason("适用原因模板");
        evaluationMethodDto.setMethod(3);
        evaluationMethodDto.setGmtCreated(new Date());
        evaluationMethodService.add(evaluationMethodDto);
        logger.debug("------------------> "+evaluationMethodDto);
    }

    @Test
    public void list(){
        evaluationMethodFieldDao.list(2).forEach(evaluationMethodField -> {
            logger.debug("------------------> "+evaluationMethodField);
        });
    }

    @Test
    public void select(){
        evaluationMethodService.list(2).forEach(evaluationMethodVo -> {
            logger.debug("---------------------> "+evaluationMethodVo);
        });
    }

    @Test
    public void update(){
        EvaluationMethodDto evaluationMethodDto = evaluationMethodService.get(2);
        logger.debug("---------------------> "+evaluationMethodDto);
        evaluationMethodDto.setMethod(4);
        evaluationMethodService.update(evaluationMethodDto);
        logger.debug("---------------------> "+evaluationMethodDto);
    }

    @Before
    public void init() {
        evaluationMethodService = (EvaluationMethodService) context.getBean("evaluationMethodService");
        evaluationMethodFieldDao = context.getBean(EvaluationMethodFieldDao.class);
    }
}
