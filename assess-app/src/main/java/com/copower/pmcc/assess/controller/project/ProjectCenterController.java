package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.ProjectCenterService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/9/19
 * @time: 11:57
 */
@Controller
@RequestMapping(value = "/projectCenter")
public class ProjectCenterController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectCenterService projectCenterService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;

    @RequestMapping(value = "/index", name = "项目中心")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/projectCenter");

        Integer weekTaskCount = projectCenterService.getTheWeekTaskCount();
        Integer todayTaskCount = projectCenterService.getTodayTaskCount();
        Integer userJoinProjectCount = projectCenterService.getUserJoinProjectCount();
        modelAndView.addObject("weekTaskCount", weekTaskCount);
        modelAndView.addObject("todayTaskCount", todayTaskCount);
        modelAndView.addObject("userJoinProjectCount", userJoinProjectCount);
        return modelAndView;
    }


    @RequestMapping(value = "/projectProgress", name = "项目进度查看")
    public ModelAndView projectProgress() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/projectProgress");
        return modelAndView;
    }

    @RequestMapping(value = "/projectCalendar", name = "项目日历")
    public ModelAndView projectCalendar() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/projectCalendar");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getProjectProgress", name = "取得项目进度", method = RequestMethod.GET)
    public BootstrapTableVo getProjectProgress() {
       return projectCenterService.getProjectProgressVo();
    }

    @ResponseBody
    @RequestMapping(value = "/getProjectList", name = "取得项目列表", method = RequestMethod.GET)
    public BootstrapTableVo getProjectList() {
        return projectCenterService.getProjectList();
    }

    @ResponseBody
    @RequestMapping(value = "/getCsrProjectInfoList", name = "取得 债权人列表", method = RequestMethod.GET)
    public BootstrapTableVo getCsrProjectInfoList(String name) {
        return projectCenterService.csrProjectInfoList(name);
    }

    @ResponseBody
    @RequestMapping(value = "/getProjectListByMonth", name = "得指定月份完成的项目列表", method = RequestMethod.GET)
    public HttpResult getProjectListByMonth(String dates, String datee) {
        List<ProjectInfo> projectListByMonth = projectCenterService.getProjectListByMonth(dates, datee);
        return HttpResult.newCorrectResult(projectListByMonth);
    }

    @RequestMapping(value = "/projectList", name = "项目列表")
    public ModelAndView projectList() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/projectList");
        return modelAndView;
    }

    @RequestMapping(value = "/projectCsrList", name = "项目 债权列表")
    public ModelAndView projectCsrList() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/csr/projectCsrList");
        return modelAndView;
    }

    @RequestMapping(value = "/projectNew", name = "新建项目")
    public ModelAndView projectNew() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/projectNew");
        //获取到类型 类别 范围
        List<KeyValueDto> keyValueDtoList = baseProjectClassifyService.getProjectInitClassify();
        modelAndView.addObject("keyValueDtoList",keyValueDtoList);
        return modelAndView;
    }

}
