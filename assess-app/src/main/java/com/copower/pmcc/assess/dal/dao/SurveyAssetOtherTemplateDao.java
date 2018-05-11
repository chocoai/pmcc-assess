package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.mapper.SurveyAssetInventoryMapper;
import com.copower.pmcc.assess.dal.mapper.SurveyAssetOtherTemplateMapper;
import com.copower.pmcc.assess.dto.input.project.SurveyAssetOtherTemplateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
