package com.copower.pmcc.assess.service.project.survey;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.NetDownloadUtils;
import com.copower.pmcc.assess.common.enums.BasicApplyTypeEnum;
import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basic.entity.BasicApply;
import com.copower.pmcc.assess.dal.basic.entity.BasicBuilding;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomSurveyExamineTask;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyExamineTaskVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicBuildingService;
import com.copower.pmcc.assess.service.data.DataBuildingNewRateService;
import com.copower.pmcc.assess.service.data.DataExamineTaskService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.FileUtils;
import com.copower.pmcc.erp.common.utils.FtpUtilsExtense;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by zly on 2018/5/15.
 */
@Service
public class SurveyCommonService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private FtpUtilsExtense ftpUtilsExtense;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private DataExamineTaskService dataExamineTaskService;
    @Autowired
    private SurveyExamineTaskService surveyExamineTaskService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataBuildingNewRateService dataBuildingNewRateService;


    /**
     * 获取房产所有调查表单
     *
     * @return
     */
    public List<KeyValueDto> getExamineFormTypeList() {
        List<KeyValueDto> keyValueDtoList = Lists.newArrayList();
        KeyValueDto keyValueDto = new KeyValueDto();
        keyValueDto.setKey(AssessExamineTaskConstant.FC_RESIDENCE);
        keyValueDto.setValue(dataExamineTaskService.getCacheDataExamineTaskByFieldName(AssessExamineTaskConstant.FC_RESIDENCE).getName());
        keyValueDtoList.add(keyValueDto);

        keyValueDto = new KeyValueDto();
        keyValueDto.setKey(AssessExamineTaskConstant.FC_INDUSTRY);
        keyValueDto.setValue(dataExamineTaskService.getCacheDataExamineTaskByFieldName(AssessExamineTaskConstant.FC_INDUSTRY).getName());
        keyValueDtoList.add(keyValueDto);
        return keyValueDtoList;
    }

    /**
     * 获取需处理的任务集合
     *
     * @param planDetailsId
     * @param userAccount
     * @return
     */
    public Map<String, List<SurveyExamineTaskVo>> getExamineTaskByUserAccount(Integer planDetailsId, String userAccount) {
        Map<String, List<SurveyExamineTaskVo>> map = Maps.newHashMap();
        List<SurveyExamineTaskVo> estateTaskList = Lists.newArrayList();
        List<SurveyExamineTaskVo> buildingTaskList = Lists.newArrayList();
        List<SurveyExamineTaskVo> unitTaskList = Lists.newArrayList();
        List<SurveyExamineTaskVo> houseTaskList = Lists.newArrayList();
        SurveyExamineTask surveyExamineTask = new SurveyExamineTask();
        surveyExamineTask.setPlanDetailsId(planDetailsId);
        if (StringUtils.isNotBlank(userAccount))
            surveyExamineTask.setUserAccount(userAccount);
        List<CustomSurveyExamineTask> examineTaskList = surveyExamineTaskService.getCustomeExamineTaskList(planDetailsId, userAccount);
        List<SurveyExamineTaskVo> examineTaskVos = surveyExamineTaskService.getSurveyExamineTaskVos(examineTaskList);
        if (CollectionUtils.isNotEmpty(examineTaskVos)) {
            for (SurveyExamineTaskVo examineTaskVo : examineTaskVos) {
                if (StringUtils.isNotBlank(examineTaskVo.getApplyUrl())) {
                    if (examineTaskVo.getFieldName().contains(AssessExamineTaskConstant.ESTATE))
                        estateTaskList.add(examineTaskVo);
                    if (examineTaskVo.getFieldName().contains(AssessExamineTaskConstant.BUILDING))
                        buildingTaskList.add(examineTaskVo);
                    if (examineTaskVo.getFieldName().contains(AssessExamineTaskConstant.UNIT))
                        unitTaskList.add(examineTaskVo);
                    if (examineTaskVo.getFieldName().contains(AssessExamineTaskConstant.HOUSE))
                        houseTaskList.add(examineTaskVo);
                }
            }
        }
        map.put(AssessExamineTaskConstant.ESTATE, estateTaskList);
        map.put(AssessExamineTaskConstant.BUILDING, buildingTaskList);
        map.put(AssessExamineTaskConstant.UNIT, unitTaskList);
        map.put(AssessExamineTaskConstant.HOUSE, houseTaskList);
        return map;
    }

    /**
     * 获取该细任务的所有任务
     *
     * @param planDetailsId
     * @return
     */
    public Map<String, List<SurveyExamineTaskVo>> getExamineTaskAll(Integer planDetailsId) {
        return getExamineTaskByUserAccount(planDetailsId, null);
    }


    /**
     * 更新任务状态
     *
     * @param planDetailsId
     * @param userAccount
     * @param projectStatusEnum
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateExamineTaskStatus(Integer planDetailsId, String userAccount, ProjectStatusEnum projectStatusEnum) {
        //查询条件
        SurveyExamineTask where = new SurveyExamineTask();
        where.setPlanDetailsId(planDetailsId);
        where.setUserAccount(userAccount);
        //更新内容
        SurveyExamineTask surveyExamineTask = new SurveyExamineTask();
        surveyExamineTask.setTaskStatus(projectStatusEnum.getKey());

        surveyExamineTaskService.updateSurveyExamineTask(surveyExamineTask, where);

        if (surveyExamineTaskService.getRuningTaskCount(planDetailsId) <= 0) {
            ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
            planDetails.setStatus(ProcessStatusEnum.FINISH.getValue());
            projectPlanDetailsService.updateProjectPlanDetails(planDetails);
        }
    }

    /**
     * @param planDetailsId
     * @return
     */
    public boolean isAllTaskFinish(Integer planDetailsId) {
        SurveyExamineTask where = new SurveyExamineTask();
        where.setPlanDetailsId(planDetailsId);
        int allCount = surveyExamineTaskService.getSurveyExamineTaskCount(where);

        where.setTaskStatus(ProjectStatusEnum.FINISH.getKey());
        int finishCount = surveyExamineTaskService.getSurveyExamineTaskCount(where);
        return allCount == finishCount;
    }

    /**
     * 获取案例调查所有任务
     *
     * @param planDetailsId
     * @return
     */
    public List<ProjectPlanDetailsVo> getPlanTaskExamineList(Integer planDetailsId) {
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
        List<ProjectPlanDetails> planDetailsList = projectPlanDetailsService.getPlanDetailsListRecursion(planDetailsId, true);
        List<ProjectPlanDetailsVo> planDetailsVoList = LangUtils.transform(planDetailsList, o -> projectPlanDetailsService.getProjectPlanDetailsVo(o));
        if (CollectionUtils.isNotEmpty(planDetailsVoList)) {
            //获取当前人该阶段下待处理的任务
            ProjectResponsibilityDto projectResponsibilityDto = new ProjectResponsibilityDto();
            projectResponsibilityDto.setProjectId(projectPlanDetails.getProjectId());
            projectResponsibilityDto.setPlanId(projectPlanDetails.getPlanId());
            projectResponsibilityDto.setAppKey(applicationConstant.getAppKey());
            projectResponsibilityDto.setUserAccount(commonService.thisUserAccount());
            List<ProjectResponsibilityDto> projectTaskList = bpmRpcProjectTaskService.getProjectTaskList(projectResponsibilityDto);
            String viewUrl = String.format("/%s/ProjectTask/projectTaskDetailsById?planDetailsId=", applicationConstant.getAppKey());
            for (ProjectPlanDetailsVo projectPlanDetailsVo : planDetailsVoList) {
                if (projectPlanDetailsVo.getId().equals(planDetailsId)) {
                    projectPlanDetailsVo.set_parentId(null);//顶级节点parentId必须为空才能显示
                }
                if (CollectionUtils.isNotEmpty(projectTaskList)) {
                    for (ProjectResponsibilityDto responsibilityDto : projectTaskList) {
                        if (responsibilityDto.getPlanDetailsId().equals(projectPlanDetailsVo.getId())) {
                            projectPlanDetailsVo.setExcuteUrl(String.format("%s?responsibilityId=%s", responsibilityDto.getUrl(), responsibilityDto.getId()));
                        }
                    }
                }

                //设置查看url
                if (StringUtils.isNotBlank(projectPlanDetailsVo.getExecuteUserAccount()) && projectPlanDetailsVo.getBisStart()) {
                    projectPlanDetailsVo.setDisplayUrl(String.format("%s%s", viewUrl, projectPlanDetailsVo.getId()));
                }
            }
        }
        return planDetailsVoList;
    }

    /**
     * 获取查勘过程申请表信息
     *
     * @param declareId
     * @return
     */
    public BasicApply getSceneExploreBasicApply(Integer declareId) {
        try {
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(declareId);
            ProjectInfo projectInfo = projectInfoService.getProjectInfoById(declareRecord.getProjectId());
            ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.SCENE_EXPLORE, projectInfo.getProjectCategoryId());
            ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetails(declareId, projectPhase.getId());
            BasicApply basicApply = basicApplyService.getBasicApplyByPlanDetailsId(planDetails.getId());
            return basicApply;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }


    /**
     * 获取楼栋可使用年限
     *
     * @param declareId
     * @return
     */
    public Integer getBuildingUsableYear(Integer declareId) {
        //获取该证现场查勘时楼栋的可使用年限
        Integer buildingUsableYear = 0;
        try {
            BasicApply basicApply = this.getSceneExploreBasicApply(declareId);
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            buildingUsableYear = this.getBuildingUsableYear(basicApply, basicBuilding);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return buildingUsableYear;
    }

    public Integer getBuildingUsableYear(BasicApply basicApply, BasicBuilding basicBuilding) {
        Integer buildingUsableYear = 0;
        if (basicApply == null || basicBuilding == null) return buildingUsableYear;
        if (BasicApplyTypeEnum.RESIDENCE.getId().equals(basicApply.getType())) {
            BaseDataDic baseDataDic = baseDataDicService.getDataDicById(basicBuilding.getResidenceUseYear());
            buildingUsableYear = Integer.valueOf(baseDataDic.getRemark());
        } else if (BasicApplyTypeEnum.INDUSTRY.getId().equals(basicApply.getType())) {
            DataBuildingNewRate buildingNewRate = dataBuildingNewRateService.getByiDdataBuildingNewRate(basicBuilding.getIndustryUseYear());
            buildingUsableYear = buildingNewRate.getDurableLife();
        }
        return buildingUsableYear;
    }


    /**
     * 获取初始化权证json数据
     *
     * @param projectId
     * @param declareId
     * @return
     */
    public String getDeclareCertJson(Integer projectId, Integer declareId) {
        List<DeclareRecord> declareRecordList = declareRecordService.getDeclareRecordByProjectId(projectId);
        if (CollectionUtils.isEmpty(declareRecordList)) return null;
        JSONArray jsonArray = new JSONArray();
        for (DeclareRecord declareRecord : declareRecordList) {
            if (declareRecord.getId().equals(declareId)) continue;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("key", declareRecord.getId());
            jsonObject.put("isChecked", false);
            jsonObject.put("value", declareRecord.getName());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * 获取调查信息相关表
     *
     * @return
     */
    public List<String> getTableList() {
        String dbName = BaseConstant.DATABASE_PMCC_ASSESS;
        String sql = String.format("select TABLE_NAME from information_schema.`TABLES` where table_schema='%s' and TABLE_NAME LIKE 'tb_examine_%%'", dbName);
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        List<String> tableList = Lists.newArrayList();
        for (Map<String, Object> map : mapList) {
            tableList.add(String.valueOf(map.get("TABLE_NAME")));
        }
        return tableList;
    }

    /**
     * 获取同步sql语句
     *
     * @param tableName
     * @param oldPlanDetailsId
     * @param newPlanDetailsId
     * @param newDeclareId
     * @return
     */
    public String getSynchronizeSql(String tableName, Integer oldPlanDetailsId, Integer newPlanDetailsId, Integer newDeclareId) {
        String dbName = BaseConstant.DATABASE_PMCC_ASSESS;
        String sql = String.format("select column_name from information_schema.columns where table_name='%s' and table_schema='%s'", tableName, dbName);
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

        String resultString = MessageFormat.format("INSERT into {0}({1}) SELECT %s FROM {0} where plan_details_id={2};", tableName, columnString, String.valueOf(oldPlanDetailsId));
        columnString = columnString.replace("plan_details_id", String.valueOf(newPlanDetailsId)).replace("declare_id", String.valueOf(newDeclareId));
        resultString = String.format(resultString, columnString);
        return resultString;
    }
}
