package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.ad.api.enums.AdPersonalEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.BaseProjectClassify;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.proxy.face.ProjectPlanInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
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
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private GenerateReportInfoService generateReportInfoService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;

    @Override
    public ModelAndView applyView(ProjectPlan projectPlan) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageGenerate/planGenerateIndex", "", 0, "-1", "");
        setModelParam(projectPlan, modelAndView);
        return modelAndView;
    }

    private void setModelParam(ProjectPlan projectPlan, ModelAndView modelAndView) {
        List<SchemeAreaGroup> schemeAreaGroupList = generateReportService.getAreaGroupList(projectPlan.getProjectId());
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
        modelAndView.addObject("generationVos", generateReportInfoService.initGenerateReportInfo(projectPlan.getProjectId(),projectPlan.getId()));
        List<KeyValueDto> keyValueDtoList = Lists.newArrayList();
        //获取到类型 类别 范围
        List<String> keys = Arrays.asList(AssessProjectClassifyConstant.SINGLE_HOUSE_LAND_CERTIFICATE_TYPE_SIMPLE,AssessProjectClassifyConstant.SINGLE_HOUSE_PROPERTY_CERTIFICATE_TYPE_SIMPLE) ;
        for (String key:keys){
            BaseProjectClassify baseProjectClassify = baseProjectClassifyService.getCacheProjectClassifyByFieldName(key);
            if (baseProjectClassify != null){
                keyValueDtoList.add(new KeyValueDto(baseProjectClassify.getId().toString(),baseProjectClassify.getName())) ;
            }
        }
        modelAndView.addObject("projectCategoryKeyValueDtoList", keyValueDtoList);
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
