package com.copower.pmcc.assess.controller.project.survey;

import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kings on 2018-7-6.
 */
@Controller
@RequestMapping("/surveySceneExplore")
public class SurveySceneExploreController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private SurveyCommonService surveyCommonService;


    @ResponseBody
    @GetMapping(name = "获取查勘任务列表", value = "/getSceneExploreList")
    public BootstrapTableVo getSceneExploreList(Integer projectId, Integer pid) {
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        List<ProjectPlanDetailsVo> detailsVos = surveyCommonService.getPlanTaskExamineList(pid);
        if (CollectionUtils.isNotEmpty(detailsVos)) {
            ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.ASSET_INVENTORY, projectInfo.getProjectCategoryId());
            if (projectPhase != null) {
                detailsVos = detailsVos.stream().filter(o -> !projectPhase.getId().equals(o.getProjectPhaseId())).collect(Collectors.toList());
            }
        }
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(detailsVos) ? Lists.newArrayList() : detailsVos);
        bootstrapTableVo.setTotal((long) detailsVos.size());
        return bootstrapTableVo;
    }



}
