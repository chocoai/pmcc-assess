package com.copower.pmcc.assess.service.event.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectChangeLogDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.ProjectInfoChangeVo;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectStateChangeService;
import com.copower.pmcc.assess.service.project.initiate.InitiateConsignorService;
import com.copower.pmcc.assess.service.project.initiate.InitiatePossessorService;
import com.copower.pmcc.assess.service.project.initiate.InitiateUnitInformationService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.erp.api.dto.SysProjectDto;
import com.copower.pmcc.erp.api.provider.ErpRpcProjectService;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProjectInfoChangeEvent extends BaseProcessEvent {
    @Autowired
    private ProjectInfoService projectInfoService;

    @Autowired
    private ProjectChangeLogDao projectChangeLogDao;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private ErpRpcProjectService erpRpcProjectService;
    @Autowired
    private ProjectStateChangeService stateChangeService;
    @Autowired
    private InitiateConsignorService consignorService;
    @Autowired
    private InitiateUnitInformationService unitInformationService;
    @Autowired
    private InitiatePossessorService possessorService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        if(!processExecution.getProcessStatus().isFinish()) return;
        ProjectChangeLog costsProjectChangeLog = stateChangeService.getDataByProcessInsId(processExecution.getProcessInstanceId());
        ProjectInfoChangeVo projectInfoChangeVo = JSON.parseObject(costsProjectChangeLog.getNewRecord(), ProjectInfoChangeVo.class);
        costsProjectChangeLog.setStatus(ProcessStatusEnum.FINISH.getValue());
        projectChangeLogDao.modifyProjectChangeLog(costsProjectChangeLog);

        ProjectInfo projectInfo = JSON.parseObject(projectInfoChangeVo.getProjectInfo(), ProjectInfo.class);
        if(projectInfo!=null&&projectInfo.getId()!=null){
            projectInfoService.saveProjectInfo(projectInfo);

            InitiateConsignor initiateConsignor = JSON.parseObject(projectInfoChangeVo.getConsignor(), InitiateConsignor.class);
            consignorService.saveAndUpdateInitiateConsignor(initiateConsignor);
            InitiatePossessor initiatePossessor = JSON.parseObject(projectInfoChangeVo.getPossessor(), InitiatePossessor.class);
            possessorService.saveAndUpdate(initiatePossessor);
            InitiateUnitInformation initiateUnitInformation = JSON.parseObject(projectInfoChangeVo.getUnitInformation(), InitiateUnitInformation.class);
            unitInformationService.saveAndUpdate(initiateUnitInformation);
            ProjectInfo info = projectInfoService.getProjectInfoById(projectInfo.getId());
            //更新到erp中
            Integer publicProjectId = info.getPublicProjectId();
            if (publicProjectId != null) {
                SysProjectDto sysProjectDto = erpRpcProjectService.getProjectInfoById(publicProjectId);
                sysProjectDto.setProjectName(projectInfo.getProjectName());
                erpRpcProjectService.saveProject(sysProjectDto);
            }
        }
    }
}
