package com.copower.pmcc.assess.dal.basis.dao.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveySceneExplore;
import com.copower.pmcc.assess.dal.basis.entity.SurveySceneExploreExample;
import com.copower.pmcc.assess.dal.basis.mapper.SurveySceneExploreMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zly on 2018/5/15.
 */
@Repository
public class SurveySceneExploreDao {
    @Autowired
    private SurveySceneExploreMapper surveySceneExploreMapper;

    public boolean updateSurveySceneExplore(SurveySceneExplore surveySceneExplore) {
        int i = surveySceneExploreMapper.updateByPrimaryKeySelective(surveySceneExplore);
        return i > 0;
    }

    public boolean addSurveySceneExplore(SurveySceneExplore surveySceneExplore) {
        int i = surveySceneExploreMapper.insertSelective(surveySceneExplore);
        return i > 0;
    }

    public boolean deleteSurveySceneExplore(Integer id) {
        int i = surveySceneExploreMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public SurveySceneExplore getSurveySceneExplore(SurveySceneExplore surveySceneExplore) {
        SurveySceneExploreExample example = new SurveySceneExploreExample();
        MybatisUtils.convertObj2Example(surveySceneExplore,example);
        List<SurveySceneExplore> surveySceneExplores = surveySceneExploreMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(surveySceneExplores)){
            return surveySceneExplores.get(0);
        }
        return null;
    }

    public SurveySceneExplore getSurveySceneExplore(String processInsId) {
        SurveySceneExploreExample example = new SurveySceneExploreExample();
        example.createCriteria().andProcessInsIdEqualTo(processInsId);
        List<SurveySceneExplore> surveySceneExplores = surveySceneExploreMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(surveySceneExplores)){
            return surveySceneExplores.get(0);
        }
        return null;
    }

    public SurveySceneExplore getSurveySceneExploreById(Integer id) {
        return surveySceneExploreMapper.selectByPrimaryKey(id);
    }

    //获取未完成数据
    public List<SurveySceneExplore> getUnfinishedData() {
        SurveySceneExploreExample example = new SurveySceneExploreExample();
        SurveySceneExploreExample.Criteria criteria = example.createCriteria();
        criteria.andBatchApplyIdIsNotNull();
        criteria.andPlanDetailsIdIsNull();
        return surveySceneExploreMapper.selectByExample(example);
    }
}
