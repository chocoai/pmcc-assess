package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetRightItemDao;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightItem;
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
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zch on 2019-12-16.
 * 他项权利详情表
 */
@Service
public class SurveyAssetRightItemService {


    @Autowired
    private CommonService commonService;
    @Autowired
    private SurveyAssetRightItemDao surveyAssetRightItemDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    public boolean updateSurveyAssetRightItem(SurveyAssetRightItem oo, boolean updateNull) {
        return surveyAssetRightItemDao.updateSurveyAssetRightItem(oo, updateNull);
    }

    public boolean saveSurveyAssetRightItem(SurveyAssetRightItem oo) {
        if (oo == null) {
            return false;
        }
        if (StringUtils.isEmpty(oo.getCreator())) {
            oo.setCreator(commonService.thisUserAccount());
        }
        return surveyAssetRightItemDao.saveSurveyAssetRightItem(oo);
    }

    public void saveAndUpdateSurveyAssetRightItem(SurveyAssetRightItem oo,boolean updateNull) {
        if (oo == null) {
            return;
        }
        if (oo.getId() != null && oo.getId() != 0) {
            surveyAssetRightItemDao.updateSurveyAssetRightItem(oo, updateNull);
        } else {
            saveSurveyAssetRightItem(oo);
        }
    }

    private void removeFileByTableId(Integer tableId){
        if (tableId == null){
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(tableId);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetRightItem.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto) ;
        if (CollectionUtils.isEmpty(sysAttachmentDtoList)){
            return;
        }
        sysAttachmentDtoList.forEach(sysAttachmentDto1 -> baseAttachmentService.deleteAttachment(sysAttachmentDto1.getId()));
    }

    public void deleteSurveyAssetRightItemById(String id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isNotEmpty(ids)) {
            if (ids.size() == 1) {
                removeFileByTableId(ids.get(0)) ;
                surveyAssetRightItemDao.deleteSurveyAssetRightItemById(ids.get(0));
            } else {
                ids.forEach(integer -> removeFileByTableId(integer));
                surveyAssetRightItemDao.deleteSurveyAssetRightItemByIds(ids);
            }
        }
    }

    public BootstrapTableVo getBootstrapTableVo(SurveyAssetRightItem oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SurveyAssetRightItem> declareApplyExtensionList = getSurveyAssetRightItemListByExample(oo);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isNotEmpty(declareApplyExtensionList) ? declareApplyExtensionList : new ArrayList(0));
        return vo;
    }


    public List<SurveyAssetRightItem> getSurveyAssetRightItemByIds(List<Integer> ids) {
        return surveyAssetRightItemDao.getSurveyAssetRightItemByIds(ids);
    }

    public SurveyAssetRightItem getSurveyAssetRightItemById(Integer id) {
        return surveyAssetRightItemDao.getSurveyAssetRightItemById(id);
    }

    public List<SurveyAssetRightItem> getSurveyAssetRightItemListByExample(SurveyAssetRightItem oo) {
        return surveyAssetRightItemDao.getSurveyAssetRightItemListByExample(oo);
    }
    
}
