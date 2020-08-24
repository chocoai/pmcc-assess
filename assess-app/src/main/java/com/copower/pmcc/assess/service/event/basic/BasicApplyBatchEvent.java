package com.copower.pmcc.assess.service.event.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatch;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kings on 2018-10-30
 */
@Component
public class BasicApplyBatchEvent extends BaseProcessEvent {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        if(!processExecution.getProcessStatus().isFinish()) return;
        BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByProcessInsId(processExecution.getProcessInstanceId());
        basicApplyBatchService.handleCaseData(applyBatch);
    }


}
