package com.copower.pmcc.assess.controller.project.scheme;

import com.copower.pmcc.assess.dto.output.project.scheme.ProjectTaskLiquidationAnalysisVo;
import com.copower.pmcc.assess.service.project.scheme.ProjectTaskLiquidationAnalysisService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kings on 2018-10-25.
 */
@Controller
@RequestMapping("/projectTaskLiquidationAnalysis")
public class ProjectTaskLiquidationAnalysisController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProjectTaskLiquidationAnalysisService projectTaskLiquidationAnalysisService;


    @ResponseBody
    @RequestMapping(value = "/getTaxAllocation", name = "获取率费配置", method = RequestMethod.POST)
    public HttpResult changeFunctionContent(Integer projectPlanDetailsId, Integer judgeObjectId, Integer mainId) {
        List<ProjectTaskLiquidationAnalysisVo> vos = new ArrayList<>();
        if ("0".equals(mainId)) {
            vos = projectTaskLiquidationAnalysisService.getTaxAllocation(projectPlanDetailsId, judgeObjectId);
            return HttpResult.newCorrectResult(vos);
        } else {
            vos = projectTaskLiquidationAnalysisService.editPageTaxAllocation(mainId);
            return HttpResult.newCorrectResult(vos);
        }
    }
}
