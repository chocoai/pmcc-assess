package com.copower.pmcc.assess.service.project.generate;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.ad.api.enums.AdPersonalEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.data.DataQualificationVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPhaseVo;
import com.copower.pmcc.assess.proxy.face.ProjectPlanInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataQualificationService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/25
 * @time: 16:45
 */
@Component
@WorkFlowAnnotation(desc = "报告生成计划")
public class ProjectPlanGenerateAssist implements ProjectPlanInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private GenerateReportService generateReportService;
    @Autowired
    private DataQualificationService dataQualificationService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;

    @Override
    public ModelAndView applyView(ProjectPlan projectPlan) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageGenerate/planGenerateIndex", "", 0, "-1", "");
        //获取报告类型
        List<BaseDataDic> reportTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.REPORT_TYPE);
        modelAndView.addObject("reportTypeList", reportTypeList);
        List<SchemeAreaGroup> schemeAreaGroupList = generateReportService.getAreaGroupList(projectPlan.getProjectId());
        modelAndView.addObject("schemeAreaGroupList", schemeAreaGroupList);
        modelAndView.addObject("projectPlan", projectPlan);
        List<DataQualificationVo> dataQualificationVos = Lists.newArrayList();
        List<DataQualification> dataQualificationList = dataQualificationService.getDataQualificationList(AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCFDCGJS.getValue());
        //资质数据
        if (CollectionUtils.isNotEmpty(dataQualificationList)) {
            dataQualificationList.parallelStream().forEach(dataQualification -> dataQualificationVos.add(dataQualificationService.getDataQualificationVo(dataQualification)));
            modelAndView.addObject("dataQualificationList", JSONObject.toJSONString(dataQualificationVos));
        }
        modelAndView.addObject("PERSONAL_QUALIFICATION_ASSESS_ZCFDCGJS", AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCFDCGJS.getValue());
        SchemeReportGeneration schemeReportGeneration = new SchemeReportGeneration();
        List<ProjectPlanDetails> projectPlanDetailsList = Lists.newArrayList();
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlan.getProjectId());
        Set<Long> startTime_ = Sets.newHashSet();
        Set<Long> endTime_ = Sets.newHashSet();
        List<Long> startTime = Lists.newArrayList();
        List<Long> endTime = Lists.newArrayList();
        if (projectInfo != null) {
            List<ProjectPhaseVo> projectPhaseVos = projectPhaseService.queryProjectPhaseByCategory(projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), null);
            List<ProjectPhaseVo> projectPhaseVoList = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(projectPhaseVos)) {
                projectPhaseVos.parallelStream().forEach(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        projectPhaseVoList.add(projectPhaseVo);
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        projectPhaseVoList.add(projectPhaseVo);
                    }
                });
            }
            if (CollectionUtils.isNotEmpty(projectPhaseVoList)) {
                projectPhaseVoList.parallelStream().forEach(projectPhaseVo -> {
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(projectPlan.getProjectId());
                    query.setProjectPhaseId(projectPhaseVo.getId());
                    List<ProjectPlanDetails> projectPlanDetails = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetails)) {
                        projectPlanDetailsList.addAll(projectPlanDetails);
                    }
                });
            }
            if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                projectPlanDetailsList.parallelStream().forEach(projectPlanDetails -> {
                    startTime_.add(projectPlanDetails.getPlanStartDate().getTime());
                    endTime_.add(projectPlanDetails.getPlanEndDate().getTime());
                });
            }
        }
        if (CollectionUtils.isNotEmpty(startTime_)) {
            startTime_.parallelStream().forEach(aLong -> {
                startTime.add(aLong);
            });
            Collections.sort(startTime);
            schemeReportGeneration.setInvestigationsStartDate(com.copower.pmcc.erp.common.utils.DateUtils.convertDate(startTime.get(0)));
        }
        if (CollectionUtils.isNotEmpty(endTime_)) {
            endTime_.parallelStream().forEach(aLong -> {
                endTime.add(aLong);
            });
            Collections.sort(endTime);
            schemeReportGeneration.setInvestigationsEndDate(com.copower.pmcc.erp.common.utils.DateUtils.convertDate(endTime.get(0)));
        }
        modelAndView.addObject("schemeReportGeneration", schemeReportGeneration);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(ProjectPlan projectPlan, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageGenerate/planGenerateApproval", projectPlan.getProcessInsId(), boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalEdit(ProjectPlan projectPlan, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageGenerate/planGenerateIndex", projectPlan.getProcessInsId(), boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public ModelAndView detailsView(ProjectPlan projectPlan, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageGenerate/planGenerateApproval", projectPlan.getProcessInsId(), boxId, "-1", "");
        return modelAndView;
    }

}
