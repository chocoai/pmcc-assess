package com.copower.pmcc.assess.service.project.survey;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInfoGroupDao;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryDao;
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
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private SurveyAssetInfoItemService surveyAssetInfoItemService;
    @Autowired
    private SurveyAssetInventoryDao surveyAssetInventoryDao;

    private final String viewSpilt = "viewSpilt";
    private final String uniformity = "uniformity";
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
        for (String s : list) {
            switch (s) {
                case viewSpilt:
                    inventory = new SurveyAssetInventory();
                    inventory.setGroupId(group.getId());
                    surveyAssetInventoryDao.addSurveyAssetInventory(inventory);
                    group.setViewSpiltId(inventory.getId());
                    break;
                case uniformity:

                    break;
                case taxArrears:
                    inventory = new SurveyAssetInventory();
                    inventory.setGroupId(group.getId());
                    surveyAssetInventoryDao.addSurveyAssetInventory(inventory);
                    group.setTaxArrearsId(inventory.getId());
                    break;
                case damage:
                    inventory = new SurveyAssetInventory();
                    inventory.setGroupId(group.getId());
                    surveyAssetInventoryDao.addSurveyAssetInventory(inventory);
                    group.setDamageId(inventory.getId());
                    break;
                case transfer:
                    inventory = new SurveyAssetInventory();
                    inventory.setGroupId(group.getId());
                    surveyAssetInventoryDao.addSurveyAssetInventory(inventory);
                    group.setTransferId(inventory.getId());
                    break;
            }
        }
        surveyAssetInfoGroupDao.updateSurveyAssetInfoGroup(group, false);
    }

    private void removeFileByTableId(Integer tableId) throws Exception {
        if (tableId == null) {
            return;
        }
        List<Integer> integers = surveyAssetInfoItemService.getSurveyAssetInfoItemIdsByGroupId(tableId);
        if (CollectionUtils.isNotEmpty(integers)) {
            throw new Exception("存在子类,请删除子类以后在删除本数据");
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(tableId);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetInfoGroup.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if (CollectionUtils.isEmpty(sysAttachmentDtoList)) {
            return;
        }
        sysAttachmentDtoList.forEach(sysAttachmentDto1 -> baseAttachmentService.deleteAttachment(sysAttachmentDto1.getId()));
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteSurveyAssetInfoGroupById(String id) throws Exception {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isNotEmpty(ids)) {
            if (ids.size() == 1) {
                removeFileByTableId(ids.get(0));
                surveyAssetInfoGroupDao.deleteSurveyAssetInfoGroupById(ids.get(0));
            } else {
                for (Integer integer : ids) {
                    removeFileByTableId(integer);
                    surveyAssetInfoGroupDao.deleteSurveyAssetInfoGroupById(integer);
                }
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
                surveyAssetInfoItemService.updateSurveyAssetInfoItem(assetInfoItem, false);
            }
        }
    }

    /**保存分组的清查信息
     * @param formData
     */
    public void saveGroupInventoryData(String formData) throws BusinessException {
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
                    if (inventory != null) {
                        surveyAssetInventoryDao.updateSurveyAssetInventory(inventory);
                    }
                }
            }
        }
    }
}
