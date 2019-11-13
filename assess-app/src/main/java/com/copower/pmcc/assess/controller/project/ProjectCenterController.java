package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.common.enums.AssessProjectTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
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
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.assess.service.project.scheme.ProjectPlanSchemeAssist;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import org.apache.commons.collections.CollectionUtils;
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
    @Autowired
    private ProjectPhaseService projectPhaseService;

    @RequestMapping(value = "/projectNew", name = "新建项目")
    public ModelAndView projectNew() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/projectNew");
        //获取到类型 类别 范围
        List<KeyValueDto> keyValueDtoList = baseProjectClassifyService.getProjectInitClassify();
        modelAndView.addObject("keyValueDtoList", keyValueDtoList);
        return modelAndView;
    }

    @RequestMapping(value = "/financialReferenceProject", name = "财务引用评估项目项目(评估项目不要引用这个，因为里面的信息较少)")
    public ModelAndView financialReferenceProject() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/report/financialReferenceProject");
        modelAndView.addObject("companyId", publicService.getCurrentCompany().getCompanyId());
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
        List<DocumentTemplate> documentClientTemplateList = documentTemplateService.getDocumentTemplateList("", baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_TEMPLATE_TYPE_DISPATCH_CLIENT).getId());
        modelAndView.addObject("documentTemplateList", documentTemplateList);
        modelAndView.addObject("documentClientTemplateList", documentClientTemplateList);

        //报告签收单
        List<DocumentTemplate> signBill = documentTemplateService.getDocumentTemplateList("", baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_TEMPLATE_TYPE_REPORT_SIGNFOR).getId());
        modelAndView.addObject("signBill", signBill);
        return modelAndView;
    }

    @RequestMapping(value = "/projectStageInfo/{projectId}/{workStageId}", name = "阶段信息页面")
    public ModelAndView projectStageInfo(@PathVariable(name = "projectId", required = true) Integer projectId, @PathVariable(name = "workStageId", required = true) Integer workStageId) {
        String viewUrl = "/project/detailInfo/projectStageInfoDefault";
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(workStageId);
        //如果为房产 现场与方案阶段使用不同的页面
        String projectTypeKey = projectInfoService.getAssessProjectType(projectInfo.getProjectCategoryId()).getKey();
        if (AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_HOUSE.getKey().equals(projectTypeKey) || AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_LAND.getKey().equals(projectTypeKey)) {
            //评估方案计划
            if (StringUtils.equals(projectWorkStage.getStageForm(), StringUtils.uncapitalize(ProjectPlanSchemeAssist.class.getSimpleName()))) {
                viewUrl = "/project/detailInfo/house/schemeStageInfo";
            }
        }
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(viewUrl);
        List<ProjectPlanVo> projectPlanList = projectInfoService.getProjectPlanList(projectId);
        modelAndView.addObject("projectPlanList", projectPlanList);
        if (CollectionUtils.isNotEmpty(projectPlanList)) {
            for (ProjectPlanVo projectPlanVo : projectPlanList) {
                if (projectPlanVo.getWorkStageId().equals(workStageId)) {
                    modelAndView.addObject("projectPlan", projectPlanVo);
                    modelAndView.addObject("projectPhaseVoList", projectPhaseService.getCacheProjectPhaseByCategoryId(projectInfo.getProjectCategoryId(), workStageId));
                }
            }
        }
        modelAndView.addObject("projectId", projectId);
        modelAndView.addObject("companyId", publicService.getCurrentCompany().getCompanyId());
        modelAndView.addObject(StringUtils.uncapitalize(ProjectInfo.class.getSimpleName()), projectInfoService.getSimpleProjectInfoVo(projectInfo));
        modelAndView.addObject(StringUtils.uncapitalize(ProjectWorkStage.class.getSimpleName()), projectWorkStage);
        modelAndView.addObject(StringUtils.uncapitalize(SysUserDto.class.getSimpleName()), processControllerComponent.getThisUserInfo());
        return modelAndView;
    }

}
