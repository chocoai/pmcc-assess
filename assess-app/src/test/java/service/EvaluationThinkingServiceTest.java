package service;

import com.copower.pmcc.assess.service.data.EvaluationThinkingService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 13426 on 2018/4/27.
 */
public class EvaluationThinkingServiceTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    EvaluationThinkingService evaluationThinkingService = null;

    @Test
    public void list(){
        evaluationThinkingService.list(null).forEach(evaluationThinking -> {
            System.out.println("-------------------------> "+evaluationThinking);
        });
    }

    @Before
    public void before(){
        evaluationThinkingService = context.getBean(EvaluationThinkingService.class);
    }
}
