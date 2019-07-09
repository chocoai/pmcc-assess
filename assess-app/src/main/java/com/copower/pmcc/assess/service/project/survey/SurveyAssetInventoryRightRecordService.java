package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryRightRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyJudgeObjectGroupDto;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyRightGroupDto;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyAssetInventoryRightRecordVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author: zch
 * @date: 2019/3/18 17:29
 * @description:他项权力申报类 (他项权利主类 > 他项权力申报类 > 他项权力普通类)
 */
@Service
public class SurveyAssetInventoryRightRecordService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SurveyAssetInventoryRightRecordDao surveyAssetInventoryRightRecordDao;
    @Autowired
    private SurveyAssetInventoryRightService surveyAssetInventoryRightService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private SurveyAssetInventoryService surveyAssetInventoryService;

    @Autowired
    private CommonService commonService;

    public void clear(SurveyAssetInventoryRightRecord surveyAssetInventoryRightRecord) {
        surveyAssetInventoryRightRecord.setCreator(commonService.thisUserAccount());
        List<SurveyAssetInventoryRightRecord> surveyAssetInventoryRightRecordList = this.surveyAssetInventoryRightRecordList(surveyAssetInventoryRightRecord);
        if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightRecordList)) {
            for (SurveyAssetInventoryRightRecord inventoryRightRecord : surveyAssetInventoryRightRecordList) {
                SurveyAssetInventoryRight select = new SurveyAssetInventoryRight();
                select.setInventoryRightRecordId(inventoryRightRecord.getId());
                surveyAssetInventoryRightService.removeSurveyAssetInventoryRight(select);
                try {
                    deleteSurveyAssetInventoryRightRecordById(inventoryRightRecord.getId());
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            }
        }
    }

    /**
     * 利用申报id获取他项权力组信息
     *
     * @param declareRecordId
     * @param projectId
     * @return
     */
    public List<SurveyAssetInventoryRightRecord> getSurveyAssetInventoryRightRecordByDeclareRecord(Integer declareRecordId, Integer projectId) {
        List<SurveyAssetInventoryRightRecord> surveyAssetInventoryRightRecordList = Lists.newArrayList();
        String value = declareRecordId.toString();
        SurveyAssetInventoryRightRecord query = new SurveyAssetInventoryRightRecord();
        query.setProjectId(projectId);
        List<SurveyAssetInventoryRightRecord> rightRecordList = this.surveyAssetInventoryRightRecordList(query);
        if (CollectionUtils.isNotEmpty(rightRecordList)) {
            rightRecordList.stream().forEach(surveyAssetInventoryRightRecord -> {
                if (StringUtils.isNotBlank(value)) {
                    if (StringUtils.isNotBlank(surveyAssetInventoryRightRecord.getRecordIds())) {
                        List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(surveyAssetInventoryRightRecord.getRecordIds()));
                        if (integers.contains(declareRecordId)) {
                            surveyAssetInventoryRightRecordList.add(surveyAssetInventoryRightRecord);
                        }
                    }
                }
            });
        }
        return surveyAssetInventoryRightRecordList;
    }

    /**
     * 根据项目id获取分组
     *
     * @param projectId
     * @return
     */
    public List<SurveyAssetInventoryRightRecord> getListByProjectId(Integer projectId) {
        SurveyAssetInventoryRightRecord where = new SurveyAssetInventoryRightRecord();
        where.setProjectId(projectId);
        List<SurveyAssetInventoryRightRecord> rightRecords = surveyAssetInventoryRightRecordDao.surveyAssetInventoryRightRecordList(where);
        return rightRecords;
    }

    /**
     * 获取区域下拥有他权的权证数据
     *
     * @param areaId
     * @return
     */
    public List<Integer> getDeclareRecordsByAreaId(Integer projectId, Integer areaId) {
        List<SurveyAssetInventoryRightRecord> rightRecords = getListByProjectId(projectId);
        if (CollectionUtils.isEmpty(rightRecords)) return null;
        List<DeclareRecord> areaDeclareRecords = declareRecordService.getDeclareRecordByAreaId(areaId);
        List<Integer> areaDeclareRecordIds = LangUtils.transform(areaDeclareRecords, o -> o.getId());
        List<Integer> resultList = Lists.newArrayList();
        for (SurveyAssetInventoryRightRecord rightRecord : rightRecords) {
            if (StringUtils.isNotBlank(rightRecord.getRecordIds())) {
                List<Integer> list = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(rightRecord.getRecordIds()));
                for (Integer integer : list) {
                    if (areaDeclareRecordIds.contains(integer))
                        resultList.add(integer);
                }
            }
        }
        return resultList;
    }

    public List<SurveyJudgeObjectGroupDto> groupJudgeObject(Integer projectId, List<SchemeJudgeObject> judgeObjects) {
        List<SurveyAssetInventoryRightRecord> rightRecords = getListByProjectId(projectId);
        List<SurveyJudgeObjectGroupDto> list = Lists.newArrayList();
        //他权其它
        BaseProjectClassify projectClassify = baseProjectClassifyService.getCacheProjectClassifyByFieldName(AssessProjectClassifyConstant.SINGLE_HOUSE_PROPERTY_TASKRIGHT_OTHER);
        List<SurveyAssetInventory> inventoryList = surveyAssetInventoryService.getDataByDeclareIds(LangUtils.transform(judgeObjects, o -> o.getDeclareRecordId()));
        Map<Integer, String> transferLimitMap = FormatUtils.mappingSingleEntity(inventoryList, o -> o.getDeclareRecordId(), o -> o.getTransferLimit());
        if (CollectionUtils.isEmpty(rightRecords)) {
            for (SchemeJudgeObject judgeObject : judgeObjects) {
                SurveyJudgeObjectGroupDto surveyJudgeObjectGroupDto = new SurveyJudgeObjectGroupDto();
                surveyJudgeObjectGroupDto.setJudgeObjectNumber(judgeObject.getNumber());
                surveyJudgeObjectGroupDto.setJudgeObjectId(judgeObject.getId());
                surveyJudgeObjectGroupDto.setDeclareRecordId(judgeObject.getDeclareRecordId());
                String s = transferLimitMap.get(judgeObject.getDeclareRecordId());
                surveyJudgeObjectGroupDto.setTransferLimit(s);//取资产清查中的转让限制
                surveyJudgeObjectGroupDto.setResult(StringUtils.isBlank(s) ? "强" : "一般");
            }
        } else {
            for (SurveyAssetInventoryRightRecord rightRecord : rightRecords) {
                List<Integer> declareIds = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(rightRecord.getRecordIds()));
                for (SchemeJudgeObject judgeObject : judgeObjects) {
                    if (declareIds.contains(judgeObject.getDeclareRecordId())) {
                        SurveyJudgeObjectGroupDto surveyJudgeObjectGroupDto = new SurveyJudgeObjectGroupDto();
                        surveyJudgeObjectGroupDto.setJudgeObjectNumber(judgeObject.getNumber());
                        surveyJudgeObjectGroupDto.setJudgeObjectId(judgeObject.getId());
                        surveyJudgeObjectGroupDto.setDeclareRecordId(judgeObject.getDeclareRecordId());
                        surveyJudgeObjectGroupDto.setTransferLimit(transferLimitMap.get(judgeObject.getDeclareRecordId()));//取资产清查中的转让限制
                        List<SurveyAssetInventoryRight> inventoryRights = surveyAssetInventoryRightService.getSurveyAssetInventoryRightBy(rightRecord.getId());
                        surveyJudgeObjectGroupDto.setRightList(inventoryRights);
                        Boolean rightOtherEmpty = true;
                        if (CollectionUtils.isNotEmpty(inventoryRights) && LangUtils.transform(inventoryRights, o -> o.getCategory()).contains(projectClassify.getId())) {
                            rightOtherEmpty = false;
                        }
                        if (rightOtherEmpty == Boolean.TRUE && StringUtils.isNotBlank(surveyJudgeObjectGroupDto.getTransferLimit())) {
                            surveyJudgeObjectGroupDto.setResult("一般");
                        } else if (rightOtherEmpty == Boolean.FALSE && StringUtils.isNotBlank(surveyJudgeObjectGroupDto.getTransferLimit())) {
                            surveyJudgeObjectGroupDto.setResult("弱");
                        } else {
                            surveyJudgeObjectGroupDto.setResult("强");
                        }
                        list.add(surveyJudgeObjectGroupDto);
                    }
                }
            }
        }
        return list;
    }


    /**
     * 根据类别将他权分组
     *
     * @param projectId    项目id
     * @param judgeObjects 区域下的估价对象
     * @return
     */
    public List<SurveyRightGroupDto> groupRightByCategory(Integer projectId, List<SchemeJudgeObject> judgeObjects) {
        List<SurveyAssetInventoryRightRecord> rightRecords = getListByProjectId(projectId);
        if (CollectionUtils.isEmpty(rightRecords)) return null;
        List<SurveyRightGroupDto> groupDtoList = Lists.newArrayList();
        List<Integer> declareIds = LangUtils.transform(judgeObjects, o -> o.getDeclareRecordId());
        //1.先判断该他权分组中权证是否在该区域下
        //2.如果属于当前区域则循环他权内容
        for (SurveyAssetInventoryRightRecord rightRecord : rightRecords) {
            if (StringUtils.isNotBlank(rightRecord.getRecordIds())) {
                List<Integer> list = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(rightRecord.getRecordIds()));
                Collection intersection = CollectionUtils.intersection(list, declareIds);//集合交集
                if (CollectionUtils.isNotEmpty(intersection)) {
                    List<SurveyAssetInventoryRight> inventoryRights = surveyAssetInventoryRightService.getSurveyAssetInventoryRightBy(rightRecord.getId());
                    if (CollectionUtils.isNotEmpty(inventoryRights)) {
                        for (SurveyAssetInventoryRight inventoryRight : inventoryRights) {
                            SurveyRightGroupDto surveyRightGroupDto = new SurveyRightGroupDto();
                            surveyRightGroupDto.setKey(String.format("%s%s", baseDataDicService.getNameById(inventoryRight.getCategory()), inventoryRight.getRemark()));
                            surveyRightGroupDto.setGroupId(rightRecord.getId());
                            surveyRightGroupDto.setCategory(inventoryRight.getCategory());
                            surveyRightGroupDto.setCategoryName(baseProjectClassifyService.getNameById(inventoryRight.getCategory()));
                            surveyRightGroupDto.setRemark(inventoryRight.getRemark());
                            surveyRightGroupDto.setDeclareRecordIds(Sets.newHashSet(list));
                            this.pushSurveyRightGroupDto(groupDtoList, surveyRightGroupDto);
                        }
                    }
                }
            }
        }
        return groupDtoList;
    }

    private void pushSurveyRightGroupDto(List<SurveyRightGroupDto> list, SurveyRightGroupDto surveyRightGroupDto) {
        if (list == null || surveyRightGroupDto == null) return;
        List<String> keys = LangUtils.transform(list, o -> o.getKey());
        if (keys.contains(surveyRightGroupDto.getKey())) {
            for (SurveyRightGroupDto rightGroupDto : list) {
                if (StringUtils.equals(rightGroupDto.getKey(), surveyRightGroupDto.getKey())) {
                    rightGroupDto.getDeclareRecordIds().addAll(surveyRightGroupDto.getDeclareRecordIds());
                }
            }
        } else {
            list.add(surveyRightGroupDto);
        }
    }


    public boolean addSurveyAssetInventoryRightRecord(SurveyAssetInventoryRightRecord surveyAssetInventoryRightRecord) throws Exception {
        surveyAssetInventoryRightRecord.setCreator(commonService.thisUserAccount());
        return surveyAssetInventoryRightRecordDao.addSurveyAssetInventoryRightRecord(surveyAssetInventoryRightRecord);
    }

    public boolean updateSurveyAssetInventoryRightRecord(SurveyAssetInventoryRightRecord surveyAssetInventoryRightRecord) throws Exception {
        SurveyAssetInventoryRight select = new SurveyAssetInventoryRight();
        select.setInventoryRightRecordId(surveyAssetInventoryRightRecord.getId());
        List<SurveyAssetInventoryRight> surveyAssetInventoryRightList = surveyAssetInventoryRightService.getSurveyAssetInventoryRightList(select);
        if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightList)) {
            for (SurveyAssetInventoryRight oo : surveyAssetInventoryRightList) {
                oo.setInventoryRightRecordId(surveyAssetInventoryRightRecord.getId());
                oo.setPlanDetailsId(surveyAssetInventoryRightRecord.getPlanDetailsId());
                oo.setProjectId(surveyAssetInventoryRightRecord.getProjectId());
                surveyAssetInventoryRightService.save(oo);
            }
        }
        return surveyAssetInventoryRightRecordDao.updateSurveyAssetInventoryRightRecord(surveyAssetInventoryRightRecord);
    }

    public boolean deleteSurveyAssetInventoryRightRecordById(Integer id) throws Exception {
        return surveyAssetInventoryRightRecordDao.deleteSurveyAssetInventoryRightRecordById(id);
    }

    public SurveyAssetInventoryRightRecord getSurveyAssetInventoryRightRecordById(Integer id) throws Exception {
        return surveyAssetInventoryRightRecordDao.getSurveyAssetInventoryRightRecordById(id);
    }

    public List<SurveyAssetInventoryRightRecord> surveyAssetInventoryRightRecordList(SurveyAssetInventoryRightRecord surveyAssetInventoryRightRecord) {
        return surveyAssetInventoryRightRecordDao.surveyAssetInventoryRightRecordList(surveyAssetInventoryRightRecord);
    }

    public SurveyAssetInventoryRightRecordVo getSurveyAssetInventoryRightRecordVo(SurveyAssetInventoryRightRecord oo) {
        SurveyAssetInventoryRightRecordVo vo = new SurveyAssetInventoryRightRecordVo();
        if (oo == null) {
            return null;
        }
        org.springframework.beans.BeanUtils.copyProperties(oo, vo);
        List<DeclareRecord> declareRecordList = Lists.newArrayList();
        if (StringUtils.isNotBlank(oo.getRecordIds())) {
            declareRecordList = declareRecordService.getDeclareRecordListByIds(FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(oo.getRecordIds())));
        }
        if (CollectionUtils.isNotEmpty(declareRecordList)) {
            StringBuilder stringBuilder = new StringBuilder(8);
            declareRecordList.forEach(o -> stringBuilder.append(o.getName()).append(","));
            vo.setRecordNames(StringUtils.strip(stringBuilder.toString(), ","));
        }
        return vo;
    }

}
