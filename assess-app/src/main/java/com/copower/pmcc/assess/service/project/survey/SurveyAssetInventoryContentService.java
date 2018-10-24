package com.copower.pmcc.assess.service.project.survey;


import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryContentDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryContent;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyAssetInventoryContentVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SurveyAssetInventoryContentService {
    @Autowired
    private SurveyAssetInventoryContentDao surveyAssetInventoryContentDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    public BootstrapTableVo getList(Integer planDetailsId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SurveyAssetInventoryContent> surveyAssetInventoryContentsList = surveyAssetInventoryContentDao.getSurveyAssetInventoryContent(planDetailsId);
        List<SurveyAssetInventoryContentVo> surveyAssetInventoryContentVos = getVoList(surveyAssetInventoryContentsList);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(surveyAssetInventoryContentVos) ? new ArrayList<SurveyAssetInventoryContent>() : surveyAssetInventoryContentVos);
        return vo;
    }

    public List<SurveyAssetInventoryContent> getContentListByPlanDetailsId(Integer planDetailsId){
        List<SurveyAssetInventoryContent> surveyAssetInventoryContentsList = surveyAssetInventoryContentDao.getSurveyAssetInventoryContent(planDetailsId);
        return surveyAssetInventoryContentsList;
    }

    public List<SurveyAssetInventoryContentVo> getVoList(List<SurveyAssetInventoryContent> list) {
        if (CollectionUtils.isEmpty(list)) return null;
        return LangUtils.transform(list, p -> {
            SurveyAssetInventoryContentVo surveyAssetInventoryContentVo = new SurveyAssetInventoryContentVo();
            BeanUtils.copyProperties(p, surveyAssetInventoryContentVo);
            if (p.getInventoryContent() != null) {
                BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(Integer.valueOf(p.getInventoryContent()));
                if (baseDataDic != null)
                    surveyAssetInventoryContentVo.setInventoryContentName(baseDataDic.getName());
            }
            return surveyAssetInventoryContentVo;
        });
    }

    public void save(SurveyAssetInventoryContent surveyAssetInventoryContent) throws BusinessException {
        if(surveyAssetInventoryContent == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if(surveyAssetInventoryContent.getId() != null && surveyAssetInventoryContent.getId() > 0){
            surveyAssetInventoryContentDao.update(surveyAssetInventoryContent);
        }else{
            surveyAssetInventoryContent.setCreator(commonService.thisUserAccount());
            surveyAssetInventoryContentDao.save(surveyAssetInventoryContent);

            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetInventoryContent.class),surveyAssetInventoryContent.getId());
        }
    }

    public boolean delete(Integer id) throws BusinessException {
        if(id ==null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());;
        return surveyAssetInventoryContentDao.delete(id);
    }

}
