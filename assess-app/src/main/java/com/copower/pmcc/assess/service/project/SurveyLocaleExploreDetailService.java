package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.BaseAttachmentDao;
import com.copower.pmcc.assess.dal.dao.SurveyLocaleExploreDetailDao;
import com.copower.pmcc.assess.dal.entity.BaseAttachment;
import com.copower.pmcc.assess.dal.entity.DataPriceTimepointDescription;
import com.copower.pmcc.assess.dal.entity.SurveyLocaleExploreDetail;
import com.copower.pmcc.assess.service.ServiceComponent;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zly on 2018/5/16.
 */
@Service
public class SurveyLocaleExploreDetailService {

    @Autowired
    private SurveyLocaleExploreDetailDao surveyLocaleExploreDetailDao;
    @Autowired
    private ServiceComponent serviceComponent;
    @Autowired
    private BaseAttachmentDao baseAttachmentDao;

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

    public boolean save(SurveyLocaleExploreDetail surveyLocaleExploreDetail) throws BusinessException {
        if (surveyLocaleExploreDetail == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (surveyLocaleExploreDetail.getId() != null && surveyLocaleExploreDetail.getId() > 0) {
            return surveyLocaleExploreDetailDao.update(surveyLocaleExploreDetail);
        } else {
            surveyLocaleExploreDetail.setCreator(serviceComponent.getThisUser());
            boolean flag = surveyLocaleExploreDetailDao.save(surveyLocaleExploreDetail);
            //更新附件表id
            BaseAttachment queryParam = new BaseAttachment();
            queryParam.setTableId(0);
            queryParam.setTableName("tb_survey_locale_explore_detail");

            BaseAttachment example = new BaseAttachment();
            example.setTableId(surveyLocaleExploreDetail.getId());
            baseAttachmentDao.updateAttachementByExample(queryParam,example);
            return flag;

        }
    }

    public boolean delete(Integer id) throws BusinessException {
        if (id == null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        ;
        return surveyLocaleExploreDetailDao.delete(id);
    }

}
