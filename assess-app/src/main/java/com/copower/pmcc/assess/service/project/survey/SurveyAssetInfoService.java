package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInfoDao;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInfoItemDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.ProjectPhaseInterface;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.SysProjectEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Objects;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zch on 2020-3-20.
 */
@Service
public class SurveyAssetInfoService implements ProjectPhaseInterface {
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private SurveyAssetInfoDao surveyAssetInfoDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private SurveyAssetInfoItemService surveyAssetInfoItemService;
    @Autowired
    private SurveyAssetInfoGroupService surveyAssetInfoGroupService;
    @Autowired
    private SurveyAssetInventoryContentService surveyAssetInventoryContentService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private SurveyAssetInventoryService surveyAssetInventoryService;
    @Autowired
    private SurveyAssetInfoItemDao surveyAssetInfoItemDao;

    /**
     * 提交要做的事
     *
     * @param surveyAssetInfo
     */
    public void submit(SurveyAssetInfo surveyAssetInfo) {

        List<DeclareRecord> declareRecordList = declareRecordService.getDeclareRecordByProjectId(surveyAssetInfo.getProjectId());
        //处理状态问题
        SurveyAssetInfoItem queryItem = new SurveyAssetInfoItem();
        queryItem.setCreator(commonService.thisUserAccount());
        queryItem.setAssetInfoId(surveyAssetInfo.getId());
        queryItem.setStatus(SysProjectEnum.FINISH.getValue());
        List<SurveyAssetInfoItem> surveyAssetInfoItems = surveyAssetInfoItemService.getSurveyAssetInfoItemListByQuery(queryItem);
        if (CollectionUtils.isNotEmpty(surveyAssetInfoItems)) {
            if (CollectionUtils.isNotEmpty(surveyAssetInfoItems)) {
                for (SurveyAssetInfoItem assetInfoItem : surveyAssetInfoItems) {
                    DeclareRecord declareRecord = declareRecordList.stream().filter(record -> Objects.equal(record.getId(), assetInfoItem.getDeclareId())).findFirst().get();
                    declareRecord.setInventoryStatus(SysProjectEnum.FINISH.getValue());
                    declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
                }
            }
        }
        //更新
        surveyAssetInfo.setStatus(SysProjectEnum.FINISH.getValue());

        //更新本次 工作事项状态
        updateSurveyAssetInfo(surveyAssetInfo, false);


    }

    /**
     * 统计
     *
     * @param surveyAssetInfo
     */
    public void statistics(SurveyAssetInfo surveyAssetInfo) {
        List<DeclareRecord> declareRecordList = declareRecordService.getDeclareRecordByProjectId(surveyAssetInfo.getProjectId());
        surveyAssetInfo.setCount(declareRecordList.size());

        List<DeclareRecord> recordList = declareRecordList.stream().filter(declareRecord -> Objects.equal(declareRecord.getInventoryStatus(), SysProjectEnum.FINISH.getValue())).collect(Collectors.toList());


        surveyAssetInfo.setFinishCount(recordList.size());

        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(surveyAssetInfo.getPlanDetailId());

        SurveyAssetInfoItem queryItem = new SurveyAssetInfoItem();
        queryItem.setCreator(projectPlanDetails.getExecuteUserAccount());
        queryItem.setAssetInfoId(surveyAssetInfo.getId());
        queryItem.setStatus(SysProjectEnum.FINISH.getValue());

        List<SurveyAssetInfoItem> surveyAssetInfoItems = surveyAssetInfoItemService.getSurveyAssetInfoItemListByQuery(queryItem);
        surveyAssetInfo.setThisCount(surveyAssetInfoItems.size());
    }


    public SurveyAssetInfo getSurveyAssetInfoByPlanDetailsId(ProjectPlanDetails projectPlanDetails) {
        SurveyAssetInfo query = new SurveyAssetInfo();
        query.setPlanDetailId(projectPlanDetails.getId());
        query.setProjectId(projectPlanDetails.getProjectId());
        List<SurveyAssetInfo> xlxReportIndividuals = getSurveyAssetInfoListByQuery(query);
        if (CollectionUtils.isNotEmpty(xlxReportIndividuals)) {
            return xlxReportIndividuals.get(0);
        }
        query.setStatus(SysProjectEnum.RUNING.getValue());
        saveSurveyAssetInfo(query);
        return query;
    }

    public SurveyAssetInfo getSurveyAssetInfoByPlanDetailsId(Integer projectPlanDetailsId) {
        SurveyAssetInfo query = new SurveyAssetInfo();
        query.setPlanDetailId(projectPlanDetailsId);
        List<SurveyAssetInfo> list = getSurveyAssetInfoListByQuery(query);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return query;
    }

    public boolean updateSurveyAssetInfo(SurveyAssetInfo oo, boolean updateNull) {
        return surveyAssetInfoDao.updateSurveyAssetInfo(oo, updateNull);
    }

    public boolean saveSurveyAssetInfo(SurveyAssetInfo oo) {
        if (oo == null) {
            return false;
        }
        if (StringUtils.isEmpty(oo.getCreator())) {
            oo.setCreator(commonService.thisUserAccount());
        }
        boolean b = surveyAssetInfoDao.saveSurveyAssetInfo(oo);
        baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetInfo.class), oo.getId());
        return b;

    }

    public void saveAndUpdateSurveyAssetInfo(SurveyAssetInfo oo, boolean updateNull) {
        if (oo == null) {
            return;
        }
        if (oo.getId() != null && oo.getId() != 0) {
            updateSurveyAssetInfo(oo, updateNull);
        } else {
            saveSurveyAssetInfo(oo);
        }
    }

    private void removeFileByTableId(Integer tableId) {
        if (tableId == null) {
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(tableId);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetInfo.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if (CollectionUtils.isEmpty(sysAttachmentDtoList)) {
            return;
        }
        sysAttachmentDtoList.forEach(sysAttachmentDto1 -> baseAttachmentService.deleteAttachment(sysAttachmentDto1.getId()));
    }

    public void deleteSurveyAssetInfoById(String id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isNotEmpty(ids)) {
            if (ids.size() == 1) {
                removeFileByTableId(ids.get(0));
                surveyAssetInfoDao.deleteSurveyAssetInfoById(ids.get(0));
            } else {
                ids.forEach(integer -> removeFileByTableId(integer));
                surveyAssetInfoDao.deleteSurveyAssetInfoByIds(ids);
            }
        }
    }

    public BootstrapTableVo getBootstrapTableVo(SurveyAssetInfo oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SurveyAssetInfo> surveyAssetInfos = getSurveyAssetInfoListByQuery(oo);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isNotEmpty(surveyAssetInfos) ? surveyAssetInfos : new ArrayList(0));
        return vo;
    }

    public List<SurveyAssetInfo> getSurveyAssetInfoByIds(List<Integer> ids) {
        return surveyAssetInfoDao.getSurveyAssetInfoByIds(ids);
    }

    public SurveyAssetInfo getSurveyAssetInfoById(Integer id) {
        return surveyAssetInfoDao.getSurveyAssetInfoById(id);
    }


    public List<SurveyAssetInfo> getSurveyAssetInfoListByQuery(SurveyAssetInfo oo) {
        return surveyAssetInfoDao.getSurveyAssetInfoListByExample(oo);
    }

    public void writeBackDeclareRecord(String processInsId) {
        SurveyAssetInfo query = new SurveyAssetInfo();
        query.setProcessInsId(processInsId);
        List<SurveyAssetInfo> surveyAssetInfos = surveyAssetInfoDao.getSurveyAssetInfoListByExample(query);
        if (CollectionUtils.isNotEmpty(surveyAssetInfos)) {
            for (SurveyAssetInfo surveyAssetInfo : surveyAssetInfos) {
                writeBackDeclareRecord(surveyAssetInfo);
            }
        }
    }

    /**
     * 反写申报记录数据的证载用途与实际用途
     *
     * @param surveyAssetInfo
     */
    @Transactional(rollbackFor = Exception.class)
    public void writeBackDeclareRecord(SurveyAssetInfo surveyAssetInfo) {
        if (surveyAssetInfo == null) {
            return;
        }
        Map<Integer, DeclareRecord> recordMap = new HashMap<>();
        List<DeclareRecord> declareRecordList = declareRecordService.getDeclareRecordByProjectId(surveyAssetInfo.getProjectId());
        SurveyAssetInfoItem queryItem = new SurveyAssetInfoItem();
        queryItem.setAssetInfoId(surveyAssetInfo.getId());
        queryItem.setStatus(SysProjectEnum.FINISH.getValue());

        SurveyAssetInfoGroup queryGroup = new SurveyAssetInfoGroup();
        queryGroup.setAssetInfoId(surveyAssetInfo.getId());
        queryGroup.setStatus(SysProjectEnum.FINISH.getValue());

        List<SurveyAssetInfoItem> surveyAssetInfoItems = new ArrayList<>();

        List<SurveyAssetInfoGroup> assetInfoGroups = surveyAssetInfoGroupService.getSurveyAssetInfoGroupListByQuery(queryGroup);
        if (CollectionUtils.isNotEmpty(assetInfoGroups)) {
            Iterator<SurveyAssetInfoGroup> iterator = assetInfoGroups.iterator();
            while (iterator.hasNext()) {
                SurveyAssetInfoGroup infoGroup = iterator.next();
                if (infoGroup.getInventoryId() == null) {
                    continue;
                }
                List<Integer> integers = surveyAssetInfoItemService.getSurveyAssetInfoItemIdsByGroupId(infoGroup.getId());
                if (CollectionUtils.isEmpty(integers)) {
                    continue;
                }
                List<SurveyAssetInfoItem> assetInfoItems = surveyAssetInfoItemService.getSurveyAssetInfoItemByIds(integers);
                if (CollectionUtils.isEmpty(assetInfoItems)) {
                    continue;
                }
                for (SurveyAssetInfoItem infoItem : assetInfoItems) {
                    infoItem.setInventoryId(infoGroup.getInventoryId());
                }
                surveyAssetInfoItems.addAll(assetInfoItems);
            }
        }
        List<SurveyAssetInfoItem> surveyAssetInfoItems2 = surveyAssetInfoItemService.getSurveyAssetInfoItemListByQuery(queryItem);
        if (CollectionUtils.isNotEmpty(surveyAssetInfoItems2)) {
            surveyAssetInfoItems.addAll(surveyAssetInfoItems2);
        }

        if (CollectionUtils.isNotEmpty(surveyAssetInfoItems)) {
            if (CollectionUtils.isNotEmpty(surveyAssetInfoItems)) {
                for (SurveyAssetInfoItem assetInfoItem : surveyAssetInfoItems) {
                    DeclareRecord declareRecord = declareRecordList.stream().filter(record -> Objects.equal(record.getId(), assetInfoItem.getDeclareId())).findFirst().get();
                    if (declareRecord == null) {
                        continue;
                    }
                    if (assetInfoItem.getInventoryId() == null) {
                        continue;
                    }
                    recordMap.put(assetInfoItem.getInventoryId(), declareRecord);
                }
            }
        }
        if (recordMap.isEmpty()) {
            return;
        }
        Iterator<Map.Entry<Integer, DeclareRecord>> entryIterator = recordMap.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<Integer, DeclareRecord> recordEntry = entryIterator.next();
            List<SurveyAssetInventoryContent> inventoryContentList = surveyAssetInventoryContentService.getSurveyAssetInventoryContentListByMasterId(recordEntry.getKey());
            if (CollectionUtils.isEmpty(inventoryContentList)) {
                continue;
            }
            writeBackDeclareRecord(recordEntry.getValue(), inventoryContentList);
        }
    }

    private void writeBackDeclareRecord(DeclareRecord declareRecord, List<SurveyAssetInventoryContent> contentList) {
        BaseDataDic areaDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_AREA);
        for (SurveyAssetInventoryContent content : contentList) {
            //反写实际面积
            if (areaDic != null) {
                if (content.getInventoryContent().equals(areaDic.getId()) && StringUtils.isNotBlank(content.getActual()) && NumberUtils.isNumber(content.getActual())) {
                    declareRecord.setPracticalArea(new BigDecimal(content.getActual()));
                }
            }
        }
        declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
    }


    public List<SurveyAssetInventory> getSurveyAssetInventoryListByDeclareRecordId(Integer declareRecordId) {
        List<SurveyAssetInventory> inventoryList = new ArrayList<>();
        SurveyAssetInfoItem queryItem = new SurveyAssetInfoItem();
        queryItem.setStatus(SysProjectEnum.FINISH.getValue());
        queryItem.setDeclareId(declareRecordId);
        List<SurveyAssetInfoItem> surveyAssetInfoItems = surveyAssetInfoItemService.getSurveyAssetInfoItemListByQuery(queryItem);
        if (CollectionUtils.isNotEmpty(surveyAssetInfoItems)) {
            Iterator<SurveyAssetInfoItem> iterator = surveyAssetInfoItems.iterator();
            while (iterator.hasNext()) {
                SurveyAssetInfoItem assetInfoItem = iterator.next();
                if (assetInfoItem.getInventoryId() != null && assetInfoItem.getInventoryId() != 0) {
                    SurveyAssetInventory assetInventory = surveyAssetInventoryService.getSurveyAssetInventoryById(assetInfoItem.getInventoryId());
                    if (assetInventory != null){
                        inventoryList.add(assetInventory);
                    }
                }
                if (assetInfoItem.getGroupId() != null && assetInfoItem.getGroupId() != 0){
                    SurveyAssetInfoGroup assetInfoGroup = surveyAssetInfoGroupService.getSurveyAssetInfoGroupById(assetInfoItem.getGroupId());
                    if (assetInfoGroup.getInventoryId() != null && assetInfoGroup.getInventoryId() != 0){
                        SurveyAssetInventory assetInventory = surveyAssetInventoryService.getSurveyAssetInventoryById(assetInfoGroup.getInventoryId());
                        if (assetInventory != null){
                            inventoryList.add(assetInventory) ;
                        }
                    }
                }
            }
        }
        return inventoryList;
    }

    public SurveyAssetInventory getSurveyAssetInventoryByDeclareRecordId(Integer declareRecordId){
        List<SurveyAssetInventory> surveyAssetInventories = getSurveyAssetInventoryListByDeclareRecordId(declareRecordId);
        if (CollectionUtils.isNotEmpty(surveyAssetInventories)){
            return surveyAssetInventories.get( 0) ;
        }
        return null;
    }

    @Override
    public boolean beforeDeleteVerify(Integer projectPlanDetailsId) {
        return true;
    }

    @Override
    public void afterDeleteExecute(Integer projectPlanDetailsId) {
        SurveyAssetInfo surveyAssetInfo = getSurveyAssetInfoByPlanDetailsId(projectPlanDetailsId);

        List<SurveyAssetInfoItem> assetInfoItemList = surveyAssetInfoItemService.getItemsByAssetInfoId(surveyAssetInfo.getId());
        if(CollectionUtils.isNotEmpty(assetInfoItemList)){
            for(SurveyAssetInfoItem item:assetInfoItemList){
                //权证状态修改
                DeclareRecord recordById = declareRecordService.getDeclareRecordById(item.getDeclareId());
                if(recordById!=null){
                    recordById.setInventoryStatus(null);
                    declareRecordService.updateDeclareRecord(recordById,true);
                }
                //删除认领的数据
                surveyAssetInfoItemDao.deleteSurveyAssetInfoItemById(item.getId());
            }
        }

    }
}
