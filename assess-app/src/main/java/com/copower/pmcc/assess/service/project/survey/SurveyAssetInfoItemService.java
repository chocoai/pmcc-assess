package com.copower.pmcc.assess.service.project.survey;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInfoItemDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchDetailService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.SysProjectEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zch on 2020-3-20.
 */
@Service
public class SurveyAssetInfoItemService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private SurveyAssetInfoItemDao surveyAssetInfoItemDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private SurveyAssetInfoGroupService surveyAssetInfoGroupService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private SurveyAssetInventoryService surveyAssetInventoryService;
    @Autowired
    private SurveyAssetInfoService surveyAssetInfoService;
    @Autowired
    private SurveyAssetInventoryContentService surveyAssetInventoryContentService;


    public boolean updateSurveyAssetInfoItem(SurveyAssetInfoItem oo, boolean updateNull) {
        return surveyAssetInfoItemDao.updateSurveyAssetInfoItem(oo, updateNull);
    }

    @Transactional(rollbackFor = {Exception.class})
    public boolean addSurveyAssetInfoItem(SurveyAssetInfoItem oo) throws Exception {
        if (oo == null) {
            return false;
        }
        if (StringUtils.isEmpty(oo.getCreator())) {
            oo.setCreator(commonService.thisUserAccount());
        }
        List<SurveyAssetInfoItem> infoItems = getSurveyAssetInfoItemListByQuery(oo);
        if (CollectionUtils.isNotEmpty(infoItems)) {
            throw new Exception(String.join("", oo.getName() + ":已经存在"));
        }
        boolean b = surveyAssetInfoItemDao.addSurveyAssetInfoItem(oo);
        baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetInfoItem.class), oo.getId());
        return b;

    }

    public void saveAndUpdateSurveyAssetInfoItem(SurveyAssetInfoItem oo, boolean updateNull) throws Exception {
        if (oo == null) {
            return;
        }
        if (oo.getId() != null && oo.getId() != 0) {
            updateSurveyAssetInfoItem(oo, updateNull);
        } else {
            addSurveyAssetInfoItem(oo);
        }
    }

    public Integer addSurveyAssetInfoItemRecordData(String formData) {
        List<SurveyAssetInfoItem> surveyAssetInfoItems = JSONObject.parseArray(formData, SurveyAssetInfoItem.class);
        int i = 0;
        if (CollectionUtils.isNotEmpty(surveyAssetInfoItems)) {
            Iterator<SurveyAssetInfoItem> iterator = surveyAssetInfoItems.iterator();
            while (iterator.hasNext()) {
                SurveyAssetInfoItem assetInfoItem = iterator.next();
                try {
                    DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(assetInfoItem.getDeclareId());
                    if (Objects.equal(declareRecord.getInventoryStatus(), SysProjectEnum.FINISH.getValue())) {
                        continue;
                    }
                    if (Objects.equal(declareRecord.getInventoryStatus(), SysProjectEnum.RUNING.getValue())) {
                        continue;
                    }
                    addSurveyAssetInfoItem(assetInfoItem);
                    declareRecord.setInventoryStatus(SysProjectEnum.RUNING.getValue());
                    declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
                    i++;
                } catch (Exception e) {
                    baseService.writeExceptionInfo(e);
                }
            }
        }
        return i;
    }

    private void removeFileByTableId(Integer tableId) throws Exception {
        if (tableId == null) {
            return;
        }
        SurveyAssetInfoItem infoItem = getSurveyAssetInfoItemById(tableId);

        if (!Objects.equal(commonService.thisUserAccount(), infoItem.getCreator())) {
            throw new Exception("不属于当前操作人的数据");
        }

        DeclareRecord recordById = declareRecordService.getDeclareRecordById(infoItem.getDeclareId());


        if (Objects.equal(recordById.getInventoryStatus(), SysProjectEnum.FINISH.getValue())) {
            throw new Exception("已经被清查了,不可以在删除");
        }

        recordById.setInventoryStatus(null);
        declareRecordService.updateDeclareRecord(recordById, true);

        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(tableId);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetInfoItem.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if (CollectionUtils.isEmpty(sysAttachmentDtoList)) {
            return;
        }
        sysAttachmentDtoList.forEach(sysAttachmentDto1 -> baseAttachmentService.deleteAttachment(sysAttachmentDto1.getId()));
    }

    public void deleteSurveyAssetInfoItemById(String id) throws Exception {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isNotEmpty(ids)) {
            if (ids.size() == 1) {
                removeFileByTableId(ids.get(0));
                surveyAssetInfoItemDao.deleteSurveyAssetInfoItemById(ids.get(0));
            } else {
                for (Integer integer : ids) {
                    removeFileByTableId(integer);
                    surveyAssetInfoItemDao.deleteSurveyAssetInfoItemById(integer);
                }
            }
        }
    }

    public BootstrapTableVo getBootstrapTableVo(SurveyAssetInfoItem oo, Integer eatate, Integer building, Integer unit) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        List<BasicApplyBatchDetail> houseBatchDetailList = null;
        if (unit != null) {
            houseBatchDetailList = basicApplyBatchDetailService.getHouseBatchDetailList(unit);
        } else if (building != null) {
            houseBatchDetailList = basicApplyBatchDetailService.getHouseBatchDetailList(building);
        } else if (eatate != null) {
            houseBatchDetailList = basicApplyBatchDetailService.getHouseBatchDetailList(eatate);
        }
//        oo.setCreator(commonService.thisUserAccount());
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SurveyAssetInfoItem> surveyAssetInfoItems = getSurveyAssetInfoItemLikeList(oo, LangUtils.transform(houseBatchDetailList, o -> o.getDeclareRecordId()));
        if (CollectionUtils.isNotEmpty(surveyAssetInfoItems)) {
            for (SurveyAssetInfoItem assetInfoItem : surveyAssetInfoItems) {
                if (assetInfoItem.getGroupId() != null && assetInfoItem.getGroupId() != 0) {
                    SurveyAssetInfoGroup infoGroup = surveyAssetInfoGroupService.getSurveyAssetInfoGroupById(assetInfoItem.getGroupId());
                    assetInfoItem.setGroupName(infoGroup.getGroupName());
                }
            }
        }
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isNotEmpty(surveyAssetInfoItems) ? surveyAssetInfoItems : new ArrayList(0));
        return vo;
    }

    public List<SurveyAssetInfoItem> getSurveyAssetInfoItemByIds(List<Integer> ids) {
        return surveyAssetInfoItemDao.getSurveyAssetInfoItemByIds(ids);
    }

    public SurveyAssetInfoItem getSurveyAssetInfoItemById(Integer id) {
        return surveyAssetInfoItemDao.getSurveyAssetInfoItemById(id);
    }

    public SurveyAssetInfoItem getSurveyAssetInfoItemByDeclareId(Integer declareId) {
        SurveyAssetInfoItem item = new SurveyAssetInfoItem();
        item.setDeclareId(declareId);
        List<SurveyAssetInfoItem> assetInfoItems = surveyAssetInfoItemDao.getSurveyAssetInfoItemListByExample(item);
        if (CollectionUtils.isEmpty(assetInfoItems)) return null;
        return assetInfoItems.get(0);
    }


    public List<SurveyAssetInfoItem> getSurveyAssetInfoItemListByQuery(SurveyAssetInfoItem oo) {
        return surveyAssetInfoItemDao.getSurveyAssetInfoItemListByExample(oo);
    }

    public List<SurveyAssetInfoItem> getSurveyAssetInfoItemLikeList(SurveyAssetInfoItem oo, List<Integer> declareIds) {
        return surveyAssetInfoItemDao.getSurveyAssetInfoItemLikeList(oo, declareIds);
    }


    public List<Integer> getSurveyAssetInfoItemIdsByGroupId(Integer groupId) {
        SurveyAssetInfoItem query = new SurveyAssetInfoItem();
        query.setGroupId(groupId);
        List<Integer> integerList = new ArrayList<>();
        List<SurveyAssetInfoItem> infoItems = getSurveyAssetInfoItemListByQuery(query);
        if (CollectionUtils.isNotEmpty(infoItems)) {
            integerList.addAll(infoItems.stream().map(oo -> oo.getId()).collect(Collectors.toSet()));
        }
        return integerList;
    }

    public List<SurveyAssetInfoItem> getItemsByAssetInfoId(Integer assetInfoId) {
        SurveyAssetInfoItem query = new SurveyAssetInfoItem();
        query.setAssetInfoId(assetInfoId);
        List<SurveyAssetInfoItem> infoItems = getSurveyAssetInfoItemListByQuery(query);
        return infoItems;
    }

    /**
     * 批量处理损坏调查
     *
     * @param assetInfoItemIds
     * @param inventory
     */
    public void damageSurveyBatch(String assetInfoItemIds, SurveyAssetInventory inventory) {
        if (StringUtils.isBlank(assetInfoItemIds) || inventory == null) return;
        List<Integer> list = FormatUtils.transformString2Integer(assetInfoItemIds);
        for (Integer integer : list) {
            try {
                SurveyAssetInfoItem assetInfoItem = getSurveyAssetInfoItemById(integer);
                if (assetInfoItem == null) continue;
                SurveyAssetInventory targetInventory = null;
                if (assetInfoItem.getInventoryId() == null) {
                    targetInventory = new SurveyAssetInventory();
                    SurveyAssetInfo surveyAssetInfo = surveyAssetInfoService.getSurveyAssetInfoById(assetInfoItem.getAssetInfoId());
                    targetInventory.setProjectId(surveyAssetInfo.getProjectId());
                    targetInventory.setPlanDetailId(surveyAssetInfo.getPlanDetailId());
                    targetInventory.setDeclareRecordId(assetInfoItem.getDeclareId());
                    targetInventory.setCreator(commonService.thisUserAccount());
                    surveyAssetInventoryService.addSurveyAssetInventory(targetInventory);
                } else {
                    targetInventory = surveyAssetInventoryService.getSurveyAssetInventoryById(assetInfoItem.getInventoryId());
                }
                targetInventory.setRimIsNormal(inventory.getRimIsNormal());
                targetInventory.setZoneDamage(inventory.getZoneDamage());
                targetInventory.setEntityIsDamage(inventory.getEntityIsDamage());
                targetInventory.setEntityDamage(inventory.getEntityDamage());
                surveyAssetInventoryService.updateSurveyAssetInventory(targetInventory);

                assetInfoItem.setBisFinishDamage(true);
                assetInfoItem.setInventoryId(targetInventory.getId());
                updateSurveyAssetInfoItem(assetInfoItem, false);
            } catch (Exception e) {
                baseService.writeExceptionInfo(e);
            }
        }
    }

    /**
     * 批量处理一致性清查
     *
     * @param assetInfoItemIds
     */
    public void checkUniformityBatch(String assetInfoItemIds) {
        //1.先确定该项是否已做过清查，如果没有做过清查，先初始化清查必要数据
        //2.找到需清查的数据项依次判断是否为一致
        if (StringUtils.isBlank(assetInfoItemIds)) return;
        List<Integer> list = FormatUtils.transformString2Integer(assetInfoItemIds);
        for (Integer integer : list) {
            try {
                SurveyAssetInfoItem assetInfoItem = getSurveyAssetInfoItemById(integer);
                SurveyAssetInventory targetInventory = null;
                List<SurveyAssetInventoryContent> inventoryContents = null;
                if (assetInfoItem.getInventoryId() == null) {
                    targetInventory = new SurveyAssetInventory();
                    SurveyAssetInfo surveyAssetInfo = surveyAssetInfoService.getSurveyAssetInfoById(assetInfoItem.getAssetInfoId());
                    targetInventory.setProjectId(surveyAssetInfo.getProjectId());
                    targetInventory.setPlanDetailId(surveyAssetInfo.getPlanDetailId());
                    targetInventory.setDeclareRecordId(assetInfoItem.getDeclareId());
                    targetInventory.setCreator(commonService.thisUserAccount());
                    surveyAssetInventoryService.addSurveyAssetInventory(targetInventory);
                } else {
                    targetInventory = surveyAssetInventoryService.getSurveyAssetInventoryById(assetInfoItem.getInventoryId());
                }
                inventoryContents = surveyAssetInventoryContentService.getSurveyAssetInventoryContentListByMasterId(targetInventory.getId());
                if (CollectionUtils.isEmpty(inventoryContents)) {
                    surveyAssetInventoryContentService.initContentByInventoryId(assetInfoItem, targetInventory.getId());
                }else {
                    surveyAssetInventoryContentService.setContentInitialValue(targetInventory.getId(),inventoryContents);
                }
                assetInfoItem.setBisFinishUniformity(true);
                assetInfoItem.setInventoryId(targetInventory.getId());
                updateSurveyAssetInfoItem(assetInfoItem, false);
            } catch (Exception e) {
                baseService.writeExceptionInfo(e);
            }
        }
    }
}
