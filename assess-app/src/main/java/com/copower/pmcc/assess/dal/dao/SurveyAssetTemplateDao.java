package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.SurveyAssetTemplate;
import com.copower.pmcc.assess.dal.entity.SurveyAssetTemplateExample;
import com.copower.pmcc.assess.dal.mapper.SurveyAssetTemplateMapper;
import com.copower.pmcc.assess.dto.input.project.SurveyAssetTemplateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SurveyAssetTemplateDao {

    @Autowired
    private SurveyAssetTemplateMapper surveyAssetTemplateMapper;

    public List<SurveyAssetTemplate> getSurveyAssetTemplate(Integer pid) {
        SurveyAssetTemplateExample example = new SurveyAssetTemplateExample();

        if (pid != null) {
            example.createCriteria().andPidEqualTo(pid);
        }

        example.setOrderByClause("id");
        List<SurveyAssetTemplate> surveyAssetTemplates = surveyAssetTemplateMapper.selectByExample(example);

        return surveyAssetTemplates;
    }

    public boolean update(SurveyAssetTemplate surveyAssetTemplate) {
        int i = surveyAssetTemplateMapper.updateByPrimaryKeySelective(surveyAssetTemplate);
        return i > 0;
    }

    public boolean save(SurveyAssetTemplate surveyAssetTemplate) {
        int i = surveyAssetTemplateMapper.insertSelective(surveyAssetTemplate);
        return i > 0;
    }

    public boolean delete(Integer id) {
        int i = surveyAssetTemplateMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public List<SurveyAssetTemplate> getSurveyAssetTemplateByPid(Integer pid) {
        SurveyAssetTemplateExample example = new SurveyAssetTemplateExample();
        example.createCriteria().andPidEqualTo(pid);
        return surveyAssetTemplateMapper.selectByExample(example);
    }

    public int deleteByPid(Integer pid) {
        SurveyAssetTemplateExample example = new SurveyAssetTemplateExample();
        example.createCriteria().andPidEqualTo(pid);
       return surveyAssetTemplateMapper.deleteByExample(example);
    }
}
