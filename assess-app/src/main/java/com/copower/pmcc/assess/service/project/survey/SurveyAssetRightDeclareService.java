package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetRightDao;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetRightDeclareDao;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetRightGroupDao;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetRightItemDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zch on 2019-12-16.
 * 他项权利 申报记录
 */
@Service
public class SurveyAssetRightDeclareService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private SurveyAssetRightDeclareDao surveyAssetRightDeclareDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectPhaseService projectPhaseService;

    public ProjectPlanDetails getProjectPlanDetailsByQuery(Integer projectId) {
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        ProjectPhase otherRightPhase = projectPhaseService.getCacheProjectPhaseByCategoryId(AssessPhaseKeyConstant.OTHER_RIGHT, projectInfo.getProjectCategoryId());
        ProjectPlanDetails query = new ProjectPlanDetails();
        query.setProjectId(projectId);
        query.setProjectPhaseId(otherRightPhase.getId());
        List<ProjectPlanDetails> detailsList = projectPlanDetailsService.getProjectDetails(query);
        return detailsList.get(0);
    }

    public SurveyAssetRightDeclare getRightDeclareByDeclareId(Integer declareId) {
        if (declareId == null) return null;
        SurveyAssetRightDeclare where = new SurveyAssetRightDeclare();
        where.setDeclareId(declareId);
        List<SurveyAssetRightDeclare> list = surveyAssetRightDeclareDao.getSurveyAssetRightDeclareList(where);
        if (CollectionUtils.isEmpty(list)) return null;
        return list.get(0);
    }

    public boolean updateSurveyAssetRightDeclare(SurveyAssetRightDeclare oo, boolean updateNull) {
        return surveyAssetRightDeclareDao.updateSurveyAssetRightDeclare(oo, updateNull);
    }

    public boolean saveSurveyAssetRightDeclare(SurveyAssetRightDeclare oo) {
        if (oo == null) {
            return false;
        }
        if (StringUtils.isEmpty(oo.getCreator())) {
            oo.setCreator(commonService.thisUserAccount());
        }
        return surveyAssetRightDeclareDao.saveSurveyAssetRightDeclare(oo);
    }

    public void saveAndUpdateSurveyAssetRightDeclare(SurveyAssetRightDeclare oo, boolean updateNull) {
        if (oo == null) {
            return;
        }
        if (oo.getId() != null && oo.getId() != 0) {
            surveyAssetRightDeclareDao.updateSurveyAssetRightDeclare(oo, updateNull);
        } else {
            saveSurveyAssetRightDeclare(oo);
        }
    }

    private void removeFileByTableId(Integer tableId) {
        if (tableId == null) {
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(tableId);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetRightDeclare.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if (CollectionUtils.isEmpty(sysAttachmentDtoList)) {
            return;
        }
        sysAttachmentDtoList.forEach(sysAttachmentDto1 -> baseAttachmentService.deleteAttachment(sysAttachmentDto1.getId()));
    }

    public void deleteSurveyAssetRightDeclareById(String id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isNotEmpty(ids)) {
            if (ids.size() == 1) {
                removeFileByTableId(ids.get(0));
                surveyAssetRightDeclareDao.deleteSurveyAssetRightDeclareById(ids.get(0));
            } else {
                ids.forEach(integer -> removeFileByTableId(integer));
                surveyAssetRightDeclareDao.deleteSurveyAssetRightDeclareByIds(ids);
            }
        }
    }

    public BootstrapTableVo getBootstrapTableVo(SurveyAssetRightDeclare oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SurveyAssetRightDeclare> declareApplyExtensionList = getSurveyAssetRightDeclareListByExample(oo);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isNotEmpty(declareApplyExtensionList) ? declareApplyExtensionList : new ArrayList(0));
        return vo;
    }


    public List<SurveyAssetRightDeclare> getSurveyAssetRightDeclareByIds(List<Integer> ids) {
        return surveyAssetRightDeclareDao.getSurveyAssetRightDeclareByIds(ids);
    }

    public List<SurveyAssetRightDeclare> getRightDeclareListByDeclareIds(List<Integer> declareIds) {
        return surveyAssetRightDeclareDao.getRightDeclareListByDeclareIds(declareIds);
    }

    public SurveyAssetRightDeclare getSurveyAssetRightDeclareById(Integer id) {
        return surveyAssetRightDeclareDao.getSurveyAssetRightDeclareById(id);
    }

    public List<SurveyAssetRightDeclare> getSurveyAssetRightDeclareListByExample(SurveyAssetRightDeclare oo) {
        return surveyAssetRightDeclareDao.getSurveyAssetRightDeclareListByExample(oo);
    }

    public List<SurveyAssetRightDeclare> getSurveyAssetRightDeclareListByGroupId(Integer groupId) {
        SurveyAssetRightDeclare select = new SurveyAssetRightDeclare();
        select.setGroupId(groupId);
        return getSurveyAssetRightDeclareListByExample(select);
    }

}
