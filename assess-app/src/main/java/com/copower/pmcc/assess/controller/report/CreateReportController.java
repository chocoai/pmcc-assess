package com.copower.pmcc.assess.controller.report;

import com.copower.pmcc.assess.service.base.BaseReportService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: zch
 * @Date: 2019/1/15 10:47
 * @Description:
 */
@RequestMapping(value = "/createReport")
@RestController
public class CreateReportController {

    @Autowired
    private BaseReportService baseReportService;

    @PostMapping(value = "/createReportWord",name = "生产报告模板并转为附件id返回")
    public HttpResult createReportWord(String ids,Integer projectPlanId,Integer areaId){
        try {
            Integer id = baseReportService.createReportWord(ids, projectPlanId,areaId);
            return HttpResult.newCorrectResult(200,id);
        } catch (Exception e) {
            return HttpResult.newErrorResult(500,e);
        }
    }

}
