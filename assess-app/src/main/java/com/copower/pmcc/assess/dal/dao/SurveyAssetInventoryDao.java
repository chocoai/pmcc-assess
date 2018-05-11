package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.mapper.SurveyAssetInventoryMapper;
import com.copower.pmcc.assess.dto.input.project.SurveyAssetInventoryDto;
import com.copower.pmcc.assess.dto.input.project.SurveyAssetTemplateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by zly on 2018/5/10.
 */
@Repository
public class SurveyAssetInventoryDao {

    @Autowired
    private SurveyAssetInventoryMapper surveyAssetInventoryMapper;

    public boolean update(SurveyAssetInventoryDto surveyAssetInventoryDto) {
        int i = surveyAssetInventoryMapper.updateByPrimaryKeySelective(surveyAssetInventoryDto);
        return i > 0;
    }

    public int save(SurveyAssetInventoryDto surveyAssetInventoryDto) {
        surveyAssetInventoryMapper.insertSelective(surveyAssetInventoryDto);
        return surveyAssetInventoryDto.getId();
    }
}
