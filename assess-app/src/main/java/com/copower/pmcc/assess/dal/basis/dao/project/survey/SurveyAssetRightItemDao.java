package com.copower.pmcc.assess.dal.basis.dao.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightItem;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightItemExample;
import com.copower.pmcc.assess.dal.basis.mapper.SurveyAssetRightItemMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2019-12-16.
 */
@Repository
public class SurveyAssetRightItemDao {

    @Autowired
    private SurveyAssetRightItemMapper mapper;

    public boolean updateSurveyAssetRightItem(SurveyAssetRightItem oo, boolean updateNull) {
        return updateNull ? mapper.updateByPrimaryKey(oo) == 1 : mapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public boolean deleteSurveyAssetRightItemById(Integer id){
        return mapper.deleteByPrimaryKey(id)==1 ;
    }

    public void deleteSurveyAssetRightItemByIds(List<Integer> ids){
        SurveyAssetRightItemExample example = new SurveyAssetRightItemExample();
        example.createCriteria().andIdIn(ids) ;
        mapper.deleteByExample(example);
    }

    public SurveyAssetRightItem getSurveyAssetRightItemById(Integer id){
        return mapper.selectByPrimaryKey(id) ;
    }

    public boolean saveSurveyAssetRightItem(SurveyAssetRightItem oo){
        return mapper.insertSelective(oo) ==1;
    }

    public List<SurveyAssetRightItem> getSurveyAssetRightItemByIds(List<Integer> ids){
        SurveyAssetRightItemExample example = new SurveyAssetRightItemExample();
        example.createCriteria().andIdIn(ids) ;
        return mapper.selectByExample(example) ;
    }

    public List<SurveyAssetRightItem> getSurveyAssetRightItemListByExample(SurveyAssetRightItem oo){
        SurveyAssetRightItemExample example = new SurveyAssetRightItemExample();
        MybatisUtils.convertObj2Example(oo, example);
        example.setOrderByClause("id");
        return mapper.selectByExample(example) ;
    }
    
}
