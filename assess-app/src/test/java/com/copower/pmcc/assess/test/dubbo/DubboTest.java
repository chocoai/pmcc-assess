package com.copower.pmcc.assess.test.dubbo;

import com.copower.pmcc.bpm.api.provider.BpmRpcToolsService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by kings on 2019-7-24.
 */

public class DubboTest {

    @Test
    public void test(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("test/dubbo-test.xml");
        applicationContext.start();
        BpmRpcToolsService toolsService = (BpmRpcToolsService)applicationContext.getBean("bpmRpcToolsService");
        BootstrapTableVo workLogList = toolsService.getWorkLogList("pmcc-assess", "", "", 1, 10);
        System.out.print(workLogList.getTotal());
    }
}
