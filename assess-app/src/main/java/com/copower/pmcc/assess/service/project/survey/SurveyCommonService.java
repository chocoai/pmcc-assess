package com.copower.pmcc.assess.service.project.survey;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.NetDownloadUtils;
import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomSurveyExamineTask;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineBuildingVo;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyExamineDataInfoVo;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyExamineTaskVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.data.DataExamineTaskService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.examine.*;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
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
import org.springframework.util.ObjectUtils;

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
    private ExamineBlockService examineBlockService;
    @Autowired
    private ExamineEstateLandStateService examineEstateLandStateService;
    @Autowired
    private ExamineEstateService examineEstateService;
    @Autowired
    private ExamineHouseService examineHouseService;
    @Autowired
    private ExamineHouseTradingService examineHouseTradingService;
    @Autowired
    private ExamineUnitService examineUnitService;
    @Autowired
    private ExamineBuildingService examineBuildingService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 下载定位图片
     *
     * @param tableName
     * @param tableId
     * @param surveyLocaltion
     */
    public void downLoadLocationImage(String tableName, Integer tableId, String surveyLocaltion) {
        String localDir = baseAttachmentService.createTempDirPath(commonService.thisUserAccount());
        String imageName = baseAttachmentService.createNoRepeatFileName("jpg");
        String url = String.format("%s?location=%s&zoom=17&size=900*600&markers=mid,,A:%s&key=%s",
                BaseConstant.MPA_API_URL, surveyLocaltion, surveyLocaltion, BaseConstant.MAP_WEB_SERVICE_KEY);
        try {
            NetDownloadUtils.download(url, imageName, localDir);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        //再将图片上传到FTP
        String ftpFileName = baseAttachmentService.createNoRepeatFileName("jpg");
        String ftpDirName = baseAttachmentService.createFTPBasePath();
        try {
            ftpUtilsExtense.uploadFilesToFTP(ftpDirName, new FileInputStream(localDir + File.separator + imageName), ftpFileName);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        //数据库添加定位图片记录
        SysAttachmentDto baseAttachment = new SysAttachmentDto();
        baseAttachment.setTableId(tableId);
        baseAttachment.setTableName(tableName);
        baseAttachment.setFieldsName("survey_localtion");
        baseAttachment.setFtpFileName(ftpFileName);
        baseAttachment.setFileExtension("jpg");
        baseAttachment.setFilePath(ftpDirName);
        baseAttachment.setFileName("定位图.jpg");
        baseAttachment.setFileSize(FileUtils.getSize(new File(localDir + File.separator + imageName).length()));
        baseAttachment.setCreater(processControllerComponent.getThisUser());
        //baseAttachment.setModifier(processControllerComponent.getThisUser());
        baseAttachmentService.addAttachment(baseAttachment);
    }


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
        List<SurveyExamineTaskVo> blockTaskList = Lists.newArrayList();
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
                    if (examineTaskVo.getFieldName().contains(AssessExamineTaskConstant.BLOCK))
                        blockTaskList.add(examineTaskVo);
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
        map.put(AssessExamineTaskConstant.BLOCK, blockTaskList);
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
     * 获取调查数据信息
     *
     * @param declareId
     * @return
     */
    public SurveyExamineDataInfoVo getExamineDataInfoVo(Integer declareId, Integer planDetailsId, ExamineTypeEnum examineTypeEnum) {
        SurveyExamineDataInfoVo surveyExamineDataInfoVo = new SurveyExamineDataInfoVo();

        ExamineBlock examineBlock = examineBlockService.getBlockByDeclareId(declareId, planDetailsId, examineTypeEnum);
        surveyExamineDataInfoVo.setExamineBlockVo(examineBlockService.getExamineBlockVo(examineBlock));

        ExamineEstateLandState estateLandState = examineEstateLandStateService.getEstateLandStateByDeclareId(declareId, planDetailsId, examineTypeEnum);
        surveyExamineDataInfoVo.setExamineEstateLandStateVo(examineEstateLandStateService.getExamineEstateLandStateVo(estateLandState));

        ExamineEstate examineEstate = examineEstateService.getEstateByDeclareId(declareId, planDetailsId, examineTypeEnum);
        surveyExamineDataInfoVo.setExamineEstateVo(examineEstateService.getExamineEstateVo(examineEstate));

        ExamineUnit examineUnit = examineUnitService.getUnitByDeclareId(declareId, planDetailsId, examineTypeEnum);
        surveyExamineDataInfoVo.setExamineUnitVo(examineUnitService.getExamineUnitVo(examineUnit));

        ExamineHouse examineHouse = examineHouseService.getHouseByDeclareId(declareId, planDetailsId, examineTypeEnum);
        surveyExamineDataInfoVo.setExamineHouseVo(examineHouseService.getExamineHouseVo(examineHouse));

        ExamineHouseTrading examineHouseTrading = examineHouseTradingService.getHouseTradingByDeclareId(declareId, planDetailsId, examineTypeEnum);
        surveyExamineDataInfoVo.setExamineHouseTradingVo(examineHouseTradingService.getExamineHouseTradingVo(examineHouseTrading));

        List<ExamineBuilding> examineBuildings = examineBuildingService.getByDeclareIdAndExamineType(declareId, planDetailsId, examineTypeEnum.getId());
        if (!ObjectUtils.isEmpty(examineBuildings)) {
           List<ExamineBuildingVo> examineBuildingVos = Lists.newArrayList();
            for (int i = 0; i < examineBuildings.size() ; i++) {
                if (i == 0){
                    surveyExamineDataInfoVo.getExamineBuildingVoMap().put("oneExamineBuilding",examineBuildingService.getExamineBuildingVo(examineBuildings.get(i)));
                }
                if (i == 1){
                    surveyExamineDataInfoVo.getExamineBuildingVoMap().put("twoExamineBuilding",examineBuildingService.getExamineBuildingVo(examineBuildings.get(i)));
                }
                if (i == 2){
                    surveyExamineDataInfoVo.getExamineBuildingVoMap().put("threeExamineBuilding",examineBuildingService.getExamineBuildingVo(examineBuildings.get(i)));
                }
                if (i == 4){
                    surveyExamineDataInfoVo.getExamineBuildingVoMap().put("fourExamineBuilding",examineBuildingService.getExamineBuildingVo(examineBuildings.get(i)));
                }
            }
        }
        return surveyExamineDataInfoVo;
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
            if(declareRecord.getId().equals(declareId)) continue;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("key",declareRecord.getId());
            jsonObject.put("isChecked",false);
            jsonObject.put("value",declareRecord.getName());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * 复制调查任务及信息
     * @param planDetailsId
     */
    public void copyExamineTaskAndInfo(Integer planDetailsId) throws BusinessException {

    }

    /**
     * 获取调查信息相关表
     * @return
     */
    public List<String> getTableList() {
        String dbName=BaseConstant.CURRENT_DATABASE_NAME;
        String sql = String.format("select TABLE_NAME from information_schema.`TABLES` where table_schema='%s' and TABLE_NAME LIKE 'tb_examine_%%'",dbName);
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        List<String> tableList = Lists.newArrayList();
        for (Map<String, Object> map : mapList) {
            tableList.add(String.valueOf(map.get("TABLE_NAME")));
        }
        return tableList;
    }

    /**
     * 获取同步sql语句
     * @param tableName
     * @param oldPlanDetailsId
     * @param newPlanDetailsId
     * @param newDeclareId
     * @return
     */
    public String getSynchronizeSql(String tableName, Integer oldPlanDetailsId, Integer newPlanDetailsId, Integer newDeclareId) {
        String dbName=BaseConstant.CURRENT_DATABASE_NAME;
        String sql = String.format("select column_name from information_schema.columns where table_name='%s' and table_schema='%s'", tableName,dbName);
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
