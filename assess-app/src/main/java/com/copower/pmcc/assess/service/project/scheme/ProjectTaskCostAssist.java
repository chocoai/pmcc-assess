package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.MdMarketCostDto;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.method.MdCostAndDevelopmentOtherService;
import com.copower.pmcc.assess.service.method.MdMarketCostService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import org.apache.commons.collections.CollectionUtils;
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
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/scheme/taskCostIndex", "", 0, "0", "");
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
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/scheme/taskCostApproval", processInsId, boxId, taskId, agentUserAccount);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        schemeSupportInfoService.initSupportInfo(projectPlanDetails.getId(), projectInfo.getEntrustPurpose(), AssessDataDicKeyConstant.MD_MARKET_COMPARE);
        setViewParam(projectPlanDetails, modelAndView);
        SchemeInfo schemeInfo = schemeInfoService.getSchemeInfo(projectPlanDetails.getId());
        if (!ObjectUtils.isEmpty(schemeInfo.getMethodDataId())) {
            MdCost mdCost = mdMarketCostService.getByMdCostId(schemeInfo.getMethodDataId());
            String type = mdCost.getType();
            if (Objects.equal(type, FormatUtils.entityNameConvertToTableName(MdCostBuilding.class))) {
                MdCostBuilding mdCostBuilding = new MdCostBuilding();
                mdCostBuilding.setCostId(mdCost.getId());
                List<MdCostBuilding> mdCostBuildings = mdMarketCostService.mdCostBuildingList(mdCostBuilding);
                if (!ObjectUtils.isEmpty(mdCostBuildings)) {
                    mdCostBuilding = mdCostBuildings.get(0);
                    modelAndView.addObject("mdCostBuilding", mdCostBuilding);
                    modelAndView.addObject("mdCostBuildingJSON", mdCostBuilding.getJsonContent());
                }
            }
            if (Objects.equal(type, FormatUtils.entityNameConvertToTableName(MdCostConstruction.class))) {
                MdCostConstruction mdCostConstruction = new MdCostConstruction();
                mdCostConstruction.setCostId(mdCost.getId());
                List<MdCostConstruction> mdCostConstructionList = mdMarketCostService.getMdCostConstructionList(mdCostConstruction);
                if (!ObjectUtils.isEmpty(mdCostConstructionList)) {
                    mdCostConstruction = mdCostConstructionList.get(0);
                    modelAndView.addObject("mdCostConstruction", mdCostConstruction);
                    modelAndView.addObject("mdCostConstructionJSON", mdCostConstruction.getJsonContent());
                }
            }

        }
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
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/scheme/taskCostIndex", processInsId, boxId, taskId, agentUserAccount);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        schemeSupportInfoService.initSupportInfo(projectPlanDetails.getId(), projectInfo.getEntrustPurpose(), AssessDataDicKeyConstant.MD_MARKET_COMPARE);
        setViewParam(projectPlanDetails, modelAndView);
        SchemeInfo schemeInfo = schemeInfoService.getSchemeInfo(projectPlanDetails.getId());
        if (!ObjectUtils.isEmpty(schemeInfo.getMethodDataId())) {
            MdCost mdCost = mdMarketCostService.getByMdCostId(schemeInfo.getMethodDataId());
            String type = mdCost.getType();

            //update MdCostBuilding
            if (Objects.equal(type, FormatUtils.entityNameConvertToTableName(MdCostBuilding.class))) {
                MdCostBuilding mdCostBuilding = new MdCostBuilding();
                mdCostBuilding.setCostId(mdCost.getId());
                List<MdCostBuilding> mdCostBuildings = mdMarketCostService.mdCostBuildingList(mdCostBuilding);
                if (!ObjectUtils.isEmpty(mdCostBuildings)) {
                    mdCostBuilding = mdCostBuildings.get(0);
                    modelAndView.addObject("mdCostBuildingJSON", mdCostBuilding.getJsonContent());
                    modelAndView.addObject("mdCostBuilding", mdCostBuilding);
                    if (mdCostBuilding.getEngineeringId() != null) {
                        MdCostAndDevelopmentOther mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getMdCostAndDevelopmentOther(mdCostBuilding.getEngineeringId());
                        modelAndView.addObject("mdCostAndDevelopmentOther", mdCostAndDevelopmentOther);
                        modelAndView.addObject("mdCostAndDevelopmentOtherJSON", mdCostAndDevelopmentOther.getJsonContent());
                    }
                }
            }
            //update MdCostConstruction
            if (Objects.equal(type, FormatUtils.entityNameConvertToTableName(MdCostConstruction.class))) {
                MdCostConstruction mdCostConstruction = new MdCostConstruction();
                mdCostConstruction.setCostId(mdCost.getId());
                List<MdCostConstruction> mdCostConstructionList = mdMarketCostService.getMdCostConstructionList(mdCostConstruction);
                if (!ObjectUtils.isEmpty(mdCostConstructionList)) {
                    mdCostConstruction = mdCostConstructionList.get(0);
                    modelAndView.addObject("mdCostConstructionJSON", mdCostConstruction.getJsonContent());
                    modelAndView.addObject("mdCostConstruction", mdCostConstruction);
                    if (mdCostConstruction.getEngineeringId() != null) {
                        MdCostAndDevelopmentOther mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getMdCostAndDevelopmentOther(mdCostConstruction.getEngineeringId());
                        modelAndView.addObject("mdCostAndDevelopmentOther", mdCostAndDevelopmentOther);
                        modelAndView.addObject("mdCostAndDevelopmentOtherJSON", mdCostAndDevelopmentOther.getJsonContent());
                    }
                }
            }

        }
        //手动注入 委估对象数据
        MdCost mdCost = new MdCost();
        mdCost.setPrice(BigDecimal.valueOf(10));
        mdCost.setArea(BigDecimal.valueOf(20));
        modelAndView.addObject("mdCost", mdCost);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/scheme/taskCostApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        schemeSupportInfoService.initSupportInfo(projectPlanDetails.getId(), projectInfo.getEntrustPurpose(), AssessDataDicKeyConstant.MD_MARKET_COMPARE);
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        MdMarketCostDto mdMarketCostDto = JSON.parseObject(formData, MdMarketCostDto.class);
        MdCost mdCost = new MdCost();
        MdCostAndDevelopmentOther mdCostAndDevelopmentOther = null;
        int id = 0;
        if (CollectionUtils.isNotEmpty(mdMarketCostDto.getSupportInfoList())) {
            for (SchemeSupportInfo schemeSupportInfo : mdMarketCostDto.getSupportInfoList()) {
                schemeSupportInfoService.saveSupportInfo(schemeSupportInfo);
            }
        }
        JSONObject jsonObject = JSON.parseObject(formData);

        //save MdCostBuilding
        if (!ObjectUtils.isEmpty(mdMarketCostDto.getMdCostBuilding())) {//评估单价 (建筑物)
            mdCost.setType(FormatUtils.entityNameConvertToTableName(MdCostBuilding.class));
            id = mdMarketCostService.addMdCost(mdCost);
            MdCostBuilding mdCostBuilding = mdMarketCostDto.getMdCostBuilding();
            mdCostBuilding.setCostId(id);

            String jsonContent = jsonObject.getString("mdCostBuilding");
            if (StringUtils.isEmpty(jsonContent)) {
                mdCostBuilding.setJsonContent(JSON.toJSONString(mdCostBuilding));
            } else {
                mdCostBuilding.setJsonContent(JSON.toJSONString(jsonContent));
            }
            mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getMdCostAndDevelopmentOther(mdCostBuilding.getClass().getSimpleName(),0);
            if (mdCostAndDevelopmentOther != null) {
                mdCostBuilding.setEngineeringId(mdCostAndDevelopmentOther.getId());//
            }
            int pid = 0;
            try {
                pid = mdMarketCostService.addMdCostBuilding(mdCostBuilding);
                mdCostAndDevelopmentOther.setPid(pid);//
                mdCostAndDevelopmentOtherService.updateMdCostAndDevelopmentOther(mdCostAndDevelopmentOther);
            } catch (Exception e1) {
                logger.error(e1.getMessage());
                try {
                    throw new Exception();
                } catch (Exception e11) {

                }
            }
        }

        //save MdCostConstruction
        if (!ObjectUtils.isEmpty(mdMarketCostDto.getMdCostConstruction())) {//在建工程
            mdCost.setType(FormatUtils.entityNameConvertToTableName(MdCostConstruction.class));
            MdCostConstruction mdCostConstruction = mdMarketCostDto.getMdCostConstruction();
            id = mdMarketCostService.addMdCost(mdCost);
            mdCostConstruction.setCostId(id);
            String jsonContent = jsonObject.getString("mdCostConstruction");
            if (StringUtils.isEmpty(jsonContent)) {
                mdCostConstruction.setJsonContent(JSON.toJSONString(mdCostConstruction));
            } else {
                mdCostConstruction.setJsonContent(JSON.toJSONString(jsonContent));
            }
            mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getMdCostAndDevelopmentOther(mdCostConstruction.getClass().getSimpleName(),0);
            if (mdCostAndDevelopmentOther != null) {
                mdCostConstruction.setEngineeringId(mdCostAndDevelopmentOther.getId());//
            }
            int pid = 0;
            try {
                pid = mdMarketCostService.addMdCostConstruction(mdCostConstruction);
                mdCostAndDevelopmentOther.setPid(pid);//
                mdCostAndDevelopmentOtherService.updateMdCostAndDevelopmentOther(mdCostAndDevelopmentOther);
            } catch (Exception e1) {
                logger.error(e1.getMessage());
                try {
                    throw  new Exception();
                } catch (Exception e11) {
                }
            }
        }


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
        MdMarketCostDto mdMarketCostDto = JSON.parseObject(formData, MdMarketCostDto.class);
        if (CollectionUtils.isNotEmpty(mdMarketCostDto.getSupportInfoList())) {
            for (SchemeSupportInfo schemeSupportInfo : mdMarketCostDto.getSupportInfoList()) {
                schemeSupportInfoService.saveSupportInfo(schemeSupportInfo);//saveAndUpdate
            }
        }
        JSONObject jsonObject = JSON.parseObject(formData);
        if (!ObjectUtils.isEmpty(mdMarketCostDto.getMdCostBuilding())) {//评估单价 (建筑物)
            MdCostBuilding building = mdMarketCostDto.getMdCostBuilding();
            try {
                if (building.getId() != null) {
                    String jsonContent = jsonObject.getString("mdCostBuilding");
                    if (StringUtils.isEmpty(jsonContent)) {
                        building.setJsonContent(JSON.toJSONString(building));
                    } else {
                        building.setJsonContent(JSON.toJSONString(jsonContent));
                    }
                    mdMarketCostService.updateMdCostBuilding(building);
                }
            } catch (Exception e1) {
                logger.error(String.format("取到空实体数据了,%s", e1.getMessage()), e1);
            }
        }
        if (!ObjectUtils.isEmpty(mdMarketCostDto.getMdCostConstruction())) {//在建工程
            MdCostConstruction costConstruction = mdMarketCostDto.getMdCostConstruction();
            try {
                if (costConstruction.getId() != null) {
                    String jsonContent = jsonObject.getString("mdCostConstruction");
                    if (StringUtils.isEmpty(jsonContent)) {
                        costConstruction.setJsonContent(JSON.toJSONString(costConstruction));
                    } else {
                        costConstruction.setJsonContent(JSON.toJSONString(jsonContent));
                    }
                    mdMarketCostService.updateMdCostConstruction(costConstruction);
                }
            } catch (Exception e1) {
                logger.error(String.format("取到空实体数据了,%s", e1.getMessage()), e1);
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
    }


}
