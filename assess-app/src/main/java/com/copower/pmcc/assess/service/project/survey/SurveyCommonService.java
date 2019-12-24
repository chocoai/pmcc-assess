package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.common.enums.basic.BasicApplyTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomSurveyExamineTask;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyExamineTaskVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicBuildingService;
import com.copower.pmcc.assess.service.basic.BasicHouseService;
import com.copower.pmcc.assess.service.data.DataBuildingNewRateService;
import com.copower.pmcc.assess.service.data.DataExamineTaskService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.common.CommonService;
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
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataBuildingNewRateService dataBuildingNewRateService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BaseService baseService;


    /**
     * 获取房产所有调查表单
     *
     * @return
     */
    public List<KeyValueDto> getExamineFormTypeList() {
        List<KeyValueDto> keyValueDtoList = Lists.newArrayList();
        KeyValueDto keyValueDto = new KeyValueDto();
        keyValueDto.setKey(String.valueOf(BasicApplyTypeEnum.RESIDENCE.getId()));
        keyValueDto.setValue(BasicApplyTypeEnum.RESIDENCE.getName());
        keyValueDtoList.add(keyValueDto);

        keyValueDto = new KeyValueDto();
        keyValueDto.setKey(String.valueOf(BasicApplyTypeEnum.INDUSTRY.getId()));
        keyValueDto.setValue(BasicApplyTypeEnum.INDUSTRY.getName());
        keyValueDtoList.add(keyValueDto);
        return keyValueDtoList;
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
            ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.SCENE_EXPLORE, projectInfo.getProjectCategoryId());
            ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetails(declareId, projectPhase.getId());
            BasicApply basicApply = basicApplyService.getBasicApplyByPlanDetailsId(planDetails.getId());
            return basicApply;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return null;
        }
    }

    /**
     * 获取查勘过程申请表信息
     *
     * @param declareId
     * @return
     */
    public List<BasicApply> getSceneExploreBasicApplyList(Integer declareId) {
        try {
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(declareId);
            ProjectInfo projectInfo = projectInfoService.getProjectInfoById(declareRecord.getProjectId());
            ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.SCENE_EXPLORE, projectInfo.getProjectCategoryId());
            ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetails(declareId, projectPhase.getId());
            List<BasicApply> basicApplyList = basicApplyService.getBasicApplyListByPlanDetailsId(planDetails.getId());
            return basicApplyList;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return null;
        }
    }

    public BasicApplyBatch getBasicApplyBatchById(Integer declareId) {
        try {
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(declareId);
            ProjectInfo projectInfo = projectInfoService.getProjectInfoById(declareRecord.getProjectId());
            ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.SCENE_EXPLORE, projectInfo.getProjectCategoryId());
            ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetails(declareId, projectPhase.getId());
            BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchByPlanDetailsId(planDetails.getId());
            return basicApplyBatch;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
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

    /**
     * 获取房产经济耐用年限
     *
     * @param basicApply
     * @param basicBuilding
     * @return
     */
    public Integer getBuildingUsableYear(BasicApply basicApply, BasicBuilding basicBuilding) {
        Integer buildingUsableYear = 0;
        if (basicApply == null || basicBuilding == null) return buildingUsableYear;
        if (BasicApplyTypeEnum.RESIDENCE.getId().equals(basicApply.getType())) {
            BaseDataDic baseDataDic = baseDataDicService.getDataDicById(basicBuilding.getResidenceUseYear());
            if (baseDataDic != null)
                buildingUsableYear = Integer.valueOf(baseDataDic.getRemark());
        } else if (BasicApplyTypeEnum.INDUSTRY.getId().equals(basicApply.getType())) {
            DataBuildingNewRate buildingNewRate = dataBuildingNewRateService.getByiDdataBuildingNewRate(basicBuilding.getIndustryUseYear());
            buildingUsableYear = buildingNewRate.getDurableLife();
        }
        return buildingUsableYear;
    }

    //修改申报实际用途
    public void updateDeclarePracticalUse(ProjectPlanDetails projectPlanDetails) {
        if (projectPlanDetails.getDeclareRecordId() != null) {
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
            if (declareRecord != null) {
                try {
                    BasicApply basicApply = basicApplyService.getBasicApplyByPlanDetailsId(projectPlanDetails.getId());
                    BasicHouse house = basicHouseService.getBasicHouseById(basicApply.getBasicHouseId());
                    if (house.getPracticalUse() != null) {
                        declareRecord.setPracticalUse(baseDataDicService.getNameById(house.getPracticalUse()));
                        declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

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

}
