package com.copower.pmcc.assess.controller.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanVo;
import com.copower.pmcc.assess.dto.output.project.ProjectReviewScoreItemVo;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectReviewScoreService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.BoxRuDto;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
@RequestMapping(value = "/projectReviewScore")
public class ProjectReviewScoreController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProjectReviewScoreService projectReviewScoreService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;

    @RequestMapping(value = "/apply", name = "申请页面")
    public ModelAndView apply(Integer projectId, Integer reviewId) throws BusinessException {
        String boxKey = baseParameterService.getBaseParameter(BaseParameterEnum.PROJECT_REVIEW_PROCESS_KEY);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxKey);
        if (boxReDto == null || boxReDto.getId() == null) {
            throw new BusinessException("流程模型参数未配置!");
        }
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assessment/reviewScoreApply", boxReDto.getId());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        modelAndView.addObject("projectInfo", projectInfoService.getSimpleProjectInfoVo(projectInfo));
        List<ProjectPlanVo> projectPlanList = projectInfoService.getProjectPlanList(projectId);
        modelAndView.addObject("projectPlanList", projectPlanList);
        ProjectReviewScore projectReviewScore = null;
        if (reviewId != null) {
            projectReviewScore = projectReviewScoreService.getReviewScoreById(reviewId);
        } else if (projectId != null) {
            projectReviewScore = projectReviewScoreService.getReviewScoreByProjectId(projectId);
        }
        if (projectReviewScore == null) {
            projectReviewScore = new ProjectReviewScore();
            projectReviewScore.setProjectId(projectId);
            projectReviewScore.setProjectName(projectInfo.getProjectName());
            projectReviewScore.setCreator(processControllerComponent.getThisUser());
            projectReviewScoreService.saveReviewScore(projectReviewScore);
        }
        modelAndView.addObject("projectReviewScore", projectReviewScore);
        return modelAndView;
    }

    @RequestMapping(value = "/getReviewScoreItemVoListByPlanId", name = "获取该阶段下的数据", method = RequestMethod.GET)
    @ResponseBody
    public BootstrapTableVo getReviewScoreItemVoListByPlanId(Integer reviewId, Integer planId) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        List<ProjectReviewScoreItemVo> scoreItemVos = projectReviewScoreService.getReviewScoreItemVoListByPlanId(reviewId, planId);
        scoreItemVos = CollectionUtils.isEmpty(scoreItemVos) ? Lists.newArrayList() : scoreItemVos;
        bootstrapTableVo.setRows(scoreItemVos);
        bootstrapTableVo.setTotal((long) scoreItemVos.size());
        return bootstrapTableVo;
    }

    @RequestMapping(value = "/edit", name = "返回修改页面")
    public ModelAndView edit(String processInsId, String taskId, Integer boxId, String agentUserAccount, Integer projectId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assessment/reviewScoreApply", processInsId, boxId, taskId, agentUserAccount);
        setModelViewParam(modelAndView, processInsId);
        return modelAndView;
    }

    @RequestMapping(value = "/approval", name = "审核页面")
    public ModelAndView approval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assessment/reviewScoreApproval", processInsId, boxId, taskId, agentUserAccount);
        setModelViewParam(modelAndView, processInsId);
        return modelAndView;
    }

    @RequestMapping(value = "/detail", name = "详情页面")
    public ModelAndView detail(String processInsId, Integer boxId) {
        if (boxId == null) {
            BoxRuDto boxRuDto = bpmRpcBoxService.getBoxRuByProcessInstId(processInsId);
            boxId = boxRuDto.getBoxId();
        }
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assessment/reviewScoreDetail", processInsId, boxId, "-1", "");
        setModelViewParam(modelAndView, processInsId);
        return modelAndView;
    }

    private void setModelViewParam(ModelAndView modelAndView, String processInsId) {
        ProjectReviewScore reviewScore = projectReviewScoreService.getReviewScoreByProcessInsId(processInsId);
        modelAndView.addObject("projectReviewScore", reviewScore);
        List<ProjectPlanVo> projectPlanList = projectInfoService.getProjectPlanList(reviewScore.getProjectId());
        modelAndView.addObject("projectPlanList", projectPlanList);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(reviewScore.getProjectId());
        modelAndView.addObject("projectInfo", projectInfoService.getSimpleProjectInfoVo(projectInfo));
    }

    @ResponseBody
    @RequestMapping(value = "/getHistroyList", name = "获取历史数据列表")
    public BootstrapTableVo getHistroyList(Integer reviewId, Integer projectPhaseId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ProjectReviewScoreItemVo> scoreItemList = projectReviewScoreService.getHistoryItemsByProjectPhaseId(reviewId, projectPhaseId);
        vo.setTotal(page.getTotal());
        vo.setRows(scoreItemList);
        return vo;
    }

    @ResponseBody
    @PostMapping(value = "/applyCommit", name = "申请提交")
    public HttpResult applyCommit(String formData) {
        try {
            ProjectReviewScore projectReviewScore = JSON.parseObject(formData, ProjectReviewScore.class);
            Integer projectId = projectReviewScore.getProjectId();
            Long count = projectReviewScoreService.getProjectReviewScoreCount(projectId);
            if (count > 0) {//一个项目只能提交一次
                return HttpResult.newErrorResult("请不要重复提交");
            }
            //验证时间、验证项目是否能考核
            if (!projectInfoService.chksValidInitDate(projectInfoService.getProjectInfoById(projectId))) {
                return HttpResult.newErrorResult("该项目不允许申请");
            }
            if (!projectInfoService.chksValidProject(projectId)) {
                return HttpResult.newErrorResult("该项目不允许申请");
            }
            projectReviewScoreService.applyCommit(projectId, projectReviewScore);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @PostMapping(value = "/editCommit", name = "返回修改提交")
    public HttpResult editCommit(ApprovalModelDto approvalModelDto, String formData) {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
            if (StringUtils.isNotBlank(formData)) {
                ProjectReviewScore projectReviewScore = JSON.parseObject(formData, ProjectReviewScore.class);
                projectReviewScoreService.saveReviewScore(projectReviewScore);
            }
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(value = "/approvalCommit", name = "审批提交")
    @ResponseBody
    public HttpResult approvalCommit(ApprovalModelDto approvalModelDto, String formData) {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
            if (StringUtils.isNotBlank(formData)) {
                ProjectReviewScore projectReviewScore = JSON.parseObject(formData, ProjectReviewScore.class);
                projectReviewScoreService.saveReviewScore(projectReviewScore);
            }
            return HttpResult.newCorrectResult();
        } catch (BpmException e) {
            logger.error("提交审批失败", e);
            return HttpResult.newErrorResult(e);
        }
    }

    @GetMapping(value = "/getReviewScoreByProjectId", name = "获取复核得分by项目id")
    @ResponseBody
    public HttpResult getReviewScoreByProjectId(Integer projectId) {
        try {
            ProjectReviewScore projectReviewScore = projectReviewScoreService.getReviewScoreByProjectId(projectId);
            return HttpResult.newCorrectResult(projectReviewScore);
        } catch (Exception e) {
            logger.error("获取复核得分by项目id失败", e);
            return HttpResult.newErrorResult(e);
        }
    }

    @PostMapping(value = "/saveReviewScoreItem", name = "保存复核工时明细数据")
    @ResponseBody
    public HttpResult saveReviewScoreItem(String formData) {
        try {
            ProjectReviewScoreItem reviewScoreItem = JSON.parseObject(formData, ProjectReviewScoreItem.class);
            projectReviewScoreService.saveReviewScoreItem(reviewScoreItem);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("保存复核工时明细数据失败", e);
            return HttpResult.newErrorResult(e);
        }
    }

    @GetMapping(value = "/getReviewScoreItemById", name = "获取数据id")
    @ResponseBody
    public HttpResult getReviewScoreItemById(Integer id) {
        try {
            ProjectReviewScoreItem reviewScoreItem = projectReviewScoreService.getReviewScoreItemById(id);
            return HttpResult.newCorrectResult(reviewScoreItem);
        } catch (Exception e) {
            logger.error("获取复核得分by项目id失败", e);
            return HttpResult.newErrorResult(e);
        }
    }
}
