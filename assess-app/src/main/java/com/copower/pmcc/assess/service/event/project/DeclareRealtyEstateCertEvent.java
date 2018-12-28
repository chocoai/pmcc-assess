package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.dal.basis.entity.DeclareApply;
import com.copower.pmcc.assess.service.project.declare.DeclareApplyService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kings on 2018-9-29
 * 监听 在建工程和设备安装以及房产证 土地证 以及 不动产证
 */
@Component
public class DeclareRealtyEstateCertEvent extends ProjectTaskEvent {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareApplyService declareApplyService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution)throws  Exception {
        super.processFinishExecute(processExecution);
        DeclareApply declareApply = declareApplyService.getDeclareApplyByProcessInsId(processExecution.getProcessInstanceId());
        if (declareApply == null) {
            return;
        }
        declareApplyService.writeToDeclareRecord(declareApply);
    }
}
