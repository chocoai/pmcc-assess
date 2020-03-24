package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInfoItemDao;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInfoItem;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
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


    public boolean updateSurveyAssetInfoItem(SurveyAssetInfoItem oo, boolean updateNull) {
        return surveyAssetInfoItemDao.updateSurveyAssetInfoItem(oo, updateNull);
    }

    public boolean saveSurveyAssetInfoItem(SurveyAssetInfoItem oo) {
        if (oo == null) {
            return false;
        }
        if (StringUtils.isEmpty(oo.getCreator())) {
            oo.setCreator(commonService.thisUserAccount());
        }
        boolean b = surveyAssetInfoItemDao.saveSurveyAssetInfoItem(oo);
        baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetInfoItem.class), oo.getId());
        return b;

    }

    public void saveAndUpdateSurveyAssetInfoItem(SurveyAssetInfoItem oo, boolean updateNull) {
        if (oo == null) {
            return;
        }
        if (oo.getId() != null && oo.getId() != 0) {
            updateSurveyAssetInfoItem(oo, updateNull);
        } else {
            saveSurveyAssetInfoItem(oo);
        }
    }

    private void removeFileByTableId(Integer tableId) {
        if (tableId == null) {
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(tableId);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetInfoItem.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if (CollectionUtils.isEmpty(sysAttachmentDtoList)) {
            return;
        }
        sysAttachmentDtoList.forEach(sysAttachmentDto1 -> baseAttachmentService.deleteAttachment(sysAttachmentDto1.getId()));
    }

    public void deleteSurveyAssetInfoItemById(String id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isNotEmpty(ids)) {
            if (ids.size() == 1) {
                removeFileByTableId(ids.get(0));
                surveyAssetInfoItemDao.deleteSurveyAssetInfoItemById(ids.get(0));
            } else {
                ids.forEach(integer -> removeFileByTableId(integer));
                surveyAssetInfoItemDao.deleteSurveyAssetInfoItemByIds(ids);
            }
        }
    }

    public BootstrapTableVo getBootstrapTableVo(SurveyAssetInfoItem oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SurveyAssetInfoItem> surveyAssetInfoItems = getSurveyAssetInfoItemListByQuery(oo);
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


    public List<SurveyAssetInfoItem> getSurveyAssetInfoItemListByQuery(SurveyAssetInfoItem oo) {
        return surveyAssetInfoItemDao.getSurveyAssetInfoItemListByExample(oo);
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

    public void deleteSurveyAssetInfoItemByGroupId(Integer groupId) {
        List<Integer> integerList = getSurveyAssetInfoItemIdsByGroupId(groupId);
        if (CollectionUtils.isNotEmpty(integerList)) {
           for (Integer id:integerList){
               SurveyAssetInfoItem infoItem = getSurveyAssetInfoItemById(id) ;
               infoItem.setGroupId(null);
               updateSurveyAssetInfoItem(infoItem,true) ;
           }
        }
    }



}
