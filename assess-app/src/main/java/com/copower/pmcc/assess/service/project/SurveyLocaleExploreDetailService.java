package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.constant.AssessTableNameConstant;
import com.copower.pmcc.assess.dal.dao.base.BaseAttachmentDao;
import com.copower.pmcc.assess.dal.dao.SurveyLocaleExploreDetailDao;
import com.copower.pmcc.assess.dal.entity.BaseAttachment;
import com.copower.pmcc.assess.dal.entity.DataPriceTimepointDescription;
import com.copower.pmcc.assess.dal.entity.SurveyLocaleExploreDetail;
import com.copower.pmcc.assess.dto.input.project.SurveyLocaleExploreDetailDto;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zly on 2018/5/16.
 */
@Service
public class SurveyLocaleExploreDetailService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SurveyLocaleExploreDetailDao surveyLocaleExploreDetailDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseAttachmentDao baseAttachmentDao;
    @Autowired
    private SurveyCommonService surveyCommonService;

    public SurveyLocaleExploreDetail getSingelDetail(Integer id) {
        return surveyLocaleExploreDetailDao.getSingelDetail(id);
    }

    public BootstrapTableVo getList(Integer planDetailsId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SurveyLocaleExploreDetail> surveyLocaleExploreDetailList = surveyLocaleExploreDetailDao.getSurveyLocaleExploreDetail(planDetailsId);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(surveyLocaleExploreDetailList) ? new ArrayList<DataPriceTimepointDescription>() : surveyLocaleExploreDetailList);
        return vo;
    }

    public List<SurveyLocaleExploreDetail> getDetailList(Integer planDetailsId) {
        List<SurveyLocaleExploreDetail> surveyLocaleExploreDetailList = surveyLocaleExploreDetailDao.getSurveyLocaleExploreDetail(planDetailsId);
        return surveyLocaleExploreDetailList;
    }

    @Transactional
    public boolean save(SurveyLocaleExploreDetailDto detailDto) throws BusinessException {
        if (detailDto == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (detailDto.getId() != null && detailDto.getId() > 0) {
            surveyCommonService.saveDynamicForm(detailDto.getDynamicFormId(),detailDto.getDynamicFormData(), detailDto.getDynamicTableName(),detailDto.getDynamicTableId());
            return surveyLocaleExploreDetailDao.update(detailDto);
        } else {
            Integer dynamicTableId = surveyCommonService.saveDynamicForm(detailDto.getDynamicFormId(),detailDto.getDynamicFormData(), detailDto.getDynamicTableName(),detailDto.getDynamicTableId());
            detailDto.setDynamicTableId(dynamicTableId);
            detailDto.setCreator(processControllerComponent.getThisUser());
            boolean flag = surveyLocaleExploreDetailDao.save(detailDto);
            //下载定位图
            surveyCommonService.downLoadLocationImage(AssessTableNameConstant.SURVEY_LOCALE_EXPLORE_DETAIL,detailDto.getId(),detailDto.getSurveyLocaltion());

            //更新附件表id
            BaseAttachment queryParam = new BaseAttachment();
            queryParam.setTableId(0);
            queryParam.setTableName(AssessTableNameConstant.SURVEY_LOCALE_EXPLORE_DETAIL);
            queryParam.setCreater(processControllerComponent.getThisUser());

            BaseAttachment example = new BaseAttachment();
            example.setTableId(detailDto.getId());
            baseAttachmentDao.updateAttachementByExample(queryParam, example);
            return flag;

        }
    }

    public boolean delete(Integer id) throws BusinessException {
        if (id == null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        ;
        return surveyLocaleExploreDetailDao.delete(id);
    }

}
