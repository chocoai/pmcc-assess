package com.copower.pmcc.assess.dal.dao.project.suvey;

import com.copower.pmcc.assess.dal.mapper.SurveyLocaleCorrelationCardMapper;
import com.copower.pmcc.assess.dto.input.project.SurveyLocaleCorrelationCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by zly on 2018/5/16.
 */
@Repository
public class SurveyLocaleCorrelationCardDao {
    @Autowired
    private SurveyLocaleCorrelationCardMapper surveyLocaleCorrelationCardMapper;

    public boolean save(SurveyLocaleCorrelationCardDto surveyLocaleCorrelationCardDto) {
        int i = surveyLocaleCorrelationCardMapper.insertSelective(surveyLocaleCorrelationCardDto);
        return i > 0;
    }

    public boolean update(SurveyLocaleCorrelationCardDto surveyLocaleCorrelationCardDto) {
        int i = surveyLocaleCorrelationCardMapper.updateByPrimaryKeySelective(surveyLocaleCorrelationCardDto);
        return i > 0;
    }
}
