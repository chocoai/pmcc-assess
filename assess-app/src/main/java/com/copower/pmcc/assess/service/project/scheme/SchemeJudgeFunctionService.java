package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeFunctionDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeFunction;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeJudgeFunctionApplyDto;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.common.CommonService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 13426 on 2018/5/21.
 */
@Service
public class SchemeJudgeFunctionService {
    @Autowired
    private SchemeJudgeFunctionDao schemeJudgeFunctionDao;
    @Autowired
    private SchemeJudgeFunctionService schemeJudgeFunctionService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProjectPhaseService projectPhaseService;


    public boolean addSchemeJudgeFunction(SchemeJudgeFunction schemeJudgeFunction) {
        return schemeJudgeFunctionDao.addSchemeJudgeFunction(schemeJudgeFunction);
    }

    public List<SchemeJudgeFunction> getSchemeJudgeFunctions(Integer areaGroupId, Integer groupNumber) {
        SchemeJudgeFunction schemeJudgeFunction = new SchemeJudgeFunction();
        schemeJudgeFunction.setAreaGroupId(areaGroupId);
        schemeJudgeFunction.setGroupNumber(groupNumber);
        return schemeJudgeFunctionDao.getSchemeJudgeFunction(schemeJudgeFunction);
    }


    public boolean updateSchemeJudgeFunction(SchemeJudgeFunction schemeJudgeFunction) {
        return schemeJudgeFunctionDao.updateSchemeJudgeFunction(schemeJudgeFunction);
    }

    public SchemeJudgeFunction getSchemeJudgeFunction(Integer id) {
        return schemeJudgeFunctionDao.getSchemeJudgeFunction(id);
    }

    public boolean removeSchemeJudgeFunction(Integer id) {
        return schemeJudgeFunctionDao.removeSchemeJudgeFunction(id);
    }

    /**
     * 保存评估方法
     *
     * @param applyDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveJudgeFunction(SchemeJudgeFunctionApplyDto applyDto) {
        List<SchemeJudgeFunction> judgeFunctionList = applyDto.getJudgeFunctionList();
        if (CollectionUtils.isNotEmpty(judgeFunctionList)) {
            judgeFunctionList.forEach(p -> {
                if (p.getId() != null && p.getId() > 0) {
                    schemeJudgeFunctionService.updateSchemeJudgeFunction(p);
                } else {
                    p.setCreator(commonService.thisUserAccount());
                    schemeJudgeFunctionService.addSchemeJudgeFunction(p);
                }
            });
        }

        //先清除原添加的数据
        ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
        projectPlanDetails.setAreaGroupId(applyDto.getAreaGroupId());
        projectPlanDetails.setGroupNumber(applyDto.getGroupNumber());
        projectPlanDetails.setBisLastLayer(true);
        List<ProjectPlanDetails> list = projectPlanDetailsService.getProjectDetails(projectPlanDetails);
        if (CollectionUtils.isNotEmpty(list)) {
            for (ProjectPlanDetails details : list) {
                projectPlanDetailsService.deleteProjectPlanDetails(details.getId());
            }
        }

        projectPlanDetails = new ProjectPlanDetails();
        projectPlanDetails.setAreaGroupId(applyDto.getAreaGroupId());
        projectPlanDetails.setGroupNumber(applyDto.getGroupNumber());
        List<ProjectPlanDetails> planDetailsList = projectPlanDetailsService.getProjectDetails(projectPlanDetails);
        if (CollectionUtils.isNotEmpty(planDetailsList)) {
            ProjectPlanDetails projectPlan = planDetailsList.get(0);
            List<SchemeJudgeFunction> schemeJudgeFunctions = schemeJudgeFunctionService.getSchemeJudgeFunctions(applyDto.getAreaGroupId(), applyDto.getGroupNumber());
            if (CollectionUtils.isNotEmpty(schemeJudgeFunctions)) {
                int i = 0;
                for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctions) {
                    if (Boolean.TRUE == schemeJudgeFunction.getBisApplicable()) {
                        ProjectPlanDetails details = new ProjectPlanDetails();
                        details.setProjectWorkStageId(projectPlan.getProjectWorkStageId());
                        details.setPlanId(projectPlan.getPlanId());
                        details.setProjectId(projectPlan.getProjectId());
                        details.setProjectPhaseName(schemeJudgeFunction.getName());
                        details.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                        details.setGroupNumber(projectPlan.getGroupNumber());
                        details.setPid(projectPlan.getId());
                        details.setBisLastLayer(true);
                        details.setSorting(i);
                        details.setAreaGroupId(applyDto.getAreaGroupId());
                        //确定任务关联的事项
                        ProjectPhase projectPhase = getProjectPhaseByMethodType(schemeJudgeFunction.getMethodType());
                        if (projectPhase == null) continue;
                        details.setProjectPhaseId(projectPhase.getId());
                        details.setProjectPhaseName(projectPhase.getProjectPhaseName());
                        projectPlanDetailsDao.addProjectPlanDetails(details);
                        i++;
                    }
                }
            }
        }
    }

    /**
     * 根据方法类型确定对应的工作事项
     *
     * @param methodType
     * @return
     */
    public ProjectPhase getProjectPhaseByMethodType(Integer methodType) {
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(methodType);
        if (baseDataDic == null) return null;
        return projectPhaseService.getCacheProjectPhaseByKey(baseDataDic.getFieldName());
    }
}
