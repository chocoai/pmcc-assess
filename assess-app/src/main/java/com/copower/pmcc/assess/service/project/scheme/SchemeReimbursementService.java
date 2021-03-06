package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeReimbursementDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeReimbursementItemDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeReimbursementDto;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeReimbursementItemVo;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetRightDeclareService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetRightGroupService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetRightService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Ordering;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.lucene.queries.function.valuesource.LiteralValueSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 法定优先受偿款
 *
 * @author noOne
 */

@Service
public class SchemeReimbursementService {
    @Autowired
    private SchemeReimbursementDao schemeReimbursementDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private SchemeReimbursementItemDao schemeReimbursementItemDao;
    @Autowired
    private SurveyAssetRightGroupService surveyAssetRightGroupService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private SurveyAssetRightDeclareService surveyAssetRightDeclareService;

    public List<SchemeReimbursement> getObjectList(SchemeReimbursement schemeReimbursement) {
        return schemeReimbursementDao.getObjectList(schemeReimbursement);
    }

    public SchemeReimbursementItemVo getItemVoByGroupId(Integer groupId) {
        if (groupId == null) {
            return null;
        }
        SchemeReimbursementItem select = new SchemeReimbursementItem();
        select.setInventoryRightRecordId(groupId);
        List<SchemeReimbursementItemVo> list = findQueryBySchemeReimbursementItem2(select);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().findFirst().get();
        }
        return null;
    }

    public SchemeReimbursementItem getItemByJudgeId(Integer judgeObjectId) {
        if (judgeObjectId == null) return null;
        SchemeReimbursementItem where = new SchemeReimbursementItem();
        where.setJudgeObjectId(judgeObjectId);
        return schemeReimbursementItemDao.getSingleObject(where);
    }

    public SchemeReimbursement getDataByPlanDetailsId(Integer planDetailsId) {
        SchemeReimbursement where = new SchemeReimbursement();
        where.setPlanDetailsId(planDetailsId);
        return schemeReimbursementDao.getSchemeReimbursement(where);
    }

    public SchemeReimbursement getDataByProcessInsId(String processInsId) {
        SchemeReimbursement where = new SchemeReimbursement();
        where.setProcessInsId(processInsId);
        return schemeReimbursementDao.getSchemeReimbursement(where);
    }

    public void applyCommit(String formData, String processInsId) {
        SchemeReimbursementDto schemeReimbursementDto = JSON.parseObject(formData, SchemeReimbursementDto.class);
        SchemeReimbursement schemeReimbursement = this.getSingleObject(schemeReimbursementDto.getId());
        schemeReimbursement.setProcessInsId(processInsId);
        schemeReimbursementDao.editSchemeReimbursement(schemeReimbursement);

        //修改子表
        List<SchemeReimbursementItem> list = schemeReimbursementDto.getItemList();
        if (CollectionUtils.isNotEmpty(list)) {
            for (SchemeReimbursementItem schemeReimbursementItem : list) {
                if (schemeReimbursementItem.getId() != null) {
                    schemeReimbursementItemDao.updateObject(schemeReimbursementItem);
                }
            }
        }
    }


    public void saveSchemeReimbursement(SchemeReimbursement schemeReimbursement) {
        if (schemeReimbursement.getId() != null && schemeReimbursement.getId().intValue() > 0) {
            schemeReimbursementDao.editSchemeReimbursement(schemeReimbursement);
        } else {
            schemeReimbursement.setCreator(processControllerComponent.getThisUser());
            schemeReimbursementDao.addSchemeReimbursement(schemeReimbursement);
        }
    }

    public SchemeReimbursement getSingleObject(Integer id) {
        return schemeReimbursementDao.getSchemeReimbursement(id);
    }


    public String getFullDescription(SchemeReimbursementItem object, Integer number) {
        StringBuilder builder = new StringBuilder(8);
        BigDecimal decimal = new BigDecimal("10000");
        if (number == 0 || number == 1) {
//            builder.append(String.format("已抵押担保的债权数额总价%s万元,", (object.getMortgagedTotalPrice().divide(decimal))));
            builder.append(String.format("%s万元,", (object.getMortgagedTotalPrice().divide(decimal))));
        }
        if (number == 0 || number == 2) {
//            builder.append(String.format("拖欠的建设工程价款总价%s万元,", (object.getOwedTotalPrice().divide(decimal))));
            builder.append(String.format("%s万元,", (object.getOwedTotalPrice().divide(decimal))));
        }
        if (number == 0 || number == 3) {
//            builder.append(String.format("其它法定优先受偿款总价%s万元,", (object.getOtherTotalPrice().divide(decimal))));
            builder.append(String.format("%s万元,", (object.getOtherTotalPrice().divide(decimal))));
        }
        if (number == 0 || number == 4) {
            builder.append(String.format("%s万元,", (object.getKnowTotalPrice().divide(decimal))));
//            builder.append(String.format("估价师知悉的法定优先受偿款总价%s万元,", (object.getKnowTotalPrice().divide(decimal))));
        }
        return builder.toString();
    }


    /**
     * 获取明细
     *
     * @param masterId
     * @return
     */
    public List<SchemeReimbursementItemVo> getItemByMasterId(Integer masterId) {
        if (masterId == null) return null;
        SchemeReimbursementItem schemeReimbursementItem = new SchemeReimbursementItem();
        schemeReimbursementItem.setMasterId(masterId);
        List<SchemeReimbursementItemVo> itemVos = findQueryBySchemeReimbursementItem2(schemeReimbursementItem);
        if (CollectionUtils.isEmpty(itemVos)) {
            SchemeReimbursement reimbursement = schemeReimbursementDao.getSchemeReimbursement(masterId);
            List<SchemeJudgeObject> judgeObjects = schemeJudgeObjectService.getJudgeObjectApplicableListByAreaGroupId(reimbursement.getAreaId());
            if (CollectionUtils.isNotEmpty(judgeObjects)) {
                for (SchemeJudgeObject judgeObject : judgeObjects) {
                    SchemeReimbursementItem reimbursementItem = new SchemeReimbursementItem();
                    reimbursementItem.setName(judgeObject.getName());
                    reimbursementItem.setProjectId(judgeObject.getProjectId());
                    reimbursementItem.setMasterId(masterId);
                    reimbursementItem.setJudgeObjectId(judgeObject.getId());
                    reimbursementItem.setPlanDetailsId(reimbursementItem.getPlanDetailsId());
                    reimbursementItem.setKnowTotalPrice(new BigDecimal("0"));
                    reimbursementItem.setMortgagedTotalPrice(new BigDecimal("0"));
                    reimbursementItem.setOwedTotalPrice(new BigDecimal("0"));
                    reimbursementItem.setOtherTotalPrice(new BigDecimal("0"));
                    reimbursementItem.setCreator(processControllerComponent.getThisUser());
                    schemeReimbursementItemDao.addObject(reimbursementItem);
                }
            }
            itemVos = findQueryBySchemeReimbursementItem2(schemeReimbursementItem);
        }
        return itemVos;
    }

    public void saveAndUpdateSchemeReimbursementItem(SchemeReimbursementItem schemeReimbursementItem) {
        if (schemeReimbursementItem == null) {
            return;
        }
        if (schemeReimbursementItem.getId() == null || schemeReimbursementItem.getId() == 0) {
            schemeReimbursementItem.setCreator(processControllerComponent.getThisUser());
            schemeReimbursementItemDao.addObject(schemeReimbursementItem);
        } else {
            schemeReimbursementItemDao.updateObject(schemeReimbursementItem);
        }
    }

    public List<SchemeReimbursementItemVo> findQueryBySchemeReimbursementItem2(SchemeReimbursementItem schemeReimbursementItem) {
        List<SchemeReimbursementItemVo> vos = Lists.newArrayList();
        List<SchemeReimbursementItem> list = schemeReimbursementItemDao.getListObject2(schemeReimbursementItem);
        if (CollectionUtils.isNotEmpty(list)) {
            for (SchemeReimbursementItem item : list) {
                vos.add(getSchemeReimbursementItemVo(item));
            }
        }
        return vos;
    }

    private List<Integer> splitIntegerListJudgeNumber(String number) {
        List<Integer> integerList = new ArrayList<>();
        if (StringUtils.isEmpty(number)) {
            return integerList;
        }
        List<String> string2List = FormatUtils.transformString2List(number, ",");
        if (CollectionUtils.isEmpty(string2List)) {
            string2List = FormatUtils.transformString2List(number, "-");
        }
        if (CollectionUtils.isEmpty(string2List)) {
            string2List = FormatUtils.transformString2List(number, "_");
        }
        if (CollectionUtils.isNotEmpty(string2List)) {
            for (String num : string2List) {
                if (NumberUtils.isNumber(num)) {
                    integerList.add(NumberUtils.createInteger(num));
                }
            }
        }
        return integerList;
    }

    public void repeatInit(Integer id) {
        SchemeReimbursement schemeReimbursement = schemeReimbursementDao.getSchemeReimbursement(id);
        SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.getSchemeAreaGroup(schemeReimbursement.getAreaId());
        init(schemeReimbursement, schemeAreaGroup);
    }

    public SchemeReimbursement getSchemeReimbursement(Integer planDetailsId, Integer areaId) {
        SchemeReimbursement where = new SchemeReimbursement();
        where.setPlanDetailsId(planDetailsId);
        where.setAreaId(areaId);
        return schemeReimbursementDao.getSchemeReimbursement(where);
    }

    public List<SchemeReimbursement> getSchemeReimbursementList(Integer project, Integer areaId) {
        SchemeReimbursement where = new SchemeReimbursement();
        where.setProjectId(project);
        where.setAreaId(areaId);
        return schemeReimbursementDao.getObjectList(where);
    }

    /**
     * 删除数据
     *
     * @param reimbursementId
     */
    public void deleteItemsByMasterId(Integer reimbursementId) {
        if (reimbursementId == null) return;
        schemeReimbursementItemDao.deleteItemsByMasterId(reimbursementId);
    }

    /**
     * 初始化数据
     *
     * @param schemeReimbursement
     * @param schemeAreaGroup
     */
    public void init(SchemeReimbursement schemeReimbursement, SchemeAreaGroup schemeAreaGroup) {
        //1.先删除已生成的受偿款数据；2找出与权证相关的估价对象，循环处理每一个估价对象，关联其他权信息
        if (schemeReimbursement == null || schemeReimbursement.getId() == null) return;
        deleteItemsByMasterId(schemeReimbursement.getId());//清除数据
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(schemeAreaGroup.getId());
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                SchemeReimbursementItem reimbursementItem = new SchemeReimbursementItem();
                reimbursementItem.setCreator(processControllerComponent.getThisUser());
                reimbursementItem.setProjectId(schemeReimbursement.getProjectId());
                reimbursementItem.setPlanDetailsId(schemeReimbursement.getPlanDetailsId());
                if (schemeJudgeObject.getSplitNumber() != null) {
                    reimbursementItem.setName(schemeJudgeObject.getNumber() + BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME);
                } else {
                    reimbursementItem.setName(schemeJudgeObject.getName());
                }
                reimbursementItem.setMasterId(schemeReimbursement.getId());
                reimbursementItem.setJudgeObjectId(schemeJudgeObject.getId());
                reimbursementItem.setBisEnable(true);
                reimbursementItem.setSorting(schemeJudgeObject.getSorting());
                //找出该估价对象关联的他权信息
                SurveyAssetRightDeclare rightDeclare = surveyAssetRightDeclareService.getRightDeclareByDeclareId(schemeJudgeObject.getDeclareRecordId());
                if (rightDeclare != null)
                    reimbursementItem.setInventoryRightRecordId(rightDeclare.getGroupId());
                saveAndUpdateSchemeReimbursementItem(reimbursementItem);
            }
        }
    }


    /**
     * 根据方案合并
     *
     * @param reimbursementId
     */
    public void groupByProgramme(Integer areaGroupId, Integer reimbursementId) {
        //1.找出方案中合并的估价对象 2.按合并的规则将现有数据进行合并
        deleteItemsByMasterId(reimbursementId);//清除数据
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectApplicableListByAreaGroupId(areaGroupId);
        if (CollectionUtils.isEmpty(judgeObjectList)) return;
        SchemeReimbursement schemeReimbursement = getSingleObject(reimbursementId);
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            SchemeReimbursementItem reimbursementItem = new SchemeReimbursementItem();
            reimbursementItem.setCreator(processControllerComponent.getThisUser());
            reimbursementItem.setProjectId(schemeReimbursement.getProjectId());
            reimbursementItem.setPlanDetailsId(schemeReimbursement.getPlanDetailsId());
            reimbursementItem.setName(schemeJudgeObject.getName());
            reimbursementItem.setMasterId(schemeReimbursement.getId());
            reimbursementItem.setJudgeObjectId(schemeJudgeObject.getBisMerge() == Boolean.TRUE ? schemeJudgeObject.getStandardJudgeId() : schemeJudgeObject.getId());
            reimbursementItem.setJudgeObjectNumber(schemeJudgeObject.getNumber());
            if (schemeJudgeObject.getStandardJudgeId() != null) {//找出该估价对象关联的他权信息
                SurveyAssetRightDeclare rightDeclare = surveyAssetRightDeclareService.getRightDeclareByDeclareId(schemeJudgeObject.getDeclareRecordId());
                if (rightDeclare != null)
                    reimbursementItem.setInventoryRightRecordId(rightDeclare.getGroupId());
            }
            reimbursementItem.setBisEnable(true);
            reimbursementItem.setSorting(schemeJudgeObject.getSorting());
            saveAndUpdateSchemeReimbursementItem(reimbursementItem);
        }
    }

    /**
     * 根据他权合并
     *
     * @param reimbursementId
     */
    public void groupByOtherRight(Integer areaGroupId, Integer reimbursementId) {
        deleteItemsByMasterId(reimbursementId);//清除数据
        //1.先找出该区域下的权证数据，2.再到他权的数据表中找到关联上的权证数据 3.将有关联关系的数据绑到一起 4.将剩余的估价对象数据单独生成
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaGroupId);
        SchemeReimbursement schemeReimbursement = getSingleObject(reimbursementId);
        List<Integer> declareIds = LangUtils.transform(judgeObjectList, o -> o.getDeclareRecordId());
        List<SurveyAssetRightDeclare> rightDeclareList = surveyAssetRightDeclareService.getRightDeclareListByDeclareIds(declareIds);
        if (CollectionUtils.isNotEmpty(rightDeclareList)) {
            Multimap<Integer, SurveyAssetRightDeclare> multimap = FormatUtils.mappingMultiEntity(rightDeclareList, o -> o.getGroupId());
            for (Map.Entry<Integer, Collection<SurveyAssetRightDeclare>> entry : multimap.asMap().entrySet()) {
                List<SurveyAssetRightDeclare> surveyAssetRightDeclares = Lists.newArrayList(entry.getValue());
                SurveyAssetRightDeclare assetRightDeclare = surveyAssetRightDeclares.get(0);
                //找个同属于该组的估价对象
                List<Integer> groupDeclareIds = LangUtils.transform(surveyAssetRightDeclares, o -> o.getDeclareId());
                List<SchemeJudgeObject> groupJudgeObjects = schemeJudgeObjectService.getListByDeclareIds(groupDeclareIds);
                String number = StringUtils.join(LangUtils.transform(groupJudgeObjects, o -> o.getNumber()),',');
                SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getJudgeObjectByDeclareId(assetRightDeclare.getDeclareId());
                SchemeReimbursementItem reimbursementItem = new SchemeReimbursementItem();
                reimbursementItem.setCreator(processControllerComponent.getThisUser());
                reimbursementItem.setProjectId(schemeReimbursement.getProjectId());
                reimbursementItem.setPlanDetailsId(schemeReimbursement.getPlanDetailsId());
                reimbursementItem.setName(number + BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME);
                reimbursementItem.setMasterId(schemeReimbursement.getId());
                reimbursementItem.setJudgeObjectId(schemeJudgeObject.getId());
                reimbursementItem.setJudgeObjectNumber(number);
                SurveyAssetRightDeclare rightDeclare = surveyAssetRightDeclareService.getRightDeclareByDeclareId(schemeJudgeObject.getDeclareRecordId());
                if (rightDeclare != null)
                    reimbursementItem.setInventoryRightRecordId(rightDeclare.getGroupId());
                reimbursementItem.setBisEnable(true);
                reimbursementItem.setSorting(schemeJudgeObject.getSorting());
                saveAndUpdateSchemeReimbursementItem(reimbursementItem);
                for (SurveyAssetRightDeclare surveyAssetRightDeclare : surveyAssetRightDeclares) {//移除已处理的估价对象
                    declareIds.remove(surveyAssetRightDeclare.getDeclareId());
                }
            }
        }
        List<SchemeJudgeObject> objectList = schemeJudgeObjectService.getListByDeclareIds(declareIds);
        if (CollectionUtils.isNotEmpty(objectList)) {
            for (SchemeJudgeObject judgeObject : objectList) {
                SchemeReimbursementItem reimbursementItem = new SchemeReimbursementItem();
                reimbursementItem.setCreator(processControllerComponent.getThisUser());
                reimbursementItem.setProjectId(schemeReimbursement.getProjectId());
                reimbursementItem.setPlanDetailsId(schemeReimbursement.getPlanDetailsId());
                reimbursementItem.setName(judgeObject.getName());
                reimbursementItem.setMasterId(schemeReimbursement.getId());
                reimbursementItem.setJudgeObjectId(judgeObject.getId());
                reimbursementItem.setJudgeObjectNumber(judgeObject.getNumber());
                if (judgeObject.getStandardJudgeId() != null) {//找出该估价对象关联的他权信息
                    SurveyAssetRightDeclare rightDeclare = surveyAssetRightDeclareService.getRightDeclareByDeclareId(judgeObject.getDeclareRecordId());
                    if (rightDeclare != null)
                        reimbursementItem.setInventoryRightRecordId(rightDeclare.getGroupId());
                }
                reimbursementItem.setBisEnable(true);
                reimbursementItem.setSorting(judgeObject.getSorting());
                saveAndUpdateSchemeReimbursementItem(reimbursementItem);
            }
        }
    }


    /**
     * 获取法定优先集合和估价对象的map
     *
     * @param schemeJudgeObjectList
     * @param projectId
     * @return
     */
    public Map<SchemeJudgeObject, List<SchemeReimbursementItemVo>> getSchemeReimbursementItemMap(List<SchemeJudgeObject> schemeJudgeObjectList, Integer projectId) {
        Map<SchemeJudgeObject, List<SchemeReimbursementItemVo>> schemeJudgeObjectListMap = Maps.newLinkedHashMap();
        Map<Integer, List<SchemeReimbursementItemVo>> integerListMap = new HashMap<>();
        //筛选出由他项权利组 生成的法定优先款
        if (CollectionUtils.isEmpty(schemeJudgeObjectList)) {
            return schemeJudgeObjectListMap;
        }
        List<Integer> ids = schemeJudgeObjectList.stream().map(schemeJudgeObject -> schemeJudgeObject.getId()).collect(Collectors.toList());
        Iterator<SchemeJudgeObject> iterator = schemeJudgeObjectList.iterator();
        while (iterator.hasNext()) {
            SchemeJudgeObject judgeObject = iterator.next();
            if (judgeObject.getDeclareRecordId() == null) {
                continue;
            }
            List<SurveyAssetRightGroup> rightGroupList = surveyAssetRightGroupService.getSurveyAssetRightGroupByDeclareRecord(judgeObject.getDeclareRecordId(), projectId);
            if (CollectionUtils.isEmpty(rightGroupList)) {
                continue;
            }
            Map<Integer, SchemeReimbursementItemVo> integerSchemeReimbursementItemVoMap = Maps.newHashMap();
            for (SurveyAssetRightGroup rightGroup : rightGroupList) {
                SchemeReimbursementItemVo vo = getItemVoByGroupId(rightGroup.getId());
                if (vo != null) {
                    integerSchemeReimbursementItemVoMap.put(vo.getId(), vo);
                }
            }
            integerListMap.put(judgeObject.getId(), integerSchemeReimbursementItemVoMap.values().stream().collect(Collectors.toList()));
        }
        Set<Integer> integerSet = integerListMap.keySet();//已经从组里面 筛选出来的估价对象 id
        Set<Integer> integers = schemeJudgeObjectList.stream().map(oo -> oo.getId()).collect(Collectors.toSet());
        Collection<Integer> collection = CollectionUtils.subtract(integers, integerSet);
        //自动生成的法定优先款
        if (CollectionUtils.isNotEmpty(collection)) {
            Iterator<Integer> integerIterator = collection.iterator();
            while (integerIterator.hasNext()) {
                Integer integer = integerIterator.next();
                SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.stream().filter(obj -> Objects.equal(obj.getId(), integer)).findFirst().get();
                SchemeReimbursementItem select = new SchemeReimbursementItem();
                select.setProjectId(projectId);
                select.setJudgeObjectId(integer);
                select.setName(String.join("", schemeJudgeObject.getNumber(), "号"));
                select.setInventoryRightRecordId(null);
                List<SchemeReimbursementItem> schemeReimbursementItemList = schemeReimbursementItemDao.getListObject2(select);
                if (CollectionUtils.isEmpty(schemeReimbursementItemList)) {
                    continue;
                }
                integerListMap.put(integer, schemeReimbursementItemList.stream().map(oo -> getSchemeReimbursementItemVo(oo)).collect(Collectors.toList()));
            }
        }
        //拼接
        if (!integerListMap.isEmpty()) {
            Set<Map.Entry<Integer, List<SchemeReimbursementItemVo>>> entries = integerListMap.entrySet();
            Iterator<Map.Entry<Integer, List<SchemeReimbursementItemVo>>> entryIterator = entries.iterator();
            while (entryIterator.hasNext()) {
                Map.Entry<Integer, List<SchemeReimbursementItemVo>> entry = entryIterator.next();
                if (ids.contains(entry.getKey())) {
                    SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.stream().filter(obj -> Objects.equal(obj.getId(), entry.getKey())).findFirst().get();
                    schemeJudgeObjectListMap.put(schemeJudgeObject, entry.getValue());
                }
            }
        }
        return schemeJudgeObjectListMap;
    }

    public SchemeReimbursementItemVo getSchemeReimbursementItemVo(SchemeReimbursementItem schemeReimbursementItem) {
        SchemeReimbursementItemVo vo = new SchemeReimbursementItemVo();
        BeanUtils.copyProperties(schemeReimbursementItem, vo);
        vo.setJudgeObjectName(schemeReimbursementItem.getName());
        return vo;
    }
}
