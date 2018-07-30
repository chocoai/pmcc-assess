package com.copower.pmcc.assess.service.project.scheme;


import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeObjectDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeEvaluationObjectDto;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeJudgeObjectApplyDto;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeJudgeObjectVo;
import com.copower.pmcc.assess.service.SchemeAreaGroupService;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private SchemeJudgeObjectDao dao;
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
        //1.保存估价对象信息 2.根据测算号生成委估对象 3.根据委估信息生成对应的计划目录

        List<SchemeJudgeObject> schemeJudgeObjects = applyDto.getSchemeJudgeObjects();
        ProjectPlan projectPlan = projectPlanDao.getProjectplanById(applyDto.getPlanId());

        SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.get(applyDto.getAreaGroupId());
        schemeAreaGroup.setValueTimePoint(applyDto.getValueTimePoint());
        schemeAreaGroupService.update(schemeAreaGroup);
        HashSet<Integer> hashSet = Sets.newHashSet();
        for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjects) {
            if (schemeJudgeObject.getId() != null && schemeJudgeObject.getId() > 0) {
                update(schemeJudgeObject);
            } else {
                SchemeJudgeObject judgeObject = get(schemeJudgeObject.getSourceId());
                if (judgeObject != null) {
                    //1.先拷贝一份数据
                    judgeObject.setId(0);
                    judgeObject.setBestUseId(schemeJudgeObject.getBestUseId());
                    judgeObject.setGroupNumber(schemeJudgeObject.getGroupNumber());
                    judgeObject.setSplitNumber(schemeJudgeObject.getSplitNumber());
                    judgeObject.setEvaluationArea(schemeJudgeObject.getEvaluationArea());
                    judgeObject.setSourceId(schemeJudgeObject.getSourceId());
                    add(judgeObject);
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
        projectPlanDetails.setProjectPhaseName(schemeAreaGroup.getProvinceCityDistrictStr());
        projectPlanDetails.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
        projectPlanDetails.setBisLastLayer(false);
        projectPlanDetails.setSorting(0);
        projectPlanDetails.setAreaGroupId(applyDto.getAreaGroupId());
        projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetails);

        hashSet.forEach(p -> {
            SchemeEvaluationObject schemeEvaluationObject = new SchemeEvaluationObjectDto();
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
            details.setEvaluationId(schemeEvaluationObject.getId());
            details.setPid(projectPlanDetails.getId());
            details.setBisLastLayer(false);
            details.setSorting(p);
            details.setAreaGroupId(applyDto.getAreaGroupId());
            projectPlanDetailsDao.addProjectPlanDetails(details);

            //找出适用方法
            //先根据委估对象 找到委估对象下的所有估价对象 并找出这些估价对象中挂有评估方法且为适用的方法数据

            List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getSchemeJudgeObjectList(schemeEvaluationObject.getId());
            if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                List<Integer> judgeIds = LangUtils.transform(judgeObjectList, o -> o.getId());

            }

        });
    }

    @Transactional
    public boolean add(SchemeJudgeObject dto) {
        return dao.add(dto);
    }

    @Transactional
    public boolean update(SchemeJudgeObject dto) {
        return dao.update(dto);
    }

    @Transactional(readOnly = true)
    public SchemeJudgeObject get(Integer id) {
        return dao.get(id);
    }

    @Transactional
    public boolean remove(Integer id) {
        return dao.remove(id);
    }

    public List<SchemeJudgeObjectVo> listGroupId(Integer areaGroupId) {
        List<SchemeJudgeObjectVo> schemeJudgeObjectVos = new ArrayList<>();
        for (SchemeJudgeObject s : dao.list(areaGroupId)) {
            schemeJudgeObjectVos.add(change(s));
        }
        return schemeJudgeObjectVos;
    }

    public SchemeJudgeObjectVo change(SchemeJudgeObject object) {
        SchemeJudgeObjectVo vo = new SchemeJudgeObjectVo();
        BeanUtils.copyProperties(object, vo);
        return vo;
    }

}
