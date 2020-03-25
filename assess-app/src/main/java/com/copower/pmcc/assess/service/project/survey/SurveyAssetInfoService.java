package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInfoDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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


    /**
     * 提交要做的事
     * @param surveyAssetInfo
     */
    public void submit(SurveyAssetInfo surveyAssetInfo) {

        //处理状态问题

        statistics(surveyAssetInfo);

        updateSurveyAssetInfo(surveyAssetInfo,false) ;


    }

    /**
     * 统计
     *
     * @param surveyAssetInfo
     */
    public void statistics(SurveyAssetInfo surveyAssetInfo) {
        List<DeclareRecord> declareRecordList = declareRecordService.getDeclareRecordByProjectId(surveyAssetInfo.getProjectId());
        surveyAssetInfo.setCount(declareRecordList.size());

        DeclareRecord query = new DeclareRecord();
        query.setProjectId(surveyAssetInfo.getProjectId());
        query.setBisInventory(true);
        List<DeclareRecord> declareRecords = declareRecordService.getDeclareRecordList(query);

        surveyAssetInfo.setFinishCount(declareRecords.size());

        List<SurveyAssetInfoItem> infoItemList = new ArrayList<>();
        SurveyAssetInfoItem queryItem = new SurveyAssetInfoItem();
        queryItem.setCreator(commonService.thisUserAccount());
        queryItem.setAssetInfoId(surveyAssetInfo.getId());
        SurveyAssetInfoGroup queryGroup = new SurveyAssetInfoGroup();
        queryGroup.setAssetInfoId(surveyAssetInfo.getId());
        queryGroup.setCreator(commonService.thisUserAccount());
        List<SurveyAssetInfoGroup> assetInfoGroups = surveyAssetInfoGroupService.getSurveyAssetInfoGroupListByQuery(queryGroup);
        if (CollectionUtils.isNotEmpty(assetInfoGroups)) {
            for (SurveyAssetInfoGroup infoGroup : assetInfoGroups) {
                if (infoGroup.getInventoryId() == null){
                    continue;
                }
                List<Integer> integerList = surveyAssetInfoItemService.getSurveyAssetInfoItemIdsByGroupId(infoGroup.getId());
                if (CollectionUtils.isNotEmpty(integerList)) {
                    for (Integer id : integerList) {
                        SurveyAssetInfoItem assetInfoItem = surveyAssetInfoItemService.getSurveyAssetInfoItemById(id);
                        infoItemList.add(assetInfoItem);
                    }
                }
            }
        }
        List<SurveyAssetInfoItem> surveyAssetInfoItems = surveyAssetInfoItemService.getSurveyAssetInfoItemListByQuery(queryItem);
        if (CollectionUtils.isNotEmpty(surveyAssetInfoItems)) {
            List<SurveyAssetInfoItem> infoItems = surveyAssetInfoItems.stream().filter(surveyAssetInfoItem -> surveyAssetInfoItem.getInventoryId() != null).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(infoItems)) {
                infoItemList.addAll(infoItems);
            }
        }
        int thisCount = 0;
        if (CollectionUtils.isNotEmpty(declareRecords)) {
            if (CollectionUtils.isNotEmpty(infoItemList)) {
                Set<Integer> integerSet = infoItemList.stream().map(info -> info.getDeclareId()).distinct().collect(Collectors.toSet());
                if (CollectionUtils.isNotEmpty(integerSet)) {
                    for (Integer integer : integerSet) {
                        if (declareRecords.stream().anyMatch(declareRecord -> Objects.equal(declareRecord.getId(), integer))) {
                            thisCount++;
                        }
                    }
                }
            }
        }
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

}
