package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.service.data.EvaluationThinkingService;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 13426 on 2018/4/26.
 */
public class EvaluationThinkingServiceTest {
    private final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    private Logger logger = LoggerFactory.getLogger(getClass());
    EvaluationThinkingService evaluationThinkingService = null;

    @Test
    public void list(){
        evaluationThinkingService.list(null).forEach(evaluationThinking -> {
            logger.info("---------------------------------------> "+evaluationThinking);
        });
    }

    @Before
    public void before(){
        evaluationThinkingService = context.getBean(EvaluationThinkingService.class);
    }
}
