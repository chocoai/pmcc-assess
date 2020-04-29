package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeReimbursementDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeReimbursementItemDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeReimbursementDto;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeReimbursementItemVo;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetRightGroupService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetRightService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private SurveyAssetRightGroupService surveyAssetRightGroupService;
    @Autowired
    private SurveyAssetRightService surveyAssetRightService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;

    public List<SchemeReimbursement> getObjectList(SchemeReimbursement schemeReimbursement) {
        return schemeReimbursementDao.getObjectList(schemeReimbursement);
    }

    public SchemeReimbursementItemVo getSchemeReimbursementItemVoByInventoryRightRecordId(Integer groupId) {
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

    public SchemeReimbursement getSchemeReimbursementByAreaId(Integer areaId, Integer projectId) {
        if (areaId == null) {
            return null;
        }
        SchemeReimbursement query = new SchemeReimbursement();
        if (projectId != null) {
            query.setProjectId(projectId);
        }
        query.setAreaId(areaId);
        List<SchemeReimbursement> schemeReimbursementList = this.getObjectList(query);
        if (CollectionUtils.isNotEmpty(schemeReimbursementList)) {
            return schemeReimbursementList.get(0);
        }
        return null;
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
        Ordering<SchemeReimbursementItemVo> ordering = Ordering.from(new Comparator<SchemeReimbursementItemVo>() {
            @Override
            public int compare(SchemeReimbursementItemVo o1, SchemeReimbursementItemVo o2) {
                if (StringUtils.isBlank(o1.getName())) {
                    return 0;
                }
                if (StringUtils.isBlank(o2.getName())) {
                    return 0;
                }
                String nameA = StringUtils.remove(o1.getName(), "号");
                String nameB = StringUtils.remove(o2.getName(), "号");
                Integer a = getNumberOrderingByString(nameA);
                Integer b = getNumberOrderingByString(nameB);
                if (a != null && b != null) {
                    return a.compareTo(b);
                }
                return 0;
            }
        });
        itemVos.sort(ordering);
        return itemVos;
    }

    private Integer getNumberOrderingByString(String number) {
        List<Integer> integerList = splitIntegerListJudgeNumber(number);
        if (CollectionUtils.isNotEmpty(integerList)) {
            Collections.sort(integerList);
            return integerList.get(0);
        }
        return null;
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

    private String getNameBySchemeJudgeObjectList(List<SchemeJudgeObject> judgeObjectList) {
        List<Integer> integerList = new ArrayList<>(judgeObjectList.size());
        if (CollectionUtils.isNotEmpty(judgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
                integerList.addAll(splitIntegerListJudgeNumber(schemeJudgeObject.getNumber()));
            }
        }
        Collections.sort(integerList);
        return String.join("", StringUtils.join(integerList, ","), "号");
    }

    public void repeatInit(Integer id) {
        SchemeReimbursement schemeReimbursement = schemeReimbursementDao.getSchemeReimbursement(id);
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(schemeReimbursement.getPlanDetailsId());
        SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.getSchemeAreaGroup(schemeReimbursement.getAreaId());
        init(schemeReimbursement, projectPlanDetails, schemeAreaGroup, true);
    }

    public SchemeReimbursement getSchemeReimbursementByAreaIdAndByPlanDetailsId(Integer planDetailsId,Integer areaId){
        return schemeReimbursementDao.getSchemeReimbursementByAreaIdAndByPlanDetailsId(planDetailsId,areaId) ;
    }


    /**
     * 初始化数据
     *
     * @param schemeReimbursement
     * @param projectPlanDetails
     * @param schemeAreaGroup
     */
    public void init(SchemeReimbursement schemeReimbursement, ProjectPlanDetails projectPlanDetails, SchemeAreaGroup schemeAreaGroup, boolean repeat) {
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.OTHER_RIGHT, projectInfo.getProjectCategoryId());
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(schemeAreaGroup.getId());
        if (projectPlanDetails == null || CollectionUtils.isEmpty(schemeJudgeObjectList) || projectPhase == null || schemeReimbursement == null) {
            return;
        }

        ProjectPlanDetails query = new ProjectPlanDetails();
        query.setProjectId(projectPlanDetails.getProjectId());
        query.setProjectPhaseId(projectPhase.getId());
        List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
        if (CollectionUtils.isEmpty(projectPlanDetailsList)) {
            return;
        }
        SurveyAssetRight surveyAssetRight = surveyAssetRightService.getSurveyAssetRightOnly(projectPlanDetailsList.get(0), null);
        if (surveyAssetRight == null) {
            return;
        }
        List<SurveyAssetRightGroup> rightGroupList = surveyAssetRightGroupService.getSurveyAssetRightGroupListByMasterId(surveyAssetRight.getId());
        List<DeclareRecord> declareRecordLists = declareRecordService.getDeclareRecordByProjectId(projectPlanDetails.getProjectId());
        List<Integer> declares = new ArrayList<>();
        //他项权利组(多对) 每组有一个或者多个他项权力   他项权利组对应一个SchemeReimbursementItem
        if (CollectionUtils.isNotEmpty(rightGroupList)) {
            for (SurveyAssetRightGroup rightGroup : rightGroupList) {
                SchemeReimbursementItem itemVo = getSchemeReimbursementItemVoByInventoryRightRecordId(rightGroup.getId());
                List<SurveyAssetRightDeclare> declareList = surveyAssetRightGroupService.getSurveyAssetRightDeclareListByGroupId(rightGroup.getId());
                if (CollectionUtils.isEmpty(declareList)) {
                    if (repeat && itemVo != null) {
                        schemeReimbursementItemDao.deleteObject(itemVo.getId());
                    }
                    continue;
                }
                List<Integer> ids = declareList.stream().map(surveyAssetRightDeclare -> surveyAssetRightDeclare.getDeclareId()).collect(Collectors.toList());
                if (CollectionUtils.isEmpty(ids)) {
                    continue;
                }
                declares.addAll(ids);
                Map<SchemeJudgeObject, DeclareRecord> schemeJudgeObjectDeclareRecordMap = surveyAssetRightService.getDeclareRecordJudgeObjectMap(declareRecordService.getDeclareRecordListByIds(ids), schemeJudgeObjectList);
                if (schemeJudgeObjectDeclareRecordMap.isEmpty()) {
                    continue;
                }
                List<SchemeJudgeObject> judgeObjectList = Lists.newArrayList();
                for (Map.Entry<SchemeJudgeObject, DeclareRecord> recordEntry : schemeJudgeObjectDeclareRecordMap.entrySet()) {
                    judgeObjectList.add(recordEntry.getKey());
                }
                //当查询不到则生成
                if (itemVo == null) {
                    itemVo = new SchemeReimbursementItem();
                    itemVo.setCreator(processControllerComponent.getThisUser());
                    //生成name
                    itemVo.setName(getNameBySchemeJudgeObjectList(judgeObjectList));
                }
                if (repeat) {
                    itemVo.setName(getNameBySchemeJudgeObjectList(judgeObjectList));

                }
                itemVo.setMasterId(schemeReimbursement.getId());
                itemVo.setInventoryRightRecordId(rightGroup.getId());
                itemVo.setProjectId(schemeReimbursement.getProjectId());
                itemVo.setPlanDetailsId(schemeReimbursement.getPlanDetailsId());
                //注意这写入的估价对象id实际是随便写的一个实际上应当是 申报id -> 他项权力组 <==> 法定优先受偿款(子即Item)
                itemVo.setJudgeObjectId(judgeObjectList.get(0).getId());
                saveAndUpdateSchemeReimbursementItem(itemVo);
            }
        }
        //remove
        if (CollectionUtils.isNotEmpty(declares) && CollectionUtils.isNotEmpty(declareRecordLists)) {
            Iterator<DeclareRecord> iterator = declareRecordLists.iterator();
            while (iterator.hasNext()) {
                DeclareRecord declareRecord = iterator.next();
                if (declares.contains(declareRecord.getId())) {
                    iterator.remove();
                    Map<SchemeJudgeObject, DeclareRecord> schemeJudgeObjectDeclareRecordMap = surveyAssetRightService.getDeclareRecordJudgeObjectMap(Lists.newArrayList(declareRecord), schemeJudgeObjectList);
                    if (!schemeJudgeObjectDeclareRecordMap.isEmpty()) {
                        for (Map.Entry<SchemeJudgeObject, DeclareRecord> recordEntry : schemeJudgeObjectDeclareRecordMap.entrySet()) {
                            SchemeReimbursementItem itemVo = new SchemeReimbursementItem();
                            itemVo.setCreator(processControllerComponent.getThisUser());
                            itemVo.setProjectId(schemeReimbursement.getProjectId());
                            itemVo.setPlanDetailsId(schemeReimbursement.getPlanDetailsId());
                            itemVo.setName(String.join("", recordEntry.getKey().getNumber(), "号"));
                            itemVo.setMasterId(schemeReimbursement.getId());
                            itemVo.setInventoryRightRecordId(null);
                            itemVo.setJudgeObjectId(recordEntry.getKey().getId());
                            List<SchemeReimbursementItem> schemeReimbursementItemList = schemeReimbursementItemDao.getListObject2(itemVo);
                            if (CollectionUtils.isNotEmpty(schemeReimbursementItemList)) {
                                schemeReimbursementItemList.forEach(schemeReimbursementItem -> schemeReimbursementItemDao.deleteObject(schemeReimbursementItem.getId()));
                            }
                        }
                    }
                }
            }
        }
        //对多余的申报id 同样生成 法定优先款
        if (CollectionUtils.isNotEmpty(declareRecordLists)) {
            Map<SchemeJudgeObject, DeclareRecord> schemeJudgeObjectDeclareRecordMap = surveyAssetRightService.getDeclareRecordJudgeObjectMap(declareRecordLists, schemeJudgeObjectList);
            if (!schemeJudgeObjectDeclareRecordMap.isEmpty()) {
                for (Map.Entry<SchemeJudgeObject, DeclareRecord> recordEntry : schemeJudgeObjectDeclareRecordMap.entrySet()) {
                    SchemeReimbursementItem itemVo = new SchemeReimbursementItem();
                    itemVo.setCreator(processControllerComponent.getThisUser());
                    itemVo.setProjectId(schemeReimbursement.getProjectId());
                    itemVo.setPlanDetailsId(schemeReimbursement.getPlanDetailsId());
                    itemVo.setName(String.join("", recordEntry.getKey().getNumber(), "号"));
                    itemVo.setMasterId(schemeReimbursement.getId());
                    itemVo.setInventoryRightRecordId(null);
                    //注意这写入的估价对象id实际是随便写的一个实际上应当是 申报id -> 他项权力组 <==> 法定优先受偿款(子即Item)
                    itemVo.setJudgeObjectId(recordEntry.getKey().getId());
                    List<SchemeReimbursementItem> schemeReimbursementItemList = schemeReimbursementItemDao.getListObject2(itemVo);
                    if (CollectionUtils.isEmpty(schemeReimbursementItemList)) {
                        schemeReimbursementItemDao.addObject(itemVo);
                    }
                }
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
    public Map<SchemeJudgeObject, List<SchemeReimbursementItemVo>> getSchemeReimbursementItemVoMapAndSchemeJudgeObject2(List<SchemeJudgeObject> schemeJudgeObjectList, Integer projectId) {
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
                SchemeReimbursementItemVo vo = getSchemeReimbursementItemVoByInventoryRightRecordId(rightGroup.getId());
                if(vo!=null){
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
