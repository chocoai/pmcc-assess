package com.copower.pmcc.assess.dal.basis.dao.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyExamineItem;
import com.copower.pmcc.assess.dal.basis.entity.SurveyExamineItemExample;
import com.copower.pmcc.assess.dal.basis.mapper.SurveyExamineItemMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zly on 2018/5/15.
 */
@Repository
public class SurveyExamineItemDao {
    @Autowired
    private SurveyExamineItemMapper surveyExamineItemMapper;

    public boolean update(SurveyExamineItem surveyExamineItem) {
        int i = surveyExamineItemMapper.updateByPrimaryKeySelective(surveyExamineItem);
        return i > 0;
    }

    public boolean save(SurveyExamineItem surveyExamineItem) {
        int i = surveyExamineItemMapper.insertSelective(surveyExamineItem);
        return i > 0;
    }

    public boolean delete(Integer id) {
        int i = surveyExamineItemMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public SurveyExamineItem getExamineItemByPlanDetailsId(Integer planDetailsId){
        SurveyExamineItemExample example = new SurveyExamineItemExample();
        example.createCriteria().andPlanDetailsIdEqualTo(planDetailsId);
        example.setOrderByClause(" id ASC");
        List<SurveyExamineItem> surveyExamineItems = surveyExamineItemMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(surveyExamineItems)){
            return surveyExamineItems.get(0);
        }
        return null;
    }

    public SurveyExamineItem getSurveyExamineItem(String processInsId) {
        SurveyExamineItemExample example = new SurveyExamineItemExample();
        example.createCriteria().andProcessInsIdEqualTo(processInsId);
        example.setOrderByClause(" id ASC");
        List<SurveyExamineItem> surveyExamineItems = surveyExamineItemMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(surveyExamineItems)){
            return surveyExamineItems.get(0);
        }
        return null;
    }

    public List<SurveyExamineItem> getExamineItemByDeclareId(Integer declareRecordId) {
        SurveyExamineItemExample example = new SurveyExamineItemExample();
        example.createCriteria().andDeclareRecordIdEqualTo(declareRecordId);
        return surveyExamineItemMapper.selectByExample(example);
    }
}
