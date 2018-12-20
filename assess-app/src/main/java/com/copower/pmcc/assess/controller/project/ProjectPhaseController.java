package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseAssist;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.dto.output.project.ProjectPhaseVo;
import com.copower.pmcc.assess.dto.output.project.ProjectWorkStageVo;
import com.copower.pmcc.assess.service.base.BaseAssistService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.bpm.api.dto.model.BoxReActivityDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.PublicRole;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxRoleUserService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0
 * @date: 2017/08/30 14:53
 */
@Controller
@RequestMapping("/ProjectPhase")
public class ProjectPhaseController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private BpmRpcBoxRoleUserService bpmRpcBoxRoleUserService;
    @Autowired
    private BaseAssistService baseAssistService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;

    @RequestMapping(value = "/view", name = "阶段事项页面视图", method = RequestMethod.GET)
    public ModelAndView view() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/stage/ProjectPhase");
        //取工作内容具体项的关系表单信息
        List<BaseAssist> sysBaseFormListStage = baseAssistService.getBaseAssist(BaseConstant.ASSESS_BASE_ASSIST_STAGE, "");
        modelAndView.addObject("sysBaseFormListStage", sysBaseFormListStage);
        List<BaseAssist> sysBaseFormListMatter = baseAssistService.getBaseAssist(BaseConstant.ASSESS_BASE_ASSIST_MATTER, "");
        modelAndView.addObject("sysBaseFormListMatter", sysBaseFormListMatter);
        List<PublicRole> publicRoleConfig = bpmRpcBoxRoleUserService.getPublicRoleConfig();
        modelAndView.addObject("publicRole", publicRoleConfig);
        modelAndView.addObject("projectClassList", baseProjectClassifyService.getCacheProjectClassifyListByPid(0));
        return modelAndView;
    }

    //填装数据
    private void fillRelateData(List<ProjectPhaseVo> projectPhaseVos) {
        if (CollectionUtils.isEmpty(projectPhaseVos))
            return;
        for (ProjectPhaseVo projectPhaseVo : projectPhaseVos) {
            //获取模型名称
            if (StringUtils.isNotBlank(projectPhaseVo.getBoxName())) {
                Integer boxIdByBoxName = bpmRpcBoxService.getBoxIdByBoxName(projectPhaseVo.getBoxName());
                BoxReDto boxReInfoByBoxId = bpmRpcBoxService.getBoxReInfoByBoxId(boxIdByBoxName);
                if (boxReInfoByBoxId != null) {
                    projectPhaseVo.setBoxCnName(boxReInfoByBoxId.getCnName());
                }
            }


        }
    }

    private List<ProjectWorkStageVo> convertProjectWorkStage(List<ProjectWorkStage> projectWorkStages) {
        if (CollectionUtils.isEmpty(projectWorkStages))
            return null;

        List<ProjectWorkStageVo> projectWorkStageVos = LangUtils.transform(projectWorkStages, input -> {
            ProjectWorkStageVo projectWorkStageVo = new ProjectWorkStageVo();
            //拷贝共有的属性
            BeanUtils.copyProperties(input, projectWorkStageVo);

            //获取计划模型名称
            if (StringUtils.isNotBlank(input.getBoxName())) {
                Integer boxIdByBoxName = bpmRpcBoxService.getBoxIdByBoxName(input.getBoxName());
                BoxReDto boxReInfoByBoxId = bpmRpcBoxService.getBoxReInfoByBoxId(boxIdByBoxName);
                if (boxReInfoByBoxId != null) {
                    projectWorkStageVo.setBoxCnName(boxReInfoByBoxId.getCnName());
                }
            }
            //获取复核模型名称
            if (StringUtils.isNotBlank(input.getReviewBoxName())) {
                if (StringUtils.isNotBlank(input.getReviewBoxName())) {
                    Integer boxIdByBoxName = bpmRpcBoxService.getBoxIdByBoxName(input.getBoxName());
                    BoxReDto boxReInfoByBoxId = bpmRpcBoxService.getBoxReInfoByBoxId(boxIdByBoxName);
                    if (boxReInfoByBoxId != null) {
                        projectWorkStageVo.setBoxCnName(boxReInfoByBoxId.getCnName());
                    }
                }
            }


            return projectWorkStageVo;
        });

        return projectWorkStageVos;
    }

    @RequestMapping(value = "/list", name = "工作事项列表数据", method = RequestMethod.GET)
    @ResponseBody
    public BootstrapTableVo projectPhaseList(Integer typeId, Integer categoryId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ProjectPhaseVo> projectPhaseVos = projectPhaseService.queryProjectPhaseByCategory(typeId, categoryId, requestBaseParam.getSearch());
        fillRelateData(projectPhaseVos); //填装没有的数据
        return new BootstrapTableVo(page.getTotal(), projectPhaseVos);
    }

    @RequestMapping(value = "/projectPhaseAllList", name = "工作事项全部列表数据", method = RequestMethod.GET)
    @ResponseBody
    public HttpResult projectPhaseAllList(Integer typeId, Integer categoryId) {
        List<ProjectPhaseVo> projectPhaseVos = projectPhaseService.queryProjectPhaseByCategory(typeId, categoryId, "");
        return HttpResult.newCorrectResult(projectPhaseVos);
    }


    @RequestMapping(value = "/saveProjectPhase", name = "保存工作事项", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult saveProjectPhase(ProjectPhase projectPhase) {
        try {
            projectPhaseService.createOrUpdateProjectPhase(projectPhase);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/disableOrEnableProjectPhase", name = "禁用/启用工作事项", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult disableOrEnableProjectPhase(Integer id, Boolean bisEnable) {
        try {
            ProjectPhase projectPhase = new ProjectPhase();
            projectPhase.setId(id);
            projectPhase.setBisEnable(bisEnable);
            projectPhaseService.updateProjectPhaseById(projectPhase);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/logicDeleteProjectPhase", name = "逻辑删除工作事项", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult logicDeleteProjectPhase(Integer id) {
        try {
            ProjectPhase projectPhase = new ProjectPhase();
            projectPhase.setId(id);
            projectPhase.setBisEnable(Boolean.FALSE);
            projectPhaseService.updateProjectPhaseById(projectPhase);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/workStage", name = "获取项目阶段数据", method = RequestMethod.GET)
    @ResponseBody
    public HttpResult workStage(Integer id, Integer typeId) {
        List<ProjectWorkStage> workStages = Lists.newArrayList();
        try {
            if (id == null || id.intValue() == 0) {
                workStages = projectWorkStageService.queryWorkStageByClassIdAndTypeId(typeId, false);
            } else {
                ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(id);
                if (projectWorkStage != null)
                    workStages.add(projectWorkStage);
            }

        } catch (Exception e) {
            return HttpResult.newCorrectResult(e.getMessage());
        }

        List<ProjectWorkStageVo> projectWorkStageVos = convertProjectWorkStage(workStages);
        return HttpResult.newCorrectResult(projectWorkStageVos);
    }


    @RequestMapping(value = "/saveWorkStage", name = "保存项目阶段", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult saveWorkStage(ProjectWorkStage projectWorkStage) {
        try {
            projectWorkStageService.createOrUpdateWorkStage(projectWorkStage);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/enableWorkStage", name = "启用禁用项目阶段", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult enableWorkStage(Integer id, Boolean enable) {
        try {
            ProjectWorkStage projectWorkStage = new ProjectWorkStage();
            projectWorkStage.setId(id);
            projectWorkStage.setBisEnable(enable);
            projectWorkStageService.updateWorkStage(projectWorkStage);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

        return HttpResult.newCorrectResult();
    }


    @RequestMapping(value = "/getBoxReActivityByPhaseId", name = "根据工作事项编号，取得配置的模型节点", method = RequestMethod.GET)
    @ResponseBody
    public HttpResult getBoxReActivityByPhaseId(Integer id) {
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(id);
        if (StringUtils.isBlank(projectPhase.getBoxName())) {
            return HttpResult.newErrorResult("");
        }

        Integer boxIdByBoxName = bpmRpcBoxService.getBoxIdByBoxName(projectPhase.getBoxName());
        List<BoxReActivityDto> boxReActivityByBoxId = bpmRpcBoxService.getBoxReActivityByBoxId(boxIdByBoxName);
        return HttpResult.newCorrectResult(boxReActivityByBoxId);

    }


}
