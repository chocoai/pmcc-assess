package com.copower.pmcc.assess.proxy.face;

import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.service.base.BaseAssistService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotationBeanFactory;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotationEntity;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述: 初始化自定义工作流服务到bpm
 *
 * @author Red
 * @version 1.0
 * @date: 2017/12/28 14:10
 */

@Service
public class InitWorkFlowAnnotationListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(InitWorkFlowAnnotationListener.class);

    @Autowired
    private BaseAssistService baseAssistService;
    @Autowired
    private ApplicationConstant applicationConstant;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            ApplicationContext applicationContext = event.getApplicationContext();

            try {
                List<WorkFlowAnnotationEntity> planInterfaceBens = WorkFlowAnnotationBeanFactory.workFlow(applicationContext, applicationConstant.getAppKey(), ProjectPlanInterface.class);
                List<WorkFlowAnnotationEntity> taskInterfaceBens = WorkFlowAnnotationBeanFactory.workFlow(applicationContext, applicationConstant.getAppKey(), ProjectTaskInterface.class);
                if (CollectionUtils.isNotEmpty(planInterfaceBens)) {
                    baseAssistService.initBaseAssist(planInterfaceBens, BaseConstant.ASSESS_BASE_ASSIST_STAGE);
                }

                if (CollectionUtils.isNotEmpty(taskInterfaceBens)) {
                    baseAssistService.initBaseAssist(taskInterfaceBens, BaseConstant.ASSESS_BASE_ASSIST_MATTER);
                }

            } catch (Exception e) {
                logger.error(String.format("初始化%s的自定义工作流服务存在问题,请手动初始化.", applicationConstant.getAppKey()), e);
            }
        }

    }
}
