package com.copower.pmcc.assess.controller.project;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPhaseVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.assess.service.project.generate.ProjectPlanGenerateAssist;
import com.copower.pmcc.assess.service.project.plan.execute.PlanComplieExecute;
import com.copower.pmcc.assess.service.project.plan.execute.PlanDefaultExecute;
import com.copower.pmcc.assess.service.project.plan.execute.PlanSurveyExecute;
import com.copower.pmcc.assess.service.project.scheme.ProjectPlanSchemeAssist;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
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
    private ProjectPlanDao projectPlanDao;
    @Autowired
    private PublicService publicService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;

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

    @PostMapping(name = "项目菜单任务分派 添加任务", value = "/saveProjectStagePlan")
    public HttpResult saveProjectStagePlan(String formData) {
        try {
            ProjectPlanDetails projectPlanDetails = JSONObject.parseObject(formData,ProjectPlanDetails.class) ;
            projectPlanDetailsService.saveProjectStagePlan(projectPlanDetails);
            return HttpResult.newCorrectResult(projectPlanDetails);
        } catch (Exception e) {
            logger.error("任务分派 添加任务", e);
            return HttpResult.newErrorResult("任务分派 添加任务异常");
        }
    }

    @RequestMapping(value = "/projectTraceMenu", name = "进入项目模块菜单")
    public ModelAndView projectTraceMenu(Integer projectId) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/projectTraceMenu/projectDetails");
        setBaseMenuParams(projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectId)), modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/openProjectMenuLink/{projectId}/{workStageId}", name = "进入单个项目模块")
    public ModelAndView openProjectMenuLink(@PathVariable(name = "projectId", required = true) Integer projectId, @PathVariable(name = "workStageId", required = true) Integer workStageId) {
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(workStageId);
        //默认为项目立项信息
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/projectTraceMenu/projectTraceProjectInfo");
        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectId));
        setBaseMenuParams(projectInfoVo, modelAndView);
        setProjectTraceMenuParams(projectInfoVo, projectWorkStage, modelAndView);
        BaseProjectClassify baseProjectClassify = baseProjectClassifyService.getCacheProjectClassifyById(projectInfoVo.getProjectCategoryId());
        HashMap<String, String> viewMap = getViewUrlMap(baseProjectClassify.getFieldName(), projectWorkStage);
        if (!viewMap.isEmpty()) {
            viewMap.forEach((s, s2) -> {
                if (Objects.equal(s, projectWorkStage.getStageForm())) {
                    modelAndView.setViewName(s2);
                }
            });
        }
        return modelAndView;
    }

    /**
     * 模块参数设置 菜单参数
     *
     * @param projectInfoVo
     * @param modelAndView
     */
    private void setBaseMenuParams(ProjectInfoVo projectInfoVo, ModelAndView modelAndView) {
        ProjectWorkStage select = new ProjectWorkStage();
        select.setProjectClassId(projectInfoVo.getProjectClassId());
        select.setProjectTypeId(projectInfoVo.getProjectTypeId());
//        select.setProjectCategoryId(projectInfoVo.getProjectCategoryId());
        List<ProjectWorkStage> workStageList = projectWorkStageService.getProjectWorkStageList(select);
        modelAndView.addObject("workStageList", workStageList);
        modelAndView.addObject(StringUtils.uncapitalize(ProjectInfo.class.getSimpleName()), projectInfoVo);
        modelAndView.addObject("companyId", publicService.getCurrentCompany().getCompanyId());
    }

    /**
     * 设置参数
     *
     * @param projectWorkStage
     * @param projectInfoVo
     * @param modelAndView
     */
    private void setProjectTraceMenuParams(ProjectInfoVo projectInfoVo, ProjectWorkStage projectWorkStage, ModelAndView modelAndView) {
        modelAndView.addObject(StringUtils.uncapitalize(SysUserDto.class.getSimpleName()), processControllerComponent.getThisUserInfo());
        List<ProjectPlan> projectPlans = projectPlanDao.getProjectPlanList2(projectInfoVo.getId(), projectWorkStage.getId(), projectInfoVo.getProjectCategoryId());
        if (CollectionUtils.isEmpty(projectPlans)) {
            return;
        }
        ProjectPlanVo projectPlanItem = projectInfoService.getProjectPlanItem(projectPlans.get(0).getId());
        modelAndView.addObject(StringUtils.uncapitalize(ProjectPlan.class.getSimpleName()), projectPlanItem);
        List<ProjectPhaseVo> projectPhaseVoList = Lists.newArrayList();
        ProjectPhase select = new ProjectPhase();
        select.setProjectCategoryId(projectInfoVo.getProjectCategoryId());
        select.setProjectTypeId(projectInfoVo.getProjectTypeId());
        select.setProjectClassId(projectInfoVo.getProjectClassId());
        select.setWorkStageId(projectWorkStage.getId());
        List<ProjectPhase> projectPhaseList = projectPhaseService.getProjectPhaseList(select);
        if (CollectionUtils.isNotEmpty(projectPhaseList)){
            projectPhaseList.forEach(projectPhase -> projectPhaseVoList.add(projectPhaseService.getProjectPhaseVo(projectPhase)));
        }
        modelAndView.addObject("projectPhaseVoList", projectPhaseVoList);
        modelAndView.addObject(StringUtils.uncapitalize(ProjectWorkStage.class.getSimpleName()), projectWorkStage);
    }

    /**
     * 配置视图
     *
     * @param key
     * @param projectWorkStage
     * @return
     */
    private HashMap<String, String> getViewUrlMap(String key, ProjectWorkStage projectWorkStage) {
        //com.copower.pmcc.assess.service.project.ProjectPlanDetailsService.updateExecuteUser() 这需要注释特殊线包含的代码块
        HashMap<String, String> map = new HashMap<>(6);
        //房产,简单房产都使用这个view
        if (Objects.equal(AssessProjectClassifyConstant.SINGLE_HOUSE_PROPERTY_CERTIFICATE_TYPE_SIMPLE, key) || Objects.equal(AssessProjectClassifyConstant.SINGLE_HOUSE_PROPERTY_CERTIFICATE_TYPE, key)) {
            //申报
            if (Objects.equal(projectWorkStage.getStageForm(), StringUtils.uncapitalize(PlanDefaultExecute.class.getSimpleName()))) {
                map.put(projectWorkStage.getStageForm(), "/projectTraceMenu/house/projectTraceProjectDeclare");
            }
            //现场查勘
            if (Objects.equal(projectWorkStage.getStageForm(), StringUtils.uncapitalize(PlanSurveyExecute.class.getSimpleName()))) {
                map.put(projectWorkStage.getStageForm(), "/projectTraceMenu/house/projectTracePlanSurvey");
            }
            //评估方案计划
            if (Objects.equal(projectWorkStage.getStageForm(), StringUtils.uncapitalize(ProjectPlanSchemeAssist.class.getSimpleName()))) {
                map.put(projectWorkStage.getStageForm(), "/projectTraceMenu/house/projectWorkingPlan");
            }
            //报告编写
            if (Objects.equal(projectWorkStage.getStageForm(), StringUtils.uncapitalize(PlanComplieExecute.class.getSimpleName()))) {
                map.put(projectWorkStage.getStageForm(), "/projectTraceMenu/house/projectReportWriting");
            }
            //报告生成
            if (Objects.equal(projectWorkStage.getStageForm(), StringUtils.uncapitalize(ProjectPlanGenerateAssist.class.getSimpleName()))) {
                map.put(projectWorkStage.getStageForm(), "/projectTraceMenu/house/projectReportGeneration");
            }
        }
        return map;
    }


}
