package com.copower.pmcc.assess.service.event.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.ProjectReviewScoreItemVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectAssessmentBonusService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectMemberService;
import com.copower.pmcc.assess.service.project.ProjectReviewScoreService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.AssessmentTypeEnum;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxRoleUserService;
import com.copower.pmcc.chks.api.dto.AssessmentPerformanceDto;
import com.copower.pmcc.chks.api.provider.ChksRpcAssessmentPerformanceService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysDepartmentDto;
import com.copower.pmcc.erp.api.provider.ErpRpcDepartmentService;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ProjectReviewScoreEvent extends BaseProcessEvent {
    @Autowired
    private BaseService baseService;
    @Autowired
    private ProjectReviewScoreService projectReviewScoreService;
    @Autowired
    private ChksRpcAssessmentPerformanceService performanceService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private BpmRpcBoxRoleUserService bpmRpcBoxRoleUserService;
    @Autowired
    private ErpRpcDepartmentService erpRpcDepartmentService;
    @Autowired
    private ProjectInfoService projectInfoService;


    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        if (!processExecution.getProcessStatus().isFinish()) return;
        try {
            ProcessStatusEnum processStatusEnum = ProcessStatusEnum.create(processExecution.getProcessStatus().getValue());
            if (processStatusEnum == null) return;
            if (processStatusEnum.isFinish()) {
                //将项目经理的工时得分写入到考核系统中，
                ProjectReviewScore reviewScore = projectReviewScoreService.getReviewScoreByProcessInsId(processExecution.getProcessInstanceId());
                if (reviewScore == null) return;
                AssessmentPerformanceDto performanceDto = new AssessmentPerformanceDto();
                performanceDto.setProjectId(reviewScore.getProjectId());
                performanceDto.setAppKey(applicationConstant.getAppKey());
                performanceDto.setProjectName(reviewScore.getProjectName());
                performanceDto.setProcessInsId(processExecution.getProcessInstanceId());
                performanceDto.setByExaminePeople(projectMemberService.getProjectManager(reviewScore.getProjectId()));
                performanceDto.setExaminePeople(getJSZG());
                performanceDto.setExamineDate(DateUtils.now());
                performanceDto.setAssessmentType(AssessmentTypeEnum.WORK_HOURS.getValue());
                performanceDto.setAssessmentKey(AssessmentTypeEnum.WORK_HOURS.getValue());
                performanceDto.setExamineStatus(ProcessStatusEnum.FINISH.getValue());
                performanceDto.setBisEffective(true);
                performanceDto.setBisQualified(true);
                if (reviewScore.getContractNegotiation() != null) {
                    performanceDto.setBusinessKey("合同洽谈、合同签署及管理");
                    performanceDto.setExamineScore(reviewScore.getContractNegotiation());
                    performanceService.saveAndUpdatePerformanceDto(performanceDto, false);
                }
                if (reviewScore.getCustomerActivities() != null) {
                    performanceDto.setBusinessKey("客户活动、客户拜访、客户设诉、客户关怀");
                    performanceDto.setExamineScore(reviewScore.getCustomerActivities());
                    performanceService.saveAndUpdatePerformanceDto(performanceDto, false);
                }
                if (reviewScore.getWorkDivision() != null) {
                    performanceDto.setBusinessKey("人员分工、时间安排、费用管理");
                    performanceDto.setExamineScore(reviewScore.getWorkDivision());
                    performanceService.saveAndUpdatePerformanceDto(performanceDto, false);
                }
                if (reviewScore.getInvoiceCollection() != null) {
                    performanceDto.setBusinessKey("开票、收款完成且与会计核对一致");
                    performanceDto.setExamineScore(reviewScore.getInvoiceCollection());
                    performanceService.saveAndUpdatePerformanceDto(performanceDto, false);
                }

                List<ProjectReviewScoreItemVo> reviewScoreItemVoList = projectReviewScoreService.getEnableItemsByReviewId(reviewScore.getId());
                if (CollectionUtils.isEmpty(reviewScoreItemVoList)) return;
                for (ProjectReviewScoreItemVo projectReviewScoreItemVo : reviewScoreItemVoList) {
                    performanceDto.setExaminePeople(projectReviewScoreItemVo.getCreator());
                    performanceDto.setExamineScore(projectReviewScoreItemVo.getScore());
                    performanceDto.setBusinessKey("复核与指导/" + projectReviewScoreItemVo.getProjectPhaseName());
                    performanceService.saveAndUpdatePerformanceDto(performanceDto, false);
                }

                ProjectInfo projectInfo = projectInfoService.getProjectInfoById(reviewScore.getProjectId());
                if (projectInfo != null) {
                    projectInfo.setBisAssessmentFinish(true);
                    projectInfoService.updateProjectInfo(projectInfo);
                }
            }
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "ProjectAssessmentBonusEvent");
        }
    }

    //获取技术总工
    private String getJSZG() {
        SysDepartmentDto departmentAssess = erpRpcDepartmentService.getDepartmentAssess();
        List<String> jszgList = bpmRpcBoxRoleUserService.getDepartmentJszg(departmentAssess.getId());
        if (CollectionUtils.isNotEmpty(jszgList)) return jszgList.get(0);
        return null;
    }
}
