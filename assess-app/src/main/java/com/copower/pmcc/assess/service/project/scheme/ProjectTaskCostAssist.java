package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.method.MdCostAndDevelopmentOtherService;
import com.copower.pmcc.assess.service.method.MdMarketCostService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
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
    private ProjectInfoService projectInfoService;
    @Autowired
    private MdMarketCostService mdMarketCostService;
    @Autowired
    private SchemeSupportInfoService schemeSupportInfoService;
    @Autowired
    private MdCostAndDevelopmentOtherService mdCostAndDevelopmentOtherService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskCostIndex", "", 0, "0", "");
        //初始化支撑数据
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        schemeSupportInfoService.initSupportInfo(projectPlanDetails, projectInfo, AssessDataDicKeyConstant.MD_MARKET_COMPARE);
        setViewParam(projectPlanDetails, modelAndView);
        mdCostAndDevelopmentOtherService.removePid();
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
        mdCostAndDevelopmentOtherService.removePid();
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
        MdCostAndDevelopmentOther mdCostAndDevelopmentOther = null;
        Integer id = 0;
        Integer pid = 0;
        JSONObject jsonObject = JSON.parseObject(formData);
        List<SchemeSupportInfo> supportInfoList = null;
        MdCostBuilding mdCostBuilding = null;
        MdCostConstruction mdCostConstruction = null;
        MdCost mdCost = new MdCost();
        String jsonContent = null;
        String keyMdCost = null;
        id = mdMarketCostService.saveAndUpdateMdCost(mdCost);

        //解析实体 ,并且对json 进行一些处理
        try {
            jsonContent = jsonObject.getString("supportInfoList");
            if (!StringUtils.isEmpty(jsonContent)) {
                supportInfoList = JSONObject.parseArray(jsonContent, SchemeSupportInfo.class);
            }
            jsonContent = jsonObject.getString("mdCostBuilding");
            if (!StringUtils.isEmpty(jsonContent)) {
                mdCostBuilding = JSONObject.parseObject(jsonContent, MdCostBuilding.class);
                mdCostBuilding.setJsonContent(JSON.toJSONString(jsonContent));
            }
            jsonContent = jsonObject.getString("mdCostConstruction");
            if (!StringUtils.isEmpty(jsonContent)) {
                mdCostConstruction = JSONObject.parseObject(jsonContent, MdCostConstruction.class);
                mdCostConstruction.setJsonContent(JSON.toJSONString(jsonContent));
            }
            //确定成本法具体选择的哪一个方法来测算的
            keyMdCost = jsonObject.getString("mdCost");
            if (org.apache.commons.lang.StringUtils.isNotBlank(keyMdCost)){
                if (Objects.equal("mdCostBuilding",keyMdCost)){
                    if (mdCostBuilding != null){
                        mdCost.setId(id);
                        mdCost.setPrice(mdCostBuilding.getAssessPrice());
                        mdCost.setType(FormatUtils.entityNameConvertToTableName(MdCostBuilding.class));
                        mdMarketCostService.saveAndUpdateMdCost(mdCost);
                    }
                }
                if (Objects.equal("mdCostConstruction",keyMdCost)){
                    if (mdCostConstruction != null){
                        mdCost.setId(id);
                        mdCost.setPrice(mdCostConstruction.getConstructionAssessmentPriceCorrecting());
                        mdCost.setType(FormatUtils.entityNameConvertToTableName(MdCostConstruction.class));
                        mdMarketCostService.saveAndUpdateMdCost(mdCost);
                    }
                }
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

        //处理(建筑物)
        if (!ObjectUtils.isEmpty(mdCostBuilding)) {
            mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getMdCostAndDevelopmentOther(MdCostBuilding.class.getSimpleName(), 0);
            //存入上级主表id
            mdCostBuilding.setPid(id);
            pid = mdMarketCostService.saveAndUpdateMdCostBuilding(mdCostBuilding);
            if (mdCostAndDevelopmentOther != null) {
                mdCostAndDevelopmentOther.setPid(pid);
                mdCostAndDevelopmentOtherService.saveAndUpdateMdCostAndDevelopmentOther(mdCostAndDevelopmentOther);
            }
        }

        //处理(在建工程)
        if (!ObjectUtils.isEmpty(mdCostConstruction)) {
            mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getMdCostAndDevelopmentOther(MdCostConstruction.class.getSimpleName(), 0);
            mdCostConstruction.setPid(id);
            pid = mdMarketCostService.saveAndUpdateMdCostConstruction(mdCostConstruction);
            if (mdCostAndDevelopmentOther != null) {
                mdCostAndDevelopmentOther.setPid(pid);
                mdCostAndDevelopmentOtherService.saveAndUpdateMdCostAndDevelopmentOther(mdCostAndDevelopmentOther);
            }
        }
        //处理评估方案中的各个评估方法
        SchemeInfo schemeInfo = new SchemeInfo();
        schemeInfo.setProjectId(projectPlanDetails.getProjectId());
        schemeInfo.setPlanDetailsId(projectPlanDetails.getId());
        schemeInfo.setJudgeObjectId(projectPlanDetails.getJudgeObjectId());
        schemeInfo.setProcessInsId(processInsId);
        schemeInfo.setMethodType(baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_COST).getId());
        schemeInfo.setMethodDataId(id);
        schemeInfoService.saveSchemeInfo(schemeInfo);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        List<SchemeSupportInfo> supportInfoList = null;
        MdCostBuilding mdCostBuilding = null;
        MdCostConstruction mdCostConstruction = null;
        JSONObject jsonObject = JSON.parseObject(formData);
        String jsonContent = null;
        SchemeInfo schemeInfo = schemeInfoService.getSchemeInfo(projectPlanDetails.getId());
        if (schemeInfo == null){
            return;
        }
        MdCost mdCost = mdMarketCostService.getByMdCostId(schemeInfo.getMethodDataId());
        if (mdCost == null){
            return;
        }
        String keyMdCost = null;
        //解析实体 ,并且对json 进行一些处理(id至少会有一个实体含有)
        try {
            jsonContent = jsonObject.getString("supportInfoList");
            if (!StringUtils.isEmpty(jsonContent)) {
                supportInfoList = JSONObject.parseArray(jsonContent, SchemeSupportInfo.class);
            }
            jsonContent = jsonObject.getString("mdCostBuilding");
            if (!StringUtils.isEmpty(jsonContent)) {
                mdCostBuilding = JSONObject.parseObject(jsonContent, MdCostBuilding.class);
                mdCostBuilding.setJsonContent(JSON.toJSONString(jsonContent));
            }
            jsonContent = jsonObject.getString("mdCostConstruction");
            if (!StringUtils.isEmpty(jsonContent)) {
                mdCostConstruction = JSONObject.parseObject(jsonContent, MdCostConstruction.class);
                mdCostConstruction.setJsonContent(JSON.toJSONString(jsonContent));
            }
            keyMdCost = jsonObject.getString("mdCost");
        } catch (Exception e1) {
            logger.error(String.format("实体解析失败! ==> %s", e1.getMessage()));
        }

        //处理评估方法
        if (!ObjectUtils.isEmpty(supportInfoList)) {
            for (SchemeSupportInfo schemeSupportInfo : supportInfoList) {
                schemeSupportInfoService.saveSupportInfo(schemeSupportInfo);
            }
        }

        //处理(建筑物)
        if (!ObjectUtils.isEmpty(mdCostBuilding)) {
            if (mdCostBuilding.getId() != null) {
                mdMarketCostService.saveAndUpdateMdCostBuilding(mdCostBuilding);
            } else {
                mdCostBuilding.setPid(mdCost.getId());
                Integer id = mdMarketCostService.saveAndUpdateMdCostBuilding(mdCostBuilding);
                //处理从表
                MdCostAndDevelopmentOther mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getByPidMdCostAndDevelopmentOther(MdCostBuilding.class.getSimpleName(), 0,FormatUtils.entityNameConvertToTableName(MdCostBuilding.class));
                if (mdCostAndDevelopmentOther != null) {
                    mdCostAndDevelopmentOther.setPid(id);
                    mdCostAndDevelopmentOtherService.saveAndUpdateMdCostAndDevelopmentOther(mdCostAndDevelopmentOther);
                }
            }
        }

        //处理(在建工程)
        if (!ObjectUtils.isEmpty(mdCostConstruction)) {
            if (mdCostConstruction.getId() != null) {
                mdMarketCostService.saveAndUpdateMdCostConstruction(mdCostConstruction);
            } else {
                MdCostAndDevelopmentOther mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getByPidMdCostAndDevelopmentOther(MdCostConstruction.class.getSimpleName(), 0,FormatUtils.entityNameConvertToTableName(MdCostConstruction.class));
                mdCostConstruction.setPid(mdCost.getId());
                Integer id = mdMarketCostService.saveAndUpdateMdCostConstruction(mdCostConstruction);
                if (mdCostAndDevelopmentOther != null) {
                    mdCostAndDevelopmentOther.setPid(id);
                    mdCostAndDevelopmentOtherService.saveAndUpdateMdCostAndDevelopmentOther(mdCostAndDevelopmentOther);
                }
            }
        }
        //确定成本法具体选择的哪一个方法来测算的
        if (org.apache.commons.lang.StringUtils.isNotBlank(keyMdCost)){
            if (Objects.equal("mdCostBuilding",keyMdCost)){
                if (mdCostBuilding != null){
                    mdCost.setPrice(mdCostBuilding.getAssessPrice());
                    mdCost.setType(FormatUtils.entityNameConvertToTableName(MdCostBuilding.class));
                }
            }
            if (Objects.equal("mdCostConstruction",keyMdCost)){
                if (mdCostConstruction != null){
                    mdCost.setPrice(mdCostConstruction.getConstructionAssessmentPriceCorrecting());
                    mdCost.setType(FormatUtils.entityNameConvertToTableName(MdCostConstruction.class));
                }
            }
            mdMarketCostService.saveAndUpdateMdCost(mdCost);
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
            modelAndView.addObject("judgeObject",schemeJudgeObject);
            if (schemeJudgeObject != null){
                Integer areaGroupId = schemeJudgeObject.getAreaGroupId();
                if (areaGroupId != null){
                    SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.get(areaGroupId);
                    modelAndView.addObject("schemeAreaGroup",schemeAreaGroup);
                }
            }
        }
        //评估支持数据
        List<SchemeSupportInfo> supportInfoList = schemeSupportInfoService.getSupportInfoList(projectPlanDetails.getId());
        modelAndView.addObject("supportInfosJSON", JSON.toJSONString(supportInfoList));
        SchemeInfo schemeInfo = null;
        MdCostBuilding mdCostBuilding = null;
        MdCostConstruction mdCostConstruction = null;
        try {
            schemeInfo = schemeInfoService.getSchemeInfo(projectPlanDetails.getId());
            if (schemeInfo==null){
                return;
            }
        } catch (Exception e1) {
            logger.error(String.format("没有获取到数据 ==> %s", e1.getMessage()));
        }
        if (schemeInfo.getMethodDataId() == null){
            return;
        }
        //设置 (建筑物) 页面模型参数
        MdCost mdCost = mdMarketCostService.getByMdCostId(schemeInfo.getMethodDataId());
        if (mdCost == null){
            return;
        }
        modelAndView.addObject("mdCost",mdCost);
        mdCostBuilding = new MdCostBuilding();
        mdCostBuilding.setPid(mdCost.getId());
        List<MdCostBuilding> mdCostBuildingList = mdMarketCostService.mdCostBuildingList(mdCostBuilding);
        if (!ObjectUtils.isEmpty(mdCostBuildingList)) {
            //一定会是只有一个或者没有,关于原因 查看save method
            mdCostBuilding = mdCostBuildingList.get(0);
            modelAndView.addObject("mdCostBuildingJSON", mdCostBuilding.getJsonContent());
            modelAndView.addObject("mdCostBuilding", mdCostBuilding);
            MdCostAndDevelopmentOther mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getByPidMdCostAndDevelopmentOther(MdCostBuilding.class.getSimpleName(),mdCostBuilding.getId(),FormatUtils.entityNameConvertToTableName(MdCostBuilding.class));
            //设置建筑物下的建筑安装工程
            if (mdCostAndDevelopmentOther != null) {
                modelAndView.addObject("mdCostAndDevelopmentOtherBuilding", mdCostAndDevelopmentOther);
                modelAndView.addObject("mdCostAndDevelopmentOtherBuildingJSON", mdCostAndDevelopmentOther.getJsonContent());
            }
        }
        //设置 在建工程 页面模型参数
        mdCostConstruction = new MdCostConstruction();
        mdCostConstruction.setPid(mdCost.getId());
        List<MdCostConstruction> mdCostConstructionList = mdMarketCostService.getMdCostConstructionList(mdCostConstruction);
        if (!ObjectUtils.isEmpty(mdCostConstructionList)) {
            //一定会是只有一个或者没有,关于原因 查看save method
            mdCostConstruction = mdCostConstructionList.get(0);
            modelAndView.addObject("mdCostConstructionJSON", mdCostConstruction.getJsonContent());
            modelAndView.addObject("mdCostConstruction", mdCostConstruction);
            MdCostAndDevelopmentOther mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getByPidMdCostAndDevelopmentOther(MdCostConstruction.class.getSimpleName(),mdCostConstruction.getId(),FormatUtils.entityNameConvertToTableName(MdCostConstruction.class));
            //设置在建工程下的建筑安装工程
            if (mdCostAndDevelopmentOther != null) {
                modelAndView.addObject("mdCostAndDevelopmentOtherConstruction", mdCostAndDevelopmentOther);
                modelAndView.addObject("mdCostAndDevelopmentOtherConstructionJSON", mdCostAndDevelopmentOther.getJsonContent());
            }
        }
    }

}
