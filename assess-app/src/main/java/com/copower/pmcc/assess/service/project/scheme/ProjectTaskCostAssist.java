package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.method.MdCostAndDevelopmentOtherService;
import com.copower.pmcc.assess.service.method.MdMarketCostService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
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
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskCostIndex", "", 0, "0", "");
        //初始化支撑数据
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        schemeSupportInfoService.initSupportInfo(projectPlanDetails.getId(), projectInfo.getEntrustPurpose(), AssessDataDicKeyConstant.MD_MARKET_COMPARE);
        setViewParam(projectPlanDetails, modelAndView);
        MdCost mdCost = new MdCost();
        mdCost.setPrice(BigDecimal.valueOf(10));
        mdCost.setArea(BigDecimal.valueOf(20));
        modelAndView.addObject("mdCost", mdCost);
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
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        schemeSupportInfoService.initSupportInfo(projectPlanDetails.getId(), projectInfo.getEntrustPurpose(), AssessDataDicKeyConstant.MD_MARKET_COMPARE);
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
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        schemeSupportInfoService.initSupportInfo(projectPlanDetails.getId(), projectInfo.getEntrustPurpose(), AssessDataDicKeyConstant.MD_MARKET_COMPARE);
        setViewParam(projectPlanDetails, modelAndView);
        //手动注入 委估对象数据
        MdCost mdCost = new MdCost();
        mdCost.setPrice(BigDecimal.valueOf(10));
        mdCost.setArea(BigDecimal.valueOf(20));
        modelAndView.addObject("mdCost", mdCost);
        mdCostAndDevelopmentOtherService.removePid();
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskCostApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        schemeSupportInfoService.initSupportInfo(projectPlanDetails.getId(), projectInfo.getEntrustPurpose(), AssessDataDicKeyConstant.MD_MARKET_COMPARE);
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
        id = mdMarketCostService.saveAndUpdateMdCost(mdCost);

        //解析实体 ,并且对json 进行一些处理
        try {
            jsonContent = jsonObject.getString("supportInfoList");
            if (!StringUtils.isEmpty(jsonContent)) {
                supportInfoList = JSONObject.parseArray(jsonContent, SchemeSupportInfo.class);
                jsonContent = null;//必须初始化,否则下面判定失败
            }
            jsonContent = jsonObject.getString("mdCostBuilding");
            if (!StringUtils.isEmpty(jsonContent)) {
                mdCostBuilding = JSONObject.parseObject(jsonContent, MdCostBuilding.class);
                mdCostBuilding.setJsonContent(JSON.toJSONString(jsonContent));
                jsonContent = null;//必须初始化,否则下面判定失败
            }
            jsonContent = jsonObject.getString("mdCostConstruction");
            if (!StringUtils.isEmpty(jsonContent)) {
                mdCostConstruction = JSONObject.parseObject(jsonContent, MdCostConstruction.class);
                mdCostConstruction.setJsonContent(JSON.toJSONString(jsonContent));
            }
        } catch (Exception e1) {
            logger.error(String.format("实体解析失败! ==> %s", e1.getMessage()));//不需要抛出异常
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
            if (mdCostAndDevelopmentOther != null) {
                mdCostBuilding.setEngineeringId(mdCostAndDevelopmentOther.getId());//存入从表id
            }
            mdCostBuilding.setPid(id);
            pid = mdMarketCostService.saveAndUpdateMdCostBuilding(mdCostBuilding);//存入上级主表id
            if (mdCostAndDevelopmentOther != null) {
                mdCostAndDevelopmentOther.setPid(pid);//处理从表
                mdCostAndDevelopmentOtherService.saveAndUpdateMdCostAndDevelopmentOther(mdCostAndDevelopmentOther);
            }
        }

        //处理(在建工程)
        if (!ObjectUtils.isEmpty(mdCostConstruction)) {
            mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getMdCostAndDevelopmentOther(MdCostConstruction.class.getSimpleName(), 0);
            if (mdCostAndDevelopmentOther != null) {
                mdCostConstruction.setEngineeringId(mdCostAndDevelopmentOther.getId());//存入从表id
            }
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
        schemeInfo.setProcessInsId(processInsId);
        schemeInfo.setMethodType(AssessDataDicKeyConstant.MD_COST);
        schemeInfo.setMethodDataId(id);
        schemeInfoService.saveSchemeInfo(schemeInfo);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        //
    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        List<SchemeSupportInfo> supportInfoList = null;
        MdCostBuilding mdCostBuilding = null;
        MdCostConstruction mdCostConstruction = null;
        JSONObject jsonObject = JSON.parseObject(formData);
        String jsonContent = null;
        MdCostAndDevelopmentOther mdCostAndDevelopmentOther = null;
        Integer pid = 0;
        Integer id = 0;//(id至少会有一个实体含有 否则保存不会提交成功)
        //解析实体 ,并且对json 进行一些处理(id至少会有一个实体含有)
        try {
            jsonContent = jsonObject.getString("supportInfoList");
            if (!StringUtils.isEmpty(jsonContent)) {
                supportInfoList = JSONObject.parseArray(jsonContent, SchemeSupportInfo.class);
                jsonContent = null;//必须初始化,否则下面判定失败
            }
            jsonContent = jsonObject.getString("mdCostBuilding");
            if (!StringUtils.isEmpty(jsonContent)) {
                mdCostBuilding = JSONObject.parseObject(jsonContent, MdCostBuilding.class);
                mdCostBuilding.setJsonContent(JSON.toJSONString(jsonContent));
                if (mdCostBuilding.getId() != null){
                    id = mdMarketCostService.getMdCostBuilding(mdCostBuilding.getId()).getPid();
                }
                jsonContent = null;//必须初始化,否则下面判定失败
            }
            jsonContent = jsonObject.getString("mdCostConstruction");
            if (!StringUtils.isEmpty(jsonContent)) {
                mdCostConstruction = JSONObject.parseObject(jsonContent, MdCostConstruction.class);
                mdCostConstruction.setJsonContent(JSON.toJSONString(jsonContent));
                if (mdCostConstruction.getId() != null){
                    id = mdMarketCostService.getMdCostConstruction(mdCostConstruction.getId()).getPid();
                }
            }
        } catch (Exception e1) {
            logger.error(String.format("实体解析失败! ==> %s", e1.getMessage()));//不需要抛出异常
        }

        //处理评估方法
        if (!ObjectUtils.isEmpty(supportInfoList)) {
            for (SchemeSupportInfo schemeSupportInfo : supportInfoList) {
                schemeSupportInfoService.saveSupportInfo(schemeSupportInfo);
            }
        }

        //处理(建筑物)
        if (!ObjectUtils.isEmpty(mdCostBuilding)){
            if (mdCostBuilding.getId() != null){
                mdMarketCostService.saveAndUpdateMdCostBuilding(mdCostBuilding);
            }else {
                mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getMdCostAndDevelopmentOther(MdCostBuilding.class.getSimpleName(), 0);
                if (mdCostAndDevelopmentOther != null) {
                    mdCostBuilding.setEngineeringId(mdCostAndDevelopmentOther.getId());//存入从表id
                }
                mdCostBuilding.setPid(id);
                pid = mdMarketCostService.saveAndUpdateMdCostBuilding(mdCostBuilding);
                if (mdCostAndDevelopmentOther != null) {
                    mdCostAndDevelopmentOther.setPid(pid);//处理从表
                    mdCostAndDevelopmentOtherService.saveAndUpdateMdCostAndDevelopmentOther(mdCostAndDevelopmentOther);
                }
            }
        }

        //处理(在建工程)
        if (!ObjectUtils.isEmpty(mdCostConstruction)){
            if (mdCostConstruction.getId() != null){
                mdMarketCostService.saveAndUpdateMdCostConstruction(mdCostConstruction);
            }else {
                mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getMdCostAndDevelopmentOther(MdCostConstruction.class.getSimpleName(), 0);
                if (mdCostAndDevelopmentOther != null) {
                    mdCostConstruction.setEngineeringId(mdCostAndDevelopmentOther.getId());//存入从表id
                }
                mdCostConstruction.setPid(id);
                pid = mdMarketCostService.saveAndUpdateMdCostConstruction(mdCostConstruction);
                if (mdCostAndDevelopmentOther != null) {
                    mdCostAndDevelopmentOther.setPid(pid);
                    mdCostAndDevelopmentOtherService.saveAndUpdateMdCostAndDevelopmentOther(mdCostAndDevelopmentOther);
                }
            }
        }
    }

    /**
     * 给modelview设置显示参数
     *
     * @param modelAndView
     */
    private void setViewParam(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        //评估支持数据
        List<SchemeSupportInfo> supportInfoList = schemeSupportInfoService.getSupportInfoList(projectPlanDetails.getId());
        Integer projectId = projectPlanDetails.getProjectId();
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        modelAndView.addObject("supportInfosJSON", JSON.toJSONString(supportInfoList));
        SchemeInfo schemeInfo = null;
        MdCostBuilding mdCostBuilding = null;
        MdCostConstruction mdCostConstruction = null;
        Integer pid = null;

        try {
            schemeInfo = schemeInfoService.getSchemeInfo(projectPlanDetails.getId());
        } catch (Exception e1) {
            logger.error(String.format("没有获取到数据 ==> %s", e1.getMessage()));//不需要抛出异常
        }
        if (schemeInfo != null) {
            pid = schemeInfo.getMethodDataId();
        }
        //设置 (建筑物)
        if (pid != null) {
            mdCostBuilding = new MdCostBuilding();
            mdCostBuilding.setPid(pid);
            List<MdCostBuilding> mdCostBuildingList = mdMarketCostService.mdCostBuildingList(mdCostBuilding);
            if (!ObjectUtils.isEmpty(mdCostBuildingList)){
                mdCostBuilding = mdCostBuildingList.get(0);//一定会是只有一个或者没有,关于原因 查看save method
                modelAndView.addObject("mdCostBuildingJSON", mdCostBuilding.getJsonContent());
                modelAndView.addObject("mdCostBuilding", mdCostBuilding);
                MdCostAndDevelopmentOther mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getMdCostAndDevelopmentOther(mdCostBuilding.getEngineeringId());
                if (mdCostAndDevelopmentOther != null){
                    modelAndView.addObject("mdCostAndDevelopmentOtherBuilding", mdCostAndDevelopmentOther);
                    modelAndView.addObject("mdCostAndDevelopmentOtherBuildingJSON", mdCostAndDevelopmentOther.getJsonContent());
                }
            }
        }
        //设置在建工程
        if (pid != null){
            mdCostConstruction = new MdCostConstruction();
            mdCostConstruction.setPid(pid);
            List<MdCostConstruction> mdCostConstructionList = mdMarketCostService.getMdCostConstructionList(mdCostConstruction);
            if (!ObjectUtils.isEmpty(mdCostConstructionList)){
                mdCostConstruction = mdCostConstructionList.get(0);//.....................
                modelAndView.addObject("mdCostConstructionJSON", mdCostConstruction.getJsonContent());
                modelAndView.addObject("mdCostConstruction", mdCostConstruction);
                MdCostAndDevelopmentOther mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getMdCostAndDevelopmentOther(mdCostConstruction.getEngineeringId());
                if (mdCostAndDevelopmentOther != null){
                    modelAndView.addObject("mdCostAndDevelopmentOtherConstruction", mdCostAndDevelopmentOther);
                    modelAndView.addObject("mdCostAndDevelopmentOtherConstructionJSON", mdCostAndDevelopmentOther.getJsonContent());
                }
            }
        }
    }


}
