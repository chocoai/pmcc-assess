package com.copower.pmcc.assess.dal.basis.dao.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInfoGroup;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInfoGroupExample;
import com.copower.pmcc.assess.dal.basis.mapper.SurveyAssetInfoGroupMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2020-1-19.
 */
@Repository
public class SurveyAssetInfoGroupDao {

    @Autowired
    private SurveyAssetInfoGroupMapper mapper;

    public boolean updateSurveyAssetInfoGroup(SurveyAssetInfoGroup oo, boolean updateNull) {
        return updateNull ? mapper.updateByPrimaryKey(oo) == 1 : mapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public boolean deleteSurveyAssetInfoGroupById(Integer id) {
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public void deleteSurveyAssetInfoGroupByIds(List<Integer> ids) {
        SurveyAssetInfoGroupExample example = new SurveyAssetInfoGroupExample();
        example.createCriteria().andIdIn(ids);
        mapper.deleteByExample(example);
    }

    public SurveyAssetInfoGroup getSurveyAssetInfoGroupById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public boolean addSurveyAssetInfoGroup(SurveyAssetInfoGroup oo) {
        return mapper.insertSelective(oo) == 1;
    }

    public List<SurveyAssetInfoGroup> getSurveyAssetInfoGroupByIds(List<Integer> ids) {
        SurveyAssetInfoGroupExample example = new SurveyAssetInfoGroupExample();
        example.createCriteria().andIdIn(ids);
        return mapper.selectByExample(example);
    }

    public List<SurveyAssetInfoGroup> getSurveyAssetInfoGroupListByExample(SurveyAssetInfoGroup oo) {
        SurveyAssetInfoGroupExample example = new SurveyAssetInfoGroupExample();
        SurveyAssetInfoGroupExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        MybatisUtils.convertObj2Criteria(oo, criteria);
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }


    public List<SurveyAssetInfoGroup> getSurveyAssetInfoItemListLikeQuery(SurveyAssetInfoGroup oo) {
        SurveyAssetInfoGroupExample example = new SurveyAssetInfoGroupExample();
        SurveyAssetInfoGroupExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(oo.getGroupName())){
            criteria.andGroupNameLike(String.format("%%%s%%", oo.getGroupName()));
        }
        if (oo.getAssetInfoId() != null){
            criteria.andAssetInfoIdEqualTo(oo.getAssetInfoId()) ;
        }
        if (oo.getInventoryId() != null){
            criteria.andInventoryIdEqualTo(oo.getInventoryId()) ;
        }
        if (StringUtils.isNotBlank(oo.getCreator())){
            criteria.andCreatorEqualTo(oo.getCreator()) ;
        }
        if (StringUtils.isNotBlank(oo.getStatus())) {
            criteria.andStatusEqualTo(oo.getStatus());
        }
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }


}
