package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.SurveyLocaleExploreDao;
import com.copower.pmcc.assess.dal.entity.DataPriceTimepointDescription;
import com.copower.pmcc.assess.dal.entity.SurveyLocaleExplore;
import com.copower.pmcc.assess.dto.input.project.SurveyLocaleExploreDto;
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
 * Created by zly on 2018/5/15.
 */
@Service
public class SurveyLocaleExploreService {

    @Autowired
    private SurveyLocaleExploreDao surveyLocaleExploreDao;
    @Autowired
    private ServiceComponent serviceComponent;

    public boolean save(SurveyLocaleExploreDto surveyLocaleExploreDto, Integer pid) throws BusinessException {
        if(surveyLocaleExploreDto == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if(surveyLocaleExploreDto.getId() != null && surveyLocaleExploreDto.getId() > 0){
            return surveyLocaleExploreDao.update(surveyLocaleExploreDto);
        }else{
            surveyLocaleExploreDto.setCreator(serviceComponent.getThisUser());
            return surveyLocaleExploreDao.save(surveyLocaleExploreDto);
        }
    }

    public boolean delete(Integer id) throws BusinessException {
        if(id ==null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());;
        return surveyLocaleExploreDao.delete(id);

    }

    public BootstrapTableVo getList(Integer pid) {
            BootstrapTableVo vo = new BootstrapTableVo();
            RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
            Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
            List<SurveyLocaleExplore> surveyLocaleExploreList = surveyLocaleExploreDao.getSurveyLocaleExplore(serviceComponent.getThisUser());
            vo.setTotal(page.getTotal());
            vo.setRows(CollectionUtils.isEmpty(surveyLocaleExploreList) ? new ArrayList<DataPriceTimepointDescription>() : surveyLocaleExploreList);
            return vo;

    }
}
