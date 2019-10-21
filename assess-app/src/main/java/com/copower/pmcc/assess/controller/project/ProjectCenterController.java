package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.report.AssessProjectTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DocumentTemplate;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.document.DocumentTemplateService;
import com.copower.pmcc.assess.service.project.ProjectCenterService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.assess.service.project.plan.execute.PlanSurveyExecute;
import com.copower.pmcc.assess.service.project.scheme.ProjectPlanSchemeAssist;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
    private DocumentTemplateService documentTemplateService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;

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

    @RequestMapping(value = "/projectInfo", name = "项目详情页面")
    public ModelAndView projectInfo(Integer projectId) {
        String viewUrl = "/project/detailInfo/projectDetailInfo";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(viewUrl);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        List<ProjectPlanVo> projectPlanList = projectInfoService.getProjectPlanList(projectId);
        modelAndView.addObject("projectPlanList", projectPlanList);
        modelAndView.addObject("projectId", projectId);
        modelAndView.addObject(StringUtils.uncapitalize(ProjectInfo.class.getSimpleName()), projectInfoService.getSimpleProjectInfoVo(projectInfo));
        modelAndView.addObject("companyId", publicService.getCurrentCompany().getCompanyId());
        List<DocumentTemplate> documentTemplateList = documentTemplateService.getDocumentTemplateList("", baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_TEMPLATE_TYPE_DISPATCH).getId());
        modelAndView.addObject("documentTemplateList", documentTemplateList);
        return modelAndView;
    }

    @RequestMapping(value = "/projectStageInfo/{projectId}/{workStageId}", name = "阶段信息页面")
    public ModelAndView projectStageInfo(@PathVariable(name = "projectId", required = true) Integer projectId, @PathVariable(name = "workStageId", required = true) Integer workStageId) {
        String viewUrl = "/project/detailInfo/projectStageInfoDefault";
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(workStageId);
        //如果为房产 现场与方案阶段使用不同的页面
        if(AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_HOUSE.getKey().equals(projectInfoService.getAssessProjectType(projectInfo.getProjectCategoryId()).getKey())){
            //现场查勘
            if (StringUtils.equals(projectWorkStage.getStageForm(), StringUtils.uncapitalize(PlanSurveyExecute.class.getSimpleName()))) {
                viewUrl = "/project/detailInfo/house/surveyStageInfo";
            }
            //评估方案计划
            if (StringUtils.equals(projectWorkStage.getStageForm(), StringUtils.uncapitalize(ProjectPlanSchemeAssist.class.getSimpleName()))) {
                viewUrl = "/project/detailInfo/house/schemeStageInfo";
            }
        }
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(viewUrl);
        List<ProjectPlanVo> projectPlanList = projectInfoService.getProjectPlanList(projectId);
        modelAndView.addObject("projectPlanList", projectPlanList);
        modelAndView.addObject("projectId", projectId);
        modelAndView.addObject(StringUtils.uncapitalize(ProjectInfo.class.getSimpleName()), projectInfoService.getSimpleProjectInfoVo(projectInfo));
        return modelAndView;
    }

}
