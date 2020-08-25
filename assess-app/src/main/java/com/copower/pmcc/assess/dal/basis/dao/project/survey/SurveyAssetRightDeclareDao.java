package com.copower.pmcc.assess.dal.basis.dao.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightDeclare;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightDeclareExample;
import com.copower.pmcc.assess.dal.basis.mapper.SurveyAssetRightDeclareMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2019-12-16.
 */
@Repository
public class SurveyAssetRightDeclareDao {


    @Autowired
    private SurveyAssetRightDeclareMapper mapper;

    public boolean updateSurveyAssetRightDeclare(SurveyAssetRightDeclare oo, boolean updateNull) {
        return updateNull ? mapper.updateByPrimaryKey(oo) == 1 : mapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public boolean deleteSurveyAssetRightDeclareById(Integer id) {
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public void deleteSurveyAssetRightDeclareByIds(List<Integer> ids) {
        SurveyAssetRightDeclareExample example = new SurveyAssetRightDeclareExample();
        example.createCriteria().andIdIn(ids);
        mapper.deleteByExample(example);
    }

    public SurveyAssetRightDeclare getSurveyAssetRightDeclareById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public boolean saveSurveyAssetRightDeclare(SurveyAssetRightDeclare oo) {
        return mapper.insertSelective(oo) == 1;
    }

    public List<SurveyAssetRightDeclare> getSurveyAssetRightDeclareByIds(List<Integer> ids) {
        SurveyAssetRightDeclareExample example = new SurveyAssetRightDeclareExample();
        example.createCriteria().andIdIn(ids);
        return mapper.selectByExample(example);
    }

    public List<SurveyAssetRightDeclare> getRightDeclareListByDeclareIds(List<Integer> declareIds) {
        SurveyAssetRightDeclareExample example = new SurveyAssetRightDeclareExample();
        SurveyAssetRightDeclareExample.Criteria criteria = example.createCriteria();
        if (CollectionUtils.isNotEmpty(declareIds)) {
            criteria.andDeclareIdIn(declareIds);
        }
        return mapper.selectByExample(example);
    }

    public List<SurveyAssetRightDeclare> getSurveyAssetRightDeclareList(SurveyAssetRightDeclare rightDeclare) {
        SurveyAssetRightDeclareExample example = new SurveyAssetRightDeclareExample();
        SurveyAssetRightDeclareExample.Criteria criteria = example.createCriteria();
        MybatisUtils.convertObj2Criteria(rightDeclare, criteria);
        return mapper.selectByExample(example);
    }

    public List<SurveyAssetRightDeclare> getSurveyAssetRightDeclareListByExample(SurveyAssetRightDeclare oo) {
        SurveyAssetRightDeclareExample example = new SurveyAssetRightDeclareExample();
        SurveyAssetRightDeclareExample.Criteria criteria = example.createCriteria();
        final String like = "%%";

        if (oo.getGroupId() != null) {
            criteria.andGroupIdEqualTo(oo.getGroupId());
        }

        if (oo.getPlanDetailsId() != null) {
            criteria.andPlanDetailsIdEqualTo(oo.getPlanDetailsId());
        }

        if (oo.getBuildingNumber() != null) {
            criteria.andBuildingNumberEqualTo(oo.getBuildingNumber());
        }

        if (StringUtils.isNotEmpty(oo.getDeclareName())) {
            criteria.andDeclareNameLike(String.join("", like, oo.getDeclareName(), like));
        }

        if (StringUtils.isNotEmpty(oo.getBuildingName())) {
            criteria.andBuildingNameLike(String.join("", like, oo.getBuildingName(), like));
        }
        if (StringUtils.isNotEmpty(oo.getCreator())) {
            criteria.andCreatorEqualTo(oo.getCreator());
        }

        if (StringUtils.isNotEmpty(oo.getSeat())) {
            criteria.andSeatLike(String.join("", like, oo.getSeat(), like));
        }

        if (StringUtils.isNotEmpty(oo.getOwnership())) {
            criteria.andOwnershipLike(String.join("", like, oo.getOwnership(), like));
        }

        if (StringUtils.isNotEmpty(oo.getUnitNumber())) {
            criteria.andUnitNumberLike(String.join("", like, oo.getUnitNumber(), like));
        }

        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }

}
