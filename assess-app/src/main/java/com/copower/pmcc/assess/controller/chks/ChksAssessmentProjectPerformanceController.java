package com.copower.pmcc.assess.controller.chks;

import com.copower.pmcc.assess.service.chks.ChksAssessmentProjectPerformanceService;
import com.copower.pmcc.chks.api.dto.ChksBootstrapTableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zch on 2019-12-16.
 * 获取历史考核
 */
@RestController
@RequestMapping(value = "/chksAssessmentProjectPerformance")
public class ChksAssessmentProjectPerformanceController {

    @Autowired
    private ChksAssessmentProjectPerformanceService chksAssessmentProjectPerformanceService;

    @GetMapping(value = "/getHistoryChksData", name = "获取历史考核")
    public ChksBootstrapTableVo getHistoryChksData(String processInsId) {
        return chksAssessmentProjectPerformanceService.getHistoryChksData(processInsId);
    }
}

