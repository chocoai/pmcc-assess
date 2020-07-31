package com.copower.pmcc.assess.controller.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanVo;
import com.copower.pmcc.assess.dto.output.project.ProjectReviewScoreGroupVo;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.event.project.ProjectAssessmentBonusEvent;
import com.copower.pmcc.assess.service.project.ProjectAssessmentBonusService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.ProjectReviewScoreService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @Autowired
    private ProjectWorkStageService projectWorkStageService;

    @RequestMapping(value = "/apply", name = "申请页面")
    public ModelAndView apply(Integer projectId) throws BusinessException {
        String boxKey = baseParameterService.getBaseParameter(BaseParameterEnum.PROJECT_REVIEW_PROCESS_KEY);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxKey);
        if (boxReDto == null || boxReDto.getId() == null) {
            throw new BusinessException("流程模型参数未配置!");
        }
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assessment/reviewScoreApply", boxReDto.getId());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        modelAndView.addObject("projectInfo", projectInfoService.getSimpleProjectInfoVo(projectInfo));
        List<ProjectReviewScoreItem> scoreItems = Lists.newArrayList();
        List<ProjectPlanVo> projectPlanList = projectInfoService.getProjectPlanList(projectId);
        if (CollectionUtils.isNotEmpty(projectPlanList)) {
            for (ProjectPlanVo projectPlanVo : projectPlanList) {
                ProjectReviewScoreItem scoreItem = new ProjectReviewScoreItem();
                scoreItem.setPlanId(projectPlanVo.getId());
                scoreItem.setPlanName(projectPlanVo.getPlanName());
                ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlanVo.getWorkStageId());
                scoreItem.setStandardScore(projectWorkStage.getManagerReviewScore());
                scoreItem.setScore(projectWorkStage.getManagerReviewScore());
                scoreItems.add(scoreItem);
            }
        }
        modelAndView.addObject("projectReviewScoreItems", scoreItems);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", name = "返回修改页面")
    public ModelAndView edit(String processInsId, String taskId, Integer boxId, String agentUserAccount, Integer projectId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assessment/reviewScoreApply", processInsId, boxId, taskId, agentUserAccount);
        setModelViewParam(modelAndView,processInsId);
        return modelAndView;
    }

    @RequestMapping(value = "/approval", name = "审核页面")
    public ModelAndView approval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assessment/reviewScoreApproval", processInsId, boxId, taskId, agentUserAccount);
        setModelViewParam(modelAndView,processInsId);
        return modelAndView;
    }

    @RequestMapping(value = "/detail", name = "详情页面")
    public ModelAndView detail(String processInsId,  Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assessment/reviewScoreDetail", processInsId, boxId, "-1", "agentUserAccount");
        setModelViewParam(modelAndView,processInsId);
        return modelAndView;
    }

    private void setModelViewParam(ModelAndView modelAndView,String processInsId){
        ProjectReviewScore reviewScore = projectReviewScoreService.getReviewScoreByProcessInsId(processInsId);
        modelAndView.addObject("projectReviewScore", reviewScore);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(reviewScore.getProjectId());
        modelAndView.addObject("projectInfo", projectInfoService.getSimpleProjectInfoVo(projectInfo));
        ProjectReviewScoreGroupVo reviewScoreGroupVo = projectReviewScoreService.getEnableReviewScoreGroupByReviewId(reviewScore.getId());
        if(reviewScoreGroupVo!=null){
            modelAndView.addObject("projectReviewScoreItems", reviewScoreGroupVo.getReviewScoreItemList());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getHistroyList", name = "获取历史数据列表")
    public BootstrapTableVo getHistroyList(Integer reviewId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ProjectReviewScoreGroup> scoreItems = projectReviewScoreService.getHistoryReviewScoreItemsByReviewId(reviewId);
        vo.setTotal(page.getTotal());
        vo.setRows(scoreItems);
        return vo;
    }

    @ResponseBody
    @PostMapping(value = "/applyCommit", name = "申请提交")
    public HttpResult applyCommit(String formData, Integer projectId) {
        try {
            Long count = projectReviewScoreService.getProjectReviewScoreCount(projectId);
            if (count > 0) {//一个项目只能提交一次
                return HttpResult.newErrorResult("请不要重复提交");
            }
            projectReviewScoreService.applyCommit(formData, projectId);
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
            ProjectReviewScoreGroupVo projectReviewScoreGroup = JSON.parseObject(formData, ProjectReviewScoreGroupVo.class);
            projectReviewScoreService.addReviewScoreGroup(projectReviewScoreGroup);
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

    @PostMapping(value = "/saveReviewScoreGroup", name = "保存复核工时明细数据")
    @ResponseBody
    public HttpResult saveReviewScoreGroup(String formData) {
        try {
            ProjectReviewScoreGroupVo projectReviewScoreGroupVo = JSON.parseObject(formData, ProjectReviewScoreGroupVo.class);
            projectReviewScoreService.addReviewScoreGroup(projectReviewScoreGroupVo);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("保存复核工时明细数据失败", e);
            return HttpResult.newErrorResult(e);
        }
    }
}
