package com.copower.pmcc.assess.dal.basis.dao.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightGroup;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightGroupExample;
import com.copower.pmcc.assess.dal.basis.mapper.SurveyAssetRightGroupMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2019-12-16.
 */
@Repository
public class SurveyAssetRightGroupDao {

    @Autowired
    private SurveyAssetRightGroupMapper mapper;

    public boolean updateSurveyAssetRightGroup(SurveyAssetRightGroup oo, boolean updateNull) {
        return updateNull ? mapper.updateByPrimaryKey(oo) == 1 : mapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public boolean deleteSurveyAssetRightGroupById(Integer id){
        return mapper.deleteByPrimaryKey(id)==1 ;
    }

    public void deleteSurveyAssetRightGroupByIds(List<Integer> ids){
        SurveyAssetRightGroupExample example = new SurveyAssetRightGroupExample();
        example.createCriteria().andIdIn(ids) ;
        mapper.deleteByExample(example);
    }

    public SurveyAssetRightGroup getSurveyAssetRightGroupById(Integer id){
        return mapper.selectByPrimaryKey(id) ;
    }

    public boolean saveSurveyAssetRightGroup(SurveyAssetRightGroup oo){
        return mapper.insertSelective(oo) ==1;
    }

    public List<SurveyAssetRightGroup> getSurveyAssetRightGroupByIds(List<Integer> ids){
        SurveyAssetRightGroupExample example = new SurveyAssetRightGroupExample();
        example.createCriteria().andIdIn(ids) ;
        return mapper.selectByExample(example) ;
    }

    public List<SurveyAssetRightGroup> getSurveyAssetRightGroupListByExample(SurveyAssetRightGroup oo){
        SurveyAssetRightGroupExample example = new SurveyAssetRightGroupExample();
        MybatisUtils.convertObj2Example(oo, example);
        example.setOrderByClause("id");
        return mapper.selectByExample(example) ;
    }
    
}
