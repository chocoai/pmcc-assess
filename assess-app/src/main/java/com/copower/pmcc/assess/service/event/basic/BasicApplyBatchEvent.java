package com.copower.pmcc.assess.service.event.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatch;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.executor.ProcessEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kings on 2018-10-30
 */
@Component
public class BasicApplyBatchEvent implements ProcessEventExecutor {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private BasicApplyBatchDao basicApplyBatchDao;

    @Override
    public void processStartExecute(String processInstanceId) throws Exception {

    }

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByProcessInsId(processExecution.getProcessInstanceId());
        applyBatch.setStatus(ProcessStatusEnum.FINISH.getValue());
        writeToCase(applyBatch);
    }

    /**
     * 数据写入到案例库
     * @param applyBatch
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void writeToCase(BasicApplyBatch applyBatch) throws Exception {
        //更改状态
        basicApplyBatchDao.updateInfo(applyBatch);
        //拷贝到案例库
        basicApplyBatchService.copy(applyBatch.getId());
    }

}
