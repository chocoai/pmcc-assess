package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.ad.api.enums.AdPersonalEnum;
import com.copower.pmcc.assess.common.enums.AssessProjectTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.generate.GenerateReportInfoDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.document.DocumentTemplateService;
import com.copower.pmcc.assess.service.event.project.GenerateEvent;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangpc on 2019-10-22.
 */
@Component
@WorkFlowAnnotation(desc = "出具报告成果")
public class ProjectTaskGenerateAssist implements ProjectTaskInterface {
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
    private DocumentTemplateService documentTemplateService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    @Autowired
    private GenerateReportInfoDao generateReportInfoDao;
    @Autowired
    private GenerateService generateService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageGenerate/taskGenerateIndex", "", 0, "-1", "");
        //意见稿模板
        List<DocumentTemplate> documentTemplateList = documentTemplateService.getDocumentTemplateList("",baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_TEMPLATE_TYPE_OPINION).getId(),null);
        modelAndView.addObject("documentTemplateList", documentTemplateList);
        setModelParam(projectPlanService.getProjectplanById(projectPlanDetails.getPlanId()), modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageGenerate/taskGenerateApproval", processInsId, boxId, taskId, agentUserAccount);
        setModelParam(projectPlanService.getProjectplanById(projectPlanDetails.getPlanId()), modelAndView);
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
        generateReportInfoService.initGenerateReportInfo(projectPlan.getProjectId(),projectPlan.getId());
        List<GenerateReportInfo> list =generateReportInfoDao.getGenerateReportInfoList(projectPlan.getProjectId());
        modelAndView.addObject("generationVos", LangUtils.transform(list, o -> generateReportInfoService.getGenerateReportInfoVo(o)));
        List<KeyValueDto> keyValueDtoList = Lists.newArrayList();
        for (AssessProjectTypeEnum typeEnum: AssessProjectTypeEnum.values()){
            keyValueDtoList.add(new KeyValueDto(typeEnum.getNumber().toString(),typeEnum.getDec())) ;
        }
        modelAndView.addObject("projectCategoryKeyValueDtoList", keyValueDtoList);
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageGenerate/taskGenerateIndex", processInsId, boxId, taskId, agentUserAccount);
        setModelParam(projectPlanService.getProjectplanById(projectPlanDetails.getPlanId()), modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageGenerate/taskGenerateApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        setModelParam(projectPlanService.getProjectplanById(projectPlanDetails.getPlanId()), modelAndView);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {
        if (StringUtils.isNotEmpty(processInsId)) { //修改监听器
            bpmRpcActivitiProcessManageService.setProcessEventExecutor(projectPlanDetails.getProcessInsId(), GenerateEvent.class.getSimpleName());
        }else{
            generateService.updateSymbolUsed(projectPlanDetails.getProjectId());
        }
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }
}
