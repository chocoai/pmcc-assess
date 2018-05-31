package com.copower.pmcc.assess.dal.dao.base;

import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dal.mapper.BaseReportBatchConclusionMapper;
import com.copower.pmcc.assess.dal.mapper.BaseReportBatchSurveyMapper;
import com.copower.pmcc.assess.dal.mapper.BaseReportBatchTemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/5/28
 * @time: 9:44
 */
@Repository
public class BaseReportBatchDao {
    @Autowired
    private BaseReportBatchConclusionMapper conclusionMapper;
    @Autowired
    private BaseReportBatchTemplateMapper templateMapper;
    @Autowired
    private BaseReportBatchSurveyMapper surveyMapper;

    //BaseReportBatchConclusion
    public List<BaseReportBatchConclusion> getBaseReportBatchConclusion() {
        BaseReportBatchConclusionExample example = new BaseReportBatchConclusionExample();
        return conclusionMapper.selectByExample(example);
    }

    public void addBaseReportBatchConclusion(BaseReportBatchConclusion baseReportBatchConclusion) {
        conclusionMapper.insertSelective(baseReportBatchConclusion);
    }

    public void editBaseReportBatchConclusion(BaseReportBatchConclusion baseReportBatchConclusion) {
        conclusionMapper.updateByPrimaryKeySelective(baseReportBatchConclusion);
    }

    //BaseReportBatchSurvey
    public List<BaseReportBatchSurvey> getBaseReportBatchSurvey() {
        BaseReportBatchSurveyExample example = new BaseReportBatchSurveyExample();
        return surveyMapper.selectByExample(example);
    }

    public void addBaseReportBatchSurvey(BaseReportBatchSurvey baseReportBatchSurvey) {
        surveyMapper.insertSelective(baseReportBatchSurvey);
    }

    public void editBaseReportBatchSurvey(BaseReportBatchSurvey baseReportBatchSurvey) {
        surveyMapper.updateByPrimaryKeySelective(baseReportBatchSurvey);
    }

    //BaseReportBatchTemplate
    public List<BaseReportBatchTemplate> getBaseReportBatchTemplate() {
        BaseReportBatchTemplateExample example = new BaseReportBatchTemplateExample();
        return templateMapper.selectByExample(example);
    }

    public void addBaseReportBatchTemplate(BaseReportBatchTemplate baseReportBatchTemplate) {
        templateMapper.insertSelective(baseReportBatchTemplate);
    }

    public void editBaseReportBatchTemplate(BaseReportBatchTemplate baseReportBatchTemplate) {
        templateMapper.updateByPrimaryKeySelective(baseReportBatchTemplate);
    }
}
