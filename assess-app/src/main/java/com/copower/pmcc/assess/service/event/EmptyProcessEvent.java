package com.copower.pmcc.assess.service.event;

import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.executor.ProcessEventExecutor;

/**
 * 空监听器
 * Created by wangpc on 2019-10-22.
 */
public class EmptyProcessEvent implements ProcessEventExecutor {
    @Override
    public void processStartExecute(String processInstanceId) throws Exception {

    }

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {

    }
}
