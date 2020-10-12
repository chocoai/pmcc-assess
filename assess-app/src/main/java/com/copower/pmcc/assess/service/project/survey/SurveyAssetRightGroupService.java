package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetRightGroupDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyJudgeObjectGroupDto;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyRightGroupDto;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zch on 2019-12-16.
 * 他项权利申报表(他权组)
 */
@Service
public class SurveyAssetRightGroupService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private SurveyAssetRightGroupDao surveyAssetRightGroupDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private SurveyAssetRightDeclareService surveyAssetRightDeclareService;
    @Autowired
    private SurveyAssetRightItemService surveyAssetRightItemService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private SurveyAssetInventoryService surveyAssetInventoryService;

    public SurveyAssetRightItemService getSurveyAssetRightItemService(){
        return surveyAssetRightItemService ;
    }

    /**
     * 根据主id更新从表
     * @param masterId
     * @param projectPlanDetails
     */
    protected void updateOnlyMasterId(Integer masterId,ProjectPlanDetails projectPlanDetails){
        SurveyAssetRightGroup groupQuery = new SurveyAssetRightGroup();
        groupQuery.setProjectId(projectPlanDetails.getProjectId());
        groupQuery.setPlanDetailsId(projectPlanDetails.getId());
        groupQuery.setCreator(commonService.thisUserAccount());
        List<SurveyAssetRightGroup> groupList = getSurveyAssetRightGroupListByExample(groupQuery) ;
        if (CollectionUtils.isEmpty(groupList)){
            return;
        }
        Iterator<SurveyAssetRightGroup> groupIterator = groupList.iterator();
        while (groupIterator.hasNext()){
            SurveyAssetRightGroup rightGroup = groupIterator.next();
            rightGroup.setMasterId(masterId);
            updateSurveyAssetRightGroup(rightGroup,false) ;
        }
    }

    /**
     * 清除数据 (没用子类的会被清除)
     * @param projectPlanDetails
     */
    public void clear(ProjectPlanDetails projectPlanDetails){
        SurveyAssetRightGroup groupQuery = new SurveyAssetRightGroup();
        groupQuery.setProjectId(projectPlanDetails.getProjectId());
        groupQuery.setPlanDetailsId(projectPlanDetails.getId());
        groupQuery.setCreator(commonService.thisUserAccount());
        List<SurveyAssetRightGroup> groupList = getSurveyAssetRightGroupListByExample(groupQuery) ;
        if (CollectionUtils.isEmpty(groupList)){
            return;
        }
        Iterator<SurveyAssetRightGroup> groupIterator = groupList.iterator();
        while (groupIterator.hasNext()){
            SurveyAssetRightGroup rightGroup = groupIterator.next();
            SurveyAssetRightItem itemQuery =  new SurveyAssetRightItem();
            itemQuery.setGroupId(rightGroup.getId());
            List<SurveyAssetRightItem> itemList = surveyAssetRightItemService.getSurveyAssetRightItemListByExample(itemQuery) ;
            SurveyAssetRightDeclare declareQuery = new SurveyAssetRightDeclare();
            declareQuery.setGroupId(rightGroup.getId());
            List<SurveyAssetRightDeclare> declareList = surveyAssetRightDeclareService.getSurveyAssetRightDeclareListByExample(declareQuery) ;
            if (CollectionUtils.isNotEmpty(declareList)){
                continue;
            }
            if (CollectionUtils.isNotEmpty(itemList)){
                continue;
            }
            //当从表数据没用那么认为是垃圾数据,则清除
            deleteSurveyAssetRightGroupById(rightGroup.getId().toString()) ;
        }
    }

    public boolean updateSurveyAssetRightGroup(SurveyAssetRightGroup oo, boolean updateNull) {
        return surveyAssetRightGroupDao.updateSurveyAssetRightGroup(oo, updateNull);
    }

    public boolean saveSurveyAssetRightGroup(SurveyAssetRightGroup oo) {
        if (oo == null) {
            return false;
        }
        if (StringUtils.isEmpty(oo.getCreator())) {
            oo.setCreator(commonService.thisUserAccount());
        }
        return surveyAssetRightGroupDao.saveSurveyAssetRightGroup(oo);
    }

    public void saveAndUpdateSurveyAssetRightGroup(SurveyAssetRightGroup oo,boolean updateNull) {
        if (oo == null) {
            return;
        }
        if (oo.getId() != null && oo.getId() != 0) {
            surveyAssetRightGroupDao.updateSurveyAssetRightGroup(oo, updateNull);
        } else {
            saveSurveyAssetRightGroup(oo);
        }
    }

    private void removeFileByTableId(Integer tableId){
        if (tableId == null || tableId == 0){
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(tableId);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetRightGroup.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto) ;
        if (CollectionUtils.isEmpty(sysAttachmentDtoList)){
            return;
        }
        sysAttachmentDtoList.forEach(sysAttachmentDto1 -> baseAttachmentService.deleteAttachment(sysAttachmentDto1.getId()));
        removeBranch(tableId) ;
    }

    /**
     * 删除此id下的所有子数据
     * @param integer
     */
    private void removeBranch(Integer integer){
        if (integer == null || integer == 0){
            return;
        }
        SurveyAssetRightItem itemQuery =  new SurveyAssetRightItem();
        itemQuery.setGroupId(integer);
        List<SurveyAssetRightItem> itemList = surveyAssetRightItemService.getSurveyAssetRightItemListByExample(itemQuery) ;
        if (CollectionUtils.isNotEmpty(itemList)){
            Iterator<SurveyAssetRightItem> it = itemList.iterator();
            while (it.hasNext()){
                surveyAssetRightItemService.deleteSurveyAssetRightItemById(it.next().getId().toString());
            }
        }
        SurveyAssetRightDeclare declareQuery = new SurveyAssetRightDeclare();
        declareQuery.setGroupId(integer);
        List<SurveyAssetRightDeclare> declareList = surveyAssetRightDeclareService.getSurveyAssetRightDeclareListByExample(declareQuery) ;
        if (CollectionUtils.isNotEmpty(declareList)){
            Iterator<SurveyAssetRightDeclare> it = declareList.iterator();
            while (it.hasNext()){
                surveyAssetRightDeclareService.deleteSurveyAssetRightDeclareById(it.next().getId().toString());
            }
        }
    }

    public void deleteSurveyAssetRightGroupById(String id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isNotEmpty(ids)) {
            if (ids.size() == 1) {
                removeFileByTableId(ids.get(0)) ;
                surveyAssetRightGroupDao.deleteSurveyAssetRightGroupById(ids.get(0));
            } else {
                ids.forEach(integer -> removeFileByTableId(integer));
                surveyAssetRightGroupDao.deleteSurveyAssetRightGroupByIds(ids);
            }
        }
    }

    public BootstrapTableVo getBootstrapTableVo(SurveyAssetRightGroup oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SurveyAssetRightGroup> declareApplyExtensionList = getSurveyAssetRightGroupListByExample(oo);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isNotEmpty(declareApplyExtensionList) ? declareApplyExtensionList : new ArrayList(0));
        return vo;
    }

    /**
     * 利用申报id获取他项权力组信息
     * @param declareRecordId
     * @param projectId
     * @return
     */
    public List<SurveyAssetRightGroup> getSurveyAssetRightGroupByDeclareRecord(Integer declareRecordId, Integer projectId){
        List<SurveyAssetRightGroup> groupList = new ArrayList<>(1) ;
        if (declareRecordId == null || projectId == null){
            return groupList;
        }
        SurveyAssetRightGroup query = new SurveyAssetRightGroup();
        query.setProjectId(projectId);
        List<SurveyAssetRightGroup> rightGroupList = getSurveyAssetRightGroupListByExample(query) ;
        if (CollectionUtils.isNotEmpty(rightGroupList)){
            for (SurveyAssetRightGroup surveyAssetRightGroup:rightGroupList){
                List<SurveyAssetRightDeclare> rightDeclareList = getSurveyAssetRightDeclareListByGroupId(surveyAssetRightGroup.getId()) ;
                if (CollectionUtils.isEmpty(rightDeclareList)){
                    continue;
                }
                List<Integer> integerList = rightDeclareList.stream().map(surveyAssetRightDeclare -> surveyAssetRightDeclare.getDeclareId()).collect(Collectors.toList());
                if (CollectionUtils.isEmpty(integerList)){
                    continue;
                }
                if (!integerList.contains(declareRecordId)){
                    continue;
                }
                groupList.add(surveyAssetRightGroup) ;
            }
        }
        return groupList;
    }

    public List<SurveyAssetRightGroup> getSurveyAssetRightGroupByIds(List<Integer> ids) {
        return surveyAssetRightGroupDao.getSurveyAssetRightGroupByIds(ids);
    }

    public SurveyAssetRightGroup getSurveyAssetRightGroupById(Integer id) {
        return surveyAssetRightGroupDao.getSurveyAssetRightGroupById(id);
    }

    /**
     * 根据项目id获取分组
     * @param projectId
     * @return
     */
    public List<SurveyAssetRightGroup> getListByProjectId(Integer projectId){
        SurveyAssetRightGroup query = new SurveyAssetRightGroup();
        query.setProjectId(projectId);
        return getSurveyAssetRightGroupListByExample(query) ;
    }

    public List<SurveyAssetRightGroup> getSurveyAssetRightGroupListByExample(SurveyAssetRightGroup oo) {
        return surveyAssetRightGroupDao.getSurveyAssetRightGroupListByExample(oo);
    }

    public List<SurveyAssetRightGroup> getSurveyAssetRightGroupListByMasterId(Integer masterId) {
        SurveyAssetRightGroup groupQuery = new SurveyAssetRightGroup();
        groupQuery.setMasterId(masterId);
        return surveyAssetRightGroupDao.getSurveyAssetRightGroupListByExample(groupQuery);
    }

    public List<SurveyAssetRightDeclare> getSurveyAssetRightDeclareListByGroupId(Integer groupId){
        return surveyAssetRightDeclareService.getSurveyAssetRightDeclareListByGroupId(groupId) ;
    }

    public List<SurveyAssetRightItem> getSurveyAssetRightItemListByGroupId(Integer groupId){
        return surveyAssetRightItemService.getSurveyAssetRightItemListByGroupId(groupId) ;
    }

    public List<SurveyJudgeObjectGroupDto> groupJudgeObject(Integer projectId, List<SchemeJudgeObject> judgeObjects) {
        List<SurveyAssetRightGroup> rightRecords = getListByProjectId(projectId);
        List<SurveyJudgeObjectGroupDto> list = Lists.newArrayList();
        //他权其它
        BaseDataDic projectClassify = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.HOUSE_INVENTORY_RIGHT_CATEGORY_OTHER);
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
                surveyJudgeObjectGroupDto.setResult(org.apache.commons.lang3.StringUtils.isBlank(s) ? "强" : "一般");
            }
        } else {
            for (SurveyAssetRightGroup rightRecord : rightRecords) {
                List<SurveyAssetRightDeclare> rightDeclareList = getSurveyAssetRightDeclareListByGroupId(rightRecord.getId()) ;
                List<Integer> declareIds = LangUtils.transform(rightDeclareList,oo -> oo.getDeclareId()) ;
                for (SchemeJudgeObject judgeObject : judgeObjects) {
                    if (declareIds.contains(judgeObject.getDeclareRecordId())) {
                        SurveyJudgeObjectGroupDto surveyJudgeObjectGroupDto = new SurveyJudgeObjectGroupDto();
                        surveyJudgeObjectGroupDto.setJudgeObjectNumber(judgeObject.getNumber());
                        surveyJudgeObjectGroupDto.setJudgeObjectId(judgeObject.getId());
                        surveyJudgeObjectGroupDto.setDeclareRecordId(judgeObject.getDeclareRecordId());
                        surveyJudgeObjectGroupDto.setTransferLimit(transferLimitMap.get(judgeObject.getDeclareRecordId()));//取资产清查中的转让限制
                        List<SurveyAssetRightItem> inventoryRights = getSurveyAssetRightItemListByGroupId(rightRecord.getId()) ;
                        surveyJudgeObjectGroupDto.setRightList(inventoryRights);
                        Boolean rightOtherEmpty = true;
                        if (CollectionUtils.isNotEmpty(inventoryRights) && LangUtils.transform(inventoryRights, o -> o.getCategory()).contains(projectClassify.getId())) {
                            rightOtherEmpty = false;
                        }
                        if (rightOtherEmpty == Boolean.TRUE && org.apache.commons.lang3.StringUtils.isNotBlank(surveyJudgeObjectGroupDto.getTransferLimit())) {
                            surveyJudgeObjectGroupDto.setResult("一般");
                        } else if (rightOtherEmpty == Boolean.FALSE && org.apache.commons.lang3.StringUtils.isNotBlank(surveyJudgeObjectGroupDto.getTransferLimit())) {
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
        List<SurveyAssetRightGroup> rightRecords = getListByProjectId(projectId);
        if (CollectionUtils.isEmpty(rightRecords)) {
            return null;
        }
        List<SurveyRightGroupDto> groupDtoList = Lists.newArrayList();
        List<Integer> declareIds = LangUtils.transform(judgeObjects, o -> o.getDeclareRecordId());
        //1.先判断该他权分组中权证是否在该区域下
        //2.如果属于当前区域则循环他权内容
        for (SurveyAssetRightGroup rightGroup : rightRecords) {
            List<SurveyAssetRightDeclare> rightDeclareList = getSurveyAssetRightDeclareListByGroupId(rightGroup.getId()) ;
            List<Integer> integerList = rightDeclareList.stream().map(surveyAssetRightDeclare -> surveyAssetRightDeclare.getDeclareId()).collect(Collectors.toList());
            Collection intersection = CollectionUtils.intersection(integerList, declareIds);//集合交集
            if (CollectionUtils.isNotEmpty(intersection)) {
                List<SurveyAssetRightItem> itemListByGroupId = getSurveyAssetRightItemListByGroupId(rightGroup.getId()) ;
                if (CollectionUtils.isNotEmpty(itemListByGroupId)) {
                    for (SurveyAssetRightItem assetRightItem : itemListByGroupId) {
                        SurveyRightGroupDto surveyRightGroupDto = new SurveyRightGroupDto();
                        surveyRightGroupDto.setKey(String.format("%s%s", baseDataDicService.getNameById(assetRightItem.getCategory()), assetRightItem.getRemark()));
                        surveyRightGroupDto.setGroupId(rightGroup.getId());
                        surveyRightGroupDto.setCategory(assetRightItem.getCategory());
                        surveyRightGroupDto.setCategoryName(baseProjectClassifyService.getNameById(assetRightItem.getCategory()));
                        surveyRightGroupDto.setRemark(assetRightItem.getRemark());
                        surveyRightGroupDto.setDeclareRecordIds(Sets.newHashSet(integerList));
                        this.pushSurveyRightGroupDto(groupDtoList, surveyRightGroupDto);
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
                if (org.apache.commons.lang3.StringUtils.equals(rightGroupDto.getKey(), surveyRightGroupDto.getKey())) {
                    rightGroupDto.getDeclareRecordIds().addAll(surveyRightGroupDto.getDeclareRecordIds());
                }
            }
        } else {
            list.add(surveyRightGroupDto);
        }
    }

    /**
     * 获取区域下拥有他权的权证数据
     *
     * @param areaId
     * @return
     */
    public List<Integer> getDeclareRecordsByAreaId(Integer projectId, Integer areaId) {
        List<SurveyAssetRightGroup> surveyAssetRightGroupList = getListByProjectId(projectId);
        if (CollectionUtils.isEmpty(surveyAssetRightGroupList)){
            return null;
        }
        List<DeclareRecord> areaDeclareRecords = declareRecordService.getDeclareRecordByAreaId(areaId);
        List<Integer> areaDeclareRecordIds = LangUtils.transform(areaDeclareRecords, o -> o.getId());
        List<Integer> resultList = Lists.newArrayList();
        for (SurveyAssetRightGroup rightGroup : surveyAssetRightGroupList) {
            List<SurveyAssetRightDeclare> rightDeclareList = getSurveyAssetRightDeclareListByGroupId(rightGroup.getId()) ;
            if (CollectionUtils.isEmpty(rightDeclareList)){
                continue;
            }
            List<Integer> integerList = LangUtils.transform(rightDeclareList,oo -> oo.getDeclareId()) ;
            if (CollectionUtils.isEmpty(integerList)){
                continue;
            }
            for (Integer integer : integerList) {
                if (areaDeclareRecordIds.contains(integer))
                    resultList.add(integer);
            }
        }
        return resultList;
    }

    public List<SurveyAssetRightDeclare> getRightDeclareListByDeclareIds(List<Integer> declareIds){
        return surveyAssetRightDeclareService.getRightDeclareListByDeclareIds(declareIds) ;
    }
    
}
