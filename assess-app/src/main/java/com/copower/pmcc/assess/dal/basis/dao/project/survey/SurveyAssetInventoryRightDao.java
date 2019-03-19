package com.copower.pmcc.assess.dal.basis.dao.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRight;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRightExample;
import com.copower.pmcc.assess.dal.basis.mapper.SurveyAssetInventoryRightMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
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

    public boolean add(SurveyAssetInventoryRight surveyAssetInventoryRight) {
        int i = surveyAssetInventoryRightMapper.insertSelective(surveyAssetInventoryRight);
        return i > 0;
    }

    public boolean update(SurveyAssetInventoryRight surveyAssetInventoryRight) {
        int i = surveyAssetInventoryRightMapper.updateByPrimaryKeySelective(surveyAssetInventoryRight);
        return i > 0;
    }

    public SurveyAssetInventoryRight getSurveyAssetInventoryRightById(Integer id){
        return surveyAssetInventoryRightMapper.selectByPrimaryKey(id);
    }

    public List<SurveyAssetInventoryRight> getSurveyAssetInventoryRightList(SurveyAssetInventoryRight surveyAssetInventoryRight){
        SurveyAssetInventoryRightExample example = new SurveyAssetInventoryRightExample();
        example.setOrderByClause("id desc");
        MybatisUtils.convertObj2Example(surveyAssetInventoryRight, example);
        return surveyAssetInventoryRightMapper.selectByExample(example);
    }

    public void removeSurveyAssetInventoryRight(SurveyAssetInventoryRight surveyAssetInventoryRight){
        SurveyAssetInventoryRightExample example = new SurveyAssetInventoryRightExample();
        MybatisUtils.convertObj2Example(surveyAssetInventoryRight, example);
        surveyAssetInventoryRightMapper.deleteByExample(example);
    }


    public List<SurveyAssetInventoryRight> getListByPlanDetailsId(Integer planDetailsId) {
        SurveyAssetInventoryRightExample example = new SurveyAssetInventoryRightExample();
        example.createCriteria().andPlanDetailsIdEqualTo(planDetailsId);
        example.setOrderByClause("id desc");
        List<SurveyAssetInventoryRight> surveyAssetInventoryRights = surveyAssetInventoryRightMapper.selectByExample(example);
        return surveyAssetInventoryRights;
    }

    public List<SurveyAssetInventoryRight> getListByProjectId(Integer projectId,String certName) {
        SurveyAssetInventoryRightExample example = new SurveyAssetInventoryRightExample();
        SurveyAssetInventoryRightExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(projectId);
        if(StringUtils.isNotBlank(certName)){
            criteria.andCertNameLike(String.format("%%%s%%",certName));
        }
        example.setOrderByClause("id desc");
        List<SurveyAssetInventoryRight> surveyAssetInventoryRights = surveyAssetInventoryRightMapper.selectByExample(example);
        return surveyAssetInventoryRights;
    }

    public boolean delete(Integer id) {
        int i = surveyAssetInventoryRightMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public int deleteByPlanDetailsId(Integer planDetailsId) {
        SurveyAssetInventoryRightExample example = new SurveyAssetInventoryRightExample();
        example.createCriteria().andPlanDetailsIdEqualTo(planDetailsId);
        return surveyAssetInventoryRightMapper.deleteByExample(example);
    }
}
