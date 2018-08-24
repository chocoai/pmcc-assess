package com.copower.pmcc.assess.service.event.project;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SurveySceneExplore;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.survey.SurveySceneExploreService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

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

    @Override
    public void processFinishExecute(ProcessExecution processExecution) {
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
                    synchronize(surveySceneExplore.getPlanDetailsId(), jsonObject.getInteger("key"));
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
        ProjectPlanDetails projectPlanDetails = null;
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.SCENE_EXPLORE);
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
        for (ProjectPlanDetails details : childrenPlanDetailsList) {
            details.setId(0);
            details.setPid(projectPlanDetails.getId());
            projectPlanDetailsService.saveProjectPlanDetails(details);
        }

        //同步examine表数据
        StringBuilder stringBuilder = new StringBuilder();
        List<String> tableList = getTableList();
        if (CollectionUtils.isEmpty(tableList)) return;
        for (String tableName : tableList) {
            stringBuilder.append(getSynchronizeSql(tableName, currPlanDetailsId, projectPlanDetails.getId(),declareId));
        }
        jdbcTemplate.execute(stringBuilder.toString());
    }

    private List<String> getTableList() {
        String sql = "select TABLE_NAME from information_schema.`TABLES` where table_schema='pmcc_assess' and TABLE_NAME LIKE 'tb_examine_%'";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        List<String> tableList = Lists.newArrayList();
        for (Map<String, Object> map : mapList) {
            tableList.add(String.valueOf(map.get("TABLE_NAME")));
        }
        return tableList;
    }

    private String getSynchronizeSql(String tableName, Integer oldPlanDetailsId, Integer newPlanDetailsId,Integer newDeclareId) {
        String sql = String.format("select column_name from information_schema.columns where table_name='%s' and table_schema='pmcc_assess'", tableName);
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        //1.去除id 评出sql前半截  拼出sql后半截
        for (Map<String, Object> map : mapList) {
            if (map.get("column_name").equals("id")) {
                mapList.remove(map);
                break;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Map<String, Object> map : mapList) {
            stringBuilder.append(map.get("column_name")).append(",");
        }
        String columnString = stringBuilder.toString();
        columnString = columnString.replaceAll(",$", "");

        String resultString = MessageFormat.format("INSERT into {0}({1}) SELECT %s FROM {0} where plan_details_id={2}", tableName, columnString, String.valueOf(oldPlanDetailsId));
        resultString = String.format(resultString, columnString.replace("plan_details_id", String.valueOf(newPlanDetailsId)));
        resultString = String.format(resultString, columnString.replace("declare_id", String.valueOf(newDeclareId)));
        return resultString;
    }
}
