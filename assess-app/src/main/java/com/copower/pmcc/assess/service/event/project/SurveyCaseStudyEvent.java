package com.copower.pmcc.assess.service.event.project;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SurveyCaseStudy;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.survey.SurveyCaseStudyService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kings on 2018-5-21.
 */
@Component
public class SurveyCaseStudyEvent extends ProjectTaskEvent {

    @Autowired
    private SurveyCaseStudyService surveyCaseStudyService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SurveyCommonService surveyCommonService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) {
        super.processFinishExecute(processExecution);
        SurveyCaseStudy surveyCaseStudy = surveyCaseStudyService.getSurveyCaseStudy(processExecution.getProcessInstanceId());
        String jsonContent = surveyCaseStudy.getJsonContent();
        JSONArray jsonArray = JSON.parseArray(jsonContent);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (Boolean.TRUE.equals(jsonObject.getBoolean("isChecked"))) {
                try {
                    synchronize(surveyCaseStudy.getPlanDetailsId(), jsonObject.getInteger("key"));
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 数据同步
     *
     * @param declareId
     */
    @Transactional(rollbackFor = Exception.class)
    public void synchronize(Integer currPlanDetailsId, Integer declareId) throws BusinessException {
        ProjectPlanDetails where = new ProjectPlanDetails();
        where.setDeclareRecordId(declareId);
        List<ProjectPlanDetails> planDetailsList = projectPlanDetailsService.getProjectDetails(where);
        if (CollectionUtils.isEmpty(planDetailsList)) return;
        ProjectPlanDetails projectPlanDetails = null;//找到该证下的任务，为案例调查的任务
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.CASE_STUDY);
        for (ProjectPlanDetails details : planDetailsList) {
            if (details.getProjectPhaseId() != null && details.getProjectPhaseId().equals(projectPhase.getId())) {
                projectPlanDetails = details;
                break;
            }
        }
        if (projectPlanDetails == null) return;
        List<ProjectPlanDetails> listRecursion = projectPlanDetailsService.getPlanDetailsListRecursion(projectPlanDetails.getId(), false);
        if (CollectionUtils.isNotEmpty(listRecursion)) return;

        List<ProjectPlanDetails> childrenPlanDetailsList = projectPlanDetailsService.getChildrenPlanDetailsList(currPlanDetailsId);
        if (CollectionUtils.isEmpty(childrenPlanDetailsList)) return;
        List<String> tableList = surveyCommonService.getTableList();
        if (CollectionUtils.isEmpty(tableList)) return;
        StringBuilder stringBuilder = new StringBuilder();
        int i=1;
        for (ProjectPlanDetails details : childrenPlanDetailsList) {
            ProjectPlanDetails casePlanDetails = new ProjectPlanDetails();
            BeanUtils.copyProperties(projectPlanDetails,casePlanDetails);
            casePlanDetails.setId(null);
            casePlanDetails.setPid(projectPlanDetails.getId());
            casePlanDetails.setSorting(i);
            casePlanDetails.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
            casePlanDetails.setProjectPhaseName(details.getProjectPhaseName());
            projectPlanDetailsService.saveProjectPlanDetails(casePlanDetails);
            //同步examine表数据
            for (String tableName : tableList) {
                stringBuilder.append(surveyCommonService.getSynchronizeSql(tableName, details.getId(), casePlanDetails.getId(), declareId));
            }
            i++;
        }
        jdbcTemplate.execute(stringBuilder.toString());
    }
}
