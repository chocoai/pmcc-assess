package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.BaseAttachmentDao;
import com.copower.pmcc.assess.dal.dao.SurveyLocaleExploreDetailDao;
import com.copower.pmcc.assess.dal.entity.BaseAttachment;
import com.copower.pmcc.assess.dal.entity.DataPriceTimepointDescription;
import com.copower.pmcc.assess.dal.entity.SurveyLocaleExplore;
import com.copower.pmcc.assess.dal.entity.SurveyLocaleExploreDetail;
import com.copower.pmcc.assess.dto.input.project.SurveyAssetTemplateDto;
import com.copower.pmcc.assess.dto.input.project.SurveyLocaleExploreDetailDto;
import com.copower.pmcc.assess.service.ServiceComponent;
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


    public BootstrapTableVo getList(Integer mainId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SurveyLocaleExploreDetail> surveyLocaleExploreDetailList = surveyLocaleExploreDetailDao.getSurveyLocaleExploreDetail(mainId);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(surveyLocaleExploreDetailList) ? new ArrayList<DataPriceTimepointDescription>() : surveyLocaleExploreDetailList);
        return vo;
    }

    public boolean save(SurveyLocaleExploreDetail surveyLocaleExploreDetail) throws BusinessException {
        if(surveyLocaleExploreDetail == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if(surveyLocaleExploreDetail.getId() != null && surveyLocaleExploreDetail.getId() > 0){
            return surveyLocaleExploreDetailDao.update(surveyLocaleExploreDetail);
        }else{
            BaseAttachment baseAttachment = new BaseAttachment();
            baseAttachment.setTableId(0);
            baseAttachment.setFieldsName(SurveyLocaleExploreDetailDto.SURVEYPICTURE);
            List<BaseAttachment> baseAttachments = baseAttachmentDao.getAttachmentList(baseAttachment);
            BaseAttachment baseAttachment1 = new BaseAttachment();
            baseAttachment1.setTableId(0);
            baseAttachment1.setFieldsName(SurveyLocaleExploreDetailDto.SURVEYIMAGE);
            List<BaseAttachment> baseAttachments1 = baseAttachmentDao.getAttachmentList(baseAttachment1);
            //更新附件信息
            if(baseAttachments.size() != 0){
                baseAttachment = baseAttachments.get(0);
                Integer surveyPicture = baseAttachment.getId();
                surveyLocaleExploreDetail.setSurveyPicture(""+surveyPicture);


            }
            if(baseAttachments1.size() != 0){
                 baseAttachment1 = baseAttachments1.get(0);
                Integer surveyImage = baseAttachment1.getId();
                surveyLocaleExploreDetail.setSurveyImage(""+surveyImage);

            }
            surveyLocaleExploreDetail.setCreator(serviceComponent.getThisUser());
            boolean flag = surveyLocaleExploreDetailDao.save(surveyLocaleExploreDetail);
            baseAttachment.setTableId(surveyLocaleExploreDetail.getId());
            baseAttachment1.setTableId(surveyLocaleExploreDetail.getId());
            baseAttachmentDao.updateAttachment(baseAttachment);
            baseAttachmentDao.updateAttachment(baseAttachment1);
            return flag;

        }
    }

    public boolean delete(Integer id) throws BusinessException {
        if(id ==null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());;
        return surveyLocaleExploreDetailDao.delete(id);
    }
}
