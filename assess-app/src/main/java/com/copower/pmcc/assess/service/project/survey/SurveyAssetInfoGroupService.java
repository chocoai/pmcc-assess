package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInfoGroupDao;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInfoGroup;
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

    public boolean updateSurveyAssetInfoGroup(SurveyAssetInfoGroup oo, boolean updateNull) {
        return surveyAssetInfoGroupDao.updateSurveyAssetInfoGroup(oo, updateNull);
    }

    public boolean saveSurveyAssetInfoGroup(SurveyAssetInfoGroup oo) {
        if (oo == null) {
            return false;
        }
        if (StringUtils.isEmpty(oo.getCreator())) {
            oo.setCreator(commonService.thisUserAccount());
        }
        boolean b = surveyAssetInfoGroupDao.saveSurveyAssetInfoGroup(oo);
        baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetInfoGroup.class), oo.getId());
        return b;

    }

    public void saveAndUpdateSurveyAssetInfoGroup(SurveyAssetInfoGroup oo, boolean updateNull) {
        if (oo == null) {
            return;
        }
        if (oo.getId() != null && oo.getId() != 0) {
            updateSurveyAssetInfoGroup(oo, updateNull);
        } else {
            saveSurveyAssetInfoGroup(oo);
        }
    }

    private void removeFileByTableId(Integer tableId)throws Exception {
        if (tableId == null) {
            return;
        }
        List<Integer> integers = surveyAssetInfoItemService.getSurveyAssetInfoItemIdsByGroupId(tableId);
        if (CollectionUtils.isNotEmpty(integers)){
            throw new Exception("存在子类,请删除子类以后在删除本数据") ;
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
    public void deleteSurveyAssetInfoGroupById(String id) throws Exception{
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isNotEmpty(ids)) {
            if (ids.size() == 1) {
                removeFileByTableId(ids.get(0));
                surveyAssetInfoGroupDao.deleteSurveyAssetInfoGroupById(ids.get(0));
            } else {
                for (Integer integer:ids){
                    removeFileByTableId(integer) ;
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

    public List<SurveyAssetInfoGroup> getSurveyAssetInfoItemListLikeQuery(SurveyAssetInfoGroup oo){
        return surveyAssetInfoGroupDao.getSurveyAssetInfoItemListLikeQuery(oo);
    }


}
