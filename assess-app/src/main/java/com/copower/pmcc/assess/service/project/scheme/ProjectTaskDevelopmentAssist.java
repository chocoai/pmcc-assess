package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseVo;
import com.copower.pmcc.assess.dto.output.data.InfrastructureVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.PublicBasicService;
import com.copower.pmcc.assess.service.data.DataInfrastructureService;
import com.copower.pmcc.assess.service.method.MdArchitecturalObjService;
import com.copower.pmcc.assess.service.method.MdCalculatingMethodEngineeringCostService;
import com.copower.pmcc.assess.service.method.MdDevelopmentService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "假设开发法成果")
public class ProjectTaskDevelopmentAssist implements ProjectTaskInterface {
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SchemeInfoService schemeInfoService;
    @Autowired
    private MdDevelopmentService mdDevelopmentService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataInfrastructureService dataInfrastructureService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private MdArchitecturalObjService mdArchitecturalObjService;
    @Autowired
    private TaskExecutor executor;
    @Autowired
    private MdCalculatingMethodEngineeringCostService mdCalculatingMethodEngineeringCostService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private PublicBasicService publicBasicService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskDevelopmentIndex", "", 0, "0", "");
        //初始化支撑数据
        setViewParam(projectPlanDetails, modelAndView);
        setViewBaseParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskDevelopmentApproval", processInsId, boxId, taskId, agentUserAccount);
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskDevelopmentIndex", processInsId, boxId, taskId, agentUserAccount);
        setViewParam(projectPlanDetails, modelAndView);
        setViewBaseParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskDevelopmentApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        mdDevelopmentService.saveAndUpdateData(formData,projectPlanDetails);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        mdDevelopmentService.saveAndUpdateData(formData,projectPlanDetails);
    }


    /**
     * 给modelAndView设置显示参数
     *
     * @param modelAndView
     */
    private void setViewParam(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        SchemeInfo select = new SchemeInfo();
        select.setMethodType(baseDataDicService.getCacheDataDicByFieldName(AssessReportFieldConstant.DEVELOPMENT).getId());
        select.setProjectId(projectPlanDetails.getProjectId());
        select.setPlanDetailsId(projectPlanDetails.getId());
        List<SchemeInfo> schemeInfoList = schemeInfoService.getInfoList(select);
        MdDevelopment mdDevelopment = new MdDevelopment();
        if (CollectionUtils.isNotEmpty(schemeInfoList)) {
            SchemeInfo schemeInfo = schemeInfoList.stream().sorted((o1, o2) -> o1.getId().compareTo(o2.getId())).findFirst().get();
            if (schemeInfo.getMethodDataId() != null) {
                mdDevelopment = mdDevelopmentService.getMdDevelopmentById(schemeInfo.getMethodDataId());
            }
        }
        try {
            mdDevelopmentService.initData(mdDevelopment, projectPlanDetails, projectPlanDetails.getProcessInsId());
        } catch (BusinessException e) {
            baseService.writeExceptionInfo(e);
        }
        // StringUtils.uncapitalize 首字母小写
        modelAndView.addObject(StringUtils.uncapitalize(MdDevelopment.class.getSimpleName()),mdDevelopment);
        //projectPlanDetails
        modelAndView.addObject(StringUtils.uncapitalize(ProjectPlanDetails.class.getSimpleName()), projectPlanDetails);
    }

    private void setViewBaseParam(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        if (projectPlanDetails.getJudgeObjectId() == null) {
            return;
        }
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId());
        if (schemeJudgeObject == null) {
            return;
        }
        if (schemeJudgeObject.getAreaGroupId() != null) {
            SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.get(schemeJudgeObject.getAreaGroupId());
            if (schemeAreaGroup != null) {
                List<InfrastructureVo> dataInfrastructureList = dataInfrastructureService.calculatingMethod(schemeAreaGroup.getProvince(), schemeAreaGroup.getCity(), schemeAreaGroup.getDistrict());
                modelAndView.addObject("dataInfrastructureList", dataInfrastructureList);
                modelAndView.addObject(StringUtils.uncapitalize(SchemeAreaGroup.class.getSimpleName()), schemeAreaGroup);
            }
        }
        BigDecimal area = schemeJudgeObject.getFloorArea() != null ? schemeJudgeObject.getFloorArea() : schemeJudgeObject.getEvaluationArea();
        setMdCalculatingMethodEngineeringCost(projectPlanDetails, surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId()), area, modelAndView);
    }

    /**
     * 设置工程费
     * @param projectPlanDetails
     * @param basicApply
     * @param area
     * @param modelAndView
     */
    private void setMdCalculatingMethodEngineeringCost(ProjectPlanDetails projectPlanDetails, BasicApply basicApply, BigDecimal area, ModelAndView modelAndView) {
        if (basicApply == null) {
            return;
        }
        mdArchitecturalObjService.clear(projectPlanDetails.getId());
        mdCalculatingMethodEngineeringCostService.clear(projectPlanDetails);

        List<MdArchitecturalObj> mdArchitecturalObjList = Lists.newArrayList();
        MdArchitecturalObj select = new MdArchitecturalObj();
        select.setDatabaseName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
        select.setPid(basicApply.getBasicEstateId());
        List<MdArchitecturalObj> mdArchitecturalObjList2 = mdArchitecturalObjService.getMdArchitecturalObjListByExample(select);
        if (CollectionUtils.isNotEmpty(mdArchitecturalObjList2)) {
            mdArchitecturalObjList.addAll(mdArchitecturalObjList2);
        }
        select.setDatabaseName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
        select.setPid(basicApply.getBasicBuildingId());
        mdArchitecturalObjList2 = mdArchitecturalObjService.getMdArchitecturalObjListByExample(select);
        if (CollectionUtils.isNotEmpty(mdArchitecturalObjList2)) {
            mdArchitecturalObjList.addAll(mdArchitecturalObjList2);
        }
        if (CollectionUtils.isNotEmpty(mdArchitecturalObjList)) {
            for (MdArchitecturalObj oo : mdArchitecturalObjList) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {

                        MdCalculatingMethodEngineeringCost mdCalculatingMethodEngineeringCost = new MdCalculatingMethodEngineeringCost();
                        mdCalculatingMethodEngineeringCost.setCreator(processControllerComponent.getThisUser());
                        mdCalculatingMethodEngineeringCost.setArea(area);
                        mdCalculatingMethodEngineeringCost.setArchitecturalObjId(0);
                        mdCalculatingMethodEngineeringCost.setPlanDetailsId(projectPlanDetails.getId());
                        mdCalculatingMethodEngineeringCost.setProjectId(projectPlanDetails.getProjectId());
                        mdCalculatingMethodEngineeringCost.setPrice(new BigDecimal(0));
                        mdCalculatingMethodEngineeringCostService.saveMdCalculatingMethodEngineeringCost(mdCalculatingMethodEngineeringCost);


                        MdArchitecturalObj obj = mdArchitecturalObjService.getMdArchitecturalObjById(oo.getId());
                        oo.setId(null);
                        oo.setPlanDetailsId(projectPlanDetails.getId());
                        oo.setPid(mdCalculatingMethodEngineeringCost.getId());
                        oo.setJsonContent(obj.getJsonContent());
                        oo.setPrice(new BigDecimal(0));
                        oo.setCreator(processControllerComponent.getThisUser());
                        oo.setDatabaseName(FormatUtils.entityNameConvertToTableName(MdDevelopment.class));
                        mdArchitecturalObjService.saveMdArchitecturalObj(oo);

                        mdCalculatingMethodEngineeringCost.setArchitecturalObjId(oo.getId());
                        mdCalculatingMethodEngineeringCostService.saveMdCalculatingMethodEngineeringCost(mdCalculatingMethodEngineeringCost);
                    }
                });
            }
        }
        try {
            BasicHouseVo basicHouseVo = publicBasicService.getBasicHouseVoByAppId(basicApply);
            modelAndView.addObject(StringUtils.uncapitalize(BasicHouseVo.class.getSimpleName()), basicHouseVo);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            modelAndView.addObject(StringUtils.uncapitalize(BasicHouseVo.class.getSimpleName()), new BasicHouseVo());
        }
    }
}
