package com.copower.pmcc.assess.controller.project.survey;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.DataExamineTask;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SurveyExamineTask;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.service.data.DataExamineTaskService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.survey.SurveyCaseStudyService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.assess.service.project.survey.SurveyExamineTaskService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kings on 2018-7-6.
 */
@Controller
@RequestMapping("/surveyCaseStudy")
public class SurveyCaseStudyController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private SurveyCaseStudyService surveyCaseStudyService;
    @Autowired
    private DataExamineTaskService dataExamineTaskService;
    @Autowired
    private SurveyExamineTaskService surveyExamineTaskService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private CommonService commonService;


    @ResponseBody
    @GetMapping(name = "获取计划相关调查任务", value = "/getPlanTaskExamineList")
    public BootstrapTableVo getPlanTaskExamineList(Integer planDetailsId) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        List<ProjectPlanDetailsVo> caseTaskList = surveyCommonService.getPlanTaskExamineList(planDetailsId);
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(caseTaskList) ? Lists.newArrayList() : caseTaskList);
        bootstrapTableVo.setTotal((long) caseTaskList.size());
        return bootstrapTableVo;
    }

    @ResponseBody
    @PostMapping(name = "保存案例任务,并且分派", value = "/saveCaseTask")
    public HttpResult saveCaseTask(String formData, Integer planDetailsId, String examineFormType) {
        try {
            ProjectPlanDetails projectPlanDetails = JSON.parseObject(formData, ProjectPlanDetails.class);
            surveyCaseStudyService.saveCaseTask(projectPlanDetails, planDetailsId);
            //com.copower.pmcc.assess.service.project.survey.SurveyExamineTaskService.confirmAssignment()
            ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
            if (StringUtils.isNotBlank(examineFormType)) {
                DataExamineTask dataExamineTask = null;
                if (Objects.equal(AssessExamineTaskConstant.FC_RESIDENCE, examineFormType)) {
                    dataExamineTask = dataExamineTaskService.getCacheDataExamineTaskByFieldName(AssessExamineTaskConstant.FC_RESIDENCE);
                }
                if (Objects.equal(AssessExamineTaskConstant.FC_INDUSTRY, examineFormType)) {
                    dataExamineTask = dataExamineTaskService.getCacheDataExamineTaskByFieldName(AssessExamineTaskConstant.FC_INDUSTRY);
                }
                List<DataExamineTask> dataExamineTaskList = null;
                List<DataExamineTask> examineTaskList = new ArrayList<DataExamineTask>(10);
                if (dataExamineTask != null) {
                    dataExamineTaskList = dataExamineTaskService.getCacheDataExamineTaskListByKey(dataExamineTask.getFieldName());
                }
                if (CollectionUtils.isNotEmpty(dataExamineTaskList)) {
                    for (DataExamineTask examineTask:dataExamineTaskList){
                        List<DataExamineTask> dataExamineTasks =  dataExamineTaskService.getCacheDataExamineTaskListByKey(examineTask.getFieldName());
                        if (CollectionUtils.isNotEmpty(dataExamineTasks)){
                            examineTaskList.addAll(dataExamineTasks);
                        }
                    }
                }
                if (CollectionUtils.isNotEmpty(examineTaskList)){
                    for (DataExamineTask examineTask:examineTaskList){
                        SurveyExamineTask surveyExamineTask = new SurveyExamineTask();
                        surveyExamineTask.setPid(planDetails.getPid());
                        surveyExamineTask.setUserAccount(planDetails.getExecuteUserAccount());
                        surveyExamineTask.setBisMust(examineTask.getBisMust());
                        surveyExamineTask.setPlanDetailsId(planDetails.getId());
                        surveyExamineTask.setName(examineTask.getName());
                        surveyExamineTask.setSorting(examineTask.getSorting());
                        surveyExamineTask.setExamineType(ExamineTypeEnum.CASE.getId());
                        surveyExamineTask.setDeclareId(planDetails.getDeclareRecordId());
                        surveyExamineTask.setPlanDetailsId(planDetails.getId());
                        surveyExamineTask.setDataTaskId(examineTask.getId());
                        surveyExamineTask.setTaskStatus(ProjectStatusEnum.WAIT.getKey());
                        surveyExamineTask.setCreator(commonService.thisUserAccount());
                        surveyExamineTaskService.saveSurveyExamineTask(surveyExamineTask);
                    }
                }
            }
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("保存案例任务", e);
            return HttpResult.newErrorResult("保存案例任务异常");
        }
    }

    @ResponseBody
    @PostMapping(name = "删除案例任务", value = "/deleteCaseTask")
    public HttpResult deleteCaseTask(Integer id) {
        try {
            surveyCaseStudyService.deleteCaseTask(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("删除案例任务", e);
            return HttpResult.newErrorResult("删除案例任务");
        }
    }

    @ResponseBody
    @PostMapping(name = "复制案例", value = "/copyCaseStudy")
    public HttpResult copyCaseStudy(Integer planDetailsId) {
        try {
            surveyCaseStudyService.copyCaseStudy(planDetailsId);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("复制案例", e);
            return HttpResult.newErrorResult("复制案例异常");
        }
    }

}
