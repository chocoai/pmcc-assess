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
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

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

    @Override
    public ModelAndView applyView(ProjectPlan projectPlan) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageGenerate/planGenerateIndex", "", 0, "-1", "");
        //获取报告类型
        setModelParam(projectPlan, modelAndView);
        return modelAndView;
    }

    private void setModelParam(ProjectPlan projectPlan, ModelAndView modelAndView) {
        List<BaseDataDic> reportTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.REPORT_TYPE);
        if (CollectionUtils.isNotEmpty(reportTypeList)) {
            Map<String, String> stringMap = Maps.newHashMap();
            reportTypeList.stream().forEach(oo -> {
                if (Objects.equal(oo.getFieldName(), AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT)) {
                    stringMap.put(oo.getFieldName(), "REPORT_TYPE_PREAUDIT");
                }
                if (Objects.equal(oo.getFieldName(), AssessDataDicKeyConstant.REPORT_TYPE_TECHNOLOGY)) {
                    stringMap.put(oo.getFieldName(), "REPORT_TYPE_TECHNOLOGY");
                }
                if (Objects.equal(oo.getFieldName(), AssessDataDicKeyConstant.REPORT_TYPE_RESULT)) {
                    stringMap.put(oo.getFieldName(), "REPORT_TYPE_RESULT");
                }
            });
            if (!stringMap.isEmpty()) {
                StringBuilder builder = new StringBuilder(8);
                for (Map.Entry<String, String> stringEntry : stringMap.entrySet()) {
                    builder.delete(0, builder.toString().length());
                    String[] strs = stringEntry.getKey().split("\\.");
                    for (String s : strs) {
                        builder.append(s);
                    }
                    //必要的(用做页面上附件的字段,与com.copower.pmcc.assess.service.project.generate.GenerateReportService.createSysAttachment相一致)
                    modelAndView.addObject(stringEntry.getValue(), FormatUtils.underlineToCamel(builder.toString(), false));
                }
            }
            modelAndView.addObject("reportTypeList", reportTypeList);
        }
        List<SchemeAreaGroup> schemeAreaGroupList = generateReportService.getAreaGroupList(projectPlan.getProjectId());
        modelAndView.addObject("schemeAreaGroupList", schemeAreaGroupList);
        modelAndView.addObject("projectPlan", projectPlan);
        GenerateReportGeneration schemeReportGeneration = new GenerateReportGeneration();
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
            schemeReportGeneration.setInvestigationsStartDate(startTime.stream().sorted(
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
            schemeReportGeneration.setInvestigationsEndDate(endTime.stream().sorted().findFirst().get());
        }
        Map<String, String> qualification = new HashMap<>();
        qualification.put(AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCTDGJS.getValue(), AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCTDGJS.getName());
        qualification.put(AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCZCGJS.getValue(), AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCZCGJS.getName());
        qualification.put(AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_SFJDR.getValue(), AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_SFJDR.getName());
        qualification.put(AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCFDCGJS.getValue(), AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCFDCGJS.getName());
        modelAndView.addObject("qualificationTypes", qualification);
        modelAndView.addObject("schemeReportGeneration", schemeReportGeneration);
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
