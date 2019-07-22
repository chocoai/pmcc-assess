package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
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
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryService;
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
    private SurveyAssetInventoryService surveyAssetInventoryService;
    @Autowired
    private SurveyAssetInventoryRightService surveyAssetInventoryRightService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private MdIncomeService mdIncomeService;

    public int add(SchemeAreaGroup schemeAreaGroup) {
        return schemeAreaGroupDao.add(schemeAreaGroup);
    }

    /**
     * 根据分组 获取数据信息
     *
     * @param projectId
     * @return
     */
    public List<SchemeAreaGroup> getAreaGroupList(Integer projectId) {
        List<SchemeAreaGroup> schemeAreaGroupList = schemeAreaGroupDao.getSchemeAreaGroupByProjectId(projectId);
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
    public List<SchemeAreaGroupVo> getSchemeAreaGroup(Integer projectId) {
        List<SchemeAreaGroup> voList = this.getAreaGroupList(projectId);
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
        List<SchemeAreaGroup> areaGroupHistoryList = getAreaGroupList(projectId);
        if (CollectionUtils.isNotEmpty(areaGroupHistoryList)) {
            ProjectPlanDetails where = null;
            List<ProjectPlanDetails> projectDetails = null;
            for (SchemeAreaGroup schemeAreaGroup : areaGroupHistoryList) {
                List<SchemeJudgeObject> judgeObjects = schemeJudgeObjectService.getJudgeObjectListByAreaGroupId(schemeAreaGroup.getId());
                if (CollectionUtils.isNotEmpty(judgeObjects)) {//清除估价对象下的任务
                    for (SchemeJudgeObject judgeObject : judgeObjects) {
                        where = new ProjectPlanDetails();
                        where.setProjectId(projectId);
                        where.setJudgeObjectId(judgeObject.getId());
                        projectDetails = projectPlanDetailsService.getProjectDetails(where);
                        if (CollectionUtils.isNotEmpty(projectDetails)) {
                            projectDetails.forEach(o -> projectPlanDetailsService.deleteProjectPlanDetails(o));
                        }
                    }
                    schemeJudgeObjectDao.deleteJudgeObjectByAreaId(schemeAreaGroup.getId());
                }

                //清除区域下工作任务
                where = new ProjectPlanDetails();
                where.setProjectId(projectId);
                where.setAreaId(schemeAreaGroup.getId());
                projectDetails = projectPlanDetailsService.getProjectDetails(where);
                if (CollectionUtils.isNotEmpty(projectDetails)) {
                    projectDetails.forEach(o -> projectPlanDetailsService.deleteProjectPlanDetails(o));
                }
                schemeAreaGroupDao.remove(schemeAreaGroup.getId());
            }
        }

        List<DeclareRecord> declareRecords = declareRecordDao.getDeclareRecordListByProjectId(projectId);
        List<SchemeAreaGroup> areaGroups = groupDeclareRecord(declareRecords);
        if (CollectionUtils.isNotEmpty(areaGroups)) {
            ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
            List<DeclareRecord> removeList = Lists.newArrayList();
            for (SchemeAreaGroup areaGroup : areaGroups) {
                String areaFullName = erpAreaService.getAreaFullName(areaGroup.getProvince(), areaGroup.getCity(), areaGroup.getDistrict());
                areaGroup.setAreaName(areaFullName);
                areaGroup.setProjectId(projectId);
                areaGroup.setValueTimePoint(projectInfo.getValuationDate());
                areaGroup.setEntrustPurpose(projectInfo.getEntrustPurpose());
                areaGroup.setRemarkEntrustPurpose(projectInfo.getRemarkEntrustPurpose());
                areaGroup.setValueDefinition(projectInfo.getValueType());
                areaGroup.setValueDefinitionDesc(projectInfo.getRemarkValueType());
                areaGroup.setPropertyScope(Integer.valueOf(StringUtils.defaultString(projectInfo.getPropertyScope(), "0")));
                areaGroup.setScopeInclude(projectInfo.getScopeInclude());
                areaGroup.setScopeNotInclude(projectInfo.getScopeNotInclude());
                areaGroup.setPid(0);
                areaGroup.setCreator(commonService.thisUserAccount());
                this.add(areaGroup);
                int i = 1;
                //初始化估价对象
                for (DeclareRecord declareRecord : declareRecords) {
                    boolean isSameProvince = StringUtils.equals(declareRecord.getProvince(), areaGroup.getProvince());
                    boolean isSameCity = StringUtils.equals(declareRecord.getCity(), areaGroup.getCity());
                    boolean isSameDistrict = StringUtils.equals(declareRecord.getDistrict(), areaGroup.getDistrict());
                    if (isSameProvince && isSameCity && isSameDistrict) {
                        SchemeJudgeObject schemeJudgeObject = new SchemeJudgeObject();
                        schemeJudgeObject.setProjectId(projectId);
                        schemeJudgeObject.setDeclareRecordId(declareRecord.getId());
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
                        //获取到房屋中的出租占用情况描述
                        BasicHouse basicHouse = schemeJudgeObjectService.getBasicHouseByDeclareId(declareRecord.getId(), projectInfo.getProjectCategoryId());
                        //取得他权中的担保权备注信息
                        SurveyAssetInventory assetInventory = surveyAssetInventoryService.getDataByDeclareId(declareRecord.getId());
                        List<SurveyAssetInventoryRight> inventoryRights = surveyAssetInventoryRightService.surveyAssetInventoryRights(assetInventory.getPlanDetailId());
                        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_RIGHT_TYPE_HOUSE_GUARANTEE);
                        if (baseDataDic != null && CollectionUtils.isNotEmpty(inventoryRights)) {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (SurveyAssetInventoryRight inventoryRight : inventoryRights) {
                                if (baseDataDic.getId().equals(inventoryRight.getCategory())) {
                                    stringBuilder.append(inventoryRight.getRemark());
                                }
                            }
                        }

                        schemeJudgeObject.setPid(0);
                        schemeJudgeObject.setBisSplit(false);
                        schemeJudgeObject.setSorting(i);
                        schemeJudgeObject.setCreator(commonService.thisUserAccount());
                        schemeJudgeObjectService.addSchemeJudgeObject(schemeJudgeObject);
                        //schemeJudgeObjectService.makeJudgeObjectPosition(Lists.newArrayList(schemeJudgeObject.getId()));

                        //反写申报数据的区域id
                        declareRecord.setAreaGroupId(areaGroup.getId());
                        declareRecordDao.updateDeclareRecord(declareRecord);

                        removeList.add(declareRecord);//已被添加到区域的数据下次循环移除掉
                        i++;
                    }
                }
            }
        }
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
        }
    }

    /**
     * 取消合并的区域
     *
     * @param id
     */
    @Transactional
    public void areaGroupMergeCancel(Integer id) throws BusinessException {
        List<SchemeAreaGroup> schemeAreaGroupList = schemeAreaGroupDao.getSchemeAreaGroupByPid(id);
        if (CollectionUtils.isNotEmpty(schemeAreaGroupList)) {
            for (SchemeAreaGroup areaGroup : schemeAreaGroupList) {
                areaGroup.setPid(0);
                areaGroup.setBisEnable(true);
                schemeAreaGroupDao.update(areaGroup);
                schemeJudgeObjectService.areaGroupReduction(areaGroup.getId());
            }
        }
        schemeAreaGroupDao.remove(id);
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


    public SchemeAreaGroup get(Integer id) {
        return schemeAreaGroupDao.get(id);
    }


    public SchemeAreaGroupVo getSchemeAreaGroupVo(SchemeAreaGroup schemeAreaGroup) {
        if (schemeAreaGroup == null) return null;
        SchemeAreaGroupVo schemeAreaGroupVo = new SchemeAreaGroupVo();
        BeanUtils.copyProperties(schemeAreaGroup, schemeAreaGroupVo);
        schemeAreaGroupVo.setEntrustPurposeName(baseDataDicService.getNameById(schemeAreaGroup.getEntrustPurpose()));
        schemeAreaGroupVo.setValueDefinitionName(baseDataDicService.getNameById(schemeAreaGroup.getValueDefinition()));
        schemeAreaGroupVo.setPropertyScopeName(baseDataDicService.getNameById(schemeAreaGroup.getPropertyScope()));
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
        if (result.doubleValue() > 0){
            result = result.setScale(2,BigDecimal.ROUND_HALF_UP) ;
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
        if (result.doubleValue() > 0){
            result = result.setScale(2,BigDecimal.ROUND_HALF_UP) ;
        }
        return result;
    }
}
