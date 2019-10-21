package com.copower.pmcc.assess.controller.project;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.document.DocumentTemplateService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kings on 2018-8-23.
 */
@RestController
@RequestMapping("/projectPlanDetails")
public class ProjectPlanDetailsController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private DocumentTemplateService documentTemplateService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @PostMapping(name = "重启任务", value = "/replyProjectPlanDetails")
    public HttpResult replyProjectPlanDetails(Integer planDetailsId, String formData) {
        try {
            ProjectPlanDetailsVo projectPlanDetailsVo = projectPlanDetailsService.replyProjectPlanDetails(planDetailsId, formData);
            return HttpResult.newCorrectResult(projectPlanDetailsService.getPlanDetailListByProjectPlanDetailId(projectPlanDetailsVo.getId()));
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            logger.error("重启任务", e);
            return HttpResult.newErrorResult("重启任务异常");
        }
    }

    @PostMapping(name = "调整责任人", value = "/updateExecuteUser")
    public HttpResult updateExecuteUser(Integer planDetailsId, String newExecuteUser) {
        try {
            ProjectPlanDetailsVo projectPlanDetailsVo = projectPlanDetailsService.updateExecuteUser(planDetailsId, newExecuteUser);
            return HttpResult.newCorrectResult(projectPlanDetailsVo);
        } catch (Exception e) {
            logger.error("调整责任人", e);
            return HttpResult.newErrorResult("调整责任人异常");
        }
    }

    @PostMapping(name = "批量调整责任人", value = "/batchUpdateExecuteUser")
    public HttpResult batchUpdateExecuteUser(String planDetailsIds, String newExecuteUser) {
        try {
            String[] split = planDetailsIds.split(",");
            List<String> strings = Arrays.asList(split);
            List<Integer> transform = LangUtils.transform(strings, p -> Integer.valueOf(p));
            projectPlanDetailsService.batchUpdateExecuteUser(transform, newExecuteUser);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("调整责任人", e);
            return HttpResult.newErrorResult("调整责任人异常");
        }
    }

    @PostMapping(name = "任务信息粘贴", value = "/taskPaste")
    public HttpResult taskPaste(Integer copyPlanDetailsId, Integer pastePlanDetailsId) {
        try {
            projectPlanDetailsService.taskPaste(copyPlanDetailsId, pastePlanDetailsId);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("项目详情粘贴数据", e);
            return HttpResult.newErrorResult("粘贴数据异常");
        }
    }

    @PostMapping(name = "获取数据", value = "/getProjectPlanDetailsById")
    public HttpResult getProjectPlanDetailsById(Integer id) {
        try {
            ProjectPlanDetailsVo projectPlanDetailsVo = projectPlanDetailsService.getPlanDetailListByProjectPlanDetailId(id);
            return HttpResult.newCorrectResult(projectPlanDetailsVo);
        } catch (Exception e) {
            logger.error("获取数据", e);
            return HttpResult.newErrorResult("获取数据异常");
        }
    }

    @PostMapping(name = "删除项目计划详情任务", value = "/deletePlanDetailsById")
    public HttpResult deletePlanDetailsById(Integer planDetailsId) {
        try {
            projectPlanDetailsService.deletePlanDetailsById(planDetailsId);
            return HttpResult.newCorrectResult("success");
        } catch (Exception e) {
            logger.error("删除项目计划详情任务", e);
            return HttpResult.newErrorResult("删除项目计划详情任务异常");
        }
    }

    @PostMapping(name = "项目菜单任务分派 添加任务", value = "/saveProjectStagePlan")
    public HttpResult saveProjectStagePlan(String formData) {
        try {
            ProjectPlanDetails projectPlanDetails = JSONObject.parseObject(formData, ProjectPlanDetails.class);
            projectPlanDetailsService.saveProjectStagePlan(projectPlanDetails);
            return HttpResult.newCorrectResult(projectPlanDetails);
        } catch (Exception e) {
            logger.error("任务分派 添加任务", e);
            return HttpResult.newErrorResult("任务分派 添加任务异常");
        }
    }

    @PostMapping(name = "发起任务(对任务分派添加的任务发起,更改状态)", value = "/initiateStagePlanTask")
    public HttpResult initiateStagePlanTask(Integer planId, Integer projectId) {
        try {
            projectPlanDetailsService.initiateStagePlanTask(planId, projectId);
            return HttpResult.newCorrectResult("success");
        } catch (Exception e) {
            logger.error("任务分派 添加任务", e);
            return HttpResult.newErrorResult("任务分派 添加任务异常");
        }
    }

    @PostMapping(name = "自动分派改阶段下的所有任务", value = "/autoStagePlanTask")
    public HttpResult autoStagePlanTask(Integer projectWorkStageId, Integer projectId) {
        try {
            projectPlanDetailsService.autoStagePlanTask(projectId, projectWorkStageId);
            return HttpResult.newCorrectResult("success");
        } catch (Exception e) {
            logger.error("自动分派改阶段下的所有任务", e);
            return HttpResult.newErrorResult("自动分派改阶段下的所有任务异常");
        }
    }
}
