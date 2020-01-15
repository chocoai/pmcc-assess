package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeAreaGroupDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeObjectDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeAreaGroupVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.method.MdIncomeService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * 区域分组
 * Created by 13426 on 2018/5/14.
 */
@Service
public class SchemeAreaGroupService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private SchemeJudgeObjectDao schemeJudgeObjectDao;
    @Autowired
    private DeclareRecordDao declareRecordDao;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private SchemeAreaGroupDao schemeAreaGroupDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private MdIncomeService mdIncomeService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private ProjectPhaseService projectPhaseService;

    public int add(SchemeAreaGroup schemeAreaGroup) {
        return schemeAreaGroupDao.add(schemeAreaGroup);
    }

    /**
     * 根据分组 获取数据信息
     *
     * @param projectId
     * @return
     */
    public List<SchemeAreaGroup> getAreaGroupEnableByProjectId(Integer projectId) {
        List<SchemeAreaGroup> schemeAreaGroupList = schemeAreaGroupDao.getAreaGroupEnableByProjectId(projectId);
        return schemeAreaGroupList;
    }

    public List<SchemeAreaGroup> getAreaGroupAllByProjectId(Integer projectId) {
        List<SchemeAreaGroup> schemeAreaGroupList = schemeAreaGroupDao.getAreaGroupAllByProjectId(projectId);
        return schemeAreaGroupList;
    }

    /**
     * 申报记录信息按区域分组
     *
     * @param declareRecords
     * @return
     */
    public List<SchemeAreaGroup> groupDeclareRecord(List<DeclareRecord> declareRecords) {
        if (CollectionUtils.isEmpty(declareRecords)) return null;
        //1.查看区域信息
        HashSet<String> hashSet = Sets.newHashSet();
        String areaKey = "";
        for (DeclareRecord declareRecord : declareRecords) {
            areaKey = "";
            if (StringUtils.isNotBlank(declareRecord.getProvince())) {
                areaKey += declareRecord.getProvince() + "_";
            }
            if (StringUtils.isNotBlank(declareRecord.getCity())) {
                areaKey += declareRecord.getCity() + "_";
            }
            if (StringUtils.isNotBlank(declareRecord.getDistrict())) {
                areaKey += declareRecord.getDistrict();
            }
            if (!hashSet.contains(areaKey)) {
                hashSet.add(areaKey);
            }
        }
        if (hashSet.isEmpty()) return null;
        List<SchemeAreaGroup> schemeAreaGroups = Lists.newArrayList();
        hashSet.forEach(p -> {
            SchemeAreaGroup schemeAreaGroup = new SchemeAreaGroup();
            schemeAreaGroup.setDistrict("");
            String[] areaIds = p.split("_");
            if (areaIds.length > 2)
                schemeAreaGroup.setDistrict(areaIds[2]);
            if (areaIds.length > 1)
                schemeAreaGroup.setCity(areaIds[1]);
            if (areaIds.length > 0)
                schemeAreaGroup.setProvince(areaIds[0]);
            schemeAreaGroups.add(schemeAreaGroup);
        });
        return schemeAreaGroups;
    }

    /**
     * 获取申报信息的区域分组
     *
     * @param projectId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public List<SchemeAreaGroupVo> getSchemeAreaGroupVos(Integer projectId) {
        List<SchemeAreaGroup> voList = this.getAreaGroupEnableByProjectId(projectId);
        if (CollectionUtils.isNotEmpty(voList))
            return LangUtils.transform(voList, o -> this.getSchemeAreaGroupVo(o));
        return null;
    }

    /**
     * 生成估价信息
     *
     * @param projectId
     */
    @Transactional(rollbackFor = Exception.class)
    public void generatorAreaGroup(Integer projectId) {
        //1.清除原相关数据 2.重新生成所有数据
        //1.清除估价对象，清除区域,清除区域相关的工作事项

        //1.先分析出区域，与上次生成的区域做比较，如果区域没有发生变化则不处理区域下挂的任务，如果有新增或删除则需处理
        List<DeclareRecord> declareRecords = declareRecordDao.getPartInDeclareRecordsByProjectId(projectId);
        List<SchemeAreaGroup> newAreaGroups = groupDeclareRecord(declareRecords);
        if (CollectionUtils.isEmpty(newAreaGroups)) return;
        List<SchemeAreaGroup> historyAreaGroups = getAreaGroupEnableByProjectId(projectId);
        List<SchemeAreaGroup> sameAreaGroups = Lists.newArrayList();//新旧一致的区域
        Iterator historyAreaGroupIterator = historyAreaGroups.iterator();
        while (historyAreaGroupIterator.hasNext()) {
            SchemeAreaGroup historyAreaGroup = (SchemeAreaGroup) historyAreaGroupIterator.next();
            Iterator newAreaGroupIterator = newAreaGroups.iterator();
            while (newAreaGroupIterator.hasNext()) {
                SchemeAreaGroup newAreaGroup = (SchemeAreaGroup) newAreaGroupIterator.next();
                boolean isSameProvince = StringUtils.equals(StringUtils.defaultString(historyAreaGroup.getProvince()), StringUtils.defaultString(newAreaGroup.getProvince()));
                boolean isSameCity = StringUtils.equals(StringUtils.defaultString(historyAreaGroup.getCity()), StringUtils.defaultString(newAreaGroup.getCity()));
                boolean isSameDistrict = StringUtils.equals(StringUtils.defaultString(historyAreaGroup.getDistrict()), StringUtils.defaultString(newAreaGroup.getDistrict()));
                if (isSameProvince && isSameCity && isSameDistrict) {
                    sameAreaGroups.add(historyAreaGroup);
                    historyAreaGroupIterator.remove();
                    newAreaGroupIterator.remove();
                }
            }
        }
        if (CollectionUtils.isNotEmpty(historyAreaGroups)) {//清除无效的区域
            for (SchemeAreaGroup schemeAreaGroup : historyAreaGroups) {
                List<SchemeJudgeObject> judgeObjects = schemeJudgeObjectService.getJudgeObjectListByAreaGroupId(schemeAreaGroup.getId());
                if (CollectionUtils.isNotEmpty(judgeObjects)) {//清除估价对象下的任务
                    judgeObjects.forEach(o -> schemeJudgeObjectService.clearJudgeObjectTask(projectId, o.getId()));
                    schemeJudgeObjectDao.deleteJudgeObjectByAreaId(schemeAreaGroup.getId());
                }
                //清除区域下工作任务事项
                clearAreaGroupTask(Lists.newArrayList(schemeAreaGroup));
                schemeAreaGroupDao.remove(schemeAreaGroup.getId());
            }
        }
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);

        //添加或清除估价对象 先找出上次已经生成的估价对象 再找出本次会参与生成的权证 最后确定其新增或删除
        if (CollectionUtils.isNotEmpty(newAreaGroups)) {//为新产生的区域添加数据
            for (SchemeAreaGroup areaGroup : newAreaGroups) {
                String areaFullName = erpAreaService.getAreaFullName(areaGroup.getProvince(), areaGroup.getCity(), areaGroup.getDistrict());
                areaGroup.setAreaName(areaFullName);
                areaGroup.setProjectId(projectId);
                areaGroup.setValueTimePoint(projectInfo.getValuationDate());
                areaGroup.setEntrustPurpose(projectInfo.getEntrustPurpose());
                areaGroup.setRemarkEntrustPurpose(projectInfo.getRemarkEntrustPurpose());
                areaGroup.setValueDefinition(projectInfo.getValueType());
                areaGroup.setValueDefinitionDesc(projectInfo.getRemarkValueType());
                areaGroup.setPropertyScope(Integer.valueOf(StringUtils.defaultIfBlank(projectInfo.getPropertyScope(), "0")));
                areaGroup.setScopeInclude(projectInfo.getScopeInclude());
                areaGroup.setScopeNotInclude(projectInfo.getScopeNotInclude());
                areaGroup.setPid(0);
                areaGroup.setBisNew(true);
                areaGroup.setCreator(commonService.thisUserAccount());
                this.add(areaGroup);
                int i = 1;
                //初始化估价对象
                List<DeclareRecord> declareRecordList = getAreaGroupDeclareRecords(declareRecords, areaGroup.getProvince(), areaGroup.getCity(), areaGroup.getDistrict(), true);
                Iterator declareRecordIterator = declareRecordList.iterator();
                while (declareRecordIterator.hasNext()) {
                    DeclareRecord declareRecord = (DeclareRecord) declareRecordIterator.next();
                    boolean isSameProvince = StringUtils.equals(StringUtils.defaultString(declareRecord.getProvince()), StringUtils.defaultString(areaGroup.getProvince()));
                    boolean isSameCity = StringUtils.equals(StringUtils.defaultString(declareRecord.getCity()), StringUtils.defaultString(areaGroup.getCity()));
                    boolean isSameDistrict = StringUtils.equals(StringUtils.defaultString(declareRecord.getDistrict()), StringUtils.defaultString(areaGroup.getDistrict()));
                    if (isSameProvince && isSameCity && isSameDistrict) {
                        declareRecordToJudgeObject(declareRecord, areaGroup, i);
                        declareRecordIterator.remove();
                        i++;
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(sameAreaGroups)) {//新旧同区域下，处理估价对象的新增与移除
            for (SchemeAreaGroup sameAreaGroup : sameAreaGroups) {
                List<SchemeJudgeObject> judgeObjectDeclareList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(sameAreaGroup.getId());
                List<DeclareRecord> declareRecordList = getAreaGroupDeclareRecords(declareRecords, sameAreaGroup.getProvince(), sameAreaGroup.getCity(), sameAreaGroup.getDistrict(), true);
                Iterator declareRecordIterator = declareRecordList.iterator();
                while (declareRecordIterator.hasNext()) {
                    DeclareRecord declareRecord = (DeclareRecord) declareRecordIterator.next();
                    Iterator judgeObjectIterator = judgeObjectDeclareList.iterator();
                    while (judgeObjectIterator.hasNext()) {
                        SchemeJudgeObject judgeObject = (SchemeJudgeObject) judgeObjectIterator.next();
                        if (declareRecord.getId().equals(judgeObject.getDeclareRecordId())) {
                            declareRecordIterator.remove();
                            judgeObjectIterator.remove();
                        }
                    }
                }
                if (CollectionUtils.isNotEmpty(declareRecordList)) {//为权证添加估价对象
                    declareRecordList.forEach(o -> declareRecordToJudgeObject(o, sameAreaGroup, schemeJudgeObjectDao.getAreaGroupMaxNumber(projectId, sameAreaGroup.getId()) + 1));
                }
                if (CollectionUtils.isNotEmpty(judgeObjectDeclareList)) {//需被移除的估价对象
                    judgeObjectDeclareList.forEach(o -> schemeJudgeObjectService.clearJudgeObjectTask(projectId, o.getId()));
                    //需要重新排号
                }
            }
        }
    }

    /**
     * 申报权证写入到估价对象中
     *
     * @param declareRecord
     * @param areaGroup
     * @param i
     * @return
     */
    private SchemeJudgeObject declareRecordToJudgeObject(DeclareRecord declareRecord, SchemeAreaGroup areaGroup, Integer i) {
        BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(declareRecord.getId());
        SchemeJudgeObject schemeJudgeObject = new SchemeJudgeObject();
        schemeJudgeObject.setProjectId(declareRecord.getProjectId());
        schemeJudgeObject.setDeclareRecordId(declareRecord.getId());
        schemeJudgeObject.setBuildingStatus(declareRecord.getBuildingStatus());
        if (basicApply != null) {
            schemeJudgeObject.setBasicApplyId(basicApply.getId());
            schemeJudgeObject.setEvaluationArea(basicApply.getArea());
        }
        schemeJudgeObject.setNumber(String.valueOf(i));
        schemeJudgeObject.setCreator(commonService.thisUserAccount());
        schemeJudgeObject.setAreaGroupId(areaGroup.getId());
        schemeJudgeObject.setOriginalAreaGroupId(areaGroup.getId());
        schemeJudgeObject.setFloorArea(declareRecord.getFloorArea());
        schemeJudgeObject.setName(String.format("%s%s", i, BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME));
        schemeJudgeObject.setCertName(declareRecord.getName());
        schemeJudgeObject.setOwnership(declareRecord.getOwnership());
        schemeJudgeObject.setSeat(declareRecord.getSeat());
        schemeJudgeObject.setCertUse(declareRecord.getCertUse());
        schemeJudgeObject.setPracticalUse(declareRecord.getPracticalUse());
        if (declareRecord.getLandUseEndDate() != null) {
            schemeJudgeObject.setLandUseEndDate(declareRecord.getLandUseEndDate());
            //计算出土地剩余年限
            schemeJudgeObject.setLandRemainingYear(mdIncomeService.getLandSurplusYear(declareRecord.getLandUseEndDate(), areaGroup.getValueTimePoint()));
        }
        schemeJudgeObject.setEvaluationArea(declareRecord.getPracticalArea());
        schemeJudgeObject.setPid(0);
        schemeJudgeObject.setBisSplit(false);
        schemeJudgeObject.setSorting(i);
        schemeJudgeObject.setCreator(commonService.thisUserAccount());
        schemeJudgeObjectService.addSchemeJudgeObject(schemeJudgeObject);
        //反写申报数据的区域id
        declareRecord.setAreaGroupId(areaGroup.getId());
        declareRecordDao.updateDeclareRecord(declareRecord);
        return schemeJudgeObject;
    }

    /**
     * 筛选出省市区下对应的权证
     *
     * @param declareRecords
     * @param province
     * @param city
     * @param dictrict
     * @return
     */
    private List<DeclareRecord> getAreaGroupDeclareRecords(List<DeclareRecord> declareRecords, String province, String city, String dictrict, Boolean isRemove) {
        List<DeclareRecord> list = Lists.newArrayList();
        if (CollectionUtils.isEmpty(declareRecords)) return list;
        Iterator declareRecordIterator = declareRecords.iterator();
        while (declareRecordIterator.hasNext()) {
            DeclareRecord declareRecord = (DeclareRecord) declareRecordIterator.next();
            boolean isSameProvince = StringUtils.equals(StringUtils.defaultString(declareRecord.getProvince()), StringUtils.defaultString(province));
            boolean isSameCity = StringUtils.equals(StringUtils.defaultString(declareRecord.getCity()), StringUtils.defaultString(city));
            boolean isSameDistrict = StringUtils.equals(StringUtils.defaultString(declareRecord.getDistrict()), StringUtils.defaultString(dictrict));
            if (isSameProvince && isSameCity && isSameDistrict) {
                list.add(declareRecord);
                if (isRemove)
                    declareRecordIterator.remove();
            }
        }
        return list;
    }

    /**
     * 区域拆分
     *
     * @param areaGroupId
     * @param judgeObjectIdList
     */
    @Transactional(rollbackFor = Exception.class)
    public void areaGroupSplit(Integer areaGroupId, List<Integer> judgeObjectIdList) throws BusinessException {
        //1.创建一个新的区域  2.将选择的估价对象迁移到新的区域中 3.根据情况创建出变现税费与受偿款款任务
        if (areaGroupId == null || CollectionUtils.isEmpty(judgeObjectIdList))
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        SchemeAreaGroup schemeAreaGroup = getSchemeAreaGroup(areaGroupId);



        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(schemeAreaGroup.getProjectId());
        List<ProjectPhase> judgeProjectPhases = getAreaProjectPhaseListByEntrustPurpose(projectInfo, schemeAreaGroup.getEntrustPurpose());
    }

    /**
     * 取消区域拆分
     *
     * @param areaGroupId
     */
    @Transactional(rollbackFor = Exception.class)
    public void areaGroupSplitCancel(Integer areaGroupId) {
        //1.删除该区域下任务 2.将估价对象还原到原区域中 3.删除该区域
    }

    /**
     * 根据区域设定的委托目的确定其工作事项
     *
     * @param entrustPurpose
     * @return
     */
    public List<ProjectPhase> getAreaProjectPhaseListByEntrustPurpose(ProjectInfo projectInfo, Integer entrustPurpose) {
        List<ProjectPhase> judgeProjectPhases = Lists.newArrayList();
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(entrustPurpose);
        if (baseDataDic != null && StringUtils.equals(baseDataDic.getFieldName(), AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE_MORTGAGE)) {//如果是抵押评估还需添加事项，变现分析税费、法定优先受偿款
            ProjectPhase phaseLiquidationAnalysis = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.LIQUIDATION_ANALYSIS, projectInfo.getProjectCategoryId());
            ProjectPhase phaseReimbursement = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.REIMBURSEMENT, projectInfo.getProjectCategoryId());
            judgeProjectPhases.add(phaseLiquidationAnalysis);
            judgeProjectPhases.add(phaseReimbursement);
        }
        return judgeProjectPhases;
    }

    /**
     * 同区域下非拆分的区域
     *
     * @param areaGroupId
     * @return
     */
    public SchemeAreaGroup getNotSplitAreaGroup(Integer areaGroupId) {
        SchemeAreaGroup schemeAreaGroup = getSchemeAreaGroup(areaGroupId);
        if (schemeAreaGroup != null && schemeAreaGroup.getBisSplit() == Boolean.FALSE)
            return schemeAreaGroup;
        SchemeAreaGroup where = new SchemeAreaGroup();
        where.setProvince(schemeAreaGroup.getProvince());
        where.setCity(schemeAreaGroup.getCity());
        where.setDistrict(schemeAreaGroup.getDistrict());
        where.setBisSplit(false);
        List<SchemeAreaGroup> schemeAreaGroupList = schemeAreaGroupDao.getSchemeAreaGroupList(where);
        if (CollectionUtils.isEmpty(schemeAreaGroupList)) return null;
        return schemeAreaGroupList.get(0);
    }


    /**
     * 区域分组合并
     *
     * @param projectId
     * @param areaGroupIds
     */
    @Transactional(rollbackFor = Exception.class)
    public void areaGroupMerge(Integer projectId, String areaGroupIds) throws BusinessException {
        //验证区域是否能合并
        //创建合并区域 设置参与合并的区域不可用 将委估对象移至新区域下
        List<Integer> ids = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(areaGroupIds));
        if (CollectionUtils.isEmpty(ids) || ids.size() <= 1)
            throw new BusinessException("参与合并的区域至少为两个");
        List<SchemeAreaGroup> schemeAreaGroupList = schemeAreaGroupDao.getSchemeAreaGroupByIds(ids);
        String province = schemeAreaGroupList.get(0).getProvince();
        String city = schemeAreaGroupList.get(0).getCity();
        for (SchemeAreaGroup schemeAreaGroup : schemeAreaGroupList) {
            if (!StringUtils.equals(schemeAreaGroup.getProvince(), province) || !StringUtils.equals(schemeAreaGroup.getCity(), city))
                throw new BusinessException("区域不一致不能参与合并");
        }

        SchemeAreaGroup newAreaGroup = new SchemeAreaGroup();
        newAreaGroup.setPid(0);
        newAreaGroup.setProjectId(projectId);
        newAreaGroup.setProvince(province);
        newAreaGroup.setCity(city);
        newAreaGroup.setAreaName(erpAreaService.getAreaFullName(province, city, null));
        newAreaGroup.setValueTimePoint(schemeAreaGroupList.get(0).getValueTimePoint());
        newAreaGroup.setTimePointExplain(schemeAreaGroupList.get(0).getTimePointExplain());
        newAreaGroup.setBisEnable(true);
        newAreaGroup.setBisMerge(true);
        newAreaGroup.setBisNew(true);
        newAreaGroup.setCreator(commonService.thisUserAccount());
        schemeAreaGroupDao.add(newAreaGroup);
        int i = 1;//委估对象重新编号
        for (SchemeAreaGroup oldAreaGroup : schemeAreaGroupList) {
            oldAreaGroup.setPid(newAreaGroup.getId());
            oldAreaGroup.setBisEnable(false);
            schemeAreaGroupDao.update(oldAreaGroup);
            //委估对象逐一验证，如果已存在合并或拆分的对象，则不允许区域合并
            List<SchemeJudgeObject> judgeObjects = schemeJudgeObjectService.getJudgeObjectListByAreaGroupId(oldAreaGroup.getId());
            if (CollectionUtils.isNotEmpty(judgeObjects)) {
                for (SchemeJudgeObject judgeObject : judgeObjects) {
                    if (judgeObject.getBisMerge() == Boolean.TRUE || judgeObject.getBisSplit() == Boolean.TRUE)
                        throw new BusinessException("参与合并的区域中已存在合并或拆分对象");
                    judgeObject.setOriginalNumber(judgeObject.getNumber());
                    judgeObject.setNumber(String.valueOf(i));
                    judgeObject.setOriginalAreaGroupId(oldAreaGroup.getId());
                    judgeObject.setAreaGroupId(newAreaGroup.getId());
                    schemeJudgeObjectService.updateSchemeJudgeObject(judgeObject);
                    i++;
                }
            }
            clearAreaGroupTask(Lists.newArrayList(oldAreaGroup));
        }
    }

    /**
     * 取消合并的区域
     *
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void areaGroupMergeCancel(Integer id) throws BusinessException {
        List<SchemeAreaGroup> schemeAreaGroupList = schemeAreaGroupDao.getSchemeAreaGroupByPid(id);
        if (CollectionUtils.isNotEmpty(schemeAreaGroupList)) {
            for (SchemeAreaGroup areaGroup : schemeAreaGroupList) {
                areaGroup.setPid(0);
                areaGroup.setBisEnable(true);
                areaGroup.setBisNew(true);
                schemeAreaGroupDao.update(areaGroup);
                schemeJudgeObjectService.areaGroupReduction(areaGroup.getId());
            }
        }
        clearAreaGroupTask(Lists.newArrayList(getSchemeAreaGroup(id)));
    }

    /**
     * 清理掉空的区域及事项
     *
     * @param areaGroupList
     */
    public void clearAreaGroupTask(List<SchemeAreaGroup> areaGroupList) {
        if (CollectionUtils.isEmpty(areaGroupList)) return;
        for (SchemeAreaGroup schemeAreaGroup : areaGroupList) {
            if (schemeJudgeObjectDao.getCountByAreaGroupId(schemeAreaGroup.getId()) <= 0) {
                //清除区域信息 清除区域相关的任务
                if (schemeAreaGroup.getBisMerge() == Boolean.TRUE)//合并的区域才删除
                    schemeAreaGroupDao.remove(schemeAreaGroup.getId());
                ProjectPlanDetails where = new ProjectPlanDetails();
                where.setAreaId(schemeAreaGroup.getId());
                List<ProjectPlanDetails> planDetailsList = projectPlanDetailsService.getProjectDetails(where);
                if (CollectionUtils.isNotEmpty(planDetailsList))
                    planDetailsList.forEach(o -> projectPlanDetailsService.deleteProjectPlanDetails(o));
            }
        }
    }

    /**
     * 保存区域分组
     *
     * @param schemeAreaGroup
     */
    public void saveAreaGroup(SchemeAreaGroup schemeAreaGroup) throws BusinessException {
        if (schemeAreaGroup == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (schemeAreaGroup.getId() != null && schemeAreaGroup.getId() > 0) {
            schemeAreaGroupDao.update(schemeAreaGroup);
        } else {
            schemeAreaGroup.setCreator(commonService.thisUserAccount());
            schemeAreaGroupDao.add(schemeAreaGroup);
        }
    }


    public boolean remove(Integer id) {
        return schemeAreaGroupDao.remove(id);
    }


    public boolean update(SchemeAreaGroup schemeAreaGroup) {
        return schemeAreaGroupDao.update(schemeAreaGroup);
    }


    public SchemeAreaGroup getSchemeAreaGroup(Integer id) {
        return schemeAreaGroupDao.getSchemeAreaGroup(id);
    }


    public SchemeAreaGroupVo getSchemeAreaGroupVo(SchemeAreaGroup schemeAreaGroup) {
        if (schemeAreaGroup == null) return null;
        SchemeAreaGroupVo schemeAreaGroupVo = new SchemeAreaGroupVo();
        BeanUtils.copyProperties(schemeAreaGroup, schemeAreaGroupVo);
        schemeAreaGroupVo.setEntrustPurposeName(baseDataDicService.getNameById(schemeAreaGroup.getEntrustPurpose()));
        schemeAreaGroupVo.setValueDefinitionName(baseDataDicService.getNameById(schemeAreaGroup.getValueDefinition()));
        schemeAreaGroupVo.setPropertyScopeName(baseDataDicService.getNameById(schemeAreaGroup.getPropertyScope()));
        schemeAreaGroupVo.setEntrustAimTypeName(baseDataDicService.getNameById(schemeAreaGroup.getEntrustAimType()));
        if (StringUtils.isNotBlank(schemeAreaGroup.getValueConnotation())) {
            List<String> list = JSON.parseArray(schemeAreaGroup.getValueConnotation(), String.class);
            if (CollectionUtils.isNotEmpty(list)) {
                StringBuilder builder = new StringBuilder();
                list.forEach(o -> builder.append(baseDataDicService.getNameById(o)).append(","));
                schemeAreaGroupVo.setValueConnotationName(StringUtils.strip(builder.toString(), ","));
            }
        }
        return schemeAreaGroupVo;
    }

    /**
     * 获取区域下的评估面积
     *
     * @param judgeObjects
     * @return
     */
    public BigDecimal getAreaEvaluateArea(List<SchemeJudgeObject> judgeObjects) {
        BigDecimal result = new BigDecimal("0");
        if (CollectionUtils.isNotEmpty(judgeObjects)) {
            for (SchemeJudgeObject judgeObject : judgeObjects) {
                if (judgeObject.getEvaluationArea() != null) {
                    result = result.add(judgeObject.getEvaluationArea());
                }
            }
        }
        if (result.doubleValue() > 0) {
            result = result.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return result;
    }

    /**
     * 获取区域下的评估金额
     *
     * @param judgeObjects
     * @return
     */
    public BigDecimal getAreaEvaluatePrice(List<SchemeJudgeObject> judgeObjects) {
        BigDecimal result = new BigDecimal("0");
        if (CollectionUtils.isNotEmpty(judgeObjects)) {
            for (SchemeJudgeObject judgeObject : judgeObjects) {
                if (judgeObject.getEvaluationArea() != null && judgeObject.getPrice() != null) {
                    result = result.add(judgeObject.getEvaluationArea().multiply(judgeObject.getPrice()));
                }
            }
        }
        if (result.doubleValue() > 0) {
            result = result.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return result;
    }
}
