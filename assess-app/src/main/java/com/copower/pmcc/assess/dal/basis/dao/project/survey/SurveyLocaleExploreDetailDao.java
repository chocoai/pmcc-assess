package com.copower.pmcc.assess.dal.basis.dao.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyLocaleExploreDetail;
import com.copower.pmcc.assess.dal.basis.entity.SurveyLocaleExploreDetailExample;
import com.copower.pmcc.assess.dal.basis.mapper.SurveyLocaleExploreDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zly on 2018/5/16.
 */
@Repository
public class SurveyLocaleExploreDetailDao {
    @Autowired
    private SurveyLocaleExploreDetailMapper surveyLocaleExploreDetailMapper;

    public SurveyLocaleExploreDetail getSingelDetail(Integer id){
        return surveyLocaleExploreDetailMapper.selectByPrimaryKey(id);
    }

    public List<SurveyLocaleExploreDetail> getSurveyLocaleExploreDetail(Integer planDetailId) {
        SurveyLocaleExploreDetailExample example = new SurveyLocaleExploreDetailExample();
        if (planDetailId != null) {
            example.createCriteria().andPlanDetailsIdEqualTo(planDetailId);
        }
        example.setOrderByClause(" id ASC");
        List<SurveyLocaleExploreDetail> surveyLocaleExploreDetails = surveyLocaleExploreDetailMapper.selectByExample(example);
        return surveyLocaleExploreDetails;
    }

    public boolean update(SurveyLocaleExploreDetail surveyLocaleExploreDetail) {
        int i = surveyLocaleExploreDetailMapper.updateByPrimaryKeySelective(surveyLocaleExploreDetail);
        return i > 0;
    }

    public boolean save(SurveyLocaleExploreDetail surveyLocaleExploreDetail) {
        int i = surveyLocaleExploreDetailMapper.insertSelective(surveyLocaleExploreDetail);
        return i > 0;
    }

    public boolean delete(Integer id) {
        int i = surveyLocaleExploreDetailMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
