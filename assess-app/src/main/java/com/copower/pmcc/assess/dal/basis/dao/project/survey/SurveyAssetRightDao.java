package com.copower.pmcc.assess.dal.basis.dao.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRight;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightExample;
import com.copower.pmcc.assess.dal.basis.mapper.SurveyAssetRightMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2019-12-16.
 */
@Repository
public class SurveyAssetRightDao {

    @Autowired
    private SurveyAssetRightMapper mapper;

    public boolean updateSurveyAssetRight(SurveyAssetRight oo, boolean updateNull) {
        return updateNull ? mapper.updateByPrimaryKey(oo) == 1 : mapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public boolean deleteSurveyAssetRightById(Integer id){
        return mapper.deleteByPrimaryKey(id)==1 ;
    }

    public void deleteSurveyAssetRightByIds(List<Integer> ids){
        SurveyAssetRightExample example = new SurveyAssetRightExample();
        example.createCriteria().andIdIn(ids) ;
        mapper.deleteByExample(example);
    }

    public SurveyAssetRight getSurveyAssetRightById(Integer id){
        return mapper.selectByPrimaryKey(id) ;
    }

    public boolean saveSurveyAssetRight(SurveyAssetRight oo){
        return mapper.insertSelective(oo) ==1;
    }

    public List<SurveyAssetRight> getSurveyAssetRightByIds(List<Integer> ids){
        SurveyAssetRightExample example = new SurveyAssetRightExample();
        example.createCriteria().andIdIn(ids) ;
        return mapper.selectByExample(example) ;
    }

    public List<SurveyAssetRight> getSurveyAssetRightListByExample(SurveyAssetRight oo){
        SurveyAssetRightExample example = new SurveyAssetRightExample();
        MybatisUtils.convertObj2Example(oo, example);
        example.setOrderByClause("id");
        return mapper.selectByExample(example) ;
    }
    
}
