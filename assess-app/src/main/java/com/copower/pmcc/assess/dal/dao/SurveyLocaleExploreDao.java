package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.SurveyLocaleExplore;
import com.copower.pmcc.assess.dal.entity.SurveyLocaleExploreExample;
import com.copower.pmcc.assess.dal.mapper.SurveyLocaleExploreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zly on 2018/5/15.
 */
@Repository
public class SurveyLocaleExploreDao {
    @Autowired
    private SurveyLocaleExploreMapper surveyLocaleExploreMapper;

    public boolean update(SurveyLocaleExplore surveyLocaleExploreDto) {
        int i = surveyLocaleExploreMapper.updateByPrimaryKeySelective(surveyLocaleExploreDto);
        return i > 0;
    }

    public boolean save(SurveyLocaleExplore surveyLocaleExploreDto) {
        int i = surveyLocaleExploreMapper.insertSelective(surveyLocaleExploreDto);
        return i > 0;
    }

    public boolean delete(Integer id) {
        int i = surveyLocaleExploreMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public List<SurveyLocaleExplore> getSurveyLocaleExplore(String processInsId) {
        SurveyLocaleExploreExample example = new SurveyLocaleExploreExample();
        if (processInsId != null) {
            example.createCriteria().andProcessInsIdEqualTo(processInsId);
        }
        example.setOrderByClause(" id ASC");
        List<SurveyLocaleExplore> surveyLocaleExplores = surveyLocaleExploreMapper.selectByExample(example);
        return surveyLocaleExplores;
    }
}
