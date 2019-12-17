package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetRightGroupDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightDeclare;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightGroup;
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
import java.util.Iterator;
import java.util.List;

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



    public List<SurveyAssetRightGroup> getSurveyAssetRightGroupByIds(List<Integer> ids) {
        return surveyAssetRightGroupDao.getSurveyAssetRightGroupByIds(ids);
    }

    public SurveyAssetRightGroup getSurveyAssetRightGroupById(Integer id) {
        return surveyAssetRightGroupDao.getSurveyAssetRightGroupById(id);
    }

    public List<SurveyAssetRightGroup> getSurveyAssetRightGroupListByExample(SurveyAssetRightGroup oo) {
        return surveyAssetRightGroupDao.getSurveyAssetRightGroupListByExample(oo);
    }
    
}
