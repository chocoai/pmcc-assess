package com.copower.pmcc.assess.dal.basis.dao.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventory;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryExample;
import com.copower.pmcc.assess.dal.basis.mapper.SurveyAssetInventoryMapper;
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

    public boolean update(SurveyAssetInventory surveyAssetInventory) {
        int i = surveyAssetInventoryMapper.updateByPrimaryKeySelective(surveyAssetInventory);
        return i > 0;
    }

    public int save(SurveyAssetInventory surveyAssetInventory) {
        surveyAssetInventoryMapper.insertSelective(surveyAssetInventory);
        return surveyAssetInventory.getId();
    }

    public SurveyAssetInventory getDataByProcessInsId(String processInsId) {
        SurveyAssetInventoryExample example = new SurveyAssetInventoryExample();
        example.createCriteria().andProcessInsIdEqualTo(processInsId);
        List<SurveyAssetInventory> surveyAssetInventory = surveyAssetInventoryMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(surveyAssetInventory)) {
            return surveyAssetInventory.get(0);
        }
        return null;
    }

    public SurveyAssetInventory getDataByPlanDetailsId(Integer planDetailsId) {
        SurveyAssetInventoryExample example = new SurveyAssetInventoryExample();
        example.createCriteria().andPlanDetailIdEqualTo(planDetailsId);
        List<SurveyAssetInventory> surveyAssetInventory = surveyAssetInventoryMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(surveyAssetInventory)) {
            return surveyAssetInventory.get(0);
        }
        return null;
    }

    public SurveyAssetInventory getDataByDeclareId(Integer declareId) {
        SurveyAssetInventoryExample example = new SurveyAssetInventoryExample();
        example.createCriteria().andDeclareRecordIdEqualTo(declareId);
        List<SurveyAssetInventory> surveyAssetInventory = surveyAssetInventoryMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(surveyAssetInventory)) {
            return surveyAssetInventory.get(0);
        }
        return null;
    }
}
