package com.copower.pmcc.assess.service.event.project;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SurveySceneExplore;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.assess.service.project.survey.SurveySceneExploreService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kings on 2018-5-21.
 */
@Component
public class SurveySceneExploreEvent extends ProjectTaskEvent {
    @Autowired
    private SurveySceneExploreService surveySceneExploreService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private ProjectInfoService projectInfoService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws  Exception{
        super.processFinishExecute(processExecution);

        //批量更新数据到其它证书
        //1.循环各数据，检查该数据下是否已填写相关信息，如果已填写则不做任何处理
        //2.如果没有填写数据则依次处理相关数据

        SurveySceneExplore surveySceneExplore = surveySceneExploreService.getSurveySceneExplore(processExecution.getProcessInstanceId());
        String jsonContent = surveySceneExplore.getJsonContent();
        JSONArray jsonArray = JSON.parseArray(jsonContent);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (Boolean.TRUE.equals(jsonObject.getBoolean("isChecked"))) {
                try {
                    synchronize(surveySceneExplore.getProjectId(),surveySceneExplore.getPlanDetailsId(), jsonObject.getInteger("key"));
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
    public void synchronize(Integer projectId,Integer oldPlanDetailsId, Integer declareId) throws BusinessException {
        ProjectPlanDetails where = new ProjectPlanDetails();
        where.setDeclareRecordId(declareId);
        List<ProjectPlanDetails> planDetailsList = projectPlanDetailsService.getProjectDetails(where);
        if (CollectionUtils.isEmpty(planDetailsList)) return;
        ProjectPlanDetails projectPlanDetails = null;
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.SCENE_EXPLORE,projectInfo.getProjectCategoryId());
        for (ProjectPlanDetails details : planDetailsList) {
            if (details.getProjectPhaseId() != null && details.getProjectPhaseId().equals(projectPhase.getId())) {
                projectPlanDetails = details;
                break;
            }
        }
        if (projectPlanDetails == null) return;
        List<ProjectPlanDetails> listRecursion = projectPlanDetailsService.getPlanDetailsListRecursion(projectPlanDetails.getId(), false);
        if (CollectionUtils.isNotEmpty(listRecursion)) return;

        //同步examine表数据
        StringBuilder stringBuilder = new StringBuilder();
        List<String> tableList = surveyCommonService.getTableList();
        if (CollectionUtils.isEmpty(tableList)) return;
        for (String tableName : tableList) {
            stringBuilder.append(surveyCommonService.getSynchronizeSql(tableName, oldPlanDetailsId, projectPlanDetails.getId(), declareId));
        }
        jdbcTemplate.execute(stringBuilder.toString());
    }


}
