package com.copower.pmcc.assess.service.project.scheme;


import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeObjectDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeJudgeObjectApplyDto;
import com.copower.pmcc.assess.service.SchemeAreaGroupService;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * 估价对象
 * Created by 13426 on 2018/5/21.
 */
@Service
public class SchemeJudgeObjectService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private SchemeEvaluationObjectService evaluationObjectService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private SchemeJudgeObjectDao schemeJudgeObjectDao;
    @Autowired
    private ProjectPlanDao projectPlanDao;

    /**
     * 保存委估对象
     *
     * @param applyDto
     */
    public void saveEvaluationObject(SchemeJudgeObjectApplyDto applyDto) {
        //先清除所有
        //1.保存估价对象信息 2.根据测算号生成委估对象 3.根据委估信息生成对应的计划目录

        List<SchemeJudgeObject> schemeJudgeObjects = applyDto.getSchemeJudgeObjects();
        ProjectPlan projectPlan = projectPlanDao.getProjectplanById(applyDto.getPlanId());

        SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.get(applyDto.getAreaGroupId());
        schemeAreaGroup.setValueTimePoint(applyDto.getValueTimePoint());
        schemeAreaGroupService.update(schemeAreaGroup);
        HashSet<Integer> hashSet = Sets.newHashSet();
        for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjects) {
            if (schemeJudgeObject.getId() != null && schemeJudgeObject.getId() > 0) {
                updateSchemeJudgeObject(schemeJudgeObject);
            } else {
                SchemeJudgeObject judgeObject = getSchemeJudgeObject(schemeJudgeObject.getSourceId());
                if (judgeObject != null) {
                    //1.先拷贝一份数据
                    judgeObject.setId(0);
                    judgeObject.setBestUseId(schemeJudgeObject.getBestUseId());
                    judgeObject.setGroupNumber(schemeJudgeObject.getGroupNumber());
                    judgeObject.setSplitNumber(schemeJudgeObject.getSplitNumber());
                    judgeObject.setEvaluationArea(schemeJudgeObject.getEvaluationArea());
                    judgeObject.setBisSplit(schemeJudgeObject.getBisSplit());
                    judgeObject.setSourceId(schemeJudgeObject.getSourceId());
                    addSchemeJudgeObject(judgeObject);
                }
            }
            hashSet.add(schemeJudgeObject.getGroupNumber());
        }

        //清空原生成的数据
        ProjectPlanDetails where = new ProjectPlanDetails();
        where.setProjectWorkStageId(projectPlan.getWorkStageId());
        where.setPlanId(projectPlan.getId());
        where.setProjectId(projectPlan.getProjectId());
        where.setAreaGroupId(applyDto.getAreaGroupId());
        List<ProjectPlanDetails> planDetailsList = projectPlanDetailsDao.getListObject(where);
        if (CollectionUtils.isNotEmpty(planDetailsList)) {
            for (ProjectPlanDetails projectPlanDetails : planDetailsList) {
                projectPlanDetailsDao.deleteProjectPlanDetails(projectPlanDetails.getId());
            }
        }

        //生成数据
        ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
        projectPlanDetails.setProjectWorkStageId(projectPlan.getWorkStageId());
        projectPlanDetails.setPlanId(projectPlan.getId());
        projectPlanDetails.setProjectId(projectPlan.getProjectId());
        projectPlanDetails.setProjectPhaseName(schemeAreaGroup.getAreaName());
        projectPlanDetails.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
        projectPlanDetails.setBisLastLayer(false);
        projectPlanDetails.setSorting(0);
        projectPlanDetails.setAreaGroupId(applyDto.getAreaGroupId());
        projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetails);

        hashSet.forEach(p -> {
            SchemeEvaluationObject schemeEvaluationObject = new SchemeEvaluationObject();
            schemeEvaluationObject.setProjectId(projectPlan.getProjectId());
            schemeEvaluationObject.setAreaGroupId(applyDto.getAreaGroupId());
            schemeEvaluationObject.setName(String.format("%s号委估对象", p));
            schemeEvaluationObject.setGroupNumber(p);
            schemeEvaluationObject.setCreator(commonService.thisUserAccount());
            evaluationObjectService.add(schemeEvaluationObject);

            ProjectPlanDetails details = new ProjectPlanDetails();
            details.setProjectWorkStageId(projectPlan.getWorkStageId());
            details.setPlanId(projectPlan.getId());
            details.setProjectId(projectPlan.getProjectId());
            details.setProjectPhaseName(schemeEvaluationObject.getName());
            details.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
            details.setGroupNumber(schemeEvaluationObject.getGroupNumber());
            details.setPid(projectPlanDetails.getId());
            details.setBisLastLayer(false);
            details.setSorting(p);
            details.setAreaGroupId(applyDto.getAreaGroupId());
            projectPlanDetailsDao.addProjectPlanDetails(details);

        });
    }


    public boolean addSchemeJudgeObject(SchemeJudgeObject schemeJudgeObject) {
        return schemeJudgeObjectDao.addSchemeJudgeObject(schemeJudgeObject);
    }

    public boolean updateSchemeJudgeObject(SchemeJudgeObject schemeJudgeObject) {
        return schemeJudgeObjectDao.updateSchemeJudgeObject(schemeJudgeObject);
    }


    public SchemeJudgeObject getSchemeJudgeObject(Integer id) {
        return schemeJudgeObjectDao.getSchemeJudgeObject(id);
    }

    /**
     * 获取估价对象数据列表
     * @param areaGroupId
     * @return
     */
    public List<SchemeJudgeObject> getSchemeJudgeObjectList(Integer areaGroupId) {
        return schemeJudgeObjectDao.getSchemeJudgeObjectList(areaGroupId);
    }

    public boolean removeSchemeJudgeObject(Integer id) {
        return schemeJudgeObjectDao.removeSchemeJudgeObject(id);
    }


}
