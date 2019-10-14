package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPhaseVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanVo;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.base.Objects;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kings on 2018-8-23.
 */
@Controller
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
    private ProjectPlanDao projectPlanDao;

    @ResponseBody
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

    @ResponseBody
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

    @ResponseBody
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

    @ResponseBody
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

    @ResponseBody
    @PostMapping(name = "获取数据", value = "/getProjectPlanDetailsById")
    public HttpResult replyProjectPlanDetails(Integer id) {
        try {
            ProjectPlanDetailsVo projectPlanDetailsVo = projectPlanDetailsService.getPlanDetailListByProjectPlanDetailId(id);
            return HttpResult.newCorrectResult(projectPlanDetailsVo);
        } catch (Exception e) {
            logger.error("获取数据", e);
            return HttpResult.newErrorResult("获取数据异常");
        }
    }

    @RequestMapping(value = "/projectTraceMenu", name = "单个项目模块菜单")
    public ModelAndView projectTraceMenu(Integer projectId) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/projectTraceMenu/projectDetails");
        setProjectTraceMenuParams(projectId, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/projectTraceProjectInfo", name = "项目模块 项目信息")
    public ModelAndView projectTraceProjectInfo(Integer projectId, String key) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/projectTraceMenu/projectTraceProjectInfo");
        setProjectTraceMenuParams(projectId, modelAndView);
        modelAndView.addObject("showTableId", key);
        return modelAndView;
    }

    @RequestMapping(value = "/projectTraceProjectDeclare", name = "项目模块 资产申报")
    public ModelAndView projectTraceProjectDeclare(Integer projectId) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/projectTraceMenu/projectTraceProjectDeclare");
        setProjectTraceMenuParams(projectId, modelAndView);
        ProjectInfoVo projectInfoVo = new ProjectInfoVo();
        modelAndView.getModel().forEach((s, o) -> {
            if (Objects.equal(StringUtils.uncapitalize(ProjectInfo.class.getSimpleName()), s)) {
                BeanUtils.copyProperties(o, projectInfoVo);
            }
        });
        setProjectTraceMenuCoreParams(projectPhaseService.getProjectPhaseVo(projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.ASSET_DECLARE, projectInfoVo.getProjectCategoryId())), projectInfoVo, modelAndView);
        return modelAndView;
    }

    /**
     * 模块参数设置 菜单参数
     *
     * @param projectId
     * @param modelAndView
     */
    private void setProjectTraceMenuParams(Integer projectId, ModelAndView modelAndView) {
        if (projectId == null) {
            return;
        }
        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectId));
        if (projectInfoVo == null) {
            return;
        }
        modelAndView.addObject(StringUtils.uncapitalize(ProjectInfo.class.getSimpleName()), projectInfoVo);
    }

    /**
     * 阶段参数
     *
     * @param projectPhase
     * @param projectInfoVo
     * @param modelAndView
     */
    private void setProjectTraceMenuCoreParams(ProjectPhaseVo projectPhase, ProjectInfoVo projectInfoVo, ModelAndView modelAndView) {
        if (projectPhase == null){
            return;
        }
        List<ProjectPlan> projectPlans = projectPlanDao.getProjectPlanList2(projectInfoVo.getId(),projectPhase.getWorkStageId(),projectInfoVo.getProjectCategoryId()) ;
        if (CollectionUtils.isEmpty(projectPlans)){
            return;
        }
        ProjectPlanVo projectPlanItem = projectInfoService.getProjectPlanItem(projectPlans.get(0).getId());
        modelAndView.addObject(StringUtils.uncapitalize(ProjectPlan.class.getSimpleName()), projectPlanItem);
        modelAndView.addObject(StringUtils.uncapitalize(ProjectPhase.class.getSimpleName()), projectPhase);
    }


}
