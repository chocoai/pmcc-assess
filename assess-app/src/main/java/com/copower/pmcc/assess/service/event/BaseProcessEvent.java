package com.copower.pmcc.assess.service.event;

import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomDdlTableMapper;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.bpm.api.dto.model.BoxRuDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.executor.ProcessEventExecutor;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProcessExecutionService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * Created by wangpc on 2017/8/21.
 */
@Component
public class BaseProcessEvent implements ProcessEventExecutor {
    @Autowired
    private BaseService baseService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private CustomDdlTableMapper customDdlTableMapper;

    @Override
    public void processStartExecute(String s) {

    }

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        BoxRuDto boxRuDto = bpmRpcBoxService.getBoxRuByProcessInstId(processExecution.getProcessInstanceId());
        //处理流程结束的公共方法
        try {
            String sql = String.format("update %s set status='%s' where id=%s", boxRuDto.getTableName(), processExecution.getProcessStatus().getValue(), boxRuDto.getTableId());
            customDdlTableMapper.customTableSelect(sql);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
        }
    }
}
