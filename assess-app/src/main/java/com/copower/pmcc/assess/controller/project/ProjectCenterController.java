package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.ProjectCenterService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private ProjectInfoService projectInfoService;

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
        List<KeyValueDto> statusEnumList = ProjectStatusEnum.getProjectStatusEnumList(ProjectStatusEnum.NORMAL.getKey(),
                ProjectStatusEnum.FINISH.getKey(), ProjectStatusEnum.CLOSE.getKey());
        modelAndView.addObject("statusEnumList", statusEnumList);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getMyProjectList", name = "取得我的立项", method = RequestMethod.GET)
    public BootstrapTableVo getMyProjectList(String queryName, String projectStatus) {
        return projectCenterService.getMyProjectList(queryName, projectStatus);
    }

    @RequestMapping(value = "/myParticipation", name = "我的参与")
    public ModelAndView myParticipation() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/myParticipation");
        List<KeyValueDto> statusEnumList = ProjectStatusEnum.getProjectStatusEnumList(ProjectStatusEnum.NORMAL.getKey(),
                ProjectStatusEnum.FINISH.getKey(), ProjectStatusEnum.CLOSE.getKey());
        modelAndView.addObject("companyId", publicService.getCurrentCompany().getCompanyId());
        modelAndView.addObject("statusEnumList", statusEnumList);
        //委托目的
        List<BaseDataDic> entrustPurposeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE);
        modelAndView.addObject("entrustPurposeList", entrustPurposeList);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getParticipationProject", name = "取得参与项目", method = RequestMethod.GET)
    public BootstrapTableVo getParticipationProject(String queryName, String projectStatus, String queryCreator, String queryMember, Integer entrustPurpose,
                                                    String queryManager, String queryTimeStart, String queryTimeEnd, String queryConsignor, Integer queryUseUnit, Integer queryDepartmentId) throws Exception {
        return projectCenterService.getParticipationProject(queryName, projectStatus, queryCreator, queryMember, entrustPurpose,
                queryManager, queryTimeStart, queryTimeEnd, queryConsignor, queryUseUnit, null, queryDepartmentId);
    }

    @RequestMapping(value = "/projectList", name = "所有项目")
    public ModelAndView projectList() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/projectList");
        List<KeyValueDto> statusEnumList = ProjectStatusEnum.getProjectStatusEnumList(ProjectStatusEnum.NORMAL.getKey(),
                ProjectStatusEnum.FINISH.getKey(), ProjectStatusEnum.CLOSE.getKey(), ProjectStatusEnum.DRAFT.getKey());
        modelAndView.addObject("statusEnumList", statusEnumList);
        //委托目的
        List<BaseDataDic> entrustPurposeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE);
        modelAndView.addObject("entrustPurposeList", entrustPurposeList);
        //贷款类型
        List<BaseDataDic> loanTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_LOAN_TYPE);
        modelAndView.addObject("loanTypeList", loanTypeList);
        modelAndView.addObject("companyId", publicService.getCurrentCompany().getCompanyId());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getProjectList", name = "取得所有项目列表", method = RequestMethod.GET)
    public BootstrapTableVo getProjectList(String queryName, String projectStatus, String queryCreator, String queryMember, Integer entrustPurpose,
                                           String queryManager, String queryTimeStart, String queryTimeEnd, String queryConsignor, Integer queryUseUnit,
                                           String queryEstateName, Integer queryLoanType, Integer queryDepartmentId) throws Exception {
        return projectCenterService.getProjectList(queryName, projectStatus, queryCreator, queryMember, entrustPurpose,
                queryManager, queryTimeStart, queryTimeEnd, queryConsignor, queryUseUnit, queryEstateName, queryLoanType, queryDepartmentId);
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

    @RequestMapping(value = "/projectTraceMenu", name = "单个项目模块菜单")
    public ModelAndView projectTraceMenu(Integer projectId) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/projectTraceMenu/projectDetails");
        setProjectTraceMenuParams(projectId,modelAndView) ;
        return modelAndView;
    }

    /**
     * 模块参数设置
     * @param projectId
     * @param modelAndView
     */
    private void setProjectTraceMenuParams(Integer projectId , ModelAndView modelAndView){
        if (projectId == null){
            return;
        }
        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectId)) ;
        if (projectInfoVo == null){
            return;
        }
        modelAndView.addObject(StringUtils.uncapitalize(ProjectInfo.class.getSimpleName()),projectInfoVo) ;
    }


}
