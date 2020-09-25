package com.copower.pmcc.assess.dal.basis.dao.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryContent;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryContentExample;
import com.copower.pmcc.assess.dal.basis.mapper.SurveyAssetInventoryContentMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SurveyAssetInventoryContentDao {

    @Autowired
    private SurveyAssetInventoryContentMapper surveyAssetInventoryContentMapper;

    public List<SurveyAssetInventoryContent> getSurveyAssetInventoryContent(Integer planDetailsId) {
        SurveyAssetInventoryContentExample example = new SurveyAssetInventoryContentExample();
        if (planDetailsId != null) {
            example.createCriteria().andPlanDetailsIdEqualTo(planDetailsId);
        }
        example.setOrderByClause("id desc");
        List<SurveyAssetInventoryContent> surveyAssetInventoryContents = surveyAssetInventoryContentMapper.selectByExample(example);

        return surveyAssetInventoryContents;
    }

    public boolean update(SurveyAssetInventoryContent surveyAssetInventoryContent) {
        int i = surveyAssetInventoryContentMapper.updateByPrimaryKeySelective(surveyAssetInventoryContent);
        return i > 0;
    }

    public boolean add(SurveyAssetInventoryContent surveyAssetInventoryContent) {
        int i = surveyAssetInventoryContentMapper.insertSelective(surveyAssetInventoryContent);
        return i > 0;
    }

    public boolean delete(Integer id) {
        int i = surveyAssetInventoryContentMapper.deleteByPrimaryKey(id);
        return i > 0;
    }


    public List<SurveyAssetInventoryContent> getSurveyAssetInfoItemListByExample(SurveyAssetInventoryContent oo) {
        SurveyAssetInventoryContentExample example = new SurveyAssetInventoryContentExample();
        SurveyAssetInventoryContentExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        MybatisUtils.convertObj2Criteria(oo, criteria);
        example.setOrderByClause("id");
        return surveyAssetInventoryContentMapper.selectByExample(example);
    }

    public SurveyAssetInventoryContent getSingleObject(SurveyAssetInventoryContent surveyAssetInventoryContent) {
        SurveyAssetInventoryContentExample example = new SurveyAssetInventoryContentExample();
        MybatisUtils.convertObj2Example(surveyAssetInventoryContent, example);
        List<SurveyAssetInventoryContent> list = surveyAssetInventoryContentMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) return null;
        return list.get(0);
    }

    public List<SurveyAssetInventoryContent> getSurveyAssetInventoryContentListByMasterId(Integer masterId) {
        SurveyAssetInventoryContentExample example = new SurveyAssetInventoryContentExample();
        SurveyAssetInventoryContentExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        criteria.andMasterIdEqualTo(masterId);
        return surveyAssetInventoryContentMapper.selectByExample(example);
    }

    public List<SurveyAssetInventoryContent> getInventoryContentListByItemId(Integer itemId) {
        SurveyAssetInventoryContentExample example = new SurveyAssetInventoryContentExample();
        SurveyAssetInventoryContentExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        criteria.andInfoItemIdEqualTo(itemId);
        return surveyAssetInventoryContentMapper.selectByExample(example);
    }

    public Boolean deleteInventoryContentByItemId(Integer itemId) {
        SurveyAssetInventoryContentExample example = new SurveyAssetInventoryContentExample();
        SurveyAssetInventoryContentExample.Criteria criteria = example.createCriteria();
        criteria.andInfoItemIdEqualTo(itemId);
        return surveyAssetInventoryContentMapper.deleteByExample(example) > 0;
    }

}
