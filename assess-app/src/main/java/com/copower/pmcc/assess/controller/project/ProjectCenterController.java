package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.ProjectCenterService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
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
 * @author: Calvin(qiudong @ copowercpa.com)
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

    @RequestMapping(value = "/projectNew", name = "新建项目")
    public ModelAndView projectNew() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/projectNew");
        //获取到类型 类别 范围
        List<KeyValueDto> keyValueDtoList = baseProjectClassifyService.getProjectInitClassify();
        modelAndView.addObject("keyValueDtoList", keyValueDtoList);
        return modelAndView;
    }

    @RequestMapping(value = "/myProject", name = "我的立项")
    public ModelAndView myProject() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/myProject");
        List<KeyValueDto> statusEnumList = ProjectStatusEnum.getProjectStatusEnumList();
        modelAndView.addObject("statusEnumList", statusEnumList);
        return modelAndView;
    }

    @RequestMapping(value = "/myParticipation", name = "我的参与")
    public ModelAndView myParticipation() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/myParticipation");
        List<KeyValueDto> statusEnumList = ProjectStatusEnum.getProjectStatusEnumList();
        modelAndView.addObject("statusEnumList", statusEnumList);
        return modelAndView;
    }

    @RequestMapping(value = "/projectList", name = "所有项目")
    public ModelAndView projectList() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/projectList");
        List<KeyValueDto> statusEnumList = ProjectStatusEnum.getProjectStatusEnumList();
        modelAndView.addObject("statusEnumList", statusEnumList);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getProjectList", name = "取得项目列表", method = RequestMethod.GET)
    public BootstrapTableVo getProjectList(String queryName, String projectStatus) {
        return projectCenterService.getProjectList(queryName, projectStatus);
    }

    @ResponseBody
    @RequestMapping(value = "/getCsrProjectInfoList", name = "取得 债权人列表", method = RequestMethod.GET)
    public BootstrapTableVo getCsrProjectInfoList(String name) {
        return projectCenterService.csrProjectInfoList(name);
    }


    @RequestMapping(value = "/projectCsrList", name = "债权项目列表")
    public ModelAndView projectCsrList() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/csr/projectCsrList");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getMyProjectList", name = "取得我的立项", method = RequestMethod.GET)
    public BootstrapTableVo getMyProjectList(String queryName, String projectStatus) {
        return projectCenterService.getMyProjectList(queryName, projectStatus);
    }

    @ResponseBody
    @RequestMapping(value = "/getParticipationProject", name = "取得参与项目", method = RequestMethod.GET)
    public BootstrapTableVo getParticipationProject(String queryName, String projectStatus) {
        return projectCenterService.getParticipationProject(queryName, projectStatus);
    }
}
