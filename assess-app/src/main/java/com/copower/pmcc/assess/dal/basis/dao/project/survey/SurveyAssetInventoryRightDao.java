package com.copower.pmcc.assess.dal.basis.dao.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRight;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRightExample;
import com.copower.pmcc.assess.dal.basis.mapper.SurveyAssetInventoryRightMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zly on 2018/5/10.
 */
@Repository
public class SurveyAssetInventoryRightDao {

    @Autowired
    private SurveyAssetInventoryRightMapper surveyAssetInventoryRightMapper;

    public boolean save(SurveyAssetInventoryRight surveyAssetInventoryRight) {
        int i = surveyAssetInventoryRightMapper.insertSelective(surveyAssetInventoryRight);
        return i > 0;
    }



    public boolean update(SurveyAssetInventoryRight surveyAssetInventoryRight) {
        int i = surveyAssetInventoryRightMapper.updateByPrimaryKeySelective(surveyAssetInventoryRight);
        return i > 0;
    }


    public List<SurveyAssetInventoryRight> getListByPlanDetailsId(Integer planDetailsId) {
        SurveyAssetInventoryRightExample example = new SurveyAssetInventoryRightExample();
        example.createCriteria().andPlanDetailsIdEqualTo(planDetailsId);
        example.setOrderByClause("id desc");
        List<SurveyAssetInventoryRight> surveyAssetInventoryRights = surveyAssetInventoryRightMapper.selectByExample(example);
        return surveyAssetInventoryRights;
    }

    public List<SurveyAssetInventoryRight> getListByProjectId(Integer projectId) {
        SurveyAssetInventoryRightExample example = new SurveyAssetInventoryRightExample();
        example.createCriteria().andProjectIdEqualTo(projectId);
        example.setOrderByClause("id desc");
        List<SurveyAssetInventoryRight> surveyAssetInventoryRights = surveyAssetInventoryRightMapper.selectByExample(example);
        return surveyAssetInventoryRights;
    }

    public boolean delete(Integer id) {
        int i = surveyAssetInventoryRightMapper.deleteByPrimaryKey(id);
        return i > 0;
    }
}
