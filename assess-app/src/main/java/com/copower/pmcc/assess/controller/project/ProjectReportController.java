package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.dal.basis.entity.GenerateReportGeneration;
import com.copower.pmcc.assess.dto.input.project.generate.GenerateReportGenerationDto;
import com.copower.pmcc.assess.service.project.generate.GenerateReportService;
import com.copower.pmcc.assess.service.project.generate.GenerateReportGenerationService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kings on 2018-5-23.
 */
@RestController
@RequestMapping(value = "generateReport", name = "出具报告")
public class ProjectReportController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private GenerateReportService generateReportService;
    @Autowired
    private GenerateReportGenerationService generateReportGenerationService;

    @PostMapping(value = "/generate", name = "生成报告")
    public HttpResult generate(String ids, GenerateReportGenerationDto generateReportGenerationDto) {
        //生成报告信息
        try {
            GenerateReportGeneration generateReportGeneration = new GenerateReportGeneration();
            BeanUtils.copyProperties(generateReportGenerationDto, generateReportGeneration);
            generateReportService.createReportWord(ids, generateReportGeneration);
            return HttpResult.newCorrectResult(200, generateReportGeneration);
        } catch (Exception e) {
            logger.error("生产报告异常!", e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/getGenerateReportGeneration", name = "获取报告替换数据")
    public HttpResult getGenerateReportGeneration(Integer areaGroupId, Integer projectPlanId) {
        GenerateReportGeneration generateReportGeneration = null;
        try {
            generateReportGeneration = generateReportGenerationService.getGenerateReportGenerationByAreaGroupId(areaGroupId, projectPlanId);
            return HttpResult.newCorrectResult(200, generateReportGeneration);
        } catch (Exception e) {
            return HttpResult.newErrorResult(500, e);
        }
    }

}
