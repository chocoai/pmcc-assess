package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataInfrastructureService;
import com.copower.pmcc.assess.service.method.MdDevelopmentService;
import com.copower.pmcc.assess.service.project.declare.DeclareBuildEngineeringAndEquipmentCenterService;
import com.copower.pmcc.assess.service.project.declare.DeclareEconomicIndicatorsContentService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private DeclareEconomicIndicatorsContentService declareEconomicIndicatorsContentService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private DeclareBuildEngineeringAndEquipmentCenterService declareBuildEngineeringAndEquipmentCenterService;
    @Autowired
    private DataInfrastructureService dataInfrastructureService;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    final String JSON_STRING = "Json";

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskDevelopmentIndex", "", 0, "0", "");
        //初始化支撑数据
        setViewParam(projectPlanDetails, modelAndView);
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
        JSONObject jsonObject = JSON.parseObject(formData);
        MdDevelopment mdDevelopment = new MdDevelopment();
        MdDevelopmentLand mdDevelopmentLand = null;
        MdDevelopmentEngineering mdDevelopmentEngineering = null;
        String key = jsonObject.getString("key");
        //土地
        if (Objects.equal("1", key)) {
            mdDevelopmentLand = JSONObject.parseObject(formData, MdDevelopmentLand.class);
        }
        //在建工程
        if (Objects.equal("2", key)) {
            mdDevelopmentEngineering = JSONObject.parseObject(formData, MdDevelopmentEngineering.class);
        }
        if (mdDevelopmentLand == null && mdDevelopmentEngineering == null) {
            return;
        }
        //检查修改状态
        SchemeInfo schemeInfo = new SchemeInfo();
        schemeInfo.setProjectId(projectPlanDetails.getProjectId());
        schemeInfo.setPlanDetailsId(projectPlanDetails.getId());
        schemeInfo.setJudgeObjectId(projectPlanDetails.getJudgeObjectId());
        schemeInfo.setProcessInsId(StringUtils.isNotEmpty(processInsId) ? processInsId : "0");
        schemeInfo.setMethodType(baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_DEVELOPMENT).getId());
        List<SchemeInfo> schemeInfoList = schemeInfoService.getInfoList(schemeInfo);
        if (CollectionUtils.isNotEmpty(schemeInfoList)) {
            //属于修改
            schemeInfo = schemeInfoList.stream().findFirst().get();
            mdDevelopment = mdDevelopmentService.getMdDevelopmentById(schemeInfo.getMethodDataId());
        }
        if (mdDevelopmentLand != null) {
            mdDevelopment.setPrice(new BigDecimal(mdDevelopmentLand.getEstimateunitpricelandc33()));
            mdDevelopment.setType("1");
            //clear
            if (mdDevelopment.getId() != null) {
                MdDevelopmentEngineering query = new MdDevelopmentEngineering();
                query.setPid(mdDevelopment.getId());
                List<MdDevelopmentEngineering> list = mdDevelopmentService.getMdDevelopmentEngineeringList(query);
                if (CollectionUtils.isNotEmpty(list)) {
                    for (MdDevelopmentEngineering po : list) {
                        mdDevelopmentService.deleteMdDevelopmentEngineering(po.getId(), projectPlanDetails.getId());
                    }
                }
            }
        }
        if (mdDevelopmentEngineering != null) {
            mdDevelopment.setPrice(new BigDecimal(mdDevelopmentEngineering.getEstimateunitpricelandc33()));
            mdDevelopment.setType("2");
            //clear
            if (mdDevelopment.getId() != null) {
                MdDevelopmentLand query = new MdDevelopmentLand();
                query.setPid(mdDevelopment.getId());
                List<MdDevelopmentLand> list = mdDevelopmentService.getMdDevelopmentLandList(query);
                if (CollectionUtils.isNotEmpty(list)) {
                    for (MdDevelopmentLand po : list) {
                        mdDevelopmentService.deleteMdDevelopmentLand(po.getId(), projectPlanDetails.getId());
                    }
                }
            }
        }
        if (projectPlanDetails.getJudgeObjectId() != null) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId());
            if (schemeJudgeObject != null) {
                mdDevelopment.setName(schemeJudgeObject.getName());
            }
        }
        mdDevelopmentService.saveAndUpdateMdDevelopment(mdDevelopment);
        if (mdDevelopmentLand != null) {
            mdDevelopmentLand.setPid(mdDevelopment.getId());
            mdDevelopmentService.saveAndUpdateMdDevelopmentLand(mdDevelopmentLand);
        }
        if (mdDevelopmentEngineering != null) {
            mdDevelopmentEngineering.setPid(mdDevelopment.getId());
            mdDevelopmentService.saveAndUpdateMdDevelopmentEngineering(mdDevelopmentEngineering);
        }
        DeclareEconomicIndicatorsContent select = new DeclareEconomicIndicatorsContent();
        select.setPlanDetailsId(projectPlanDetails.getId());
        select.setIndicatorsHeadId(0);
        List<DeclareEconomicIndicatorsContent> contentList = declareEconomicIndicatorsContentService.getDeclareEconomicIndicatorsContentList(select);
        if (CollectionUtils.isNotEmpty(contentList)) {
            for (DeclareEconomicIndicatorsContent oo : contentList) {
                oo.setIndicatorsHeadId(mdDevelopmentLand != null ? mdDevelopmentLand.getId() : mdDevelopmentEngineering.getId());
                declareEconomicIndicatorsContentService.updateDeclareEconomicIndicatorsContent(oo);
            }
        }
        //处理评估方案中的各个评估方法
        schemeInfo.setMethodDataId(mdDevelopment.getId());
        schemeInfoService.saveSchemeInfo(schemeInfo);
    }

    /**
     * 给modelAndView设置显示参数
     *
     * @param modelAndView
     */
    private void setViewParam(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        Integer judgeObjectId = projectPlanDetails.getJudgeObjectId();
        SchemeInfo select = new SchemeInfo();
        select.setMethodType(baseDataDicService.getCacheDataDicByFieldName(AssessReportFieldConstant.DEVELOPMENT).getId());
        select.setPlanDetailsId(projectPlanDetails.getId());
        if (judgeObjectId != null) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId());
            if (schemeJudgeObject != null) {
                Integer areaGroupId = schemeJudgeObject.getAreaGroupId();
                if (areaGroupId != null) {
                    SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.get(areaGroupId);
                    if (schemeAreaGroup != null){
                        modelAndView.addObject(StringUtils.uncapitalize(SchemeAreaGroup.class.getSimpleName()), schemeAreaGroup);
                        modelAndView.addObject("dataInfrastructureList", dataInfrastructureService.calculatingMethod(schemeAreaGroup.getProvince(),schemeAreaGroup.getCity(),schemeAreaGroup.getDistrict()));
                    }
                }
                if (schemeJudgeObject.getDeclareRecordId() != null) {
                    DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                    if (declareRecord != null) {
                        DeclareBuildEngineeringAndEquipmentCenter query = new DeclareBuildEngineeringAndEquipmentCenter();
                        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class), declareRecord.getDataTableName())) {
                            query.setHouseId(declareRecord.getDataTableId());
                            List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(query);
                            if (CollectionUtils.isNotEmpty(centerList)) {
                                if (centerList.stream().anyMatch(oo -> oo.getIndicatorId() != null)) {
                                    DeclareBuildEngineeringAndEquipmentCenter center = centerList.stream().filter(oo -> oo.getIndicatorId() != null).findFirst().get();
                                    modelAndView.addObject(StringUtils.uncapitalize(DeclareBuildEngineeringAndEquipmentCenter.class.getSimpleName()), center);
                                }
                            }
                        }
                    }
                }
            }
            select.setJudgeObjectId(judgeObjectId);
        }
        List<SchemeInfo> schemeInfoList = schemeInfoService.getInfoList(select);
        MdDevelopment mdDevelopment = null;
        if (CollectionUtils.isNotEmpty(schemeInfoList)) {
            SchemeInfo schemeInfo = schemeInfoList.stream().findFirst().get();
            if (schemeInfo.getMethodDataId() == null) {
                mdDevelopment = mdDevelopmentService.getMdDevelopmentById(schemeInfo.getMethodDataId());
            }
        }
        MdDevelopmentLand mdDevelopmentLand = null;
        MdDevelopmentEngineering mdDevelopmentEngineering = null;
        if (mdDevelopment != null) {
            MdDevelopmentLand selectLand = new MdDevelopmentLand();
            MdDevelopmentEngineering selectEngineering = new MdDevelopmentEngineering();
            selectLand.setPid(mdDevelopment.getId());
            selectEngineering.setPid(mdDevelopment.getId());
            List<MdDevelopmentLand> mdDevelopmentLandList = mdDevelopmentService.getMdDevelopmentLandList(selectLand);
            List<MdDevelopmentEngineering> mdDevelopmentEngineeringList = mdDevelopmentService.getMdDevelopmentEngineeringList(selectEngineering);
            if (CollectionUtils.isNotEmpty(mdDevelopmentLandList)) {
                mdDevelopmentLand = mdDevelopmentLandList.stream().findFirst().get();
            }
            if (CollectionUtils.isNotEmpty(mdDevelopmentEngineeringList)) {
                mdDevelopmentEngineering = mdDevelopmentEngineeringList.stream().findFirst().get();
            }
        }
        if (mdDevelopment != null) {
            // StringUtils.uncapitalize 首字母小写
            modelAndView.addObject(StringUtils.uncapitalize(MdDevelopment.class.getSimpleName()), mdDevelopment);
            modelAndView.addObject(String.format("%s%s", StringUtils.uncapitalize(MdDevelopment.class.getSimpleName()), JSON_STRING), JSON.toJSONString(mdDevelopment));
        }
        if (mdDevelopmentLand != null) {
            modelAndView.addObject(StringUtils.uncapitalize(MdDevelopmentLand.class.getSimpleName()), mdDevelopmentLand);
            //mdDevelopmentLandJSON 类似这样 StringUtils.uncapitalize 首字母小写
            modelAndView.addObject(String.format("%s%s", StringUtils.uncapitalize(MdDevelopmentLand.class.getSimpleName()), JSON_STRING), JSON.toJSONString(mdDevelopmentLand));
        }
        if (mdDevelopmentEngineering != null) {
            modelAndView.addObject(StringUtils.uncapitalize(MdDevelopmentEngineering.class.getSimpleName()), mdDevelopmentEngineering);
            modelAndView.addObject(String.format("%s%s", StringUtils.uncapitalize(MdDevelopmentEngineering.class.getSimpleName()), JSON_STRING), JSON.toJSONString(mdDevelopmentEngineering));
        }
        modelAndView.addObject(StringUtils.uncapitalize(ProjectPlanDetails.class.getSimpleName()), projectPlanDetails);
    }
}
