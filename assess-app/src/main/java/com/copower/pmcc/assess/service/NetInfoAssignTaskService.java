package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoAssignTaskDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoAssignTask;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecord;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.event.project.NetInfoAssignTaskEvent;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NetInfoAssignTaskService {
    @Autowired
    private NetInfoAssignTaskDao netInfoAssignTaskDao;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private NetInfoRecordDao netInfoRecordDao;


    private final Logger log = LoggerFactory.getLogger(getClass());

    @Transactional(rollbackFor = Exception.class)
    public void applyCommit(Integer id, BaseParameterEnum baseParameterEnum) throws BusinessException {
        try {
            NetInfoAssignTask netInfoAssignTask = this.getDataById(id);
            netInfoAssignTask.setStatus(ProcessStatusEnum.RUN.getValue());
            //流程
            ProcessUserDto processUserDto = submitTask(netInfoAssignTask, baseParameterEnum);
            if (processUserDto != null) netInfoAssignTask.setProcessInsId(processUserDto.getProcessInsId());
            netInfoAssignTaskDao.modifyNetInfoAssignTask(netInfoAssignTask);

            List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(netInfoAssignTask.getNetInfoIds()));
            List<NetInfoRecord> infoRecords = LangUtils.transform(integers, o -> netInfoRecordDao.getInfoById(o));
            if(CollectionUtils.isNotEmpty(infoRecords)){
                for (NetInfoRecord netInfo: infoRecords) {
                    netInfo.setStatus(1);
                    netInfoRecordDao.updateInfo(netInfo);
                }
            }

        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    private ProcessUserDto submitTask(NetInfoAssignTask netInfoAssignTask, BaseParameterEnum baseParameterEnum) throws BusinessException {
        ProcessUserDto processUserDto = new ProcessUserDto();
        ProcessInfo processInfo = new ProcessInfo();
        String boxName = baseParameterService.getBaseParameter(baseParameterEnum);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        processInfo.setFolio("拍卖信息补全");//流程描述
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(NetInfoAssignTask.class));
        processInfo.setProcessEventExecutor(NetInfoAssignTaskEvent.class);
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setTableId(netInfoAssignTask.getId());
        processInfo.setRemarks(ProjectStatusEnum.STARTAPPLY.getName());
        processInfo.setStartUser(netInfoAssignTask.getExecutor());
        try {
            processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(), processInfo, commonService.thisUserAccount(), false);
            //关闭任务
            String url = String.format("/pmcc-assess/netInfoAssignTask/apply?id=%s", netInfoAssignTask.getId());
            ProjectResponsibilityDto projectPlanResponsibility = new ProjectResponsibilityDto();
            projectPlanResponsibility.setUrl(url);
            List<ProjectResponsibilityDto> projectResponsibilityDtoList = bpmRpcProjectTaskService.getProjectTaskList(projectPlanResponsibility);
            if (CollectionUtils.isNotEmpty(projectResponsibilityDtoList)) {
                for (ProjectResponsibilityDto oo : projectResponsibilityDtoList) {
                    bpmRpcProjectTaskService.deleteProjectTask(oo.getId());
                }
            }
        } catch (Exception e) {
            log.error(String.format("流程发起失败: %s", e.getMessage()), e);
            throw new BusinessException(String.format("流程发起失败: %s", e.getMessage()));
        }
        return processUserDto;
    }

    public NetInfoAssignTask getDataByProcessInsId(String processInsId) {
        NetInfoAssignTask netInfoAssignTask = new NetInfoAssignTask();
        netInfoAssignTask.setProcessInsId(processInsId);
        List<NetInfoAssignTask> netInfoAssignTasks = netInfoAssignTaskDao.getNetInfoAssignTask(netInfoAssignTask);
        if (com.alibaba.dubbo.common.utils.CollectionUtils.isNotEmpty(netInfoAssignTasks))
            netInfoAssignTask = netInfoAssignTasks.get(0);
        return netInfoAssignTask;
    }

    public NetInfoAssignTask getDataById(Integer id) {
        return netInfoAssignTaskDao.getDataById(id);
    }

    public void approvalCommit(ApprovalModelDto approvalModelDto) {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (BpmException e) {
            e.printStackTrace();
            log.error("提交失败", e);
        }
    }

    /**
     * 返回修改 提交
     *
     * @param approvalModelDto
     * @throws Exception
     */
    public void processEditSubmit(ApprovalModelDto approvalModelDto) throws Exception {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }


    public void editData(NetInfoAssignTask netInfoAssignTask) {
        netInfoAssignTaskDao.modifyNetInfoAssignTask(netInfoAssignTask);
    }


}
