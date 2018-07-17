package com.copower.pmcc.assess.dal.basis.dao.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventory;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryExample;
import com.copower.pmcc.assess.dal.basis.mapper.SurveyAssetInventoryMapper;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyAssetInventoryDto;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public SurveyAssetInventory getSurveyAssetInventoryByProcessInsId(String processInsId) {
        SurveyAssetInventoryExample example = new SurveyAssetInventoryExample();
        example.createCriteria().andProcessInsIdEqualTo(processInsId);
        List<SurveyAssetInventory> surveyAssetInventory = surveyAssetInventoryMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(surveyAssetInventory)) {
            return surveyAssetInventory.get(0);
        }
        return null;
    }
}
