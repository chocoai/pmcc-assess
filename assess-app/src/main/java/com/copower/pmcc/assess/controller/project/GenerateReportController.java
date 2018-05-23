package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.dal.entity.GenerateReport;
import com.copower.pmcc.assess.dto.input.project.GenerateReportApplyDto;
import com.copower.pmcc.assess.service.project.GenerateReportService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kings on 2018-5-23.
 */
@Controller
@RequestMapping(value = "generateReport",name = "出具报告")
public class GenerateReportController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private GenerateReportService generateReportService;

    @ResponseBody
    @RequestMapping(value = "/generate", name = "生成", method = RequestMethod.POST)
    public HttpResult generate(GenerateReportApplyDto generateReportApplyDto) {
        try {
           //生成报告信息

            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
}
