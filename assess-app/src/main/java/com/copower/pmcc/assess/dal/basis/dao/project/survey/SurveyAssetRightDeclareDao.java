package com.copower.pmcc.assess.dal.basis.dao.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightDeclare;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightDeclareExample;
import com.copower.pmcc.assess.dal.basis.mapper.SurveyAssetRightDeclareMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2019-12-16.
 */
@Repository
public class SurveyAssetRightDeclareDao {


    @Autowired
    private SurveyAssetRightDeclareMapper mapper;

    public boolean updateSurveyAssetRightDeclare(SurveyAssetRightDeclare oo, boolean updateNull) {
        return updateNull ? mapper.updateByPrimaryKey(oo) == 1 : mapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public boolean deleteSurveyAssetRightDeclareById(Integer id){
        return mapper.deleteByPrimaryKey(id)==1 ;
    }

    public void deleteSurveyAssetRightDeclareByIds(List<Integer> ids){
        SurveyAssetRightDeclareExample example = new SurveyAssetRightDeclareExample();
        example.createCriteria().andIdIn(ids) ;
        mapper.deleteByExample(example);
    }

    public SurveyAssetRightDeclare getSurveyAssetRightDeclareById(Integer id){
        return mapper.selectByPrimaryKey(id) ;
    }

    public boolean saveSurveyAssetRightDeclare(SurveyAssetRightDeclare oo){
        return mapper.insertSelective(oo) ==1;
    }

    public List<SurveyAssetRightDeclare> getSurveyAssetRightDeclareByIds(List<Integer> ids){
        SurveyAssetRightDeclareExample example = new SurveyAssetRightDeclareExample();
        example.createCriteria().andIdIn(ids) ;
        return mapper.selectByExample(example) ;
    }

    public List<SurveyAssetRightDeclare> getSurveyAssetRightDeclareListByExample(SurveyAssetRightDeclare oo){
        SurveyAssetRightDeclareExample example = new SurveyAssetRightDeclareExample();
        MybatisUtils.convertObj2Example(oo, example);
        example.setOrderByClause("id");
        return mapper.selectByExample(example) ;
    }
    
}
