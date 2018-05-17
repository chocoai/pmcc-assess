package com.copower.pmcc.assess.service.project.taks.assist;

import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.entity.SurveyLocaleExplore;
import com.copower.pmcc.assess.dal.entity.SurveyLocaleExploreDetail;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.project.SurveyLocaleExploreDetailService;
import com.copower.pmcc.assess.service.project.SurveyLocaleExploreService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "现场查勘类")
public class ProjectTaskExploreAssist implements ProjectTaskInterface {
    @Autowired
    private ControllerComponent serviceComponent;
    @Autowired
    private SurveyLocaleExploreService surveyLocaleExploreService;
    @Autowired
    private SurveyLocaleExploreDetailService surveyLocaleExploreDetailService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/task/explore/taskExploreIndex", "", 0, "0", "");

        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/task/explore/taskExploreApproval", processInsId, boxId, taskId, agentUserAccount);
        //审批走这里
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/task/explore/taskExploreIndex", processInsId, boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/task/explore/taskExploreApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        SurveyLocaleExplore surveyLocaleExplore = new SurveyLocaleExplore();
        surveyLocaleExplore.setProjectId(projectPlanDetails.getProjectId());
        surveyLocaleExplore.setPlanDetailId(projectPlanDetails.getPlanId());
        surveyLocaleExplore.setProcessInsId(processInsId);
        surveyLocaleExploreService.save(surveyLocaleExplore);
        Integer id = surveyLocaleExplore.getId();
        Integer mainId =0;
        List<SurveyLocaleExploreDetail> surveyLocaleExploreDetails = surveyLocaleExploreDetailService.changeId(mainId);
        if(surveyLocaleExploreDetails != null && surveyLocaleExploreDetails.size() > 0){
            for(SurveyLocaleExploreDetail surveyLocaleExploreDetail : surveyLocaleExploreDetails){
                surveyLocaleExploreDetail.setMainId(id);//更新关联ID
                surveyLocaleExploreDetailService.save(surveyLocaleExploreDetail);
            }
        }
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }
}
