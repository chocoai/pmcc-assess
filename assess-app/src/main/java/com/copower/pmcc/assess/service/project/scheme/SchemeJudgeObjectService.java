package com.copower.pmcc.assess.service.project.scheme;


import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeObjectDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeProgrammeDto;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private SchemeJudgeObjectDao schemeJudgeObjectDao;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private SchemeJudgeFunctionService schemeJudgeFunctionService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProjectPhaseService projectPhaseService;

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

    /**
     * 获取估价对象数据列表
     *
     * @param pid
     * @return
     */
    public BootstrapTableVo getJudgeObjectListByPid(Integer pid) {
        BootstrapTableVo vo = new BootstrapTableVo();
        SchemeJudgeObject schemeJudgeObject=new SchemeJudgeObject();
        schemeJudgeObject.setPid(pid);
        schemeJudgeObject.setBisEnable(true);
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getJudgeObjectList(schemeJudgeObject);
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(judgeObjectList) ? new ArrayList<SchemeJudgeObject>() : judgeObjectList);
        vo.setTotal(page.getTotal());
        return vo;
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
        schemeJudgeObject.setBisSetFunction(false);
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
            if (schemeJudgeObject.getSplitNumber() != null && schemeJudgeObject.getSplitNumber() > 0)
                numberBuilder.append("-").append(schemeJudgeObject.getSplitNumber());
            numberBuilder.append(",");
        }
        mergeJudgeObject.setId(null);
        mergeJudgeObject.setPid(0);
        mergeJudgeObject.setSplitNumber(null);
        mergeJudgeObject.setNumber(numberBuilder.toString().replaceAll(",$", ""));
        mergeJudgeObject.setName("");
        mergeJudgeObject.setOwnership("");
        mergeJudgeObject.setSeat("");
        mergeJudgeObject.setFloorArea(floorAreaTotal);
        mergeJudgeObject.setEvaluationArea(evaluationAreaTotal);
        mergeJudgeObject.setBisSplit(false);
        mergeJudgeObject.setBisEnable(true);
        mergeJudgeObject.setBisMerge(true);
        mergeJudgeObject.setBisSetFunction(false);
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

    /**
     * 保存区域下方案
     *
     * @param schemeProgrammeDto
     */
    @Transactional
    public void saveProgrammeArea(SchemeProgrammeDto schemeProgrammeDto) throws BusinessException {
        if (schemeProgrammeDto == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.get(schemeProgrammeDto.getAreaGroupId());
        schemeAreaGroup.setValueTimePoint(schemeProgrammeDto.getValueTimePoint());
        schemeAreaGroup.setTimePointExplain(schemeProgrammeDto.getTimePointExplain());
        schemeAreaGroupService.saveAreaGroup(schemeAreaGroup);

        List<SchemeJudgeObject> schemeJudgeObjects = schemeProgrammeDto.getSchemeJudgeObjects();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjects)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjects) {
                if (schemeJudgeObject.getId() != null && schemeJudgeObject.getId() > 0) {
                    schemeJudgeObject.setGmtModified(new Date());
                    schemeJudgeObjectDao.updateSchemeJudgeObject(schemeJudgeObject);
                }
            }
        }
    }

    /**
     * 保存方案所有内容
     *
     * @param schemeProgrammeDtos
     */
    @Transactional
    public void saveProgrammeAll(List<SchemeProgrammeDto> schemeProgrammeDtos) throws BusinessException {
        if (schemeProgrammeDtos == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        for (SchemeProgrammeDto schemeProgrammeDto : schemeProgrammeDtos) {
            saveProgrammeArea(schemeProgrammeDto);
        }
    }


    /**
     * 提交方案
     *
     * @param schemeProgrammeDtos
     */
    @Transactional
    public void submitProgramme(Integer projectId, Integer planId, List<SchemeProgrammeDto> schemeProgrammeDtos) throws BusinessException {
        //1.验证数据的完整性与准确性，查看评估方法是否都已设置
        //2.清除该计划下的所有任务，保存方案数据
        //3.生成计划任务
        int count = schemeJudgeObjectDao.getNotSetFunctionCount(projectId);
        if (count > 0)
            throw new BusinessException("还是委估对象未设置评估方法请检查");
        saveProgrammeAll(schemeProgrammeDtos);
        projectPlanDetailsService.deletePlanDetailsByPlanId(planId);
        List<SchemeAreaGroup> areaGroupList = schemeAreaGroupService.getAreaGroupList(projectId);
        if (CollectionUtils.isNotEmpty(areaGroupList)) {
            ProjectPlan projectPlan = projectPlanService.getProjectplanById(planId);
            int i = 0;
            Map<Integer, ProjectPhase> phaseMap = getProjectPhaseMap();
            for (SchemeAreaGroup schemeAreaGroup : areaGroupList) {
                ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
                projectPlanDetails.setProjectWorkStageId(projectPlan.getWorkStageId());
                projectPlanDetails.setPlanId(projectPlan.getId());
                projectPlanDetails.setProjectId(projectPlan.getProjectId());
                projectPlanDetails.setProjectPhaseName(schemeAreaGroup.getAreaName());
                projectPlanDetails.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                projectPlanDetails.setBisLastLayer(false);
                projectPlanDetails.setSorting(i++);
                projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetails);

                List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getSchemeJudgeObjectList(schemeAreaGroup.getId());
                if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                    int j = 0;
                    for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
                        ProjectPlanDetails planDetails = new ProjectPlanDetails();
                        planDetails.setProjectWorkStageId(projectPlan.getWorkStageId());
                        planDetails.setPlanId(projectPlan.getId());
                        planDetails.setProjectId(projectPlan.getProjectId());
                        StringBuilder phaseName = new StringBuilder(schemeJudgeObject.getNumber());
                        if (schemeJudgeObject.getSplitNumber() != null && schemeJudgeObject.getSplitNumber() > 0) {
                            phaseName.append("-").append(schemeJudgeObject.getSplitNumber());
                        }
                        phaseName.append("号委估对象");
                        planDetails.setProjectPhaseName(phaseName.toString());
                        planDetails.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                        planDetails.setPid(projectPlanDetails.getId());
                        planDetails.setBisLastLayer(false);
                        planDetails.setSorting(j++);
                        projectPlanDetailsDao.addProjectPlanDetails(planDetails);

                        List<SchemeJudgeFunction> judgeFunctions = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                        if (CollectionUtils.isNotEmpty(judgeFunctions)) {
                            int k = 0;
                            for (SchemeJudgeFunction judgeFunction : judgeFunctions) {
                                ProjectPhase projectPhase = phaseMap.get(judgeFunction.getMethodType());
                                ProjectPlanDetails details = new ProjectPlanDetails();
                                details.setProjectWorkStageId(projectPlan.getWorkStageId());
                                details.setPlanId(projectPlan.getId());
                                details.setProjectId(projectPlan.getProjectId());
                                details.setProjectPhaseName(projectPhase.getProjectPhaseName());
                                details.setProjectPhaseId(projectPhase.getId());
                                details.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                                details.setPid(planDetails.getId());
                                details.setBisLastLayer(true);
                                details.setSorting(k++);
                                projectPlanDetailsDao.addProjectPlanDetails(details);
                            }
                        }
                    }
                }
            }
        }


    }

    private Map<Integer, ProjectPhase> getProjectPhaseMap() {
        //1.取得该阶段所有事项 2.取得方法下字典数据配置
        Map<Integer, ProjectPhase> map = Maps.newHashMap();
        List<BaseDataDic> methodList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);
        for (BaseDataDic method : methodList) {
            ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(method.getFieldName());
            if (projectPhase != null)
                map.put(method.getId(), projectPhase);
        }
        return map;
    }

}
