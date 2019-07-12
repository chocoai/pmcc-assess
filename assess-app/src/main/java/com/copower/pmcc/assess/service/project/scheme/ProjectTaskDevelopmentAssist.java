package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.scheme.MdDevelopmentVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataInfrastructureService;
import com.copower.pmcc.assess.service.method.MdArchitecturalObjService;
import com.copower.pmcc.assess.service.method.MdDevelopmentService;
import com.copower.pmcc.assess.service.project.declare.DeclareBuildEngineeringAndEquipmentCenterService;
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
    private MdArchitecturalObjService mdArchitecturalObjService;
    @Autowired
    private DeclareBuildEngineeringAndEquipmentCenterService declareBuildEngineeringAndEquipmentCenterService;
    @Autowired
    private DeclareRecordService declareRecordService;
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
        MdDevelopmentVo mdDevelopment = JSONObject.parseObject(formData, MdDevelopmentVo.class);
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
        if (CollectionUtils.isNotEmpty(schemeInfoList)){
            for (SchemeInfo oo :schemeInfoList){
                //处理评估方案中的各个评估方法
                oo.setProcessInsId(StringUtils.isNotEmpty(processInsId) ? processInsId : "0");
                oo.setMethodDataId(mdDevelopment.getId());
                schemeInfoService.saveSchemeInfo(oo);
            }
        }else {
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
    private void setViewParam(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        SchemeInfo select = new SchemeInfo();
        select.setMethodType(baseDataDicService.getCacheDataDicByFieldName(AssessReportFieldConstant.DEVELOPMENT).getId());
        select.setProjectId(projectPlanDetails.getProjectId());
        select.setPlanDetailsId(projectPlanDetails.getId());
        DeclareBuildEngineeringAndEquipmentCenter declareBuildEngineeringAndEquipmentCenter = new DeclareBuildEngineeringAndEquipmentCenter();
        if (projectPlanDetails.getJudgeObjectId() != null) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId());
            if (schemeJudgeObject != null) {
                Integer areaGroupId = schemeJudgeObject.getAreaGroupId();
                if (areaGroupId != null) {
                    SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.get(areaGroupId);
                    if (schemeAreaGroup != null) {
                        modelAndView.addObject("dataInfrastructureList", dataInfrastructureService.calculatingMethod(schemeAreaGroup.getProvince(), schemeAreaGroup.getCity(), schemeAreaGroup.getDistrict()));
                        modelAndView.addObject(StringUtils.uncapitalize(SchemeAreaGroup.class.getSimpleName()), schemeAreaGroup);
                    }
                }
                if (schemeJudgeObject.getDeclareRecordId() != null) {
                    DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                    if (declareRecord != null) {
                        modelAndView.addObject(StringUtils.uncapitalize(DeclareRecord.class.getSimpleName()), declareRecord);
                        DeclareBuildEngineeringAndEquipmentCenter query = new DeclareBuildEngineeringAndEquipmentCenter();
                        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class), declareRecord.getDataTableName())) {
                            query.setHouseId(declareRecord.getDataTableId());
                            query.setType(DeclareRealtyHouseCert.class.getSimpleName());
                            List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(query);
                            if (CollectionUtils.isNotEmpty(centerList)) {
                                declareBuildEngineeringAndEquipmentCenter = centerList.stream().findFirst().get();
                            }
                        }
                        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class), declareRecord.getDataTableName())) {
                            query.setRealEstateId(declareRecord.getDataTableId());
                            query.setType(DeclareRealtyRealEstateCert.class.getSimpleName());
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
        MdDevelopment mdDevelopment = null;
        if (CollectionUtils.isNotEmpty(schemeInfoList)) {
            SchemeInfo schemeInfo = schemeInfoList.stream().sorted((o1,o2) -> o1.getId().compareTo(o2.getId())).findFirst().get();
            if (schemeInfo.getMethodDataId() != null) {
                mdDevelopment = mdDevelopmentService.getMdDevelopmentById(schemeInfo.getMethodDataId());
            }
        }
        if (mdDevelopment != null) {
            // StringUtils.uncapitalize 首字母小写
            modelAndView.addObject(StringUtils.uncapitalize(MdDevelopment.class.getSimpleName()), mdDevelopmentService.getMdDevelopmentVo(mdDevelopment, true));
            modelAndView.addObject(String.format("%s2", StringUtils.uncapitalize(MdDevelopment.class.getSimpleName())), mdDevelopmentService.getMdDevelopmentVo(mdDevelopment, false));
            modelAndView.addObject(String.format("%s%s", StringUtils.uncapitalize(MdDevelopment.class.getSimpleName()), JSON_STRING), JSON.toJSONString(mdDevelopmentService.getMdDevelopmentVo(mdDevelopment, false)));
        }
        //projectPlanDetails
        modelAndView.addObject(StringUtils.uncapitalize(ProjectPlanDetails.class.getSimpleName()), projectPlanDetails);
        modelAndView.addObject(StringUtils.uncapitalize(DeclareBuildEngineeringAndEquipmentCenter.class.getSimpleName()), declareBuildEngineeringAndEquipmentCenter);
    }
}
