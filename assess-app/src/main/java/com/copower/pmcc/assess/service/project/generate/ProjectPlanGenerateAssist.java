package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.ad.api.enums.AdPersonalEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.ProjectPlanInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;

    @Override
    public ModelAndView applyView(ProjectPlan projectPlan) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageGenerate/planGenerateIndex", "", 0, "-1", "");
        setModelParam(projectPlan, modelAndView);
        return modelAndView;
    }

    private void setModelParam(ProjectPlan projectPlan, ModelAndView modelAndView) {
        List<SchemeAreaGroup> schemeAreaGroupList = generateReportService.getAreaGroupList(projectPlan.getProjectId());
        GenerateReportInfo generateReportInfo = new GenerateReportInfo();
        List<ProjectPlanDetails> projectPlanDetailsList = Lists.newArrayList();
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlan.getProjectId());
        List<Date> startTime = Lists.newArrayList();
        List<Date> endTime = Lists.newArrayList();
        if (projectInfo != null) {
            projectPhaseService.queryProjectPhaseByCategory(projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), null).stream().filter(projectPhaseVo -> {
                if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                    return true;
                }
                if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                    return true;
                }
                return false;
            }).forEach(projectPhaseVo -> {
                ProjectPlanDetails query = new ProjectPlanDetails();
                query.setProjectId(projectPlan.getProjectId());
                query.setProjectPhaseId(projectPhaseVo.getId());
                List<ProjectPlanDetails> projectPlanDetails = projectPlanDetailsService.getProjectDetails(query);
                if (CollectionUtils.isNotEmpty(projectPlanDetails)) {
                    projectPlanDetailsList.addAll(projectPlanDetails);
                }
            });
            if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                projectPlanDetailsList.stream().forEach(projectPlanDetails -> {
                    startTime.add(projectPlanDetails.getPlanStartDate());
                    endTime.add(projectPlanDetails.getPlanEndDate());
                });
            }
        }
        if (CollectionUtils.isNotEmpty(startTime)) {
            generateReportInfo.setInvestigationsStartDate(startTime.stream().sorted(
                    //反向排序 取最小
                    (o1, o2) -> {
                        long thisTime = o1.getTime();
                        long anotherTime = o2.getTime();
                        return (thisTime > anotherTime ? -1 : (thisTime == anotherTime ? 0 : 1));
                    }
            ).findFirst().get());
        }
        if (CollectionUtils.isNotEmpty(endTime)) {
            //排序 取最大
            generateReportInfo.setInvestigationsEndDate(endTime.stream().sorted().findFirst().get());
        }
        Map<String, String> qualificationTypes = new HashMap<>();
        qualificationTypes.put(AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCTDGJS.getValue(), AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCTDGJS.getName());
        qualificationTypes.put(AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCZCGJS.getValue(), AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCZCGJS.getName());
        qualificationTypes.put(AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_SFJDR.getValue(), AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_SFJDR.getName());
        qualificationTypes.put(AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCFDCGJS.getValue(), AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCFDCGJS.getName());
        List<BaseDataDic> reportTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.REPORT_TYPE);
        List<BaseDataDic> reportTypeList2 = Lists.newArrayList();
        List<String> stringList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(reportTypeList)){
            for (BaseDataDic baseDataDic:reportTypeList){
                baseDataDic.setFieldName(generateCommonMethod.getReportFieldsName(baseDataDic.getFieldName(),null));
                reportTypeList2.add(baseDataDic) ;
                stringList.clear();
            }
        }
        modelAndView.addObject("reportTypeList",reportTypeList2);
        modelAndView.addObject("schemeAreaGroupList", schemeAreaGroupList);
        modelAndView.addObject("projectPlan", projectPlan);
        modelAndView.addObject("qualificationTypes", qualificationTypes);
        modelAndView.addObject("schemeReportGeneration", generateReportInfo);
        modelAndView.addObject("generationVos", generateReportService.getGenerateReportGenerationVos(projectPlan.getProjectId()));
    }

    @Override
    public ModelAndView approvalView(ProjectPlan projectPlan, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageGenerate/planGenerateApproval", projectPlan.getProcessInsId(), boxId, taskId, agentUserAccount);
        setModelParam(projectPlan, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalEdit(ProjectPlan projectPlan, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageGenerate/planGenerateIndex", projectPlan.getProcessInsId(), boxId, taskId, agentUserAccount);
        setModelParam(projectPlan, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView detailsView(ProjectPlan projectPlan, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageGenerate/planGenerateApproval", projectPlan.getProcessInsId(), boxId, "-1", "");
        setModelParam(projectPlan, modelAndView);
        return modelAndView;
    }

}
