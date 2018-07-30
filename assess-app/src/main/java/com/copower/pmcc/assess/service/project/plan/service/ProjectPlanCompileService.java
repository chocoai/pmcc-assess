package com.copower.pmcc.assess.service.project.plan.service;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeAreaGroupDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeEvaluationObjectDao;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by zly on 2018/5/21.
 */
@Service
public class ProjectPlanCompileService {

    @Autowired
    private SchemeAreaGroupDao schemeAreaGroupDao;
    @Autowired
    private SchemeEvaluationObjectDao schemeEvaluationObjectDao;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ErpAreaService erpAreaService;

    public ModelAndView getInitialize(ModelAndView modelAndView, ProjectPlan projectPlan) {
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY);
        int size = baseDataDics.size();
        modelAndView.addObject("reportAnalysisList", baseDataDics);

        Integer planId = projectPlan.getId();
        Integer projectId = projectPlan.getProjectId();
        Integer workStageId = projectPlan.getWorkStageId();
        ProjectPlanDetails projectPlanDetailsWhere=new ProjectPlanDetails();
        projectPlanDetailsWhere.setProjectId(projectId);
        projectPlanDetailsWhere.setPlanId(planId);
        List<ProjectPlanDetails> planDetails = projectPlanDetailsDao.getListObject(projectPlanDetailsWhere);
        if (CollectionUtils.isNotEmpty(planDetails)) {
            return modelAndView;//避免重复初始化
        }
        List<SchemeAreaGroup> schemeAreaGroups = schemeAreaGroupDao.getSchemeAreaGroupByProjectId(projectId);
        String address = "";
        int i = 1;
        //一级分类 地址
        if (CollectionUtils.isNotEmpty(schemeAreaGroups)) {
            for (SchemeAreaGroup schemeAreaGroup : schemeAreaGroups) {
                ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
                projectPlanDetails.setProjectWorkStageId(workStageId);
                projectPlanDetails.setPlanId(planId);
                projectPlanDetails.setProjectId(projectId);
                projectPlanDetails.setProjectPhaseName(schemeAreaGroup.getProvinceCityDistrictStr());
                projectPlanDetails.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                projectPlanDetails.setBisLastLayer(false);
                projectPlanDetails.setSorting(i++);
                projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetails);

                int j = 1;
                Integer pid = 0;
                Integer groupId = schemeAreaGroup.getId();
                List<SchemeEvaluationObject> evaluationObjects = schemeEvaluationObjectDao.getDataListByGroupId(groupId, projectId);
                //二级分类 评估对象
                pid = projectPlanDetails.getId();

                String name = "";
                Integer projectPhaseId = 0;
                if (CollectionUtils.isNotEmpty(evaluationObjects)) {
                    for (SchemeEvaluationObject evaluationObject : evaluationObjects) {
                        name = evaluationObject.getName();
                        projectPhaseId = evaluationObject.getId();
                        ProjectPlanDetails projectPlanDetailTwo = new ProjectPlanDetails();
                        projectPlanDetailTwo.setProjectWorkStageId(workStageId);
                        projectPlanDetailTwo.setPlanId(planId);
                        projectPlanDetailTwo.setProjectId(projectId);
                        projectPlanDetailTwo.setProjectPhaseName(name);
                        projectPlanDetailTwo.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                        projectPlanDetailTwo.setPid(pid);
                        projectPlanDetailTwo.setFirstPid(pid);
                        projectPlanDetailTwo.setProjectPhaseId(projectPhaseId);
                        projectPlanDetailTwo.setSorting(j++);
                        projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetailTwo);
                    }
                }
            }
        }
        return modelAndView;
    }
}
