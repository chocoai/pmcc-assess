package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.method.MdCostAndDevelopmentOtherService;
import com.copower.pmcc.assess.service.method.MdDevelopmentService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.declare.DeclareBuildEngineeringAndEquipmentCenterService;
import com.copower.pmcc.assess.service.project.declare.DeclareBuildEngineeringService;
import com.copower.pmcc.assess.service.project.declare.DeclareBuildEquipmentInstallService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "假设开发法成果")
public class ProjectTaskDevelopmentAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SchemeInfoService schemeInfoService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private SchemeSupportInfoService schemeSupportInfoService;
    @Autowired
    private MdCostAndDevelopmentOtherService mdCostAndDevelopmentOtherService;
    @Autowired
    private MdDevelopmentService mdDevelopmentService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private DeclareBuildEngineeringService declareBuildEngineeringService;
    @Autowired
    private DeclareBuildEquipmentInstallService declareBuildEquipmentInstallService;
    @Autowired
    private DeclareBuildEngineeringAndEquipmentCenterService declareBuildEngineeringAndEquipmentCenterService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskDevelopmentIndex", "", 0, "0", "");
        //初始化支撑数据
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        setViewParam(projectPlanDetails, modelAndView);
        mdCostAndDevelopmentOtherService.removePid();
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
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        setViewParam(projectPlanDetails, modelAndView);
        mdCostAndDevelopmentOtherService.removePid();
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskDevelopmentApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        MdDevelopment mdDevelopment = new MdDevelopment();
        MdCostAndDevelopmentOther mdCostAndDevelopmentOther = null;
        Integer id = 0;
        String keyMdDevelopment = null;
        JSONObject jsonObject = JSON.parseObject(formData);
        List<SchemeSupportInfo> supportInfoList = null;
        MdDevelopmentHypothesis mdDevelopmentHypothesis = null;
        MdDevelopmentArchitectural mdDevelopmentArchitectural = null;
        String jsonContent = null;
        id = mdDevelopmentService.saveAndUpdateMdDevelopment(mdDevelopment);

        //解析实体 ,并且对json 进行一些处理
        try {
            jsonContent = jsonObject.getString("supportInfoList");
            if (!StringUtils.isEmpty(jsonContent)) {
                supportInfoList = JSONObject.parseArray(jsonContent, SchemeSupportInfo.class);
            }
            jsonContent = jsonObject.getString("mdDevelopmentHypothesis");
            if (!StringUtils.isEmpty(jsonContent)) {
                mdDevelopmentHypothesis = JSONObject.parseObject(jsonContent, MdDevelopmentHypothesis.class);
                mdDevelopmentHypothesis.setJsonContent(JSON.toJSONString(jsonContent));
            }
            jsonContent = jsonObject.getString("mdDevelopmentArchitectural");
            if (!StringUtils.isEmpty(jsonContent)) {
                mdDevelopmentArchitectural = JSONObject.parseObject(jsonContent, MdDevelopmentArchitectural.class);
                mdDevelopmentArchitectural.setJsonContent(JSON.toJSONString(jsonContent));
            }
            keyMdDevelopment = jsonObject.getString("mdDevelopment");
        } catch (Exception e1) {
            //不需要抛出异常
            logger.error(String.format("实体解析失败! ==> %s", e1.getMessage()));
        }

        //处理评估方法
        if (!ObjectUtils.isEmpty(supportInfoList)) {
            for (SchemeSupportInfo schemeSupportInfo : supportInfoList) {
                schemeSupportInfoService.saveSupportInfo(schemeSupportInfo);
            }
        }

        //处理假设开发法 在建工程
        if (!ObjectUtils.isEmpty(mdDevelopmentHypothesis)) {
            mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getByPidMdCostAndDevelopmentOther(MdDevelopmentHypothesis.class.getSimpleName(), 0,FormatUtils.entityNameConvertToTableName(MdDevelopmentHypothesis.class));
            //存入上级主表id
            mdDevelopmentHypothesis.setPid(id);
            Integer pid = mdDevelopmentService.saveAndUpdateMdDevelopmentHypothesis(mdDevelopmentHypothesis);
            //处理从表
            if (mdCostAndDevelopmentOther != null) {
                mdCostAndDevelopmentOther.setPid(pid);
                mdCostAndDevelopmentOtherService.updateMdCostAndDevelopmentOther(mdCostAndDevelopmentOther);
            }
        }

        //处理假设开发法 土地
        if (!ObjectUtils.isEmpty(mdDevelopmentArchitectural)) {
            mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getByPidMdCostAndDevelopmentOther(MdDevelopmentArchitectural.class.getSimpleName(), 0,FormatUtils.entityNameConvertToTableName(MdDevelopmentArchitectural.class));
            //存入上级主表id
            mdDevelopmentArchitectural.setPid(id);
            Integer pid = mdDevelopmentService.saveAndUpdateMdDevelopmentArchitectural(mdDevelopmentArchitectural);
            //处理从表
            if (mdCostAndDevelopmentOther != null) {
                mdCostAndDevelopmentOther.setPid(pid);
                mdCostAndDevelopmentOtherService.updateMdCostAndDevelopmentOther(mdCostAndDevelopmentOther);
            }
        }

        //确定假设开发法具体选择的哪一个方法来测算的
        if (org.apache.commons.lang.StringUtils.isNotBlank(keyMdDevelopment)) {
            if (Objects.equal("MdDevelopmentArchitectural", keyMdDevelopment)) {
                if (mdDevelopmentArchitectural != null) {
                    mdDevelopment.setId(id);
                    mdDevelopment.setPrice(new BigDecimal(mdDevelopmentArchitectural.getEstimateunitpricelandc33()));
                    mdDevelopment.setType(FormatUtils.entityNameConvertToTableName(MdDevelopmentArchitectural.class));
                    mdDevelopmentService.saveAndUpdateMdDevelopment(mdDevelopment);
                }
            }
            if (Objects.equal("MdDevelopmentHypothesis", keyMdDevelopment)) {
                if (mdDevelopmentHypothesis != null) {
                    mdDevelopment.setId(id);
                    mdDevelopment.setPrice(new BigDecimal(mdDevelopmentHypothesis.getEstimateunitpricelandc33()));
                    mdDevelopment.setType(FormatUtils.entityNameConvertToTableName(MdDevelopmentHypothesis.class));
                    mdDevelopmentService.saveAndUpdateMdDevelopment(mdDevelopment);
                }
            }
        }

        //处理评估方案中的各个评估方法
        SchemeInfo schemeInfo = new SchemeInfo();
        schemeInfo.setProjectId(projectPlanDetails.getProjectId());
        schemeInfo.setPlanDetailsId(projectPlanDetails.getId());
        schemeInfo.setJudgeObjectId(projectPlanDetails.getJudgeObjectId());
        schemeInfo.setProcessInsId(processInsId);
        schemeInfo.setMethodType(baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_DEVELOPMENT).getId());
        schemeInfo.setMethodDataId(id);
        schemeInfoService.saveSchemeInfo(schemeInfo);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        JSONObject jsonObject = JSON.parseObject(formData);
        String keyMdDevelopment = null;
        List<SchemeSupportInfo> supportInfoList = null;
        MdDevelopmentHypothesis mdDevelopmentHypothesis = null;
        MdDevelopmentArchitectural mdDevelopmentArchitectural = null;
        MdCostAndDevelopmentOther mdCostAndDevelopmentOther = null;
        String jsonContent = null;
        SchemeInfo schemeInfo = schemeInfoService.getSchemeInfo(projectPlanDetails.getId());
        if (schemeInfo == null){
            return;
        }
        MdDevelopment mdDevelopment = mdDevelopmentService.getMdDevelopmentById(schemeInfo.getMethodDataId());
        if (mdDevelopment ==null){
            return;
        }
        //解析实体 ,并且对json 进行一些处理
        try {
            jsonContent = jsonObject.getString("supportInfoList");
            if (!StringUtils.isEmpty(jsonContent)) {
                supportInfoList = JSONObject.parseArray(jsonContent, SchemeSupportInfo.class);
            }
            keyMdDevelopment = jsonObject.getString("mdDevelopment");
            jsonContent = jsonObject.getString("mdDevelopmentHypothesis");
            if (!StringUtils.isEmpty(jsonContent)) {
                mdDevelopmentHypothesis = JSONObject.parseObject(jsonContent, MdDevelopmentHypothesis.class);
                mdDevelopmentHypothesis.setJsonContent(JSON.toJSONString(jsonContent));
            }
            jsonContent = jsonObject.getString("mdDevelopmentArchitectural");
            if (!StringUtils.isEmpty(jsonContent)) {
                mdDevelopmentArchitectural = JSONObject.parseObject(jsonContent, MdDevelopmentArchitectural.class);
                mdDevelopmentArchitectural.setJsonContent(JSON.toJSONString(jsonContent));
            }
        } catch (Exception e1) {
            logger.error(String.format("实体解析失败! ==> %s", e1.getMessage()));
        }

        //处理评估方法
        if (!ObjectUtils.isEmpty(supportInfoList)) {
            for (SchemeSupportInfo schemeSupportInfo : supportInfoList) {
                schemeSupportInfoService.saveSupportInfo(schemeSupportInfo);
            }
        }

        //处理假设开发法 在建工程
        if (mdDevelopmentHypothesis != null) {
            if (mdDevelopmentHypothesis.getId() == null) {
                mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getByPidMdCostAndDevelopmentOther(MdDevelopmentHypothesis.class.getSimpleName(), 0,FormatUtils.entityNameConvertToTableName(MdDevelopmentHypothesis.class));
                //存入上级主表id
                mdDevelopmentHypothesis.setPid(mdDevelopment.getId());
                Integer pid = mdDevelopmentService.saveAndUpdateMdDevelopmentHypothesis(mdDevelopmentHypothesis);
                //处理从表
                if (mdCostAndDevelopmentOther != null) {
                    mdCostAndDevelopmentOther.setPid(pid);
                    mdCostAndDevelopmentOtherService.updateMdCostAndDevelopmentOther(mdCostAndDevelopmentOther);
                }
            } else {
                mdDevelopmentService.saveAndUpdateMdDevelopmentHypothesis(mdDevelopmentHypothesis);
            }
        }

        //处理假设开发法 土地
        if (mdDevelopmentArchitectural != null) {
            if (mdDevelopmentArchitectural.getId() == null) {
                mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getByPidMdCostAndDevelopmentOther(MdDevelopmentArchitectural.class.getSimpleName(), 0,FormatUtils.entityNameConvertToTableName(MdDevelopmentArchitectural.class));
                //存入上级主表id
                mdDevelopmentArchitectural.setPid(mdDevelopment.getId());
                Integer pid = mdDevelopmentService.saveAndUpdateMdDevelopmentArchitectural(mdDevelopmentArchitectural);
                //处理从表
                if (mdCostAndDevelopmentOther != null) {
                    mdCostAndDevelopmentOther.setPid(pid);
                    mdCostAndDevelopmentOtherService.updateMdCostAndDevelopmentOther(mdCostAndDevelopmentOther);
                }
            } else {
                mdDevelopmentService.saveAndUpdateMdDevelopmentArchitectural(mdDevelopmentArchitectural);
            }
        }
        //确定假设开发法具体选择的哪一个方法来测算的
        if (org.apache.commons.lang.StringUtils.isNotBlank(keyMdDevelopment)) {
            if (Objects.equal("MdDevelopmentArchitectural", keyMdDevelopment)) {
                if (mdDevelopmentArchitectural != null) {
                    mdDevelopment.setPrice(new BigDecimal(mdDevelopmentArchitectural.getEstimateunitpricelandc33()));
                    mdDevelopment.setType(FormatUtils.entityNameConvertToTableName(MdDevelopmentArchitectural.class));
                }
            }
            if (Objects.equal("MdDevelopmentHypothesis", keyMdDevelopment)) {
                if (mdDevelopmentHypothesis != null) {
                    mdDevelopment.setPrice(new BigDecimal(mdDevelopmentHypothesis.getEstimateunitpricelandc33()));
                    mdDevelopment.setType(FormatUtils.entityNameConvertToTableName(MdDevelopmentHypothesis.class));
                }
            }
            mdDevelopmentService.saveAndUpdateMdDevelopment(mdDevelopment);
        }
    }

    /**
     * 给modelview设置显示参数
     *
     * @param modelAndView
     */
    private void setViewParam(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        Integer judgeObjectId = projectPlanDetails.getJudgeObjectId();
        if (judgeObjectId != null) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
            modelAndView.addObject("judgeObject", schemeJudgeObject);
            if (schemeJudgeObject != null) {
                Integer areaGroupId = schemeJudgeObject.getAreaGroupId();
                if (areaGroupId != null) {
                    SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.get(areaGroupId);
                    modelAndView.addObject("schemeAreaGroup", schemeAreaGroup);
                }
                if (schemeJudgeObject.getDeclareRecordId() != null) {
                    DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                    if (declareRecord != null) {
                        if (declareRecord.getDataTableId() != null) {
                            if (org.apache.commons.lang3.StringUtils.isNotBlank(declareRecord.getDataTableName())) {
                                List<DeclareBuildEconomicIndicators> buildEconomicIndicatorsList = Lists.newArrayList();
                                if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareBuildEngineering.class), declareRecord.getDataTableName())) {
                                    DeclareBuildEngineering declareBuildEngineering = declareBuildEngineeringService.getDeclareBuildEngineeringById(declareRecord.getDataTableId());
                                    if (declareBuildEngineering != null) {
                                        List<DeclareBuildEconomicIndicators> indicators = declareBuildEngineeringAndEquipmentCenterService.getDeclareBuildEconomicIndicatorsList(DeclareBuildEngineering.class.getSimpleName(), declareBuildEngineering.getId(), null);
                                        if (!ObjectUtils.isEmpty(indicators)) {
                                            buildEconomicIndicatorsList.addAll(indicators);
                                        }
                                    }
                                }
                                if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareBuildEquipmentInstall.class), declareRecord.getDataTableName())) {
                                    DeclareBuildEquipmentInstall declareBuildEquipmentInstall = declareBuildEquipmentInstallService.getDeclareBuildEquipmentInstallById(declareRecord.getDataTableId());
                                    if (declareBuildEquipmentInstall != null) {
                                        List<DeclareBuildEconomicIndicators> indicators = declareBuildEngineeringAndEquipmentCenterService.getDeclareBuildEconomicIndicatorsList(DeclareBuildEquipmentInstall.class.getSimpleName(), null, declareBuildEquipmentInstall.getId());
                                        if (!ObjectUtils.isEmpty(indicators)) {
                                            buildEconomicIndicatorsList.addAll(indicators);
                                        }
                                    }
                                }
                                modelAndView.addObject("buildEconomicIndicatorsList", buildEconomicIndicatorsList);
                            }
                        }
                    }
                }
            }
        }
        //评估支持数据
        List<SchemeSupportInfo> supportInfoList = schemeSupportInfoService.getSupportInfoList(projectPlanDetails.getId());
        modelAndView.addObject("supportInfosJSON", JSON.toJSONString(supportInfoList));
        SchemeInfo schemeInfo = null;
        try {
            schemeInfo = schemeInfoService.getSchemeInfo(projectPlanDetails.getId());
            if (schemeInfo == null) {
                return;
            }
        } catch (Exception e1) {
            //不需要抛出异常
            logger.error(String.format("没有获取到数据 ==> %s", e1.getMessage()));
        }
        if (schemeInfo.getMethodDataId() == null) {
            return;
        }
        MdDevelopment mdDevelopment = mdDevelopmentService.getMdDevelopmentById(schemeInfo.getMethodDataId());
        if (mdDevelopment == null){
            return;
        }
        modelAndView.addObject("mdDevelopment", mdDevelopment);
        //设置假设开发法 在建工程
        MdDevelopmentHypothesis mdDevelopmentHypothesis = new MdDevelopmentHypothesis();
        mdDevelopmentHypothesis.setPid(mdDevelopment.getId());
        List<MdDevelopmentHypothesis> mdDevelopmentHypothesisList = mdDevelopmentService.getMdDevelopmentHypothesisList(mdDevelopmentHypothesis);
        if (!ObjectUtils.isEmpty(mdDevelopmentHypothesisList)) {
            //一定会是只有一个或者没有,关于原因 查看save method
            mdDevelopmentHypothesis = mdDevelopmentHypothesisList.get(0);
            modelAndView.addObject("mdDevelopmentHypothesisJSON", mdDevelopmentHypothesis.getJsonContent());
            modelAndView.addObject("mdDevelopmentHypothesis", mdDevelopmentHypothesis);
            MdCostAndDevelopmentOther mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getByPidMdCostAndDevelopmentOther(MdDevelopmentHypothesis.class.getSimpleName(),mdDevelopmentHypothesis.getId(),FormatUtils.entityNameConvertToTableName(MdDevelopmentHypothesis.class));
            if (mdCostAndDevelopmentOther != null) {
                modelAndView.addObject("mdCostAndDevelopmentOtherHypothesis", mdCostAndDevelopmentOther);
                modelAndView.addObject("mdCostAndDevelopmentOtherHypothesisJSON", mdCostAndDevelopmentOther.getJsonContent());
            }
        }
        //假设开发法 土地
        MdDevelopmentArchitectural mdDevelopmentArchitectural = new MdDevelopmentArchitectural();
        mdDevelopmentArchitectural.setPid(mdDevelopment.getId());
        List<MdDevelopmentArchitectural> mdDevelopmentArchitecturalList = mdDevelopmentService.getMdDevelopmentArchitecturalList(mdDevelopmentArchitectural);
        if (!ObjectUtils.isEmpty(mdDevelopmentArchitecturalList)) {
            //一定会是 只有一个或者没有,原因...
            mdDevelopmentArchitectural = mdDevelopmentArchitecturalList.get(0);
            modelAndView.addObject("mdDevelopmentArchitecturalJSON", mdDevelopmentArchitectural.getJsonContent());
            modelAndView.addObject("mdDevelopmentArchitectural", mdDevelopmentArchitectural);
            MdCostAndDevelopmentOther mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getByPidMdCostAndDevelopmentOther(MdDevelopmentArchitectural.class.getSimpleName(),mdDevelopmentArchitectural.getId(),FormatUtils.entityNameConvertToTableName(MdDevelopmentArchitectural.class));
            if (mdCostAndDevelopmentOther != null) {
                modelAndView.addObject("mdCostAndDevelopmentOtherArchitectural", mdCostAndDevelopmentOther);
                modelAndView.addObject("mdCostAndDevelopmentOtherArchitecturalJSON", mdCostAndDevelopmentOther.getJsonContent());
            }
        }
    }
}
