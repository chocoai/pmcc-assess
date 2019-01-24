package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.service.project.generate.GenerateReportService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by kings on 2018-5-23.
 */
@RestController
@RequestMapping(value = "generateReport", name = "出具报告")
public class ProjectReportController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private GenerateReportService generateReportService;

    @PostMapping(value = "/generate", name = "生产报告模板并转为附件id返回")
    public HttpResult generate(String ids, Integer projectPlanId, Integer areaId,String HomeWorkEndTime,String InvestigationsStartDate,String InvestigationsEndDate,String reportIssuanceDate,String userAccountMember) {
        //生成报告信息
        try {
            Date HomeWorkEndTime_ = null;
            Date InvestigationsStartDate_ = null;
            Date InvestigationsEndDate_ = null;
            Date reportIssuanceDate_ = null;
            if (StringUtils.isNotBlank(HomeWorkEndTime)){
                HomeWorkEndTime_ = DateUtils.parse(HomeWorkEndTime);
            }
            if (StringUtils.isNotBlank(InvestigationsStartDate)){
                InvestigationsStartDate_ = DateUtils.parse(InvestigationsStartDate);
            }
            if (StringUtils.isNotBlank(InvestigationsEndDate)){
                InvestigationsEndDate_ = DateUtils.parse(InvestigationsEndDate);
            }
            if (StringUtils.isNotBlank(reportIssuanceDate)){
                reportIssuanceDate_ = DateUtils.parse(reportIssuanceDate);
            }
            Integer id = generateReportService.createReportWord(ids, projectPlanId, areaId,HomeWorkEndTime_,InvestigationsStartDate_,InvestigationsEndDate_,reportIssuanceDate_,userAccountMember);
            return HttpResult.newCorrectResult(200, id);
        } catch (Exception e) {
            logger.error("生产报告异常!", e);
            return HttpResult.newErrorResult(500, e);
        }
    }
}
