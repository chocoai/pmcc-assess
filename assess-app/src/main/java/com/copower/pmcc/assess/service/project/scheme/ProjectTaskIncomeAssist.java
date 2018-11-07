package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.MethodIncomeOperationModeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeIncomeApplyDto;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataTaxRateAllocationService;
import com.copower.pmcc.assess.service.method.MdIncomeService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "收益法成果")
public class ProjectTaskIncomeAssist implements ProjectTaskInterface {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private SchemeSupportInfoService schemeSupportInfoService;
    @Autowired
    private SchemeInfoService schemeInfoService;
    @Autowired
    private MdIncomeService mdIncomeService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private DataTaxRateAllocationService dataTaxRateAllocationService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskIncomeIndex", 0);
        //初始化支撑数据
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        schemeSupportInfoService.initSupportInfo(projectPlanDetails, projectInfo, AssessDataDicKeyConstant.MD_INCOME);
        setViewParam(projectPlanDetails, modelAndView);
        SchemeInfo info = schemeInfoService.getSchemeInfo(projectPlanDetails.getId());
        if (info == null) {
            MdIncome mdIncome = new MdIncome();
            mdIncome.setOperationMode(MethodIncomeOperationModeEnum.PROPRIETARY.getId());
            mdIncome.setCreator(processControllerComponent.getThisUser());
            mdIncomeService.saveIncome(mdIncome);
            modelAndView.addObject("mdIncome", mdIncome);
            SchemeInfo schemeInfo = new SchemeInfo();
            schemeInfo.setProjectId(projectPlanDetails.getProjectId());
            schemeInfo.setPlanDetailsId(projectPlanDetails.getId());
            schemeInfo.setJudgeObjectId(projectPlanDetails.getJudgeObjectId());
            schemeInfo.setMethodType(baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_INCOME).getId());
            schemeInfo.setMethodDataId(mdIncome.getId());
            try {
                schemeInfoService.saveSchemeInfo(schemeInfo);
            } catch (BusinessException e) {
                logger.error("saveSchemeInfo error ", e);
            }
        }
        return modelAndView;
    }


    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskIncomeApproval", processInsId, boxId, taskId, agentUserAccount);
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    /**
     * 返回修改
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
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskIncomeIndex", processInsId, boxId, taskId, agentUserAccount);
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskIncomeApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    /**
     * 给modelview设置显示参数
     *
     * @param modelAndView
     */
    private void setViewParam(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        SchemeInfo schemeInfo = schemeInfoService.getSchemeInfo(projectPlanDetails.getId());
        modelAndView.addObject("schemeInfo", schemeInfo);
        //评估支持数据
        List<SchemeSupportInfo> supportInfoList = schemeSupportInfoService.getSupportInfoList(projectPlanDetails.getId());
        modelAndView.addObject("supportInfosJSON", JSON.toJSONString(CollectionUtils.isEmpty(supportInfoList) ? Lists.newArrayList() : supportInfoList));
        //收益法相关
        MdIncome mdIncome = null;
        if (schemeInfo != null && schemeInfo.getMethodDataId() != null) {
            mdIncome = mdIncomeService.getIncomeById(schemeInfo.getMethodDataId());
        }
        SchemeJudgeObject judgeObject = schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId());
        if (judgeObject != null) {
            modelAndView.addObject("judgeObject", judgeObject);
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(judgeObject.getDeclareRecordId());
            if (declareRecord != null) {
                if (declareRecord.getHouseUseEndDate() != null)//房产使用剩余年限
                    modelAndView.addObject("houseSurplusYear", DateUtils.diffDate(declareRecord.getHouseUseEndDate(), new Date()) / DateUtils.DAYS_PER_YEAR);
                if (declareRecord.getLandUseEndDate() != null)//土地使用剩余年限
                    modelAndView.addObject("landSurplusYear", DateUtils.diffDate(declareRecord.getLandUseEndDate(), new Date()) / DateUtils.DAYS_PER_YEAR);
            }
        }
        //取重置价格
        SchemeAreaGroup areaGroup = schemeAreaGroupService.get(judgeObject.getAreaGroupId());
        DataTaxRateAllocation taxRateAllocation = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_LAND_REPLACEMENT_VALUE, areaGroup.getProvince(), areaGroup.getCity(), null);
        if (taxRateAllocation != null)
            modelAndView.addObject("replacementValue", taxRateAllocation.getAmount());
        //取租赁税费 房产税+印花税+增值税*(城建税+地方教育费附加+教育费附加)
        modelAndView.addObject("additionalRatio", mdIncomeService.getAdditionalRatio(areaGroup.getProvince(), areaGroup.getCity(), null));
        modelAndView.addObject("mdIncome", mdIncome);
    }

    /**
     * save
     *
     * @param projectPlanDetails
     * @param processInsId
     * @param formData
     * @throws BusinessException
     */
    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        SchemeIncomeApplyDto schemeIncomeApplyDto = JSON.parseObject(formData, SchemeIncomeApplyDto.class);
        if (CollectionUtils.isNotEmpty(schemeIncomeApplyDto.getSupportInfoList())) {
            for (SchemeSupportInfo schemeSupportInfo : schemeIncomeApplyDto.getSupportInfoList()) {
                schemeSupportInfoService.saveSupportInfo(schemeSupportInfo);
            }
        }
        MdIncome mdIncome = mdIncomeService.saveResult(schemeIncomeApplyDto.getIncomeInfo());
        SchemeInfo schemeInfo = schemeInfoService.getSchemeInfo(projectPlanDetails.getId());
        schemeInfo.setProcessInsId(processInsId);
        schemeInfoService.saveSchemeInfo(schemeInfo);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        SchemeIncomeApplyDto schemeIncomeApplyDto = JSON.parseObject(formData, SchemeIncomeApplyDto.class);
        if (CollectionUtils.isNotEmpty(schemeIncomeApplyDto.getSupportInfoList())) {
            for (SchemeSupportInfo schemeSupportInfo : schemeIncomeApplyDto.getSupportInfoList()) {
                schemeSupportInfoService.saveSupportInfo(schemeSupportInfo);
            }
        }

        mdIncomeService.saveResult(schemeIncomeApplyDto.getIncomeInfo());
    }
}
