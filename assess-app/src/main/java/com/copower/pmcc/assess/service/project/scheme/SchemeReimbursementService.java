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
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightRecordCenterService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightRecordService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
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
    private SurveyAssetInventoryRightRecordCenterService surveyAssetInventoryRightRecordCenterService;
    @Autowired
    private SurveyAssetInventoryRightRecordService surveyAssetInventoryRightRecordService;

    public List<SchemeReimbursement> getObjectList(SchemeReimbursement schemeReimbursement) {
        return schemeReimbursementDao.getObjectList(schemeReimbursement);
    }

    public SchemeReimbursementItemVo getSchemeReimbursementItemVoByInventoryRightRecordId(Integer inventoryRightRecordId) {
        if (inventoryRightRecordId == null) {
            return null;
        }
        SchemeReimbursementItem select = new SchemeReimbursementItem();
        select.setInventoryRightRecordId(inventoryRightRecordId);
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


    public String getFullDescription(SchemeReimbursementItem object) {
        StringBuilder builder = new StringBuilder(8);
        BigDecimal decimal = new BigDecimal("10000");
        builder.append(String.format("已抵押担保的债权数额总价%s万元,", (object.getMortgagedTotalPrice().divide(decimal))));
        builder.append(String.format("拖欠的建设工程价款总价%s万元,", (object.getOwedTotalPrice().divide(decimal))));
        builder.append(String.format("其它法定优先受偿款总价%s万元,", (object.getOtherTotalPrice().divide(decimal))));
        builder.append(String.format("估价师知悉的法定优先受偿款总价%s万元,", (object.getKnowTotalPrice().divide(decimal))));

        return builder.toString();
    }


    /**
     * 获取明细
     *
     * @param masterId
     * @return
     */
    public List<SchemeReimbursementItemVo> getItemByMasterId(Integer masterId) {
        SchemeReimbursementItem schemeReimbursementItem = new SchemeReimbursementItem();
        schemeReimbursementItem.setMasterId(masterId);
        return findQueryBySchemeReimbursementItem2(schemeReimbursementItem);
    }

    public void saveAndUpdateSchemeReimbursementItem(SchemeReimbursementItem schemeReimbursementItem) {
        if (schemeReimbursementItem == null) {
            return;
        }
        if (schemeReimbursementItem.getId() == null || schemeReimbursementItem.getId().intValue() == 0) {
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

    /**
     * 初始化数据
     *
     * @param schemeReimbursement
     * @param projectPlanDetails
     * @param schemeAreaGroup
     */
    public void init(SchemeReimbursement schemeReimbursement, ProjectPlanDetails projectPlanDetails, SchemeAreaGroup schemeAreaGroup) {
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.OTHER_RIGHT, projectInfo.getProjectCategoryId());
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(schemeAreaGroup.getId());
        if (projectPlanDetails == null || CollectionUtils.isEmpty(schemeJudgeObjectList) || projectPhase == null || schemeReimbursement == null){
            return;
        }
        ProjectPlanDetails query = new ProjectPlanDetails();
        query.setProjectId(projectPlanDetails.getProjectId());
        query.setProjectPhaseId(projectPhase.getId());
        List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
        if (CollectionUtils.isEmpty(projectPlanDetailsList)){
            return;
        }
        //SurveyAssetInventoryRightRecordCenter只会有一个
        SurveyAssetInventoryRightRecordCenter selectRightRecordCenter = new SurveyAssetInventoryRightRecordCenter();
        selectRightRecordCenter.setProjectId(projectPlanDetailsList.get(0).getProjectId());
        selectRightRecordCenter.setPlanDetailsId(projectPlanDetailsList.get(0).getId());
        selectRightRecordCenter.setProcessInsId(projectPlanDetailsList.get(0).getProcessInsId());
        List<SurveyAssetInventoryRightRecordCenter> centerList = surveyAssetInventoryRightRecordCenterService.getSurveyAssetInventoryRightRecordCenterList(selectRightRecordCenter);
        if (CollectionUtils.isEmpty(centerList)){
            return;
        }
        //他项权利组(多对) 每组有一个或者多个他项权力   他项权利组对应一个SchemeReimbursementItem
        List<SurveyAssetInventoryRightRecord> rightRecordList = surveyAssetInventoryRightRecordCenterService.getSurveyAssetInventoryRightRecordList(centerList.get(0).getId(), centerList.get(0).getProjectId(), centerList.get(0).getPlanDetailsId());
        if (CollectionUtils.isEmpty(rightRecordList)){
            return;
        }
        for (SurveyAssetInventoryRightRecord surveyAssetInventoryRightRecord : rightRecordList) {
            //取 生成多少条数据,1,2号
            if (StringUtils.isNotBlank(surveyAssetInventoryRightRecord.getRecordIds())) {
                List<DeclareRecord> declareRecordList = surveyAssetInventoryRightRecordCenterService.getDeclareRecordList(surveyAssetInventoryRightRecord.getRecordIds().split(","));
                if (CollectionUtils.isNotEmpty(declareRecordList)) {
                    Map<SchemeJudgeObject, DeclareRecord> schemeJudgeObjectDeclareRecordMap = surveyAssetInventoryRightRecordCenterService.getDeclareRecordJudgeObjectMap(declareRecordList, schemeJudgeObjectList);
                    if (!schemeJudgeObjectDeclareRecordMap.isEmpty()) {
                        List<SchemeJudgeObject> judgeObjectList = Lists.newArrayList();
                        for (Map.Entry<SchemeJudgeObject, DeclareRecord> recordEntry : schemeJudgeObjectDeclareRecordMap.entrySet()) {
                            judgeObjectList.add(recordEntry.getKey());
                        }
                        //生成name
                        List<String> stringList = Lists.newArrayList();
                        judgeObjectList.stream().forEach(schemeJudgeObject -> stringList.add(schemeJudgeObject.getNumber()));
                        //排一次序
                        Ordering<String> ordering = Ordering.from((o1, o2) -> {
                            int x = Integer.parseInt(o1.substring(0, 1));
                            int y = Integer.parseInt(o2.substring(0, 1));
                            return (x > y) ? -1 : ((x == y) ? 0 : 1);
                        });
                        stringList.sort(ordering.reverse());
                        SchemeReimbursementItem itemVo = this.getSchemeReimbursementItemVoByInventoryRightRecordId(surveyAssetInventoryRightRecord.getId());
                        //当查询不到则生成
                        if (itemVo == null) {
                            itemVo = new SchemeReimbursementItem();
                            itemVo.setCreator(processControllerComponent.getThisUser());
                            itemVo.setName(String.format("%s%s", StringUtils.join(stringList, ","), "号"));
                        }
                        itemVo.setMasterId(schemeReimbursement.getId());
                        itemVo.setInventoryRightRecordId(surveyAssetInventoryRightRecord.getId());
                        itemVo.setProjectId(schemeReimbursement.getProjectId());
                        itemVo.setPlanDetailsId(schemeReimbursement.getPlanDetailsId());
                        //注意这写入的估价对象id实际是随便写的一个实际上应当是 申报id -> 他项权力组 <==> 法定优先受偿款(子即Item)
                        itemVo.setJudgeObjectId(judgeObjectList.get(0).getId());
                        if (itemVo.getId() == null) {
                            schemeReimbursementItemDao.addObject(itemVo);
                        } else {
                            schemeReimbursementItemDao.updateObject(itemVo);
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取法定优先集合和估价对象的map
     *
     * @param schemeJudgeObjectList
     * @return
     */
    public Map<SchemeJudgeObject, List<SchemeReimbursementItemVo>> getSchemeReimbursementItemVoMapAndSchemeJudgeObject(List<SchemeJudgeObject> schemeJudgeObjectList, Integer projectId) {
        Map<SchemeJudgeObject, List<SchemeReimbursementItemVo>> schemeJudgeObjectListMap = Maps.newLinkedHashMap();
        List<Integer> idsB = Lists.newArrayList();
        List<Integer> idsA = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            schemeJudgeObjectList.stream().filter(oo -> oo.getDeclareRecordId() != null).forEach(schemeJudgeObject -> {
                idsA.add(schemeJudgeObject.getDeclareRecordId());
                List<SurveyAssetInventoryRightRecord> surveyAssetInventoryRightRecordList = surveyAssetInventoryRightRecordService.getSurveyAssetInventoryRightRecordByDeclareRecord(schemeJudgeObject.getDeclareRecordId(), projectId);
                if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightRecordList)) {
                    surveyAssetInventoryRightRecordList.stream().filter(po -> StringUtils.isNotBlank(po.getRecordIds())).forEach(po -> {
                        String[] strings = po.getRecordIds().split(",");
                        //只取第一个
                        idsB.add(Integer.parseInt(strings[0]));
                    });
                }
            });
        }
        //取交集
        final List<Integer> ids = new ArrayList<Integer>(0);
        if (CollectionUtils.isNotEmpty(idsA) && CollectionUtils.isNotEmpty(idsB)) {
            Collection<Integer> collection = CollectionUtils.intersection(
                    idsB.stream().distinct().collect(Collectors.toList()),
                    idsA.stream().distinct().collect(Collectors.toList())
            );
            if (CollectionUtils.isNotEmpty(collection)) {
                collection.stream().forEach(integer -> ids.add(integer));
            }
        }
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList) && CollectionUtils.isNotEmpty(ids)) {
            schemeJudgeObjectList.stream().filter(oo -> {
                //意思是必须在申报ids中的集合里面能够找到相同元素才成功放行
                Integer id = oo.getDeclareRecordId();
                if (id != null) {
                    return ids.stream().filter(integer -> id.intValue() == integer.intValue()).count() >= 1;
                }
                return false;
            }).forEach(schemeJudgeObject -> {
                List<SchemeReimbursementItemVo> itemVoList = Lists.newArrayList();
                List<SurveyAssetInventoryRightRecord> surveyAssetInventoryRightRecordList = surveyAssetInventoryRightRecordService.getSurveyAssetInventoryRightRecordByDeclareRecord(schemeJudgeObject.getDeclareRecordId(), projectId);
                if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightRecordList)) {
                    surveyAssetInventoryRightRecordList.stream().forEach(oo -> {
                        SchemeReimbursementItemVo vo = this.getSchemeReimbursementItemVoByInventoryRightRecordId(oo.getId());
                        if (vo != null) {
                            itemVoList.add(vo);
                        }
                    });
                }
                if (CollectionUtils.isNotEmpty(itemVoList)) {
                    schemeJudgeObjectListMap.put(schemeJudgeObject, itemVoList.stream().distinct().collect(Collectors.toList()));
                }
            });
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
