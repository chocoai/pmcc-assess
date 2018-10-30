package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basic.dao.BasicApplyDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicApply;
import com.copower.pmcc.assess.service.event.project.BasicApplyEvent;
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
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by kings on 2018-10-24.
 */
@Service
public class BasicApplyService {
    @Autowired
    private BasicApplyDao basicApplyDao;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CommonService commonService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public BasicApply getBasicApplyByProcessInsId(String processInsId){
        BasicApply basicApply = new BasicApply();
        basicApply.setProcessInsId(processInsId);
        List<BasicApply> basicApplies = basicApplyDao.getBlockList(basicApply);
        if (!ObjectUtils.isEmpty(basicApplies)){
            return basicApplies.get(0);
        }else {
            return null;
        }
    }

    public Integer saveBasicApply(BasicApply basicApply){
        basicApply.setCreator(commonService.thisUserAccount());
        basicApply.setStatus(ProjectStatusEnum.STARTAPPLY.getKey());
        Integer id = basicApplyDao.saveBasicApply(basicApply);
        basicApply.setId(id);
        return  id;
    }

    public boolean updateBasicApply(BasicApply basicApply){
        return basicApplyDao.updateBlock(basicApply);
    }

    /**
     * 流程发起
     * @param basicApply
     * @param tableName
     * @return
     * @throws Exception
     */
    public ProcessUserDto sumTask(BasicApply basicApply,String tableName)throws Exception {
        ProcessUserDto processUserDto = null;
        ProcessInfo processInfo = new ProcessInfo();
        //流程描述
        processInfo.setFolio(String.format("流程描述案例基础数据审批:%s", DateUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss")));
        final String boxName = "8e4533ae-26c4-4649-9264-4512342d7883";
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        processInfo.setTableName(tableName);
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setProcessEventExecutor(BasicApplyEvent.class);
        processInfo.setRemarks(ProjectStatusEnum.STARTAPPLY.getKey());
        processInfo.setProcessEventExecutorName(BasicApplyEvent.class.getSimpleName());
        processInfo.setTableId(basicApply.getId());
        try {
            processUserDto = processControllerComponent.processStart(processInfo, processControllerComponent.getThisUser(), false);
            basicApply.setProcessInsId(processUserDto.getProcessInsId());
            basicApply.setStatus(ProjectStatusEnum.RUNING.getKey());
            this.updateBasicApply(basicApply);
        } catch (Exception e) {
            logger.error(String.format("流程发起失败: %s",e.getMessage()),e);
           throw e;
        }
        return processUserDto;
    }
}
