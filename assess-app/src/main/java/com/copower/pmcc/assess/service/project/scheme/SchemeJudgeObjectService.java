package com.copower.pmcc.assess.service.project.scheme;


import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeObjectDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeJudgeObjectApplyDto;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

            }
        }

        //清除计划任务数据
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
        //清除委估对象数据
        List<SchemeEvaluationObject> evaluationObjectList = evaluationObjectService.getDataListByGroupId(applyDto.getAreaGroupId(), projectPlan.getProjectId());
        if (CollectionUtils.isNotEmpty(evaluationObjectList)) {
            for (SchemeEvaluationObject schemeEvaluationObject : evaluationObjectList) {
                evaluationObjectService.remove(schemeEvaluationObject.getId());
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
     *
     * @param areaGroupId
     * @return
     */
    public List<SchemeJudgeObject> getSchemeJudgeObjectList(Integer areaGroupId) {
        return schemeJudgeObjectDao.getSchemeJudgeObjectList(areaGroupId);
    }

    public boolean removeSchemeJudgeObject(Integer id) {
        return schemeJudgeObjectDao.removeSchemeJudgeObject(id);
    }

    /**
     * 更新委估对象区域分组id
     *
     * @param oldAreaGroupId
     * @param newAreaGroupId
     * @return
     */
    public boolean updateSchemeJudgeObject(Integer oldAreaGroupId, Integer newAreaGroupId) {
        return schemeJudgeObjectDao.updateSchemeJudgeObject(oldAreaGroupId, newAreaGroupId);
    }

    /**
     * 权证所属区域还原
     *
     * @param originalAreaGroupId
     * @return
     */
    public boolean areaGroupReduction(Integer originalAreaGroupId) {
        return schemeJudgeObjectDao.areaGroupReduction(originalAreaGroupId);
    }

    /**
     * 权证拆分
     *
     * @param id
     */
    @Transactional
    public void splitJudge(Integer projectId, Integer areaGroupId, Integer id) {
        //1.找出该权证已拆分的数据，以确定新权证的拆分序号
        //2.添加拆分数据,如果没有拆分数据，则需将主数据的拆分号设置为1
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectDao.getSchemeJudgeObject(id);
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByNumber(projectId, areaGroupId, schemeJudgeObject.getNumber());
        if (judgeObjectList.size() == 1) {
            schemeJudgeObject.setSplitNumber(1);
            schemeJudgeObjectDao.updateSchemeJudgeObject(schemeJudgeObject);
        }
        schemeJudgeObject.setId(null);
        schemeJudgeObject.setSplitNumber(judgeObjectList.size() + 1);
        schemeJudgeObject.setBisSplit(true);
        schemeJudgeObjectDao.addSchemeJudgeObject(schemeJudgeObject);
    }

    /**
     * 删除拆分出来的权证
     *
     * @param id
     */
    @Transactional
    public void delSplitJudge(Integer projectId, Integer areaGroupId, Integer id) {
        //1.删除该条数据 2.将相同委估对象编号下的拆分数据的拆分号重新编号
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectDao.getSchemeJudgeObject(id);
        String number = schemeJudgeObject.getNumber();
        Integer splitNumber = schemeJudgeObject.getSplitNumber();
        schemeJudgeObjectDao.removeSchemeJudgeObject(id);
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByNumber(projectId, areaGroupId, number);
        if (CollectionUtils.isEmpty(judgeObjectList)) return;
        SchemeJudgeObject judgeObject = null;
        if (judgeObjectList.size() == 1) {
            judgeObject = judgeObjectList.get(0);
            judgeObject.setSplitNumber(0);
            schemeJudgeObjectDao.updateSchemeJudgeObject(judgeObject);
        } else {
            for (SchemeJudgeObject object : judgeObjectList) {
                if (object.getSplitNumber() >= splitNumber) {
                    object.setSplitNumber(splitNumber);
                    schemeJudgeObjectDao.updateSchemeJudgeObject(object);
                    splitNumber++;
                }
            }
        }
    }

    /**
     * 委估对象合并
     *
     * @param ids
     */
    @Transactional
    public void mergeJudge(String ids) throws BusinessException {
        //1.循环需要合并的委估对象，将委估对象编号、权证号、所有权人、坐落、证载面积、评估面积等信息进行合并；其它信息取第一个权证信息
        //2.添加出合并委估对象，将参与合并的委估对象pid设置为新委估对象id，参与合并的委估对象设置为不可用

        //先验证如果不是同一区域的委估对象，则不允许合并

        List<Integer> judgeIds = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(ids));
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeIds);
        if (CollectionUtils.isEmpty(judgeObjectList) || judgeObjectList.size() <= 1)
            throw new BusinessException("参与合并的委估对象至少为两个");
        SchemeJudgeObject mergeJudgeObject = new SchemeJudgeObject();
        BeanUtils.copyProperties(judgeObjectList.get(0), mergeJudgeObject);
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            if (!mergeJudgeObject.getAreaGroupId().equals(schemeJudgeObject.getAreaGroupId()))
                throw new BusinessException("参与合并的委估对象必须属于同一区域");
        }

        StringBuilder numberBuilder = new StringBuilder();
        StringBuilder nameBuilder = new StringBuilder();
        StringBuilder ownershipBuilder = new StringBuilder();
        StringBuilder seatBuilder = new StringBuilder();
        BigDecimal floorAreaTotal = new BigDecimal("0");
        BigDecimal evaluationAreaTotal = new BigDecimal("0");
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            //权证与坐落相同部分只取一次，暂不处理
            //委估对象编号合并
            numberBuilder.append(schemeJudgeObject.getNumber());
            if(schemeJudgeObject.getSplitNumber()!=null&&schemeJudgeObject.getSplitNumber()>0)
                numberBuilder.append("-").append(schemeJudgeObject.getSplitNumber());
            numberBuilder.append(",");

            nameBuilder.append(schemeJudgeObject.getName()).append(",");
            ownershipBuilder.append(schemeJudgeObject.getOwnership()).append(",");
            seatBuilder.append(schemeJudgeObject.getSeat()).append(",");
            if (schemeJudgeObject.getFloorArea() != null)
                floorAreaTotal = floorAreaTotal.add(schemeJudgeObject.getFloorArea());
            if (schemeJudgeObject.getEvaluationArea() != null)
                evaluationAreaTotal = evaluationAreaTotal.add(schemeJudgeObject.getEvaluationArea());
        }
        mergeJudgeObject.setId(null);
        mergeJudgeObject.setNumber(numberBuilder.toString().replaceAll(",$", ""));
        mergeJudgeObject.setName(nameBuilder.toString().replaceAll(",$", ""));
        mergeJudgeObject.setOwnership(ownershipBuilder.toString().replaceAll(",$", ""));
        mergeJudgeObject.setSeat(seatBuilder.toString().replaceAll(",$", ""));
        mergeJudgeObject.setFloorArea(floorAreaTotal);
        mergeJudgeObject.setEvaluationArea(evaluationAreaTotal);
        mergeJudgeObject.setBisSplit(false);
        mergeJudgeObject.setBisEnable(true);
        mergeJudgeObject.setBisMerge(true);
        mergeJudgeObject.setCreator(commonService.thisUserAccount());
        schemeJudgeObjectDao.addSchemeJudgeObject(mergeJudgeObject);

        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            schemeJudgeObject.setPid(mergeJudgeObject.getId());
            schemeJudgeObject.setBisEnable(false);
            schemeJudgeObjectDao.updateSchemeJudgeObject(schemeJudgeObject);
        }
    }

    /**
     * 委估对象合并取消
     *
     * @param id
     */
    @Transactional
    public void mergeJudgeCancel(Integer id) {
        //1.将原参与合并的委估对象设置为可用，删除该合并的委估对象
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectDao.getSchemeJudgeObject(id);
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByPid(schemeJudgeObject.getId());
        for (SchemeJudgeObject judgeObject : judgeObjectList) {
            judgeObject.setBisEnable(true);
            schemeJudgeObjectDao.updateSchemeJudgeObject(judgeObject);
        }
        schemeJudgeObjectDao.removeSchemeJudgeObject(id);
    }
}
