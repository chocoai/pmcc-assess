package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.SurveyAssetOtherTemplate;
import com.copower.pmcc.assess.dal.entity.SurveyAssetOtherTemplateExample;
import com.copower.pmcc.assess.dal.mapper.SurveyAssetOtherTemplateMapper;
import com.copower.pmcc.assess.dto.input.project.SurveyAssetOtherTemplateDto;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zly on 2018/5/10.
 */
@Repository
public class SurveyAssetOtherTemplateDao {

    @Autowired
    private SurveyAssetOtherTemplateMapper surveyAssetOtherTemplateMapper;

    public boolean save(SurveyAssetOtherTemplateDto surveyAssetOtherTemplateDto) {
        int i = surveyAssetOtherTemplateMapper.insertSelective(surveyAssetOtherTemplateDto);
        return i > 0;
    }

    public SurveyAssetOtherTemplate getSurveyAssetOtherTemplateByPid(Integer pid) {
        SurveyAssetOtherTemplateExample example = new SurveyAssetOtherTemplateExample();
        example.createCriteria().andPidEqualTo(pid);
        List<SurveyAssetOtherTemplate> surveyAssetOtherTemplate = surveyAssetOtherTemplateMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(surveyAssetOtherTemplate)) {
            return surveyAssetOtherTemplate.get(0);
        }
        return null;
    }

    public boolean update(SurveyAssetOtherTemplateDto surveyAssetOtherTemplateDto) {
        int i = surveyAssetOtherTemplateMapper.updateByPrimaryKeySelective(surveyAssetOtherTemplateDto);
        return i > 0;
    }


    public List<SurveyAssetOtherTemplate> getSurveyAssetTemplate(Integer pid) {
        SurveyAssetOtherTemplateExample example = new SurveyAssetOtherTemplateExample();

        if (pid != null) {
            example.createCriteria().andPidEqualTo(pid);
        }

        example.setOrderByClause("sorting");
        List<SurveyAssetOtherTemplate> surveyAssetOtherTemplates = surveyAssetOtherTemplateMapper.selectByExample(example);

        return surveyAssetOtherTemplates;
    }

    public boolean delete(Integer id) {
        int i = surveyAssetOtherTemplateMapper.deleteByPrimaryKey(id);
        return i > 0;
    }
}
