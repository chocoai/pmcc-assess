package com.copower.pmcc.assess.service.event.basic;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basic.entity.BasicApply;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.PublicBasicService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.executor.ProcessEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kings on 2018-10-30
 */
@Component
public class BasicApplyEvent implements ProcessEventExecutor {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private PublicBasicService publicBasicService;
    @Autowired
    private BasicApplyService basicApplyService;

    @Override
    public void processStartExecute(String processInstanceId) throws Exception {

    }

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        ProcessStatusEnum processStatusEnum = ProcessStatusEnum.create(processExecution.getProcessStatus().getValue());
        BasicApply basicApply = basicApplyService.getBasicApplyByProcessInsId(processExecution.getProcessInstanceId());
        switch (processStatusEnum) {
            case CLOSE:
                if (basicApply != null) {
                    basicApply.setStatus(ProjectStatusEnum.CLOSE.getKey());
                    basicApplyService.updateBasicApply(basicApply);
                }
                break;
            case FINISH:
                if (basicApply != null) {
                    basicApply.setStatus(ProjectStatusEnum.FINISH.getKey());
                    basicApplyService.updateBasicApply(basicApply);
                }
                publicBasicService.flowWrite(processExecution.getProcessInstanceId());
                break;
        }
    }

}
