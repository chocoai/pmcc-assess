package com.copower.pmcc.assess.controller.project;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportGroup;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.generate.GenerateReportInfoService;
import com.copower.pmcc.assess.service.project.generate.GenerateReportService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by kings on 2018-5-23.
 * 出具报告
 */
@RestController
@RequestMapping(value = "/generateReport", name = "出具报告")
public class ProjectReportController {
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private GenerateReportService generateReportService;
    @Autowired
    private GenerateReportInfoService generateReportGenerationService;
    @Autowired
    private BaseService baseService;
    private final String error = "生成报告,或者出具报告";

//    @PostMapping(value = "/generate", name = "生成报告")
//    public HttpResult generate(String ids, String fomData) {
//        //生成报告信息
//        try {
//            GenerateReportInfo generateReportGeneration = JSONObject.parseObject(fomData, GenerateReportInfo.class);
//            generateReportService.createReportWord(ids, generateReportGeneration);
//            return HttpResult.newCorrectResult(200, generateReportGeneration);
//        } catch (Exception e) {
//            baseService.writeExceptionInfo(e, error);
//            return HttpResult.newErrorResult(500, e.getMessage());
//        }
//    }

    @PostMapping(value = "/generate", name = "生成报告")
    public HttpResult generate(String ids, String group,String info) {
        //生成报告信息
        try {
            GenerateReportInfo generateReportInfo = JSONObject.parseObject(info, GenerateReportInfo.class);
            GenerateReportGroup reportGroup = JSONObject.parseObject(group, GenerateReportGroup.class);
            generateReportService.createReportWord(ids, generateReportInfo ,reportGroup);
            return HttpResult.newCorrectResult(200, generateReportInfo);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, error);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }


    @PostMapping(value = "/resultSheetReportNew", name = "生成单一的结果集")
    public HttpResult resultSheetReportNew(String fieldsName, String tableName, Integer projectId) {
        //生成单一的结果集
        try {
            generateReportService.resultSheetReportNew(fieldsName, tableName, projectId);
            return HttpResult.newCorrectResult(200, "success");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "生成单一的结果集");
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @GetMapping(value = "/getGenerateReportGeneration", name = "获取报告替换数据")
    public HttpResult getGenerateReportGeneration(Integer areaGroupId, Integer projectPlanId) {
        GenerateReportInfo generateReportGeneration = null;
        try {
            generateReportGeneration = generateReportGenerationService.getGenerateReportInfoByAreaGroupId(areaGroupId, projectPlanId);
            return HttpResult.newCorrectResult(200, generateReportGeneration);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, error);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @PostMapping(value = "/saveGenerateReportInfo", name = "修改数据")
    public HttpResult saveGenerateReportInfo(String fomData) {
        try {
            List<GenerateReportInfo> list = JSONObject.parseArray(fomData, GenerateReportInfo.class);
            if (CollectionUtils.isNotEmpty(list)) {
                for (GenerateReportInfo generateReportGeneration : list) {
                    generateReportGenerationService.saveGenerateReportInfo(generateReportGeneration);
                }
            }
            return HttpResult.newCorrectResult(200, list);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, error);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/viewResultSheetReport/{projectId}", name = "生成单一的结果集 view", method = {RequestMethod.GET})
    public ModelAndView viewResultSheetReport(@PathVariable(name = "projectId", required = true) Integer projectId) {
        String view = "/project/stageGenerate/viewResultSheetReport";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        modelAndView.addObject(StringUtils.uncapitalize(ProjectInfo.class.getSimpleName()), projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectId)));
        return modelAndView;
    }

}
