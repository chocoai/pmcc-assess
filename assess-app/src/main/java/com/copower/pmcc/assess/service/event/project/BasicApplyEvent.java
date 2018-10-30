package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.dal.basis.entity.DeclareInfo;
import com.copower.pmcc.assess.service.basic.PublicBasicService;
import com.copower.pmcc.assess.service.project.declare.DeclareInfoService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kings on 2018-10-30
 *
 */
@Component
public class BasicApplyEvent extends ProjectTaskEvent {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareInfoService declareInfoService;
    @Autowired
    private PublicBasicService publicBasicService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) {
        super.processFinishExecute(processExecution);
        DeclareInfo declareInfo = declareInfoService.getDeclareInfoByProcessInsId(processExecution.getProcessInstanceId());
        if (declareInfo == null) {
            return;
        }

        if (declareInfo != null){
            if (declareInfo.getProcessInsId() != null){
                try {
                    publicBasicService.flowWrite(declareInfo.getProcessInsId());
                } catch (Exception e1) {
                    logger.error(e1.getMessage(),e1);
                }
            }
        }
    }

}
