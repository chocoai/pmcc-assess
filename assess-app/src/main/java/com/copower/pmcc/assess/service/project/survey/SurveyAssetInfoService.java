package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInfoDao;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInfo;
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

/**
 * Created by zch on 2020-3-20.
 */
@Service
public class SurveyAssetInfoService {


    @Autowired
    private CommonService commonService;
    @Autowired
    private SurveyAssetInfoDao surveyAssetInfoDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;



    public SurveyAssetInfo getSurveyAssetInfoByPlanDetailsId(Integer planDetailsId) {
        SurveyAssetInfo query = new SurveyAssetInfo();
        query.setPlanDetailId(planDetailsId);
        List<SurveyAssetInfo> xlxReportIndividuals = getSurveyAssetInfoListByQuery(query);
        if (CollectionUtils.isNotEmpty(xlxReportIndividuals)) {
            return xlxReportIndividuals.get(0);
        }
        saveSurveyAssetInfo(query) ;
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
