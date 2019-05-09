package com.copower.pmcc.assess.dal.basis.dao.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyExaminePurenessLand;
import com.copower.pmcc.assess.dal.basis.entity.SurveyExaminePurenessLandExample;
import com.copower.pmcc.assess.dal.basis.mapper.SurveyExaminePurenessLandMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zch
 * @date: 2019/5/9 16:14
 * @description:
 */
@Repository
public class SurveyExaminePurenessLandDao {

    @Autowired
    private SurveyExaminePurenessLandMapper mapper;

    public boolean saveSurveyExaminePurenessLand(SurveyExaminePurenessLand oo){
        return mapper.insertSelective(oo) == 1;
    }

    public boolean editSurveyExaminePurenessLand(SurveyExaminePurenessLand oo){
        return mapper.updateByPrimaryKeySelective(oo)==1;
    }

    public boolean deleteSurveyExaminePurenessLand(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public SurveyExaminePurenessLand getSurveyExaminePurenessLandById(Integer id){
        return mapper.selectByPrimaryKey(id);
    }

    public List<SurveyExaminePurenessLand> getSurveyExaminePurenessLandList(SurveyExaminePurenessLand oo){
        SurveyExaminePurenessLandExample example = getExample(oo);
        return mapper.selectByExample(example);
    }

    public void deleteSurveyExaminePurenessLandList(SurveyExaminePurenessLand oo){
        SurveyExaminePurenessLandExample example = getExample(oo);
        mapper.deleteByExample(example);
    }

    private SurveyExaminePurenessLandExample getExample(SurveyExaminePurenessLand oo){
        SurveyExaminePurenessLandExample example = new SurveyExaminePurenessLandExample();
        MybatisUtils.convertObj2Example(oo, example);
        SurveyExaminePurenessLandExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        return example;
    }
    
}
