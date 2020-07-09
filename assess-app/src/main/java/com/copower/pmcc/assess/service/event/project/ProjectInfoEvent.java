package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.common.enums.InitiateContactsEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateUnitInformationVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.assess.service.project.initiate.InitiateContactsService;
import com.copower.pmcc.assess.service.project.initiate.InitiateUnitInformationService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/2/6
 * @time: 10:53
 */
@Component
public class ProjectInfoEvent extends BaseProcessEvent {
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private InitiateContactsService initiateContactsService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private InitiateUnitInformationService unitInformationService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        //回写crm联系人信息
        ProjectInfo projectInfo = projectInfoService.getProjectInfoByProcessInsId(processExecution.getProcessInstanceId());
        if (projectInfo != null) {
            initiateContactsService.writeCrmCustomerDto(projectInfo.getId(), InitiateContactsEnum.UNIT_INFORMATION.getId());
        }
        //更新项目状态
        projectInfo.setProjectStatus(ProjectStatusEnum.NORMAL.getKey());
        projectInfoService.updateProjectInfo(projectInfo);

        //初始化项目阶段
        if (projectInfo.getBisAssign() != true) {
            Integer projectTypeId = projectInfo.getProjectTypeId();//项目类别id
            List<ProjectWorkStage> projectWorkStages = projectWorkStageService.queryWorkStageByClassIdAndTypeId(projectTypeId, true);
            projectInfoService.initProjectWorkStages(projectInfo, projectWorkStages);
            publicService.writeToErpProject(projectInfo);
            InitiateUnitInformationVo unitInformationVo = unitInformationService.getDataByProjectId(projectInfo.getId());
            unitInformationService.roundWrite(unitInformationVo);
        }
    }
}
