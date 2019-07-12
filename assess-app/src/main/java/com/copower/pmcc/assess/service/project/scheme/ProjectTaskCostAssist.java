package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataInfrastructureService;
import com.copower.pmcc.assess.service.method.MdMarketCostService;
import com.copower.pmcc.assess.service.project.declare.DeclareBuildEngineeringAndEquipmentCenterService;
import com.copower.pmcc.assess.service.project.declare.DeclareEconomicIndicatorsHeadService;
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

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "成本法成果")
public class ProjectTaskCostAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SchemeInfoService schemeInfoService;
    @Autowired
    private MdMarketCostService mdMarketCostService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataInfrastructureService dataInfrastructureService;
    @Autowired
    private DeclareBuildEngineeringAndEquipmentCenterService declareBuildEngineeringAndEquipmentCenterService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private DeclareEconomicIndicatorsHeadService declareEconomicIndicatorsHeadService;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    final String JSON_STRING = "Json";

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskCostIndex", "", 0, "0", "");
        //初始化支撑数据
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    /**
     * 详情处理
     *
     * @param processInsId
     * @param taskId
     * @param boxId
     * @param projectPlanDetails
     * @param agentUserAccount
     * @return
     */
    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskCostApproval", processInsId, boxId, taskId, agentUserAccount);
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    /**
     * 返回修改处理
     *
     * @param processInsId
     * @param taskId
     * @param boxId
     * @param projectPlanDetails
     * @param agentUserAccount
     * @return
     */
    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskCostIndex", processInsId, boxId, taskId, agentUserAccount);
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskCostApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        this.saveAndUpdate(projectPlanDetails, processInsId, formData);
    }

    private void saveAndUpdate(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException{
        SchemeInfo schemeInfo = new SchemeInfo();
        schemeInfo.setProjectId(projectPlanDetails.getProjectId());
        schemeInfo.setPlanDetailsId(projectPlanDetails.getId());
        schemeInfo.setJudgeObjectId(projectPlanDetails.getJudgeObjectId());
        schemeInfo.setMethodType(baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_COST).getId());
        List<SchemeInfo> schemeInfoList = schemeInfoService.getInfoList(schemeInfo) ;
        JSONObject jsonObject = JSON.parseObject(formData);
        String type = (String) jsonObject.get("type");
        MdCost mdCost = new MdCost();
        MdCostConstruction mdCostConstruction = null;
        MdCostBuilding mdCostBuilding = null;
        if (Objects.equal("2", type)) {
            mdCostConstruction = JSONObject.parseObject(formData, MdCostConstruction.class);
            if (mdCostConstruction != null) {
                if (mdCostConstruction.getPid() != null) {
                    mdCost.setId(mdCostConstruction.getPid());
                }
            }
        }
        if (Objects.equal("1", type)) {
            mdCostBuilding = JSONObject.parseObject(formData, MdCostBuilding.class);
            if (mdCostBuilding != null) {
                if (mdCostBuilding.getPid() != null) {
                    mdCost.setId(mdCostBuilding.getPid());
                }
            }
        }
        if (mdCost != null) {
            mdCost.setType(type);
            mdMarketCostService.saveAndUpdateMdCost(mdCost);
        }
        if (mdCostBuilding != null){
            mdCostBuilding.setJsonContent(formData);
            mdMarketCostService.saveAndUpdateMdCostBuilding(mdCostBuilding,mdCost) ;
            mdCost.setPrice(mdCostBuilding.getAssessPrice());
        }
        if (mdCostConstruction != null){
            mdCostConstruction.setJsonContent(formData);
            mdMarketCostService.saveAndUpdateMdCostConstruction(mdCostConstruction,mdCost);
            mdCost.setPrice(mdCostConstruction.getConstructionAssessmentPriceCorrecting());
        }

        if (mdCost != null) {
            mdMarketCostService.saveAndUpdateMdCost(mdCost);
        }

        if (CollectionUtils.isNotEmpty(schemeInfoList)){
            for (SchemeInfo oo:schemeInfoList){
                oo.setMethodDataId(mdCost.getId());
                oo.setProcessInsId(StringUtils.isNotEmpty(processInsId)?processInsId:"0");
                schemeInfoService.saveSchemeInfo(oo);
            }
        }else {
            schemeInfo.setProcessInsId(StringUtils.isNotEmpty(processInsId)?processInsId:"0");
            schemeInfo.setMethodDataId(mdCost.getId());
            schemeInfoService.saveSchemeInfo(schemeInfo);
        }
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        this.saveAndUpdate(projectPlanDetails, processInsId, formData);
    }

    /**
     * 给modelview设置显示参数
     *
     * @param modelAndView
     */
    private void setViewParam(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        MdCost mdCost = new MdCost();
        MdCostConstruction mdCostConstruction = new MdCostConstruction();
        MdCostBuilding mdCostBuilding = new MdCostBuilding();
        DeclareBuildEngineeringAndEquipmentCenter declareBuildEngineeringAndEquipmentCenter = new DeclareBuildEngineeringAndEquipmentCenter();
        DeclareEconomicIndicatorsHead declareEconomicIndicatorsHead = new DeclareEconomicIndicatorsHead();
        SchemeInfo select = new SchemeInfo();
        select.setMethodType(baseDataDicService.getCacheDataDicByFieldName(AssessReportFieldConstant.COST).getId());
        select.setPlanDetailsId(projectPlanDetails.getId());
        if (projectPlanDetails.getJudgeObjectId() != null) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId());
            if (schemeJudgeObject != null) {
                Integer areaGroupId = schemeJudgeObject.getAreaGroupId();
                if (areaGroupId != null) {
                    SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.get(areaGroupId);
                    if (schemeAreaGroup != null) {
                        modelAndView.addObject(StringUtils.uncapitalize(SchemeAreaGroup.class.getSimpleName()), schemeAreaGroup);
                        modelAndView.addObject("dataInfrastructureList", dataInfrastructureService.calculatingMethod(schemeAreaGroup.getProvince(), schemeAreaGroup.getCity(), schemeAreaGroup.getDistrict()));
                    }
                }
                if (schemeJudgeObject.getDeclareRecordId() != null) {
                    DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                    if (declareRecord != null) {
                        modelAndView.addObject(StringUtils.uncapitalize(DeclareRecord.class.getSimpleName()), declareRecord);
                        DeclareBuildEngineeringAndEquipmentCenter query = new DeclareBuildEngineeringAndEquipmentCenter();
                        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class), declareRecord.getDataTableName())) {
                            query.setType(DeclareRealtyHouseCert.class.getSimpleName());
                            query.setHouseId(declareRecord.getDataTableId());
                            List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(query);
                            if (CollectionUtils.isNotEmpty(centerList)) {
                                declareBuildEngineeringAndEquipmentCenter = centerList.stream().findFirst().get();
                            }
                        }
                        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class), declareRecord.getDataTableName())) {
                            query.setType(DeclareRealtyRealEstateCert.class.getSimpleName());
                            query.setRealEstateId(declareRecord.getDataTableId());
                            List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(query);
                            if (CollectionUtils.isNotEmpty(centerList)) {
                                declareBuildEngineeringAndEquipmentCenter = centerList.stream().findFirst().get();
                            }
                        }
                    }
                }
            }
        }
        List<SchemeInfo> schemeInfoList = schemeInfoService.getInfoList(select);
        if (CollectionUtils.isNotEmpty(schemeInfoList)) {
            if (schemeInfoList.stream().anyMatch(oo -> oo.getMethodDataId() != null)) {
                SchemeInfo schemeInfo = schemeInfoList.stream().filter(oo -> oo.getMethodDataId() != null).findFirst().get();
                mdCost = mdMarketCostService.getByMdCostId(schemeInfo.getMethodDataId());
            }
        }
        if (mdCost != null) {
            if (Objects.equal("2", mdCost.getType())) {
                MdCostConstruction query = new MdCostConstruction();
                query.setPid(mdCost.getId());
                List<MdCostConstruction> list = mdMarketCostService.getMdCostConstructionList(query);
                if (CollectionUtils.isNotEmpty(list)) {
                    mdCostConstruction = list.stream().findFirst().get();
                }
            }
            if (Objects.equal("1", mdCost.getType())) {
                MdCostBuilding query = new MdCostBuilding();
                query.setPid(mdCost.getId());
                List<MdCostBuilding> list = mdMarketCostService.getMdCostBuildingList(query);
                if (CollectionUtils.isNotEmpty(list)) {
                    mdCostBuilding = list.stream().findFirst().get();
                }
            }
        }
        if (mdCostConstruction != null) {
            modelAndView.addObject(StringUtils.uncapitalize(MdCostConstruction.class.getSimpleName()), mdCostConstruction);
        }
        if (mdCostBuilding != null) {
            modelAndView.addObject(StringUtils.uncapitalize(MdCostBuilding.class.getSimpleName()), mdCostBuilding);
        }
        if (mdCost != null) {
            modelAndView.addObject(StringUtils.uncapitalize(MdCost.class.getSimpleName()), mdCost);
        }
        if (declareBuildEngineeringAndEquipmentCenter != null) {
            modelAndView.addObject(StringUtils.uncapitalize(DeclareBuildEngineeringAndEquipmentCenter.class.getSimpleName()), declareBuildEngineeringAndEquipmentCenter);
        }
        if (declareBuildEngineeringAndEquipmentCenter != null && declareBuildEngineeringAndEquipmentCenter.getIndicatorId() != null) {
            declareEconomicIndicatorsHead = declareEconomicIndicatorsHeadService.getByDeclareEconomicIndicatorsHeadId(declareBuildEngineeringAndEquipmentCenter.getIndicatorId());
        }
        if (declareEconomicIndicatorsHead != null) {
            modelAndView.addObject(StringUtils.uncapitalize(DeclareEconomicIndicatorsHead.class.getSimpleName()), declareEconomicIndicatorsHead);
        }
        //projectPlanDetails
        modelAndView.addObject(StringUtils.uncapitalize(ProjectPlanDetails.class.getSimpleName()), projectPlanDetails);
    }

}
