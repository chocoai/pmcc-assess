package com.copower.pmcc.assess.controller.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanVo;
import com.copower.pmcc.assess.dto.output.project.ProjectReviewScoreItemVo;
import com.copower.pmcc.assess.dto.output.project.ProjectSpotCheckItemScoreVo;
import com.copower.pmcc.assess.dto.output.project.ProjectSpotCheckVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectSpotCheckService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/projectSpotCheck")
public class ProjectSpotCheckController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProjectSpotCheckService projectSpotCheckService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private ProjectInfoService projectInfoService;

    @RequestMapping(value = "/index", name = "项目抽查信息页面", method = {RequestMethod.GET})
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/assessment/spotCheckIndex");
        modelAndView.addObject("companyId", publicService.getCurrentCompany().getCompanyId());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getProjectSpotCheckList", name = "获取抽查记录数据列表")
    public BootstrapTableVo getProjectSpotCheckList() {
        return projectSpotCheckService.getProjectSpotCheckList();
    }

    @ResponseBody
    @PostMapping(value = "/saveSpotCheck", name = "保存抽查主表")
    public HttpResult saveSpotCheck(String formData) {
        try {
            ProjectSpotCheck spotCheck = JSON.parseObject(formData, ProjectSpotCheck.class);
            projectSpotCheckService.saveSpotCheck(spotCheck);
            return HttpResult.newCorrectResult(spotCheck);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "/apply", name = "申请页面")
    public ModelAndView apply(Integer spotId) throws BusinessException {
        String boxKey = baseParameterService.getBaseParameter(BaseParameterEnum.PROJECT_SPOT_CHECK_PROCESS_KEY);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxKey);
        if (boxReDto == null || boxReDto.getId() == null) {
            throw new BusinessException("流程模型参数未配置!");
        }
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assessment/spotCheckApply", boxReDto.getId());

        List<KeyValueDto> statusEnumList = ProjectStatusEnum.getProjectStatusEnumList(ProjectStatusEnum.NORMAL.getKey(),
                ProjectStatusEnum.FINISH.getKey(), ProjectStatusEnum.CLOSE.getKey(), ProjectStatusEnum.DRAFT.getKey());
        modelAndView.addObject("statusEnumList", statusEnumList);
        //委托目的
        List<BaseDataDic> entrustPurposeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE);
        modelAndView.addObject("entrustPurposeList", entrustPurposeList);
        //贷款类型
        List<BaseDataDic> loanTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_LOAN_TYPE);
        modelAndView.addObject("loanTypeList", loanTypeList);
        modelAndView.addObject("companyId", publicService.getCurrentCompany().getCompanyId());
        List<KeyValueDto> keyValueDtoList = baseProjectClassifyService.getProjectInitClassify();
        modelAndView.addObject("projectCategoryList", keyValueDtoList);

        modelAndView.addObject("projectSpotCheck", projectSpotCheckService.getSpotCheckVoById(spotId));
        return modelAndView;
    }

    @RequestMapping(value = "/getSpotCheckItemScoreVoListByPlanId", name = "获取该阶段下的数据", method = RequestMethod.GET)
    @ResponseBody
    public BootstrapTableVo getSpotCheckItemScoreVoListByPlanId(Integer itemId, Integer planId) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        List<ProjectSpotCheckItemScoreVo> checkItemScoreVos = projectSpotCheckService.getSpotCheckItemScoreVoListByPlanId(itemId, planId);
        checkItemScoreVos = CollectionUtils.isEmpty(checkItemScoreVos) ? Lists.newArrayList() : checkItemScoreVos;
        bootstrapTableVo.setRows(checkItemScoreVos);
        bootstrapTableVo.setTotal((long) checkItemScoreVos.size());
        return bootstrapTableVo;
    }

    @RequestMapping(value = "/edit", name = "返回修改页面")
    public ModelAndView edit(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assessment/spotCheckApply", processInsId, boxId, taskId, agentUserAccount);
        ProjectSpotCheck projectSpotCheck = projectSpotCheckService.getSpotCheckByProcessInsId(processInsId);
        modelAndView.addObject("projectSpotCheck", projectSpotCheckService.getSpotCheckVo(projectSpotCheck));
        return modelAndView;
    }

    @RequestMapping(value = "/approval", name = "审核页面")
    public ModelAndView approval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assessment/spotCheckApproval", processInsId, boxId, taskId, agentUserAccount);
        ProjectSpotCheck projectSpotCheck = projectSpotCheckService.getSpotCheckByProcessInsId(processInsId);
        modelAndView.addObject("projectSpotCheck", projectSpotCheckService.getSpotCheckVo(projectSpotCheck));
        return modelAndView;
    }

    @RequestMapping(value = "/detail", name = "详情页面")
    public ModelAndView detail(String processInsId, Integer boxId, Integer spotId) throws BusinessException {
        ModelAndView modelAndView= null;
        if (StringUtils.isNotBlank(processInsId)) {
            modelAndView = processControllerComponent.baseFormModelAndView("/project/assessment/spotCheckDetail", processInsId, boxId, "-1", "");
            ProjectSpotCheck projectSpotCheck = projectSpotCheckService.getSpotCheckByProcessInsId(processInsId);
            modelAndView.addObject("projectSpotCheck", projectSpotCheckService.getSpotCheckVo(projectSpotCheck));
        } else if (spotId != null) {
            String boxName = baseParameterService.getBaseParameter(BaseParameterEnum.PROJECT_SPOT_CHECK_PROCESS_KEY);
            BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
            ProjectSpotCheckVo projectSpotCheckVo = projectSpotCheckService.getSpotCheckVoById(spotId);
            modelAndView = processControllerComponent.baseFormModelAndView("/project/assessment/spotCheckDetail", projectSpotCheckVo.getProcessInsId(), boxReDto.getId(), "-1", "");
            modelAndView.addObject("projectSpotCheck", projectSpotCheckVo);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/projectSpotModify", name = "项目抽查填写页面", method = {RequestMethod.GET})
    public ModelAndView projectSpotModify(Integer projectId) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/assessment/spotCheckProjectModify");
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        modelAndView.addObject("projectInfo", projectInfoService.getSimpleProjectInfoVo(projectInfo));
        List<ProjectPlanVo> projectPlanList = projectInfoService.getProjectPlanList(projectId);
        modelAndView.addObject("projectPlanList", projectPlanList);
        return modelAndView;
    }

    @RequestMapping(value = "/projectSpotDetail", name = "项目抽查信息页面", method = {RequestMethod.GET})
    public ModelAndView projectSpotDetail(Integer projectId) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/assessment/spotCheckProjectDetail");
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        modelAndView.addObject("projectInfo", projectInfoService.getSimpleProjectInfoVo(projectInfo));
        List<ProjectPlanVo> projectPlanList = projectInfoService.getProjectPlanList(projectId);
        modelAndView.addObject("projectPlanList", projectPlanList);
        return modelAndView;
    }

    @ResponseBody
    @PostMapping(value = "/selectProject", name = "选择项目")
    public HttpResult selectProject(String projectIds, Integer spotId) {
        try {
            projectSpotCheckService.selectProject(projectIds, spotId);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getProjectSpotCheckItemList", name = "获取抽查项目数据列表")
    public BootstrapTableVo getProjectSpotCheckItemList(Integer spotId) {
        return projectSpotCheckService.getProjectSpotCheckItemList(spotId);
    }

    @ResponseBody
    @PostMapping(value = "/applyCommit", name = "申请提交")
    public HttpResult applyCommit(String formData) {
        try {
            projectSpotCheckService.applyCommit(formData);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @PostMapping(value = "/editCommit", name = "返回修改提交")
    public HttpResult editCommit(String formData, ApprovalModelDto approvalModelDto) {
        try {
            ProjectSpotCheck spotCheck = JSON.parseObject(formData, ProjectSpotCheck.class);
            projectSpotCheckService.saveSpotCheck(spotCheck);
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(value = "/approvalCommit", name = "审批提交")
    @ResponseBody
    public HttpResult approvalCommit(ApprovalModelDto approvalModelDto) {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
            return HttpResult.newCorrectResult();
        } catch (BpmException e) {
            logger.error("提交审批失败", e);
            return HttpResult.newErrorResult(e);
        }
    }

    @PostMapping(value = "/saveSpotCheckScore", name = "保存抽查得分数据")
    @ResponseBody
    public HttpResult saveSpotCheckScore(String formData) {
        try {
            ProjectSpotCheckItemScoreVo projectSpotCheckScore = JSON.parseObject(formData, ProjectSpotCheckItemScoreVo.class);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("保存抽查明细数据失败", e);
            return HttpResult.newErrorResult(e);
        }
    }

    @GetMapping(value = "/getRuningSpotCheckList", name = "获取运行中批次")
    @ResponseBody
    public HttpResult getRuningSpotCheckList() {
        try {
            return HttpResult.newCorrectResult(projectSpotCheckService.getRuningSpotCheckList());
        } catch (Exception e) {
            logger.error("获取运行中批次失败", e);
            return HttpResult.newErrorResult(e);
        }
    }
}
