package com.copower.pmcc.assess.controller.baisc;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicApplyTypeEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicDataHandleEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.controller.BaseController;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.ZtreeDto;
import com.copower.pmcc.assess.dto.input.basic.BasicFormClassifyDto;
import com.copower.pmcc.assess.dto.input.basic.BasicFormClassifyParamDto;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateVo;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseVo;
import com.copower.pmcc.assess.dto.output.project.survey.BasicApplyBatchDetailVo;
import com.copower.pmcc.assess.proxy.face.BasicEntityAbstract;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.chks.AssessmentPerformanceService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kings on 2018-10-24.
 * 案例基础数据
 */
@Controller
@RequestMapping("/basicApplyBatch")
public class BasicApplyBatchController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private PublicBasicService publicBasicService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private BasicEstateStreetInfoService basicEstateStreetInfoService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private DeclareRecordService declareRecordService;

    @RequestMapping(value = "/applyEdit", name = "返回修改页面", method = RequestMethod.GET)
    public ModelAndView basicApplyEdit(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicBatchApplyIndex", processInsId, boxId, taskId, agentUserAccount);
        modelAndView.addObject("formClassifyList", basicApplyBatchService.getFormClassifyList());
        modelAndView.addObject("examineFormTypeList", surveyCommonService.getExamineFormTypeList());
        try {
            BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByProcessInsId(processInsId);
            modelAndView.addObject("applyBatch", applyBatch);
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/initBasicApplyBatchCase", method = RequestMethod.POST)
    public HttpResult initBasicApplyBatchCase(BasicApplyBatch basicApplyBatch) {
        try {
            basicApplyBatchService.initBasicApplyBatchInfo(basicApplyBatch);
            return HttpResult.newCorrectResult(basicApplyBatch);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "/applyAgain", name = "继续申请", method = RequestMethod.GET)
    public ModelAndView applyAgain(Integer id) {
        final String boxName = baseParameterService.getParameterValues(BaseParameterEnum.CASE_BASE_INFO_BATCH_APPLY_KEY.getParameterKey());
        Integer boxId = bpmRpcBoxService.getBoxIdByBoxName(boxName);
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicBatchApplyIndex", "0", boxId, "0", "");
        try {
            BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchById(id);
            modelAndView.addObject("applyBatch", basicApplyBatch);
            modelAndView.addObject("formClassifyList", basicApplyBatchService.getFormClassifyList());
            modelAndView.addObject("examineFormTypeList", surveyCommonService.getExamineFormTypeList());
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
        }
        return modelAndView;
    }

    /**
     * 新增楼盘时获取树
     * 初始化treeByPlanDetailsId
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getBatchApplyTree", method = RequestMethod.GET)
    public List<ZtreeDto> getBatchApplyTree(Integer basicApplyBatchId, Boolean showTag, Boolean bisDetail) throws Exception {
        if (showTag == null) {
            showTag = false;
        }
        if (bisDetail == null) {
            bisDetail = false;
        }
        return basicApplyBatchService.getZtreeDto(basicApplyBatchId, showTag, bisDetail);
    }

    /**
     * 获取楼盘下案例结构
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCaseEstateZtreeDtos", method = RequestMethod.GET)
    public List<ZtreeDto> getCaseEstateZtreeDtos(Integer caseBatchApplyId) {
        return basicApplyBatchService.getEstateZtreeById(caseBatchApplyId);
    }

    @ResponseBody
    @RequestMapping(value = "/initCaseEstateZtree", name = "初始化支撑新增获取升级案例结构", method = RequestMethod.POST)
    public HttpResult initCaseEstateZtree(Integer applyBatchId, String nodesJson) {
        try {
            BasicApplyBatchDetail batchDetail = basicApplyBatchService.initCaseEstateZtree(applyBatchId, nodesJson);
            return HttpResult.newCorrectResult(batchDetail);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("初始化支撑新增获取升级案例结构异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/upgradeCase", name = "案例升级", method = RequestMethod.POST)
    public HttpResult upgradeCase(Integer applyBatchId, Integer pid, String nodeJson) {
        try {
            BasicApplyBatchDetail batchDetail = basicApplyBatchService.upgradeCase(applyBatchId, pid, nodeJson);
            return HttpResult.newCorrectResult(batchDetail);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("案例升级异常");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/basicApplyBatchSubmit", name = "数据申请 提交", method = RequestMethod.POST)
    public HttpResult basicApplyBatchSubmit(Integer id) {
        try {
            //发起流程
            basicApplyBatchService.processStartSubmit(id);
        } catch (BusinessException e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("案例申请提交异常");
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/basicApplyBatchSurveySubmit", name = "查勘案例申请 提交", method = RequestMethod.POST)
    public HttpResult basicApplyBatchSurveySubmit(Integer sourceApplyBatchId, String zTreeData) {
        try {
            basicApplyBatchService.processSurveySubmit(sourceApplyBatchId, zTreeData);
        } catch (BusinessException e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("案例申请提交异常");
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/approval", name = "审批页面", method = RequestMethod.GET)
    public ModelAndView basicApplyApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicBatchApplyApproval", processInsId, boxId, taskId, agentUserAccount);
        modelAndView.addObject("formClassifyList", basicApplyBatchService.getFormClassifyList());
        modelAndView.addObject("examineFormTypeList", surveyCommonService.getExamineFormTypeList());
        try {
            BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByProcessInsId(processInsId);
            modelAndView.addObject("applyBatch", applyBatch);
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/detail", name = "详情页面", method = RequestMethod.GET)
    public ModelAndView basicApplyBatchDetail(String processInsId, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicBatchApplyApproval", processInsId, boxId, "-1", null);
        modelAndView.addObject("formClassifyList", basicApplyBatchService.getFormClassifyList());
        modelAndView.addObject("examineFormTypeList", surveyCommonService.getExamineFormTypeList());
        try {
            BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByProcessInsId(processInsId);
            modelAndView.addObject("applyBatch", applyBatch);
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/basicApprovalSubmit", name = "审批页面 提交")
    public HttpResult basicApprovalSubmit(ApprovalModelDto approvalModelDto) {
        try {
            basicApplyBatchService.processApprovalSubmit(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult(e1);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/editSubmit", name = "返回修改 提交")
    public HttpResult basicEditSubmit(ApprovalModelDto approvalModelDto) {
        try {
            basicApplyBatchService.processEditSubmit(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("返回修改提交异常");
        }
    }

    @RequestMapping(value = "/basicBatchAppDraftListView", name = "草稿页面", method = {RequestMethod.GET})
    public ModelAndView basicAppListView() {
        String view = "/basic/basicBatchAppDraftListView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicBatchApply", name = "删除草稿数据")
    public HttpResult deleteBasicBatchApply(Integer id) {
        try {
            basicApplyBatchService.deleteBasicBatchApply(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("删除草稿数据异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicAppBatchDraftList", name = "获取草稿数据列表", method = {RequestMethod.GET})
    public BootstrapTableVo getBasicAppBatchDraftList(String estateName) throws Exception {
        return basicApplyBatchService.getBootstrapTableVo(estateName);
    }

    @RequestMapping(value = "/draftDetail", name = "草稿详情页面", method = RequestMethod.GET)
    public ModelAndView basicApplyBatchDetail(Integer id) {
        String boxName = baseParameterService.getParameterValues(BaseParameterEnum.CASE_BASE_INFO_BATCH_APPLY_KEY.getParameterKey());
        Integer boxId = bpmRpcBoxService.getBoxIdByBoxName(boxName);
        BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchById(id);
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicBatchApplyApproval", applyBatch.getProcessInsId(), boxId, "-1", null);
        modelAndView.addObject("applyBatch", applyBatch);
        modelAndView.addObject("formClassifyList", basicApplyBatchService.getFormClassifyList());
        modelAndView.addObject("examineFormTypeList", surveyCommonService.getExamineFormTypeList());

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/saveApplyDraftInfo", name = "保存", method = {RequestMethod.POST})
    public HttpResult saveApplyDraftInfo(BasicApplyBatch basicApplyBatch) {
        try {
            basicApplyBatch.setDraftFlag(true);
            basicApplyBatchService.saveBasicApplyBatch(basicApplyBatch);
            return HttpResult.newCorrectResult(basicApplyBatch);
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("保存数据异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/recordBatchApply", name = "批量申请", method = {RequestMethod.POST})
    public HttpResult recordBatchApply(String province, String city, String estateName) {
        try {
            Integer basicBatchId = basicApplyBatchService.recordBatchApply(province, city, estateName);
            return HttpResult.newCorrectResult(basicBatchId);
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("批量申请异常");
        }
    }


    //-----------------------------------------------案例批量申请


    /**
     * 初始化treeByPlanDetailsId
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getTreeByPlanDetailsId", method = RequestMethod.GET)
    public List<ZtreeDto> getTreeByPlanDetailsId(Integer planDetailsId) throws Exception {
        BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByPlanDetailsId(planDetailsId);
        return basicApplyBatchService.getZtreeDto(applyBatch.getId(), false, false);
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicApplyBatchDetailList", method = RequestMethod.GET)
    public HttpResult getBasicApplyBatchDetailList(BasicApplyBatchDetail basicApplyBatchDetail) throws Exception {
        try {
            return HttpResult.newCorrectResult(basicApplyBatchDetailService.getBasicApplyBatchDetailList(basicApplyBatchDetail));
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveApplyInfo", name = "保存", method = {RequestMethod.POST})
    public HttpResult saveApplyInfo(BasicApplyBatch basicApplyBatch) {
        try {
            basicApplyBatchService.saveBasicApplyBatch(basicApplyBatch);
            return HttpResult.newCorrectResult(basicApplyBatch);
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("保存数据异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveItemData", name = "保存数据", method = {RequestMethod.POST})
    public HttpResult saveItemData(String formData, Integer planDetailsId) {
        try {
            BasicApplyBatchDetail basicApplyBatchDetail = JSON.parseObject(formData, BasicApplyBatchDetail.class);
            BasicApplyBatchDetail batchDetail = basicApplyBatchDetailService.saveAndUpdateComplete(basicApplyBatchDetail, planDetailsId);
            BasicApplyBatchDetailVo basicApplyBatchDetailVo = basicApplyBatchDetailService.getBasicApplyBatchDetailVo(batchDetail);
            return HttpResult.newCorrectResult(basicApplyBatchDetailVo);
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("保存数据异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/updateItemData", name = "修改数据", method = {RequestMethod.POST})
    public HttpResult updateItemData(String formData, Integer planDetailsId) {
        try {
            BasicApplyBatchDetail tempData = JSON.parseObject(formData, BasicApplyBatchDetail.class);
            //更新name与权证id
            BasicApplyBatchDetail oldData = basicApplyBatchDetailService.getDataById(tempData.getId());
            oldData.setName(tempData.getName());
            BasicApplyBatchDetailVo vo = basicApplyBatchDetailService.getBasicApplyBatchDetailVo(basicApplyBatchDetailService.saveAndUpdateComplete(oldData, planDetailsId));
            return HttpResult.newCorrectResult(vo);
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("保存数据异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveDeclareRecord", name = "写入权证", method = {RequestMethod.POST})
    public HttpResult saveDeclareRecord(Integer id, Integer declareRecordId, String declareRecordName, Integer planDetailsId) {
        try {
            List<BasicApplyBatchDetail> houseBatchDetailList = basicApplyBatchDetailService.getHouseBatchDetailList(id);
            BasicApplyBatchDetailVo vo = null;
            if (CollectionUtils.isNotEmpty(houseBatchDetailList)) {
                BasicApplyBatchDetail basicApplyBatchDetail = houseBatchDetailList.get(0);
                basicApplyBatchDetail.setDeclareRecordId(declareRecordId);
                basicApplyBatchDetail.setDeclareRecordName(declareRecordName);
                vo = basicApplyBatchDetailService.getBasicApplyBatchDetailVo(basicApplyBatchDetailService.saveAndUpdateComplete(basicApplyBatchDetail, planDetailsId));
            }
            return HttpResult.newCorrectResult(vo);
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("保存数据异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/batchSaveDeclareRecordId", name = "批量设置权证", method = {RequestMethod.POST})
    public HttpResult batchSaveDeclareRecordId(String applyBatchDetailIds, String declareRecordIds, String declareRecordName) {
        try {
            basicApplyBatchDetailService.batchSaveDeclareRecordId(applyBatchDetailIds, declareRecordIds, declareRecordName);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("批量设置权证异常");
        }
    }

    /**
     * 删除操作手册信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteDetail", name = "删除一条明细", method = RequestMethod.POST)
    public HttpResult deleteDetail(Integer id) throws Exception {
        try {
            basicApplyBatchDetailService.deleteBasicApplyBatchDetail(id);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    /**
     * 批量删除操作手册信息
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/batchDeleteDetail", name = "批量删除明细", method = RequestMethod.POST)
    public HttpResult batchDeleteDetail(String ids) throws Exception {
        try {
            basicApplyBatchDetailService.batchDeleteBasicApplyBatchDetail(ids);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    /**
     * 获取和编辑
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAndEditDetail", name = "获取和编辑", method = RequestMethod.GET)
    public HttpResult getAndEditDetail(Integer id) {
        return HttpResult.newCorrectResult(basicApplyBatchDetailService.getBasicApplyBatchDetailVo(basicApplyBatchDetailService.getDataById(id)));
    }

    @RequestMapping(value = "/informationEdit", name = "信息填写", method = RequestMethod.GET)
    public ModelAndView informationEdit(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        BasicFormClassifyEnum estateTaggingTypeEnum = BasicFormClassifyEnum.getEnumByKey(basicFormClassifyParamDto.getTbType());
        BasicEntityAbstract entityAbstract = publicBasicService.getServiceBeanByKey(estateTaggingTypeEnum.getKey());
        ModelAndView modelAndView = entityAbstract.getEditModelAndView(basicFormClassifyParamDto);
        modelAndView.addObject("planDetailsId", basicFormClassifyParamDto.getPlanDetailsId());
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(basicFormClassifyParamDto.getPlanDetailsId());
        if (projectPlanDetails != null) {
            ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
            ProjectPhase caseStudyExtend = projectPhaseService.getCacheProjectPhaseByCategoryId(AssessPhaseKeyConstant.CASE_STUDY_EXTEND, projectInfo.getProjectCategoryId());
            if (caseStudyExtend.getId().equals(projectPlanDetails.getProjectPhaseId())) {
                modelAndView.addObject("projectPhase", "caseStudyExtend");
            }
        }
        BasicApplyBatchDetail applyBatchDetail = basicApplyBatchDetailService.getDataById(basicFormClassifyParamDto.getApplyBatchDetailId());
        BasicApplyBatchDetail parentBatchDetail = basicApplyBatchDetailService.getDataById(applyBatchDetail.getPid());
        if (parentBatchDetail != null) {
            modelAndView.addObject("quoteId", parentBatchDetail.getQuoteId());
        }
        modelAndView.addObject("applyBatchDetail", applyBatchDetail);
        modelAndView.addObject("tbType", basicFormClassifyParamDto.getTbType());
        modelAndView.addObject("formClassify", basicFormClassifyParamDto.getFormClassify());
        modelAndView.addObject("tbId", basicFormClassifyParamDto.getTbId());
        modelAndView.addObject("formType", BasicApplyTypeEnum.getEnumById(basicFormClassifyParamDto.getFormType()).getKey());
        if (basicFormClassifyParamDto.getApplyBatchId() != null) {
            BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchById(basicFormClassifyParamDto.getApplyBatchId());
            modelAndView.addObject(StringUtils.uncapitalize(BasicApplyBatch.class.getSimpleName()), basicApplyBatch);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/informationPhoneEdit", name = "信息手机端填写", method = RequestMethod.GET)
    public ModelAndView informationPhoneEdit(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        BasicFormClassifyEnum estateTaggingTypeEnum = BasicFormClassifyEnum.getEnumByKey(basicFormClassifyParamDto.getTbType());
        BasicEntityAbstract entityAbstract = publicBasicService.getServiceBeanByKey(estateTaggingTypeEnum.getKey());
        ModelAndView modelAndView = entityAbstract.getPhoneEditModelAndView(basicFormClassifyParamDto);
        modelAndView.addObject("planDetailsId", basicFormClassifyParamDto.getPlanDetailsId());
        modelAndView.addObject("tbType", basicFormClassifyParamDto.getTbType());
        modelAndView.addObject("formClassify", basicFormClassifyParamDto.getFormClassify());
        modelAndView.addObject("tbId", basicFormClassifyParamDto.getTbId());
        modelAndView.addObject("formType", BasicApplyTypeEnum.getEnumById(basicFormClassifyParamDto.getFormType()).getKey());
        if (basicFormClassifyParamDto.getApplyBatchId() != null) {
            BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchById(basicFormClassifyParamDto.getApplyBatchId());
            modelAndView.addObject(StringUtils.uncapitalize(BasicApplyBatch.class.getSimpleName()), basicApplyBatch);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/informationPhoneTree", name = "信息手机端树", method = RequestMethod.GET)
    public ModelAndView informationPhoneTree(Integer applyBatchId) throws Exception {
        String view = "/project/stageSurvey/taskSurveyExplorePhoneIndex";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchById(applyBatchId);
        modelAndView.addObject(StringUtils.uncapitalize(BasicApplyBatch.class.getSimpleName()), basicApplyBatch);
        modelAndView.addObject("userAccount", processControllerComponent.getThisUser());
        if (basicApplyBatch.getId() != null && basicApplyBatch.getId() != 0) {
            ProjectPlanDetails projectPlanDetailsById = projectPlanDetailsService.getProjectPlanDetailsById(basicApplyBatch.getPlanDetailsId());
            if (projectPlanDetailsById != null) {
                modelAndView.addObject(StringUtils.uncapitalize(ProjectPlanDetails.class.getSimpleName()), projectPlanDetailsById);
            }
            if (basicApplyBatch.getProjectId() != null && basicApplyBatch.getProjectId() != 0) {
                ProjectInfo projectInfo = projectInfoService.getProjectInfoById(basicApplyBatch.getProjectId());
                if (projectInfo != null) {
                    modelAndView.addObject(StringUtils.uncapitalize(ProjectInfo.class.getSimpleName()), projectInfoService.getSimpleProjectInfoVo(projectInfo));
                }
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/informationDetail", name = "信息详情", method = RequestMethod.GET)
    public ModelAndView informationDetail(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        BasicFormClassifyEnum estateTaggingTypeEnum = BasicFormClassifyEnum.getEnumByKey(basicFormClassifyParamDto.getTbType());
        BasicEntityAbstract entityAbstract = publicBasicService.getServiceBeanByKey(estateTaggingTypeEnum.getKey());
        ModelAndView detailsModelAndView = entityAbstract.getDetailModelAndView(basicFormClassifyParamDto);
        detailsModelAndView.addObject("formType", BasicApplyTypeEnum.getEnumById(basicFormClassifyParamDto.getFormType()).getKey());
        detailsModelAndView.addObject("isHistory", basicFormClassifyParamDto.getHistory());
        detailsModelAndView.addObject("tbType", basicFormClassifyParamDto.getTbType());
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(basicFormClassifyParamDto.getPlanDetailsId());
        if (projectPlanDetails != null) {
            ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
            ProjectPhase caseStudyExtend = projectPhaseService.getCacheProjectPhaseByCategoryId(AssessPhaseKeyConstant.CASE_STUDY_EXTEND, projectInfo.getProjectCategoryId());
            if (caseStudyExtend.getId().equals(projectPlanDetails.getProjectPhaseId())) {
                detailsModelAndView.addObject("projectPhase", "caseStudyExtend");
            }
        }
        BasicApplyBatchDetail applyBatchDetail = basicApplyBatchDetailService.getDataById(basicFormClassifyParamDto.getApplyBatchDetailId());
        detailsModelAndView.addObject("applyBatchDetail", applyBatchDetail);
        return detailsModelAndView;
    }

    @RequestMapping(value = "/informationView", name = "信息详情", method = RequestMethod.GET)
    public ModelAndView informationView(Integer applyBatchDetailId, Boolean isHistory) throws Exception {
        BasicFormClassifyParamDto dto = new BasicFormClassifyParamDto();
        BasicApplyBatchDetail applyBatchDetail = basicApplyBatchDetailService.getDataById(applyBatchDetailId);
        BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchById(applyBatchDetail.getApplyBatchId());
        dto.setHistory(isHistory);
        dto.setApplyBatchId(basicApplyBatch.getId());
        dto.setTbType(applyBatchDetail.getType());
        dto.setTbId(applyBatchDetail.getTableId());
        dto.setTableName(applyBatchDetail.getTableName());
        dto.setApplyBatchDetailId(applyBatchDetail.getId());
        dto.setPlanDetailsId(basicApplyBatch.getPlanDetailsId());
        dto.setFormClassify(basicApplyBatch.getClassify());
        dto.setFormType(basicApplyBatch.getType());
        ModelAndView modelAndView = informationDetail(dto);
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/saveDraft", name = "保存楼盘等")
    public HttpResult saveDraft(String formData, String formClassify, Integer planDetailsId) {
        try {
            basicApplyBatchService.saveDraft(formData, formClassify, planDetailsId);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("保存信息异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/paste", name = "粘贴", method = {RequestMethod.POST})
    public HttpResult paste(Integer sourceBatchDetailId, Integer targeBatchDetailId) {
        try {
            basicApplyBatchService.pasteExamineInfo(sourceBatchDetailId, targeBatchDetailId);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("保存数据异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deepCopy", name = "深复制", method = {RequestMethod.POST})
    public HttpResult deepCopy(Integer sourceBatchDetailId) {
        try {
            basicApplyBatchService.deepCopy(sourceBatchDetailId);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("保存数据异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveBasicApplyBatch", method = {RequestMethod.POST}, name = "保存")
    public HttpResult saveBasicApplyBatch(String formData) {
        try {
            BasicApplyBatch applyBatch = JSON.parseObject(formData, BasicApplyBatch.class);
            basicApplyBatchService.saveBasicApplyBatch(applyBatch);
            return HttpResult.newCorrectResult(applyBatch);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBatchAllById", method = {RequestMethod.POST}, name = "数据清理")
    public HttpResult deleteBatchAllById(Integer applyBatchId) {
        try {
            basicApplyBatchService.deleteBatchAllById(applyBatchId);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("数据清理异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/initBasicApplyBatchInfo", method = {RequestMethod.POST}, name = "初始化")
    public HttpResult initBasicApplyBatchInfo(BasicApplyBatch applyBatch) {
        try {
            BasicApplyBatch basicApplyBatch = basicApplyBatchService.initBasicApplyBatchInfo(applyBatch);
            return HttpResult.newCorrectResult(basicApplyBatch);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/editBuildingStatus", method = {RequestMethod.POST}, name = "保存")
    public HttpResult editBuildingStatus(String formData) {
        try {
            BasicApplyBatch applyBatch = JSON.parseObject(formData, BasicApplyBatch.class);
            //修改权证建筑状态
            if (applyBatch.getBuildingStatus() != null) {
                ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetailsById(applyBatch.getPlanDetailsId());
                if (planDetails != null && planDetails.getDeclareRecordId() != null) {
                    DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(planDetails.getDeclareRecordId());
                    declareRecord.setBuildingStatus(applyBatch.getBuildingStatus());
                    declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
                }
            }
            basicApplyBatchService.saveBasicApplyBatch(applyBatch);
            return HttpResult.newCorrectResult(applyBatch);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/getTableTypeList", method = RequestMethod.GET, name = "获取表单类型")
    public HttpResult getTableTypeList(String type) throws Exception {
        try {
            BasicEntityAbstract entityAbstract = publicBasicService.getServiceBeanByKey(type);
            List<BasicFormClassifyEnum> enumList = entityAbstract.getLowerFormClassifyList();
            List<BasicFormClassifyDto> dtos = Lists.newArrayList();
            if (!CollectionUtils.isEmpty(enumList)) {
                for (BasicFormClassifyEnum item : enumList) {
                    BasicFormClassifyDto dto = new BasicFormClassifyDto();
                    dto.setTableName(item.getTableName());
                    dto.setValue(item.getValue());
                    dto.setKey(item.getKey());
                    dtos.add(dto);
                }
            }
            return HttpResult.newCorrectResult(dtos);
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/referenceEstate", method = RequestMethod.POST, name = "引用其他楼盘")
    public HttpResult referenceEstate(Integer referenceId, Integer basicApplyBatchId, Integer planDetailsId) throws Exception {
        try {
            basicApplyBatchService.referenceEstate(referenceId, basicApplyBatchId, planDetailsId);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicAlternativeSurveyList", name = "取得提供引用查勘楼盘信息", method = RequestMethod.GET)
    public BootstrapTableVo getBasicAlternativeSurveyList(Integer planDetailsId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
        List<BasicApplyBatchDetail> estateBatchDetailList = basicApplyBatchDetailService.getExploreEstateList(projectPlanDetails);
        vo.setRows(CollectionUtils.isEmpty(estateBatchDetailList) ? new ArrayList<BasicApplyBatchDetail>() : estateBatchDetailList);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicApplyBatchDetailListByType", method = RequestMethod.POST, name = "获取数据")
    public HttpResult getBasicApplyBatchDetailListByType(String type, Integer basicApplyBatchId, Integer pid) {
        try {
            List<BasicApplyBatchDetail> batchDetailList = basicApplyBatchDetailService.getBasicApplyBatchDetailListByType(type, basicApplyBatchId, pid, true);
            return HttpResult.newCorrectResult(batchDetailList);
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseEstateListByName", name = "获取楼盘案例数据", method = RequestMethod.GET)
    public BootstrapTableVo getCaseEstateListByName(String province, String city, String name) {
        return basicApplyBatchService.getCaseEstateListByName(province, city, name);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseOtherListByName", name = "获取楼栋、单元、房屋案例数据", method = RequestMethod.GET)
    public BootstrapTableVo getCaseOtherListByName(Integer quoteId, String name) {
        return basicApplyBatchDetailService.getCaseOtherListByName(quoteId, name);
    }

    @ResponseBody
    @RequestMapping(value = "/quoteCaseOther", name = "引用案列数据", method = {RequestMethod.GET})
    public HttpResult quoteCaseOther(Integer sourceApplyBatchDetailId, Integer targetApplyBatchDetailId) {
        try {
            BasicApplyBatchDetail sourceApplyBatchDetail = basicApplyBatchDetailService.getDataById(sourceApplyBatchDetailId);
            BasicApplyBatchDetail targeApplyBatchDetail = basicApplyBatchDetailService.getDataById(targetApplyBatchDetailId);
            BasicEntityAbstract entityAbstract = publicBasicService.getServiceBeanByKey(sourceApplyBatchDetail.getType());
            Object o = entityAbstract.copyBasicEntity(sourceApplyBatchDetail.getTableId(), targeApplyBatchDetail.getTableId(), true);
            if (targeApplyBatchDetail != null) {
                targeApplyBatchDetail.setQuoteId(sourceApplyBatchDetail.getId());
                targeApplyBatchDetail.setModifyType(BasicDataHandleEnum.REFERENCE.getKey());
                basicApplyBatchDetailService.saveBasicApplyBatchDetail(targeApplyBatchDetail);
            }
            return HttpResult.newCorrectResult(o);
        } catch (Exception e) {
            logger.error(String.format("引用案列数据:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/autoCompleteCaseOther", method = {RequestMethod.GET}, name = "楼栋、单元、房屋信息自动补全")
    public HttpResult autoCompleteCaseOther(Integer quoteId, String name) {
        try {
            List<BasicApplyBatchDetail> detailList = basicApplyBatchDetailService.getBasicApplyBatchDetailList(quoteId, name);
            return HttpResult.newCorrectResult(detailList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/isNeedReferenceEstate", method = {RequestMethod.GET}, name = "判断是否应该引用楼盘数据")
    public HttpResult isNeedReferenceEstate(Integer projectId, Integer batchDetailId, String province, String city, String estateName) {
        try {
            Boolean aBoolean = basicApplyBatchService.isNeedReferenceEstate(projectId, batchDetailId, province, city, estateName);
            return HttpResult.newCorrectResult(aBoolean);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("异常");
        }
    }
}
