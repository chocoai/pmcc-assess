package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.method.MdEconomicIndicatorsDao;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeAreaGroupDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeObjectDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeAreaGroupVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.method.MdIncomeService;
import com.copower.pmcc.assess.service.project.*;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.assess.service.project.declare.DeclareBuildEngineeringAndEquipmentCenterService;
import com.copower.pmcc.bpm.api.exception.BpmException;
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
    private DeclareBuildEngineeringAndEquipmentCenterService declareBuildEngineeringAndEquipmentCenterService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private MdEconomicIndicatorsDao mdEconomicIndicatorsDao;
    @Autowired
    private BasicEstateLandCategoryInfoService basicEstateLandCategoryInfoService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;

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
        newAreaGroups = CollectionUtils.isEmpty(newAreaGroups) ? Lists.newArrayList() : newAreaGroups;
        List<SchemeAreaGroup> historyAreaGroups = getAreaGroupEnableByProjectId(projectId);
        historyAreaGroups = CollectionUtils.isEmpty(historyAreaGroups) ? Lists.newArrayList() : historyAreaGroups;
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
                List<SchemeJudgeObject> judgeObjects = schemeJudgeObjectService.getJudgeObjectListAllByAreaGroupId(schemeAreaGroup.getId());
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
                areaGroup.setEntrustAimType(projectInfo.getEntrustAimType());
                areaGroup.setValueDefinition(projectInfo.getValueType());
                areaGroup.setValueDefinitionDesc(projectInfo.getRemarkValueType());
                areaGroup.setPropertyScope(Integer.valueOf(StringUtils.defaultIfBlank(projectInfo.getPropertyScope(), "0")));
                areaGroup.setScopeInclude(projectInfo.getScopeInclude());
                areaGroup.setScopeNotInclude(projectInfo.getScopeNotInclude());
                areaGroup.setPid(0);
                areaGroup.setBisNew(true);
                areaGroup.setCreator(commonService.thisUserAccount());
                this.add(areaGroup);
                //初始化估价对象
                List<DeclareRecord> declareRecordList = getAreaGroupDeclareRecords(declareRecords, areaGroup.getProvince(), areaGroup.getCity(), areaGroup.getDistrict(), true);
                Iterator declareRecordIterator = declareRecordList.iterator();
                while (declareRecordIterator.hasNext()) {
                    DeclareRecord declareRecord = (DeclareRecord) declareRecordIterator.next();
                    boolean isSameProvince = StringUtils.equals(StringUtils.defaultString(declareRecord.getProvince()), StringUtils.defaultString(areaGroup.getProvince()));
                    boolean isSameCity = StringUtils.equals(StringUtils.defaultString(declareRecord.getCity()), StringUtils.defaultString(areaGroup.getCity()));
                    boolean isSameDistrict = StringUtils.equals(StringUtils.defaultString(declareRecord.getDistrict()), StringUtils.defaultString(areaGroup.getDistrict()));
                    if (isSameProvince && isSameCity && isSameDistrict) {
                        declareRecordToJudgeObject(declareRecord, areaGroup);
                        declareRecordIterator.remove();
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
                Boolean isNeedReNumber = false;//是否需要重新编号
                if (CollectionUtils.isNotEmpty(declareRecordList)) {//为权证添加估价对象
                    isNeedReNumber = true;
                    declareRecordList.forEach(o -> declareRecordToJudgeObject(o, sameAreaGroup));
                }
                if (CollectionUtils.isNotEmpty(judgeObjectDeclareList)) {//需被移除的估价对象
                    isNeedReNumber = true;
                    for (SchemeJudgeObject judgeObject : judgeObjectDeclareList) {
                        if (schemeJudgeObjectDao.getCountBySplitFrom(judgeObject.getId()) > 0) {//表示该对象是被拆分过的对象，将拆分的子对象也删除
                            List<SchemeJudgeObject> listBySplitFrom = schemeJudgeObjectService.getJudgeObjectListBySplitFrom(judgeObject.getId());
                            if (CollectionUtils.isNotEmpty(listBySplitFrom)) ;
                            listBySplitFrom.forEach(o -> schemeJudgeObjectService.clearJudgeObjectTask(projectId, o.getId()));
                        }
                        schemeJudgeObjectService.clearJudgeObjectTask(projectId, judgeObject.getId());
                    }
                }
                //如果合并的估价对象下，没有估价对象则将其删除
                List<SchemeJudgeObject> mergeList = schemeJudgeObjectDao.getMergeListByAreaId(sameAreaGroup.getId());
                if (CollectionUtils.isNotEmpty(mergeList)) {
                    for (SchemeJudgeObject mergeJudgeObject : mergeList) {
                        if (schemeJudgeObjectDao.getCountByPid(mergeJudgeObject.getId()) <= 0)
                            schemeJudgeObjectService.clearJudgeObjectTask(projectId, mergeJudgeObject.getId());
                    }
                }

                if (isNeedReNumber) {
                    schemeJudgeObjectService.reNumberJudgeObject(sameAreaGroup.getId()); //需要重新排号
                }
            }
        }
    }

    /**
     * 申报权证写入到估价对象中
     *
     * @param declareRecord
     * @param areaGroup
     * @return
     */
    private void declareRecordToJudgeObject(DeclareRecord declareRecord, SchemeAreaGroup areaGroup) {
        //根据查勘情况生成对应的估价对象，并将查勘applyid关联到估价对象上
        List<BasicApply> basicApplyList = basicApplyService.getListByDeclareRecordId(declareRecord.getId());
        if (CollectionUtils.isEmpty(basicApplyList)) return;
        //经济指标
        DeclareBuildEngineeringAndEquipmentCenter equipmentCenter = declareBuildEngineeringAndEquipmentCenterService.getDataByDeclareRecord(declareRecord.getId());
        MdEconomicIndicators economicIndicators = mdEconomicIndicatorsDao.getEconomicIndicatorsById(equipmentCenter.getIndicatorId());
        SchemeJudgeObject splitSource = null;//拆分源对象
        for (int i = 0; i < basicApplyList.size(); i++) {
            BasicApply basicApply = basicApplyList.get(i);
            SchemeJudgeObject schemeJudgeObject = new SchemeJudgeObject();
            schemeJudgeObject.setProjectId(declareRecord.getProjectId());
            schemeJudgeObject.setDeclareRecordId(declareRecord.getId());
            schemeJudgeObject.setBuildingStatus(declareRecord.getBuildingStatus());
            schemeJudgeObject.setBasicApplyId(basicApply.getId());
            schemeJudgeObject.setEvaluationArea(basicApply.getArea());
            if(economicIndicators!=null){
                if(StringUtils.isNotEmpty(economicIndicators.getVolumetricRate())){
                    schemeJudgeObject.setPlanPlotRatio(new BigDecimal(economicIndicators.getVolumetricRate()));
                    schemeJudgeObject.setSetPlotRatio(new BigDecimal(economicIndicators.getVolumetricRate()));
                }
                schemeJudgeObject.setParcelSettingInnerDevelop(economicIndicators.getParcelSettingInner());
            }

            BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
            if(basicEstate!=null){
                schemeJudgeObject.setParcelInnerDevelop(basicEstate.getInfrastructure());
                BasicEstateLandState estateLandState = basicEstateLandStateService.getLandStateByEstateId(basicEstate.getId());
                schemeJudgeObject.setParcelOuterDevelop(estateLandState.getDevelopmentDegreeContent());
                schemeJudgeObject.setCurrentSituation(estateLandState.getCurrentSituation());
            }

            BasicHouse basicHouse = basicHouseService.getHouseByBasicApply(basicApply);
            if (basicHouse != null) {
                schemeJudgeObject.setCertUse(basicHouse.getCertUse());
                schemeJudgeObject.setPracticalUse(basicHouse.getPracticalUse());
                BasicEstateLandCategoryInfo landCategoryInfo = basicEstateLandCategoryInfoService.getBasicEstateLandCategoryInfoByHouseId(basicHouse.getId());
                if(landCategoryInfo!=null){
                    schemeJudgeObject.setActualPlotRatio(landCategoryInfo.getPlotRatio());
                }
            }
            schemeJudgeObject.setNumber(String.valueOf(declareRecord.getNumber()));
            schemeJudgeObject.setCreator(commonService.thisUserAccount());
            schemeJudgeObject.setAreaGroupId(areaGroup.getId());
            schemeJudgeObject.setOriginalAreaGroupId(areaGroup.getId());
            schemeJudgeObject.setFloorArea(declareRecord.getFloorArea());

            schemeJudgeObject.setCertName(declareRecord.getName());
            schemeJudgeObject.setOwnership(declareRecord.getOwnership());
            schemeJudgeObject.setSeat(declareRecord.getSeat());
            if (declareRecord.getLandUseEndDate() != null) {
                schemeJudgeObject.setLandUseEndDate(declareRecord.getLandUseEndDate());//计算出土地剩余年限
                if (areaGroup.getValueTimePoint() != null)
                    schemeJudgeObject.setLandRemainingYear(mdIncomeService.getLandSurplusYear(declareRecord.getLandUseEndDate(), areaGroup.getValueTimePoint()));
            }
            schemeJudgeObject.setBisSplit(false);
            schemeJudgeObject.setSorting(declareRecord.getNumber());
            schemeJudgeObject.setCreator(commonService.thisUserAccount());
            if (basicApplyList.size() > 1) {//自动拆分
                if (i == 0) {
                    schemeJudgeObject.setSplitNumber(1);
                    schemeJudgeObject.setName(String.format("%s-%s%s", schemeJudgeObject.getNumber(), schemeJudgeObject.getSplitNumber(), BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME));
                    schemeJudgeObjectDao.addSchemeJudgeObject(schemeJudgeObject);
                    splitSource = schemeJudgeObject;
                } else {
                    schemeJudgeObjectService.splitJudge(splitSource, i + 1, basicApply.getId());
                }
            } else {
                schemeJudgeObject.setName(String.format("%s%s", declareRecord.getNumber(), BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME));
                schemeJudgeObject.setPid(0);
                schemeJudgeObjectService.addSchemeJudgeObject(schemeJudgeObject);
            }

        }
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
     * @param judgeObjectIds
     */
    @Transactional(rollbackFor = Exception.class)
    public void areaGroupSplit(Integer planId, Integer areaGroupId, List<Integer> judgeObjectIds) throws BusinessException, BpmException {
        //1.创建一个新的区域  2.将选择的估价对象迁移到新的区域中 3.根据情况创建出变现税费与受偿款款任务（主区域未生成任务则不生成）
        if (areaGroupId == null || CollectionUtils.isEmpty(judgeObjectIds))
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        SchemeAreaGroup sourceAreaGroup = getSchemeAreaGroup(areaGroupId);
        SchemeAreaGroup newAreaGroup = new SchemeAreaGroup();
        BeanUtils.copyProperties(sourceAreaGroup, newAreaGroup, "id", "creator", "gmtCreated", "gmtModified");
        newAreaGroup.setAreaName(String.format("%s【拆%s】", newAreaGroup.getAreaName(), schemeJudgeObjectDao.getCountBySplitFrom(areaGroupId) + 1));
        newAreaGroup.setSplitFrom(sourceAreaGroup.getId());
        newAreaGroup.setBisSplit(true);
        saveAreaGroup(newAreaGroup);

        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectListByIds(judgeObjectIds);
        if (CollectionUtils.isNotEmpty(judgeObjectList)) {
            //1.根据传递的估价对象数据，确定其实际应该移动的所有估价对象
            //2.再确定是否拆分对象关联的所有的估价对象被选择
            List<SchemeJudgeObject> judgeObjectListAll = Lists.newArrayList();
            for (SchemeJudgeObject judgeObject : judgeObjectList) {
                if (judgeObject.getBisMerge() == Boolean.TRUE) {
                    judgeObjectListAll.addAll(schemeJudgeObjectService.getChildrenJudgeObject(judgeObject.getId()));
                }
                judgeObjectListAll.add(judgeObject);
            }
            for (SchemeJudgeObject o : judgeObjectListAll) {
                if (o.getBisSplit() == Boolean.TRUE) {//找出应该有的拆分数据是否都存在
                    if (!judgeObjectListAll.contains(o.getSplitFrom()))
                        throw new BusinessException(String.format("拆分对象%s未包所有拆分对象", schemeJudgeObjectService.getSchemeJudgeObject(o.getSplitFrom()).getName()));
                    List<SchemeJudgeObject> listBySplitFrom = schemeJudgeObjectService.getJudgeObjectListBySplitFrom(o.getSplitFrom());
                    List<Integer> list = LangUtils.transform(listBySplitFrom, p -> p.getId());
                    for (Integer integer : list) {
                        if (!judgeObjectListAll.contains(integer))
                            throw new BusinessException(String.format("拆分对象%s未包所有拆分对象", schemeJudgeObjectService.getSchemeJudgeObject(o.getSplitFrom()).getName()));
                    }
                }
                o.setAreaGroupId(newAreaGroup.getId());
                schemeJudgeObjectService.updateSchemeJudgeObject(o);
            }
        }

        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(sourceAreaGroup.getProjectId());
        List<ProjectPhase> judgeProjectPhases = getAreaProjectPhaseListByEntrustPurpose(projectInfo, sourceAreaGroup.getEntrustPurpose());
        if (sourceAreaGroup.getBisNew() == Boolean.FALSE && CollectionUtils.isNotEmpty(judgeProjectPhases)) {
            ProjectPlan projectPlan = projectPlanService.getProjectplanById(planId);
            ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());
            String projectManager = projectMemberService.getProjectManager(projectInfo.getId());
            for (ProjectPhase projectPhase : judgeProjectPhases) {
                schemeJudgeObjectService.savePlanDetails(projectInfo, projectWorkStage, projectPlan, newAreaGroup.getId(), newAreaGroup, null, newAreaGroup.getAreaName(), projectPhase, projectManager);
            }
        }
        schemeJudgeObjectService.reNumberJudgeObject(sourceAreaGroup.getId());
        schemeJudgeObjectService.reNumberJudgeObject(newAreaGroup.getId());
    }

    /**
     * 移除拆分的区域
     *
     * @param areaGroupId
     */
    @Transactional(rollbackFor = Exception.class)
    public void areaGroupSplitRemove(Integer areaGroupId) {
        //1.删除该区域下任务 2.将估价对象还原到原区域中 3.删除该区域
        SchemeAreaGroup sourceSplitAreaGroup = getSourceSplitAreaGroup(areaGroupId);
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectListAllByAreaGroupId(areaGroupId);
        if (CollectionUtils.isNotEmpty(judgeObjectList)) {
            judgeObjectList.forEach(o -> {
                o.setAreaGroupId(sourceSplitAreaGroup.getId());
                schemeJudgeObjectService.updateSchemeJudgeObject(o);
            });
        }
        clearAreaGroupTask(Lists.newArrayList(getSchemeAreaGroup(areaGroupId)));
        remove(areaGroupId);
        schemeJudgeObjectService.reNumberJudgeObject(sourceSplitAreaGroup.getId());
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
    public SchemeAreaGroup getSourceSplitAreaGroup(Integer areaGroupId) {
        SchemeAreaGroup schemeAreaGroup = getSchemeAreaGroup(areaGroupId);
        if (schemeAreaGroup != null && schemeAreaGroup.getBisSplit() == Boolean.FALSE)
            return schemeAreaGroup;
        return getSchemeAreaGroup(schemeAreaGroup.getSplitFrom());
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
        SchemeAreaGroup standardArea = schemeAreaGroupList.get(0);
        String province = standardArea.getProvince();
        String city = standardArea.getCity();
        for (SchemeAreaGroup schemeAreaGroup : schemeAreaGroupList) {
            if (!StringUtils.equals(schemeAreaGroup.getProvince(), province) || !StringUtils.equals(schemeAreaGroup.getCity(), city))
                throw new BusinessException("城市不一致不能参与合并");
        }

        SchemeAreaGroup newAreaGroup = new SchemeAreaGroup();
        BeanUtils.copyProperties(standardArea, newAreaGroup);
        newAreaGroup.setPid(0);
        newAreaGroup.setProjectId(projectId);
        newAreaGroup.setProvince(province);
        newAreaGroup.setCity(city);
        newAreaGroup.setAreaName(erpAreaService.getAreaFullName(province, city, null));
        newAreaGroup.setBisEnable(true);
        newAreaGroup.setBisMerge(true);
        newAreaGroup.setBisNew(true);
        newAreaGroup.setCreator(commonService.thisUserAccount());
        schemeAreaGroupDao.add(newAreaGroup);
        for (SchemeAreaGroup oldAreaGroup : schemeAreaGroupList) {
            oldAreaGroup.setPid(newAreaGroup.getId());
            oldAreaGroup.setBisEnable(false);
            schemeAreaGroupDao.update(oldAreaGroup);

            List<SchemeJudgeObject> judgeObjects = schemeJudgeObjectService.getJudgeObjectListAllByAreaGroupId(oldAreaGroup.getId());
            if (CollectionUtils.isNotEmpty(judgeObjects)) {
                for (SchemeJudgeObject judgeObject : judgeObjects) {
                    judgeObject.setOriginalAreaGroupId(oldAreaGroup.getId());
                    judgeObject.setAreaGroupId(newAreaGroup.getId());
                    schemeJudgeObjectService.updateSchemeJudgeObject(judgeObject);
                }
            }
            clearAreaGroupTask(Lists.newArrayList(oldAreaGroup));
        }
        schemeJudgeObjectService.reNumberJudgeObject(newAreaGroup.getId());
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
                List<ProjectPlanDetails> planDetailsList = projectPlanDetailsService.getProjectPlanDetailsByAreaId(schemeAreaGroup.getId());
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
