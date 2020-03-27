package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInfoDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
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
public class SurveyAssetInfoService {
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


    /**
     * 提交要做的事
     *
     * @param surveyAssetInfo
     */
    public void submit(SurveyAssetInfo surveyAssetInfo) {

        List<DeclareRecord> declareRecordList = declareRecordService.getDeclareRecordByProjectId(surveyAssetInfo.getProjectId());

        //处理状态问题  组
        SurveyAssetInfoGroup queryGroup = new SurveyAssetInfoGroup();
        queryGroup.setAssetInfoId(surveyAssetInfo.getId());
        queryGroup.setCreator(commonService.thisUserAccount());
        queryGroup.setStatus(SysProjectEnum.FINISH.getValue());
        List<SurveyAssetInfoGroup> assetInfoGroups = surveyAssetInfoGroupService.getSurveyAssetInfoGroupListByQuery(queryGroup);
        if (CollectionUtils.isNotEmpty(assetInfoGroups)) {
            for (SurveyAssetInfoGroup infoGroup : assetInfoGroups) {
                List<Integer> integerList = surveyAssetInfoItemService.getSurveyAssetInfoItemIdsByGroupId(infoGroup.getId());
                if (CollectionUtils.isNotEmpty(integerList)) {
                    for (Integer id : integerList) {
                        SurveyAssetInfoItem assetInfoItem = surveyAssetInfoItemService.getSurveyAssetInfoItemById(id);
                        DeclareRecord declareRecord = declareRecordList.stream().filter(record -> Objects.equal(record.getId(), assetInfoItem.getDeclareId())).findFirst().get();
                        declareRecord.setBisInventory(true);
                        declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
                    }
                }
            }
        }

        //处理状态问题  认领的单个方式
        SurveyAssetInfoItem queryItem = new SurveyAssetInfoItem();
        queryItem.setCreator(commonService.thisUserAccount());
        queryItem.setAssetInfoId(surveyAssetInfo.getId());
        queryItem.setStatus(SysProjectEnum.FINISH.getValue());
        List<SurveyAssetInfoItem> surveyAssetInfoItems = surveyAssetInfoItemService.getSurveyAssetInfoItemListByQuery(queryItem);
        if (CollectionUtils.isNotEmpty(surveyAssetInfoItems)) {
            if (CollectionUtils.isNotEmpty(surveyAssetInfoItems)) {
                for (SurveyAssetInfoItem assetInfoItem : surveyAssetInfoItems) {
                    DeclareRecord declareRecord = declareRecordList.stream().filter(record -> Objects.equal(record.getId(), assetInfoItem.getDeclareId())).findFirst().get();
                    declareRecord.setBisInventory(true);
                    declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
                }
            }
        }

        //统计
        statistics(surveyAssetInfo);

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

        List<DeclareRecord> recordList = declareRecordList.stream().filter(declareRecord -> declareRecord.getBisInventory()).collect(Collectors.toList());


        surveyAssetInfo.setFinishCount(recordList.size());

        SurveyAssetInfoItem queryItem = new SurveyAssetInfoItem();
        queryItem.setCreator(commonService.thisUserAccount());
        queryItem.setAssetInfoId(surveyAssetInfo.getId());
        queryItem.setStatus(SysProjectEnum.FINISH.getValue());

        SurveyAssetInfoGroup queryGroup = new SurveyAssetInfoGroup();
        queryGroup.setAssetInfoId(surveyAssetInfo.getId());
        queryGroup.setCreator(commonService.thisUserAccount());
        queryGroup.setStatus(SysProjectEnum.FINISH.getValue());
        List<SurveyAssetInfoGroup> assetInfoGroups = surveyAssetInfoGroupService.getSurveyAssetInfoGroupListByQuery(queryGroup);
        List<SurveyAssetInfoItem> surveyAssetInfoItems = surveyAssetInfoItemService.getSurveyAssetInfoItemListByQuery(queryItem);

        int thisCount = assetInfoGroups.size()+surveyAssetInfoItems.size();

        surveyAssetInfo.setThisCount(thisCount);
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

        SurveyAssetInfoGroup queryGroup = new SurveyAssetInfoGroup();
        queryGroup.setAssetInfoId(surveyAssetInfo.getId());
        queryGroup.setStatus(SysProjectEnum.FINISH.getValue());
        List<SurveyAssetInfoGroup> assetInfoGroups = surveyAssetInfoGroupService.getSurveyAssetInfoGroupListByQuery(queryGroup);

        if (CollectionUtils.isNotEmpty(assetInfoGroups)) {
            for (SurveyAssetInfoGroup infoGroup : assetInfoGroups) {
                List<Integer> integerList = surveyAssetInfoItemService.getSurveyAssetInfoItemIdsByGroupId(infoGroup.getId());
                if (CollectionUtils.isNotEmpty(integerList)) {
                    for (Integer id : integerList) {
                        SurveyAssetInfoItem assetInfoItem = surveyAssetInfoItemService.getSurveyAssetInfoItemById(id);
                        DeclareRecord declareRecord = declareRecordList.stream().filter(record -> Objects.equal(record.getId(), assetInfoItem.getDeclareId())).findFirst().get();
                        if (declareRecord == null) {
                            continue;
                        }
                        recordMap.put(infoGroup.getInventoryId(), declareRecord);
                    }
                }
            }
        }

        SurveyAssetInfoItem queryItem = new SurveyAssetInfoItem();
        queryItem.setAssetInfoId(surveyAssetInfo.getId());
        queryItem.setStatus(SysProjectEnum.FINISH.getValue());
        List<SurveyAssetInfoItem> surveyAssetInfoItems = surveyAssetInfoItemService.getSurveyAssetInfoItemListByQuery(queryItem);
        if (CollectionUtils.isNotEmpty(surveyAssetInfoItems)) {
            if (CollectionUtils.isNotEmpty(surveyAssetInfoItems)) {
                for (SurveyAssetInfoItem assetInfoItem : surveyAssetInfoItems) {
                    DeclareRecord declareRecord = declareRecordList.stream().filter(record -> Objects.equal(record.getId(), assetInfoItem.getDeclareId())).findFirst().get();
                    if (declareRecord == null) {
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
        BaseDataDic addressDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_ACTUAL_ADDRESS);

        for (SurveyAssetInventoryContent content : contentList) {
            //反写实际面积
            if (areaDic != null) {
                if (content.getInventoryContent().equals(areaDic.getId()) && org.apache.commons.lang3.StringUtils.isNotBlank(content.getActual()) && NumberUtils.isNumber(content.getActual())) {
                    declareRecord.setPracticalArea(new BigDecimal(content.getActual()));
                }
            }
            //反写实际地址
            if (addressDic != null) {
                if (content.getInventoryContent().equals(addressDic.getId()) && org.apache.commons.lang3.StringUtils.isNotBlank(content.getActual())) {
                    declareRecord.setSeat(content.getActual());
                }
            }
        }
        declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
    }


}
