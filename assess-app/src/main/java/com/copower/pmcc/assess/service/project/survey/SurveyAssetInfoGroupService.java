package com.copower.pmcc.assess.service.project.survey;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInfoGroupDao;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryDao;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInfo;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInfoGroup;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInfoItem;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventory;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zch on 2020-3-20.
 */
@Service
public class SurveyAssetInfoGroupService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private SurveyAssetInfoGroupDao surveyAssetInfoGroupDao;
    @Autowired
    private SurveyAssetInfoService surveyAssetInfoService;
    @Autowired
    private SurveyAssetInfoItemService surveyAssetInfoItemService;
    @Autowired
    private SurveyAssetInventoryDao surveyAssetInventoryDao;

    private final String viewSpilt = "viewSpilt";
    private final String taxArrears = "taxArrears";
    private final String damage = "damage";
    private final String transfer = "transfer";

    public boolean updateSurveyAssetInfoGroup(SurveyAssetInfoGroup oo, boolean updateNull) {
        return surveyAssetInfoGroupDao.updateSurveyAssetInfoGroup(oo, updateNull);
    }

    /**
     * 添加清查组
     *
     * @param assetInfoId
     * @param formType
     * @param groupName
     */
    public void addSurveyAssetInfoGroup(Integer assetInfoId, String formType, String groupName) {
        if (StringUtils.isBlank(formType)) return;
        SurveyAssetInfoGroup group = new SurveyAssetInfoGroup();
        group.setAssetInfoId(assetInfoId);
        group.setFormType(formType);
        group.setGroupName(groupName);
        group.setCreator(commonService.thisUserAccount());
        surveyAssetInfoGroupDao.addSurveyAssetInfoGroup(group);
        List<String> list = FormatUtils.transformString2List(formType);
        SurveyAssetInventory inventory = null;
        SurveyAssetInfo assetInfo = surveyAssetInfoService.getSurveyAssetInfoById(assetInfoId);
        for (String s : list) {
            inventory = new SurveyAssetInventory();
            inventory.setProjectId(assetInfo.getProjectId());
            inventory.setPlanDetailId(assetInfo.getPlanDetailId());
            inventory.setGroupId(group.getId());
            surveyAssetInventoryDao.addSurveyAssetInventory(inventory);
            switch (s) {
                case viewSpilt:
                    group.setViewSpiltId(inventory.getId());
                    break;
                case taxArrears:
                    group.setTaxArrearsId(inventory.getId());
                    break;
                case damage:
                    group.setDamageId(inventory.getId());
                    break;
                case transfer:
                    group.setTransferId(inventory.getId());
                    break;
            }
        }
        surveyAssetInfoGroupDao.updateSurveyAssetInfoGroup(group, false);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteSurveyAssetInfoGroupById(String id) throws Exception {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isNotEmpty(ids)) {
            for (Integer integer : ids) {
                SurveyAssetInfoItem where = new SurveyAssetInfoItem();
                where.setGroupId(integer);
                List<SurveyAssetInfoItem> infoItems = surveyAssetInfoItemService.getSurveyAssetInfoItemListByQuery(where);
                if (CollectionUtils.isNotEmpty(infoItems)) {
                    infoItems.forEach(o -> {
                        o.setGroupId(0);
                        surveyAssetInfoItemService.updateSurveyAssetInfoItem(o, false);
                    });
                }
                SurveyAssetInfoGroup assetInfoGroup = getSurveyAssetInfoGroupById(integer);
                if (assetInfoGroup.getViewSpiltId() != null)
                    surveyAssetInventoryDao.deleteSurveyAssetInventory(assetInfoGroup.getViewSpiltId());
                if (assetInfoGroup.getViewSpiltId() != null)
                    surveyAssetInventoryDao.deleteSurveyAssetInventory(assetInfoGroup.getViewSpiltId());
                if (assetInfoGroup.getViewSpiltId() != null)
                    surveyAssetInventoryDao.deleteSurveyAssetInventory(assetInfoGroup.getViewSpiltId());
                if (assetInfoGroup.getViewSpiltId() != null)
                    surveyAssetInventoryDao.deleteSurveyAssetInventory(assetInfoGroup.getViewSpiltId());

                surveyAssetInfoGroupDao.deleteSurveyAssetInfoGroupById(integer);
            }
        }
    }

    public BootstrapTableVo getBootstrapTableVo(SurveyAssetInfoGroup oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SurveyAssetInfoGroup> surveyAssetInfoGroups = getSurveyAssetInfoItemListLikeQuery(oo);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isNotEmpty(surveyAssetInfoGroups) ? surveyAssetInfoGroups : new ArrayList(0));
        return vo;
    }

    public List<SurveyAssetInfoGroup> getSurveyAssetInfoGroupByIds(List<Integer> ids) {
        return surveyAssetInfoGroupDao.getSurveyAssetInfoGroupByIds(ids);
    }

    public SurveyAssetInfoGroup getSurveyAssetInfoGroupById(Integer id) {
        return surveyAssetInfoGroupDao.getSurveyAssetInfoGroupById(id);
    }


    public List<SurveyAssetInfoGroup> getSurveyAssetInfoGroupListByQuery(SurveyAssetInfoGroup oo) {
        return surveyAssetInfoGroupDao.getSurveyAssetInfoGroupListByExample(oo);
    }

    public List<SurveyAssetInfoGroup> getSurveyAssetInfoItemListLikeQuery(SurveyAssetInfoGroup oo) {
        return surveyAssetInfoGroupDao.getSurveyAssetInfoItemListLikeQuery(oo);
    }

    public void selectClaimInfoItem(Integer groupId, String infoItems) {
        if (groupId == null || StringUtils.isBlank(infoItems)) return;
        List<Integer> list = FormatUtils.transformString2Integer(infoItems);
        List<SurveyAssetInfoItem> assetInfoItems = surveyAssetInfoItemService.getSurveyAssetInfoItemByIds(list);
        if (CollectionUtils.isNotEmpty(assetInfoItems)) {
            for (SurveyAssetInfoItem assetInfoItem : assetInfoItems) {
                assetInfoItem.setGroupId(groupId);
                surveyAssetInfoItemService.updateSurveyAssetInfoItem(assetInfoItem, false);
            }
        }
    }

    //移除
    public void removeGroupInfoItem(String infoItems) {
        if (StringUtils.isBlank(infoItems)) return;
        List<Integer> list = FormatUtils.transformString2Integer(infoItems);
        List<SurveyAssetInfoItem> assetInfoItems = surveyAssetInfoItemService.getSurveyAssetInfoItemByIds(list);
        if (CollectionUtils.isNotEmpty(assetInfoItems)) {
            for (SurveyAssetInfoItem assetInfoItem : assetInfoItems) {
                assetInfoItem.setGroupId(0);
                assetInfoItem.setStatus(ProjectStatusEnum.RUNING.getKey());
                surveyAssetInfoItemService.updateSurveyAssetInfoItem(assetInfoItem, false);
            }
        }
    }

    /**
     * 保存分组的清查信息
     *
     * @param formData
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveGroupInventoryData(String formData, Integer groupId) throws BusinessException {
        if (StringUtils.isBlank(formData))
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        JSONObject jsonObject = JSON.parseObject(formData);
        List<String> list = Lists.newArrayList();
        list.add(jsonObject.getString(viewSpilt));
        list.add(jsonObject.getString(taxArrears));
        list.add(jsonObject.getString(damage));
        list.add(jsonObject.getString(transfer));
        if (CollectionUtils.isNotEmpty(list)) {
            for (String s : list) {
                if (StringUtils.isNotBlank(s)) {
                    SurveyAssetInventory inventory = JSON.parseObject(s, SurveyAssetInventory.class);
                    if (inventory != null && inventory.getId() != null) {
                        surveyAssetInventoryDao.updateSurveyAssetInventory(inventory);
                    }
                }
            }
        }
        List<SurveyAssetInfoItem> infoItems = surveyAssetInfoItemService.getSurveyAssetInfoItemsByGroupId(groupId);
        if (CollectionUtils.isNotEmpty(infoItems)) {
            for (SurveyAssetInfoItem infoItem : infoItems) {
                infoItem.setStatus(ProjectStatusEnum.FINISH.getKey());
                surveyAssetInfoItemService.updateSurveyAssetInfoItem(infoItem, false);
            }
        }
    }
}
