package com.copower.pmcc.assess.dal.basis.dao.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInfoItem;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInfoItemExample;
import com.copower.pmcc.assess.dal.basis.mapper.SurveyAssetInfoItemMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2020-1-19.
 */
@Repository
public class SurveyAssetInfoItemDao {

    @Autowired
    private SurveyAssetInfoItemMapper mapper;

    public boolean updateSurveyAssetInfoItem(SurveyAssetInfoItem oo, boolean updateNull) {
        return updateNull ? mapper.updateByPrimaryKey(oo) == 1 : mapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public boolean deleteSurveyAssetInfoItemById(Integer id) {
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public void deleteSurveyAssetInfoItemByIds(List<Integer> ids) {
        SurveyAssetInfoItemExample example = new SurveyAssetInfoItemExample();
        example.createCriteria().andIdIn(ids);
        mapper.deleteByExample(example);
    }

    public SurveyAssetInfoItem getSurveyAssetInfoItemByDeclareId(Integer declareId) {
        SurveyAssetInfoItemExample example = new SurveyAssetInfoItemExample();
        example.createCriteria().andDeclareIdEqualTo(declareId);
        List<SurveyAssetInfoItem> surveyAssetInfoItems = mapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(surveyAssetInfoItems)) {
            return surveyAssetInfoItems.get(0);
        }
        return null;
    }

    public SurveyAssetInfoItem getSurveyAssetInfoItemById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public boolean addSurveyAssetInfoItem(SurveyAssetInfoItem oo) {
        return mapper.insertSelective(oo) == 1;
    }

    public List<SurveyAssetInfoItem> getSurveyAssetInfoItemByIds(List<Integer> ids) {
        SurveyAssetInfoItemExample example = new SurveyAssetInfoItemExample();
        example.createCriteria().andIdIn(ids);
        return mapper.selectByExample(example);
    }

    public List<SurveyAssetInfoItem> getSurveyAssetInfoItemListByExample(SurveyAssetInfoItem oo) {
        SurveyAssetInfoItemExample example = new SurveyAssetInfoItemExample();
        SurveyAssetInfoItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        MybatisUtils.convertObj2Criteria(oo, criteria);
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }

    public List<SurveyAssetInfoItem> getSurveyAssetInfoItemLikeList(SurveyAssetInfoItem oo,List<Integer> declareIds) {
        SurveyAssetInfoItemExample example = new SurveyAssetInfoItemExample();
        SurveyAssetInfoItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (StringUtils.isNotBlank(oo.getName())) {
            criteria.andNameLike(String.format("%%%s%%", oo.getName()));
        }
        if (StringUtils.isNotBlank(oo.getGroupName())) {
            criteria.andGroupNameLike(String.format("%%%s%%", oo.getGroupName()));
        }
        if (oo.getGroupId() != null) {
            criteria.andGroupIdEqualTo(oo.getGroupId());
        }
        if (oo.getDeclareId() != null) {
            criteria.andDeclareIdEqualTo(oo.getDeclareId());
        }
        if (oo.getAssetInfoId() != null) {
            criteria.andAssetInfoIdEqualTo(oo.getAssetInfoId());
        }
        if (StringUtils.isNotBlank(oo.getCreator())) {
            criteria.andCreatorEqualTo(oo.getCreator());
        }
        if (StringUtils.isNotBlank(oo.getStatus())) {
            criteria.andStatusEqualTo(oo.getStatus());
        }
        if(CollectionUtils.isNotEmpty(declareIds)){
            criteria.andDeclareIdIn(declareIds);
        }
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }

    public List<SurveyAssetInfoItem> getSurveyAssetInfoItemListLikeQuery(SurveyAssetInfoItem oo) {
        SurveyAssetInfoItemExample example = new SurveyAssetInfoItemExample();
        SurveyAssetInfoItemExample.Criteria criteria = example.createCriteria();

        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }


}
