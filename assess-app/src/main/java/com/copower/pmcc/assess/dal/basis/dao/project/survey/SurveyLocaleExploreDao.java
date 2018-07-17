package com.copower.pmcc.assess.dal.basis.dao.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyLocaleExplore;
import com.copower.pmcc.assess.dal.basis.entity.SurveyLocaleExploreExample;
import com.copower.pmcc.assess.dal.basis.mapper.SurveyLocaleExploreMapper;
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

    public boolean update(SurveyLocaleExplore surveyLocaleExplore) {
        int i = surveyLocaleExploreMapper.updateByPrimaryKeySelective(surveyLocaleExplore);
        return i > 0;
    }

    public boolean save(SurveyLocaleExplore surveyLocaleExplore) {
        int i = surveyLocaleExploreMapper.insertSelective(surveyLocaleExplore);
        return i > 0;
    }

    public boolean delete(Integer id) {
        int i = surveyLocaleExploreMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public SurveyLocaleExplore getExploreByPlanDetailsId(Integer planDetailsId){
        SurveyLocaleExploreExample example = new SurveyLocaleExploreExample();
        example.createCriteria().andPlanDetailsIdEqualTo(planDetailsId);
        example.setOrderByClause(" id ASC");
        List<SurveyLocaleExplore> surveyLocaleExplores = surveyLocaleExploreMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(surveyLocaleExplores)){
            return surveyLocaleExplores.get(0);
        }
        return null;
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
