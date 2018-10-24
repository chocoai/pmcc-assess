package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by kings on 2018-10-24.
 */
@Service
public class BasicApplyService {
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CommonService commonService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public ProcessUserDto sumTask(Integer tableId,String tableName) {
        ProcessUserDto processUserDto = new ProcessUserDto();
        ProcessInfo processInfo = new ProcessInfo();
        //流程描述
        processInfo.setFolio(String.format("流程描述案例基础数据:%s", DateUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss")));
        String boxName = null;
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        processInfo.setTableName(tableName);
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setProcessEventExecutor(BaseProcessEvent.class);
        processInfo.setRemarks("流程发起");
        try {
            processUserDto = processControllerComponent.processStart(processInfo, commonService.thisUserAccount(), false);
        } catch (Exception e) {
            logger.error(String.format("流程发起失败: %s",e.getMessage()),e);
            try {
                throw new Exception();
            } catch (Exception e1) {
            }
        }
        return processUserDto;
    }
}
