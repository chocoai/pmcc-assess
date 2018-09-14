package com.copower.pmcc.assess.service.project.survey;


import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryRightDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRight;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyAssetInventoryRightVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
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

/**
 * Created by zly on 2018/5/11.
 */
@Service
public class SurveyAssetInventoryRightService {

    @Autowired
    private SurveyAssetInventoryRightDao surveyAssetInventoryRightDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    public BootstrapTableVo getList(Integer planDetailsId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SurveyAssetInventoryRight> surveyAssetInventoryRightList = surveyAssetInventoryRightDao.getSurveyAssetTemplate(planDetailsId);
        List<SurveyAssetInventoryRightVo> surveyAssetInventoryRightVoList = getVoList(surveyAssetInventoryRightList);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(surveyAssetInventoryRightVoList) ? new ArrayList<SurveyAssetInventoryRight>() : surveyAssetInventoryRightVoList);
        return vo;
    }

    public List<SurveyAssetInventoryRightVo> getVoList(List<SurveyAssetInventoryRight> list) {
        if (CollectionUtils.isEmpty(list)) return null;
        return LangUtils.transform(list, p -> {
            SurveyAssetInventoryRightVo surveyAssetInventoryRightVo = new SurveyAssetInventoryRightVo();
            BeanUtils.copyProperties(p, surveyAssetInventoryRightVo);
            if (p.getType() != null) {
                BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(Integer.valueOf(p.getType()));
                if (baseDataDic != null)
                    surveyAssetInventoryRightVo.setTypeName(baseDataDic.getName());
            }
            if (p.getCategory() != null) {
                BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(Integer.valueOf(p.getCategory()));
                if (baseDataDic != null)
                    surveyAssetInventoryRightVo.setCategoryName(baseDataDic.getName());
            }
            return surveyAssetInventoryRightVo;
        });
    }

    public boolean save(SurveyAssetInventoryRight surveyAssetInventoryRight) throws BusinessException {
        if(surveyAssetInventoryRight == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if(surveyAssetInventoryRight.getId() != null && surveyAssetInventoryRight.getId() > 0){
            return surveyAssetInventoryRightDao.update(surveyAssetInventoryRight);
        }else{
            surveyAssetInventoryRight.setCreator(commonService.thisUserAccount());
            return surveyAssetInventoryRightDao.save(surveyAssetInventoryRight);
        }
    }

    public boolean delete(Integer id) throws BusinessException {
        if(id ==null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());;
        return surveyAssetInventoryRightDao.delete(id);
    }
}
