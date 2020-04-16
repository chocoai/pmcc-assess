package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetRightDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRight;
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
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zch on 2019-12-16.
 * 他项权力
 */
@Service
public class SurveyAssetRightService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private SurveyAssetRightDao surveyAssetRightDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private SurveyAssetRightGroupService surveyAssetRightGroupService;
    @Autowired
    private DeclareRecordService declareRecordService;

    public Map<SchemeJudgeObject, DeclareRecord> getDeclareRecordJudgeObjectMap(List<DeclareRecord> declareRecordList, List<SchemeJudgeObject> schemeJudgeObjectList) {
        Map<SchemeJudgeObject, DeclareRecord> schemeJudgeObjectDeclareRecordMap = Maps.newHashMap();
        if (CollectionUtils.isEmpty(declareRecordList)) {
            return schemeJudgeObjectDeclareRecordMap;
        }
        if (CollectionUtils.isEmpty(schemeJudgeObjectList)) {
            return schemeJudgeObjectDeclareRecordMap;
        }
        for (DeclareRecord declareRecord:declareRecordList){
            List<SchemeJudgeObject> collect = schemeJudgeObjectList.stream().filter(schemeJudgeObject -> Objects.equal(schemeJudgeObject.getDeclareRecordId(), declareRecord.getId())).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(collect)){
                continue;
            }
            schemeJudgeObjectDeclareRecordMap.put(collect.get(0), declareRecord);
        }
        return schemeJudgeObjectDeclareRecordMap;
    }

    @Transactional(rollbackFor = {Exception.class})
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) {
        surveyAssetRightGroupService.clear(projectPlanDetails);
        SurveyAssetRight right = getSurveyAssetRightOnly(projectPlanDetails, processInsId);
        surveyAssetRightGroupService.updateOnlyMasterId(right.getId(), projectPlanDetails);
    }

    public SurveyAssetRight getSurveyAssetRightOnly(ProjectPlanDetails projectPlanDetails, String processInsId) {
        SurveyAssetRight rightQuery = new SurveyAssetRight();
        if (StringUtils.isNotEmpty(processInsId)) {
            rightQuery.setProcessInsId(processInsId);
        } else {
            if (StringUtils.isNotEmpty(projectPlanDetails.getProcessInsId())) {
                rightQuery.setProcessInsId(projectPlanDetails.getProcessInsId());
            }
        }
        rightQuery.setPlanDetailsId(projectPlanDetails.getId());
        rightQuery.setProjectId(projectPlanDetails.getProjectId());
        rightQuery.setPlanId(projectPlanDetails.getPlanId());
        List<SurveyAssetRight> rightList = getSurveyAssetRightListByExample(rightQuery);
        if (CollectionUtils.isEmpty(rightList)) {
            rightList = new ArrayList<>(1);
            saveSurveyAssetRight(rightQuery);
            rightList.add(rightQuery);
        }
        return rightList.stream().findFirst().get();
    }

    public SurveyAssetRight getSurveyAssetRightOnly2(ProjectPlanDetails projectPlanDetails) {
        SurveyAssetRight rightQuery = new SurveyAssetRight();
        rightQuery.setPlanDetailsId(projectPlanDetails.getId());
        rightQuery.setProjectId(projectPlanDetails.getProjectId());
        rightQuery.setPlanId(projectPlanDetails.getPlanId());
        List<SurveyAssetRight> rightList = getSurveyAssetRightListByExample(rightQuery);
        if (CollectionUtils.isEmpty(rightList)) {
            return null;
        }
        return rightList.stream().findFirst().get();
    }

    public boolean updateSurveyAssetRight(SurveyAssetRight oo, boolean updateNull) {
        return surveyAssetRightDao.updateSurveyAssetRight(oo, updateNull);
    }

    public boolean saveSurveyAssetRight(SurveyAssetRight oo) {
        if (oo == null) {
            return false;
        }
        if (StringUtils.isEmpty(oo.getCreator())) {
            oo.setCreator(commonService.thisUserAccount());
        }
        return surveyAssetRightDao.saveSurveyAssetRight(oo);
    }

    public void saveAndUpdateSurveyAssetRight(SurveyAssetRight oo, boolean updateNull) {
        if (oo == null) {
            return;
        }
        if (oo.getId() != null && oo.getId() != 0) {
            updateSurveyAssetRight(oo, updateNull);
        } else {
            saveSurveyAssetRight(oo);
        }
    }

    private void removeFileByTableId(Integer tableId) {
        if (tableId == null) {
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(tableId);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetRight.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if (CollectionUtils.isEmpty(sysAttachmentDtoList)) {
            return;
        }
        sysAttachmentDtoList.forEach(sysAttachmentDto1 -> baseAttachmentService.deleteAttachment(sysAttachmentDto1.getId()));
    }

    public void deleteSurveyAssetRightById(String id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isNotEmpty(ids)) {
            if (ids.size() == 1) {
                removeFileByTableId(ids.get(0));
                surveyAssetRightDao.deleteSurveyAssetRightById(ids.get(0));
            } else {
                ids.forEach(integer -> removeFileByTableId(integer));
                surveyAssetRightDao.deleteSurveyAssetRightByIds(ids);
            }
        }
    }

    public BootstrapTableVo getBootstrapTableVo(SurveyAssetRight oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SurveyAssetRight> declareApplyExtensionList = getSurveyAssetRightListByExample(oo);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isNotEmpty(declareApplyExtensionList) ? declareApplyExtensionList : new ArrayList(0));
        return vo;
    }


    public List<SurveyAssetRight> getSurveyAssetRightByIds(List<Integer> ids) {
        return surveyAssetRightDao.getSurveyAssetRightByIds(ids);
    }

    public SurveyAssetRight getSurveyAssetRightById(Integer id) {
        return surveyAssetRightDao.getSurveyAssetRightById(id);
    }

    public List<SurveyAssetRight> getSurveyAssetRightListByExample(SurveyAssetRight oo) {
        return surveyAssetRightDao.getSurveyAssetRightListByExample(oo);
    }

}
