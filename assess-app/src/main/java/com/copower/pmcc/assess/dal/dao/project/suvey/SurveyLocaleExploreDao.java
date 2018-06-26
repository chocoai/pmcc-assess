package com.copower.pmcc.assess.dal.dao.project.suvey;

import com.copower.pmcc.assess.dal.entity.SurveyLocaleExplore;
import com.copower.pmcc.assess.dal.entity.SurveyLocaleExploreExample;
import com.copower.pmcc.assess.dal.mapper.SurveyLocaleExploreMapper;
import org.apache.commons.collections.CollectionUtils;
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

    public SurveyLocaleExplore getSurveyLocaleExplore(String processInsId) {
        SurveyLocaleExploreExample example = new SurveyLocaleExploreExample();
        example.createCriteria().andProcessInsIdEqualTo(processInsId);
        example.setOrderByClause(" id ASC");
        List<SurveyLocaleExplore> surveyLocaleExplores = surveyLocaleExploreMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(surveyLocaleExplores)){
            return surveyLocaleExplores.get(0);
        }
        return null;
    }

    public List<SurveyLocaleExplore> getByDeclareRecordId(Integer declareRecordId) {
        SurveyLocaleExploreExample example = new SurveyLocaleExploreExample();
        example.createCriteria().andDeclareRecordIdEqualTo(declareRecordId);
        return surveyLocaleExploreMapper.selectByExample(example);
    }
}
