package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostApproachTaxesDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicEstateLandCategoryInfoService;
import com.copower.pmcc.assess.service.basic.BasicEstateLandStateService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailService;
import com.copower.pmcc.assess.service.method.MdCostApproachService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
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
@WorkFlowAnnotation(desc = "成本逼近法成果")
public class ProjectTaskCostApproachAssist implements ProjectTaskInterface {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private MdCostApproachService mdCostApproachService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private SchemeInfoService schemeInfoService;
    @Autowired
    private MdCostApproachTaxesDao costApproachTaxesDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private DataLandLevelDetailService dataLandLevelDetailService;
    @Autowired
    private BasicEstateLandCategoryInfoService basicEstateLandCategoryInfoService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskCostApproachIndex", "", 0, "0", "");
        MdCostApproach mdCostApproach = mdCostApproachService.getDataByPlanDetailsId(projectPlanDetails.getId());
        if (mdCostApproach == null) {
            mdCostApproach = new MdCostApproach();
            mdCostApproach.setPlanDetailsId(projectPlanDetails.getId());
            mdCostApproachService.saveMdCostApproach(mdCostApproach);
            SchemeInfo schemeInfo = new SchemeInfo();
            schemeInfo.setProjectId(projectPlanDetails.getProjectId());
            schemeInfo.setPlanDetailsId(projectPlanDetails.getId());
            schemeInfo.setJudgeObjectId(projectPlanDetails.getJudgeObjectId());
            schemeInfo.setMethodType(baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_COST_APPROACH).getId());
            schemeInfo.setMethodDataId(mdCostApproach.getId());
            initTaxeItem(mdCostApproach);
            try {
                schemeInfoService.saveSchemeInfo(schemeInfo);
            } catch (BusinessException e) {
                logger.error(e.getMessage(), e);
            }
        }

        modelAndView.addObject("master", mdCostApproach);
        modelAndView.addObject("apply", "apply");
        setViewParam(mdCostApproach, projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskCostApproachApproval", processInsId, boxId, taskId, agentUserAccount);
        MdCostApproach data = mdCostApproachService.getMdCostApproachVo(mdCostApproachService.getDataByPlanDetailsId(projectPlanDetails.getId()));
        modelAndView.addObject("master", data);
        List<MdCostApproachTaxes> list = mdCostApproachService.getMdCostApproachTaxesListByMasterId(data.getId());
        modelAndView.addObject("taxesVos", list);
        setViewParam(data, projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskCostApproachIndex", processInsId, boxId, taskId, agentUserAccount);
        MdCostApproach data = mdCostApproachService.getDataByPlanDetailsId(projectPlanDetails.getId());
        modelAndView.addObject("master", data);
        List<MdCostApproachTaxes> list = mdCostApproachService.getMdCostApproachTaxesListByMasterId(data.getId());
        modelAndView.addObject("taxesVos", list);
        setViewParam(data, projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskCostApproachApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        MdCostApproach data = mdCostApproachService.getMdCostApproachVo(mdCostApproachService.getDataByPlanDetailsId(projectPlanDetails.getId()));
        modelAndView.addObject("master", data);
        List<MdCostApproachTaxes> list = mdCostApproachService.getMdCostApproachTaxesListByMasterId(data.getId());
        modelAndView.addObject("taxesVos", list);
        setViewParam(data, projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {
        mdCostApproachService.applyCommit(formData, processInsId);
        SchemeInfo schemeInfo = schemeInfoService.getSchemeInfo(projectPlanDetails.getId());
        schemeInfo.setProcessInsId(processInsId);
        schemeInfoService.saveSchemeInfo(schemeInfo);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        mdCostApproachService.applyCommit(formData, processInsId);
    }

    /**
     * 给modelview设置显示参数
     *
     * @param modelAndView
     */
    private void setViewParam(MdCostApproach mdCostApproach, ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        List<MdCostApproachTaxes> taxesList = mdCostApproachService.getMdCostApproachTaxesListByMasterId(mdCostApproach.getId());
        modelAndView.addObject("taxesVos", taxesList);


        List<BaseDataDic> dataDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_SETTING);
        modelAndView.addObject("taxesTypes", dataDicList);
        Integer judgeObjectId = projectPlanDetails.getJudgeObjectId();
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
        modelAndView.addObject("judgeObject", schemeJudgeObject);
        BasicApply basicApply =  basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
        BasicEstate basicEstate = null;
        try {
            basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
            if (basicEstate == null) {
                return;
            }
        } catch (Exception e) {
            logger.error(String.format("没有获取到数据 ==> %s", e.getMessage()));
        }
        if(basicApply.getLandCategoryId()!=null){
            BasicEstateLandCategoryInfo categoryInfo = basicEstateLandCategoryInfoService.getBasicEstateLandCategoryInfoById(basicApply.getLandCategoryId());
            if(categoryInfo!=null){
                modelAndView.addObject("landFactorTotalScore", categoryInfo.getLandFactorTotalScore());
                modelAndView.addObject("landLevelContent", categoryInfo.getLandLevelContentResult());
                modelAndView.addObject("levelDetailId", categoryInfo.getLandLevel());
                DataLandLevelDetail levelDetail = dataLandLevelDetailService.getDataLandLevelDetailById(categoryInfo.getLandLevel());
                if(levelDetail!=null){
                    modelAndView.addObject("landLevelId", levelDetail.getLandLevelId());
                }
            }
        }
    }

    public void initTaxeItem(MdCostApproach mdCostApproach) {
        List<MdCostApproachTaxes> taxesListByMasterId = mdCostApproachService.getMdCostApproachTaxesListByMasterId(mdCostApproach.getId());
        List<BaseDataDic> dataDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_SETTING);
        //全部清空后生成
        if (CollectionUtils.isNotEmpty(taxesListByMasterId)) {
            for (MdCostApproachTaxes item : taxesListByMasterId) {
                costApproachTaxesDao.deleteCostApproachTaxes(item.getId());
            }
        }
        if (CollectionUtils.isNotEmpty(dataDicList)) {
            for (BaseDataDic type : dataDicList) {
                MdCostApproachTaxes taxes = new MdCostApproachTaxes();
                taxes.setTypeName(type.getName());
                taxes.setTypeKey(type.getFieldName());
                taxes.setMasterId(mdCostApproach.getId());
                taxes.setCreator(commonService.thisUserAccount());
                costApproachTaxesDao.addCostApproachTaxes(taxes);
            }
        }

    }
}
