package com.copower.pmcc.assess.service.project.survey;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomSurveyExamineTask;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyExamineTaskDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyExamineTaskDto;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyExamineTaskVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.ResidueRatioService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.data.DataExamineTaskService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * Created by zly on 2018/5/15.
 */
@Service
public class SurveyExamineTaskService {
    @Autowired
    private SurveyExamineTaskDao surveyExamineTaskDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private DataExamineTaskService dataExamineTaskService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private SurveyExamineTaskService surveyExamineTaskService;
    @Autowired
    private BasicApplyService basicApplyService;

    /**
     * 获取调查任务
     *
     * @param planDetailsId
     * @return
     */
    public List<SurveyExamineTask> getTaskListByPlanDetailsId(Integer planDetailsId) {
        SurveyExamineTask surveyExamineTask = new SurveyExamineTask();
        surveyExamineTask.setPlanDetailsId(planDetailsId);
        return surveyExamineTaskDao.getSurveyExamineTaskList(surveyExamineTask);
    }



    /**
     * 保存调查任务
     *
     * @param surveyExamineTask
     * @return
     */
    public void saveSurveyExamineTask(SurveyExamineTask surveyExamineTask) throws BusinessException {
        if (surveyExamineTask == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (surveyExamineTask.getId() != null && surveyExamineTask.getId() > 0) {
            surveyExamineTaskDao.updateSurveyExamineTask(surveyExamineTask);
        } else {
            surveyExamineTask.setCreator(commonService.thisUserAccount());
            surveyExamineTaskDao.addSurveyExamineTask(surveyExamineTask);
        }
    }

    /**
     * 删除计划及待提交任务
     *
     * @param list
     */
    @Transactional(rollbackFor = Exception.class)
    public void deletePlanDetailsAndTask(List<ProjectPlanDetails> list) throws Exception {
        if (CollectionUtils.isEmpty(list)) return;
        for (ProjectPlanDetails projectDetail : list) {
            //删除
            projectPlanDetailsService.deleteProjectPlanDetails(projectDetail.getId());
            //找出待提交任务-删除
            ProjectResponsibilityDto projectResponsibilityDto = new ProjectResponsibilityDto();
            projectResponsibilityDto.setProjectId(projectDetail.getProjectId());
            projectResponsibilityDto.setPlanDetailsId(projectDetail.getId());
            List<ProjectResponsibilityDto> projectTaskList = bpmRpcProjectTaskService.getProjectTaskList(projectResponsibilityDto);
            if (CollectionUtils.isNotEmpty(projectTaskList)) {
                for (ProjectResponsibilityDto responsibilityDto : projectTaskList) {
                    //删除此任务
                    bpmRpcProjectTaskService.deleteProjectTask(responsibilityDto.getId());
                }
            }
            //删除查勘过程数据
            BasicApply basicApply = basicApplyService.getBasicApplyByPlanDetailsId(projectDetail.getId());
            if (basicApply != null) {
                basicEstateService.clearInvalidData(basicApply.getId());
                basicBuildingService.clearInvalidData(basicApply.getId());
                basicUnitService.clearInvalidData(basicApply.getId());
                basicHouseService.clearInvalidData(basicApply.getId());
                basicApplyService.deleteBasicApply(basicApply.getId());
            }
        }
    }


}
