package com.copower.pmcc.assess.controller.project.xlx;


import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxReportIndividual;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.xlx.ProjectXlxReportIndividualService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * Created by zch on 2019-9-18.
 * xlx-生成报告单独事项
 */
@RestController
@RequestMapping(value = "/projectXlxReportIndividual")
public class ProjectXlxReportIndividualController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectXlxReportIndividualService projectXlxReportIndividualService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private BaseService baseService;

    @RequestMapping(value = "/view", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/customer/xlx/reportIndividual/projectXlxReportIndividualView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        //获取到类型 类别 范围
        List<KeyValueDto> keyValueDtoList = baseProjectClassifyService.getProjectInitClassify();
        modelAndView.addObject("keyValueDtoList", keyValueDtoList);
        return modelAndView;
    }

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
