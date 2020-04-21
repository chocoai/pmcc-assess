package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.method.MethodIncomeOperationModeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeIncomeApplyDto;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicBuildingService;
import com.copower.pmcc.assess.service.data.DataTaxRateAllocationService;
import com.copower.pmcc.assess.service.method.MdIncomeService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
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
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private BasicBuildingService basicBuildingService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskIncomeIndex", 0);
        applyInit(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Transactional(rollbackFor = Exception.class)
    private void applyInit(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        SchemeInfo info = schemeInfoService.getSchemeInfo(projectPlanDetails.getId());
        if (info == null) {
            MdIncome mdIncome = new MdIncome();
            mdIncome.setOperationMode(MethodIncomeOperationModeEnum.LEASE.getId());
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
        setViewParam(projectPlanDetails, modelAndView);
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
        //收益法相关
        MdIncome mdIncome = null;
        if (schemeInfo != null && schemeInfo.getMethodDataId() != null) {
            mdIncome = mdIncomeService.getIncomeById(schemeInfo.getMethodDataId());
        }
        modelAndView.addObject("mdIncome", mdIncome);
        SchemeJudgeObject judgeObject = schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId());
        SchemeAreaGroup areaGroup = schemeAreaGroupService.getSchemeAreaGroup(judgeObject.getAreaGroupId());
        modelAndView.addObject("judgeObject", judgeObject);
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(judgeObject.getDeclareRecordId());
        if (declareRecord != null) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(declareRecord.getId());
            if (basicApply != null) {
                BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
                if (basicBuilding != null) {
                    BigDecimal houseSurplusYear = mdIncomeService.getHouseSurplusYear(basicBuilding.getBeCompletedTime(), areaGroup.getValueTimePoint(), surveyCommonService.getBuildingUsableYear(basicApply, basicBuilding));
                    modelAndView.addObject("houseSurplusYear", houseSurplusYear); //房产使用剩余年限
                }
            }
            //土地使用剩余年限
            modelAndView.addObject("landSurplusYear", mdIncomeService.getLandSurplusYear(declareRecord.getLandUseEndDate(), areaGroup.getValueTimePoint()));
        }
        //取重置价格
        DataTaxRateAllocation taxRateAllocation = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_LAND_REPLACEMENT_VALUE, areaGroup.getProvince(), areaGroup.getCity(), null);
        if (taxRateAllocation != null)
            modelAndView.addObject("replacementValue", taxRateAllocation.getAmount());
        //房产税率
        taxRateAllocation = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_PROPERTY_TAX);
        if (taxRateAllocation != null)
            modelAndView.addObject("propertyTaxRatio", taxRateAllocation.getTaxRate());
        //印花税率
        taxRateAllocation = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_STAMP_DUTY);
        if (taxRateAllocation != null)
            modelAndView.addObject("stampDutyRatio", taxRateAllocation.getTaxRate());
        //增值税率
        taxRateAllocation = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_SALES_TAX);
        if (taxRateAllocation != null)
            modelAndView.addObject("salesTaxRatio", taxRateAllocation.getTaxRate());
        //城建税率
        taxRateAllocation = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_CONSTRUCTION_TAX);
        if (taxRateAllocation != null)
            modelAndView.addObject("constructionTaxRatio", taxRateAllocation.getTaxRate());
        //地方教育费附加税率
        taxRateAllocation = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_LOCAL_EDUCATION_TAX_ADDITIONAL, areaGroup.getProvince(), areaGroup.getCity(), null);
        if (taxRateAllocation != null)
            modelAndView.addObject("localEducationRatio", taxRateAllocation.getTaxRate());
        //教育费附加税率
        taxRateAllocation = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_EDUCATION_FEE_PLUS);
        if (taxRateAllocation != null)
            modelAndView.addObject("educationRatio", taxRateAllocation.getTaxRate());

        modelAndView.addObject("rentalGrowthRateExplainEditable", baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_INCOME_RENTAL_GROWTH_RATE_EXPLAIN_EDITABLE).getRemark());
        modelAndView.addObject("rentalGrowthRateExplainReadonly", baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_INCOME_RENTAL_GROWTH_RATE_EXPLAIN_READONLY).getRemark());
        modelAndView.addObject("transactionTaxeFeeRatioEditable", baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_INCOME_TRANSACTION_TAXE_FEE_RATIO_EDITABLE).getRemark());
        modelAndView.addObject("transactionTaxeFeeRatioReadonly", baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_INCOME_TRANSACTION_TAXE_FEE_RATIO_READONLY).getRemark());
        BaseDataDic INCOME = baseDataDicService.getCacheDataDicByFieldName(AssessReportFieldConstant.INCOME);
        modelAndView.addObject("methodTypeObj", INCOME);
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
        mdIncomeService.saveResult(schemeIncomeApplyDto.getIncomeInfo());
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
        mdIncomeService.saveResult(schemeIncomeApplyDto.getIncomeInfo());
    }
}
