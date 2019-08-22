package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.data.InfrastructureVo;
import com.copower.pmcc.assess.dto.output.project.scheme.MdDevelopmentVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataInfrastructureService;
import com.copower.pmcc.assess.service.method.MdArchitecturalObjService;
import com.copower.pmcc.assess.service.method.MdDevelopmentIncomeCategoryService;
import com.copower.pmcc.assess.service.method.MdDevelopmentService;
import com.copower.pmcc.assess.service.project.declare.DeclareBuildEngineeringAndEquipmentCenterService;
import com.copower.pmcc.assess.service.project.declare.DeclareEconomicIndicatorsContentService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    private DeclareBuildEngineeringAndEquipmentCenterService declareBuildEngineeringAndEquipmentCenterService;
    @Autowired
    private DeclareEconomicIndicatorsContentService declareEconomicIndicatorsContentService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private MdDevelopmentIncomeCategoryService mdDevelopmentIncomeCategoryService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private MdArchitecturalObjService mdArchitecturalObjService;
    @Autowired
    private TaskExecutor executor;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskDevelopmentIndex", "", 0, "0", "");
        //初始化支撑数据
        setViewParam(projectPlanDetails, modelAndView, true);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskDevelopmentApproval", processInsId, boxId, taskId, agentUserAccount);
        setViewParam(projectPlanDetails, modelAndView, false);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskDevelopmentIndex", processInsId, boxId, taskId, agentUserAccount);
        setViewParam(projectPlanDetails, modelAndView, false);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskDevelopmentApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        setViewParam(projectPlanDetails, modelAndView, false);
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        save(projectPlanDetails, processInsId, formData);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        save(projectPlanDetails, processInsId, formData);
    }

    /**
     * 保存或者修改
     *
     * @param projectPlanDetails
     * @param processInsId
     * @param formData
     * @throws BusinessException
     */
    private void save(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        MdDevelopment mdDevelopment = JSONObject.parseObject(formData, MdDevelopment.class);
        SchemeInfo schemeInfo = new SchemeInfo();
        schemeInfo.setProjectId(projectPlanDetails.getProjectId());
        schemeInfo.setPlanDetailsId(projectPlanDetails.getId());
        schemeInfo.setJudgeObjectId(projectPlanDetails.getJudgeObjectId());
        schemeInfo.setMethodType(baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_DEVELOPMENT).getId());
        List<SchemeInfo> schemeInfoList = schemeInfoService.getInfoList(schemeInfo);
        mdDevelopment.setContent(formData);
        if (projectPlanDetails.getJudgeObjectId() != null) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId());
            if (schemeJudgeObject != null) {
                mdDevelopment.setName(schemeJudgeObject.getName());
            }
        }
        mdDevelopmentService.saveAndUpdateMdDevelopment(mdDevelopment);
        if (CollectionUtils.isNotEmpty(schemeInfoList)) {
            for (SchemeInfo oo : schemeInfoList) {
                //处理评估方案中的各个评估方法
                oo.setProcessInsId(StringUtils.isNotEmpty(processInsId) ? processInsId : "0");
                oo.setMethodDataId(mdDevelopment.getId());
                schemeInfoService.saveSchemeInfo(oo);
            }
        } else {
            //处理评估方案中的各个评估方法
            schemeInfo.setProcessInsId(StringUtils.isNotEmpty(processInsId) ? processInsId : "0");
            schemeInfo.setMethodDataId(mdDevelopment.getId());
            schemeInfoService.saveSchemeInfo(schemeInfo);
        }
    }

    /**
     * 给modelAndView设置显示参数
     *
     * @param modelAndView
     */
    private void setViewParam(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView, boolean init) {
        SchemeInfo select = new SchemeInfo();
        select.setMethodType(baseDataDicService.getCacheDataDicByFieldName(AssessReportFieldConstant.DEVELOPMENT).getId());
        select.setProjectId(projectPlanDetails.getProjectId());
        select.setPlanDetailsId(projectPlanDetails.getId());
        List<SchemeInfo> schemeInfoList = schemeInfoService.getInfoList(select);
        MdDevelopment mdDevelopment = null;
        if (CollectionUtils.isNotEmpty(schemeInfoList)) {
            SchemeInfo schemeInfo = schemeInfoList.stream().sorted((o1, o2) -> o1.getId().compareTo(o2.getId())).findFirst().get();
            if (schemeInfo.getMethodDataId() != null) {
                mdDevelopment = mdDevelopmentService.getMdDevelopmentById(schemeInfo.getMethodDataId());
            }
        }
        if (mdDevelopment != null) {
            // StringUtils.uncapitalize 首字母小写
            modelAndView.addObject(StringUtils.uncapitalize(MdDevelopment.class.getSimpleName()), mdDevelopmentService.getMdDevelopmentVo(mdDevelopment, true));
        }
        //projectPlanDetails
        modelAndView.addObject(StringUtils.uncapitalize(ProjectPlanDetails.class.getSimpleName()), projectPlanDetails);
        setViewBaseParam(projectPlanDetails, modelAndView, init);
    }

    private void setViewBaseParam(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView, boolean init) {
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
        if (schemeJudgeObject.getDeclareRecordId() == null) {
            return;
        }
        setIndicatorsContentParam(schemeJudgeObject, projectPlanDetails, init);
    }

    private void setIndicatorsContentParam(SchemeJudgeObject schemeJudgeObject, ProjectPlanDetails projectPlanDetails, boolean init) {
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
        List<DeclareEconomicIndicatorsContent> indicatorsContentList = Lists.newArrayList();
        List<Integer> integerList = Lists.newArrayList();
        DeclareBuildEngineeringAndEquipmentCenter query = new DeclareBuildEngineeringAndEquipmentCenter();
        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class), declareRecord.getDataTableName())) {
            query.setHouseId(declareRecord.getDataTableId());
            query.setType(DeclareRealtyHouseCert.class.getSimpleName());
            List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(query);
            if (CollectionUtils.isNotEmpty(centerList)) {
                List<Integer> integerList2 = centerList.stream().filter(oo -> oo.getIndicatorId() != null).map(oo -> oo.getIndicatorId()).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(integerList2)) {
                    integerList.addAll(integerList2);
                }
            }
        }
        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class), declareRecord.getDataTableName())) {
            query.setRealEstateId(declareRecord.getDataTableId());
            query.setType(DeclareRealtyRealEstateCert.class.getSimpleName());
            List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(query);
            if (CollectionUtils.isNotEmpty(centerList)) {
                List<Integer> integerList2 = centerList.stream().filter(oo -> oo.getIndicatorId() != null).map(oo -> oo.getIndicatorId()).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(integerList2)) {
                    integerList.addAll(integerList2);
                }
            }
        }
        if (CollectionUtils.isNotEmpty(integerList)) {
            for (Integer id : integerList) {
                List<DeclareEconomicIndicatorsContent> list = declareEconomicIndicatorsContentService.getDeclareEconomicIndicatorsContentListByHeadId(id);
                if (CollectionUtils.isEmpty(list)) {
                    continue;
                }
                indicatorsContentList.addAll(list);
            }
        }
        if (init) {
            initPortOtherInfo(indicatorsContentList,projectPlanDetails,surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId()));
        }
    }

    private void initPortOtherInfo(List<DeclareEconomicIndicatorsContent> indicatorsContentList,ProjectPlanDetails projectPlanDetails,BasicApply basicApply){
        if (CollectionUtils.isNotEmpty(indicatorsContentList)) {
            mdDevelopmentIncomeCategoryService.clear();
            for (DeclareEconomicIndicatorsContent obj : indicatorsContentList) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        MdDevelopmentIncomeCategory engineering = new MdDevelopmentIncomeCategory();
                        engineering.setPid(0);
                        if (NumberUtils.isNumber(obj.getPlanIndex())) {
                            engineering.setPlannedBuildingArea(new BigDecimal(obj.getPlanIndex()));
                        }
                        if (NumberUtils.isNumber(obj.getSalabilityNumber())) {
                            engineering.setSaleableArea(new BigDecimal(obj.getSalabilityNumber()));
                        }
                        if (NumberUtils.isNumber(obj.getAssessSalabilityNumber())) {
                            engineering.setAssessArea(new BigDecimal(obj.getAssessSalabilityNumber()));
                        }
                        engineering.setCreator(processControllerComponent.getThisUser());
                        engineering.setName(obj.getName());
                        engineering.setPlanDetailsId(projectPlanDetails.getId());
                        MdDevelopmentIncomeCategory land = new MdDevelopmentIncomeCategory();
                        BeanUtils.copyProperties(engineering,land);
                        land.setType("land");
                        engineering.setType("engineering");
                        mdDevelopmentIncomeCategoryService.saveMdDevelopmentIncomeCategory(engineering);
                        mdDevelopmentIncomeCategoryService.saveMdDevelopmentIncomeCategory(land);
                    }
                });
            }
        }
        if (basicApply != null){
            mdArchitecturalObjService.clear();
            List<MdArchitecturalObj> mdArchitecturalObjList = Lists.newArrayList();
            MdArchitecturalObj select = new MdArchitecturalObj();
            select.setDatabaseName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
            select.setPid(basicApply.getBasicEstateId());
            List<MdArchitecturalObj> mdArchitecturalObjList2 = mdArchitecturalObjService.getMdArchitecturalObjListByExample(select) ;
            if (CollectionUtils.isNotEmpty(mdArchitecturalObjList2)){
                mdArchitecturalObjList.addAll(mdArchitecturalObjList2) ;
            }
            select.setDatabaseName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
            select.setPid(basicApply.getBasicBuildingId());
            mdArchitecturalObjList2 = mdArchitecturalObjService.getMdArchitecturalObjListByExample(select) ;
            if (CollectionUtils.isNotEmpty(mdArchitecturalObjList2)){
                mdArchitecturalObjList.addAll(mdArchitecturalObjList2) ;
            }
            if (CollectionUtils.isNotEmpty(mdArchitecturalObjList)){
                for (MdArchitecturalObj oo:mdArchitecturalObjList){
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            MdArchitecturalObj obj = mdArchitecturalObjService.getMdArchitecturalObjById(oo.getId()) ;
                            oo.setId(null);
                            oo.setPlanDetailsId(projectPlanDetails.getPid());
                            oo.setPid(0);
                            oo.setJsonContent(obj.getJsonContent());
                            oo.setPrice(new BigDecimal(0));
                            oo.setCreator(processControllerComponent.getThisUser());
                            oo.setType("engineering");
                            oo.setDatabaseName(FormatUtils.entityNameConvertToTableName(ProjectPlanDetails.class));
                            mdArchitecturalObjService.saveMdArchitecturalObj(oo);
                        }
                    });
                }
            }
        }
    }
}
