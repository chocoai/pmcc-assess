package com.copower.pmcc.assess.controller.project.xlx;


import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxReportIndividual;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.xlx.ProjectXlxReportIndividualService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by zch on 2019-9-18.
 * xlx-生成报告单独事项
 */
@RestController
@RequestMapping(value = "/projectXlxReportIndividual")
public class ProjectXlxReportIndividualController {
    private final String errorName = "xlx-生成报告单独事项";

    @Autowired
    private ProjectXlxReportIndividualService projectXlxReportIndividualService;
    @Autowired
    private BaseService baseService;


    @GetMapping(value = "/getBootstrapTableVo")
    public BootstrapTableVo getBootstrapTableVo(ProjectXlxReportIndividual oo) {
        return projectXlxReportIndividualService.getBootstrapTableVo(oo);
    }

    @GetMapping(value = "/getReportNumber")
    public HttpResult getReportNumber(Integer projectCategory, Integer projectId) {
        try {
            return HttpResult.newCorrectResult(200, projectXlxReportIndividualService.getReportNumber(projectCategory, projectId));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500,e) ;
        }
    }

}
