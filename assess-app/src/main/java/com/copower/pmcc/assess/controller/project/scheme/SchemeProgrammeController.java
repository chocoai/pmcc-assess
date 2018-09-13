package com.copower.pmcc.assess.controller.project.scheme;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBestUseDescriptionService;
import com.copower.pmcc.assess.service.data.EvaluationMethodService;
import com.copower.pmcc.assess.service.data.EvaluationThinkingService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by kings on 2018-9-12.
 */
@Controller
@RequestMapping("/schemeProgramme")
public class SchemeProgrammeController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private DataBestUseDescriptionService dataBestUseDescriptionService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private EvaluationMethodService evaluationMethodService;
    @Autowired
    private EvaluationThinkingService evaluationThinkingService;
    @Autowired
    private ProjectInfoService projectInfoService;

    @RequestMapping(value = "/index", name = "方案设置视图", method = {RequestMethod.GET})
    public ModelAndView index(Integer projectId) {
        String view = "/project/stageScheme/programmeIndex";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        List<SchemeAreaGroup> areaGroups = declareRecordService.getSchemeGroup(projectId);//获取分组信息
        modelAndView.addObject("areaGroups",areaGroups);
        modelAndView.addObject("bestUseList", dataBestUseDescriptionService.dataBestUseDescriptionList());
        modelAndView.addObject("setUseList", baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_PRACTICAL_USE));
        modelAndView.addObject("dataDicMethodList", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD));
        modelAndView.addObject("evaluationMethodMap", evaluationMethodService.getEvaluationMethodMap());
        modelAndView.addObject("evaluationThinkingMap", evaluationThinkingService.getEvaluationThinkingMap());
        ProjectInfoVo projectInfoVo = projectInfoService.getProjectInfoVoView(projectInfoService.getProjectInfoById(projectId));
        modelAndView.addObject("projectInfo", projectInfoVo);
        return modelAndView;
    }
}
