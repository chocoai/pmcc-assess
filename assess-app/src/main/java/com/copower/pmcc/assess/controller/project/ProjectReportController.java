package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.dal.basis.entity.SchemeReportGeneration;
import com.copower.pmcc.assess.dto.input.project.generate.SchemeReportGenerationDto;
import com.copower.pmcc.assess.service.project.generate.GenerateReportService;
import com.copower.pmcc.assess.service.project.generate.SchemeReportGenerationService;
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
    private SchemeReportGenerationService schemeReportGenerationService;

    @PostMapping(value = "/generate", name = "生产报告模板")
    public HttpResult generate(String ids, SchemeReportGenerationDto schemeReportGenerationDto) {
        //生成报告信息
        try {
            SchemeReportGeneration schemeReportGeneration = new SchemeReportGeneration();
            BeanUtils.copyProperties(schemeReportGenerationDto, schemeReportGeneration);
            generateReportService.createReportWord(ids, schemeReportGeneration);
            return HttpResult.newCorrectResult(200, schemeReportGeneration);
        } catch (Exception e) {
            logger.error("生产报告异常!", e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/getSchemeReportGeneration",name = "获取报告替换数据")
    public HttpResult getSchemeReportGeneration(Integer areaGroupId, Integer projectPlanId) {
        SchemeReportGeneration schemeReportGeneration = null;
        try {
            schemeReportGeneration = schemeReportGenerationService.getSchemeReportGenerationByAreaGroupId(areaGroupId, projectPlanId);
            return HttpResult.newCorrectResult(200, schemeReportGeneration);
        } catch (Exception e) {
            return HttpResult.newErrorResult(500, e);
        }
    }

}
