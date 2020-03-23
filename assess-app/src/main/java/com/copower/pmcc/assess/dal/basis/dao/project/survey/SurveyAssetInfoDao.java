package com.copower.pmcc.assess.dal.basis.dao.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInfo;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInfoExample;
import com.copower.pmcc.assess.dal.basis.mapper.SurveyAssetInfoMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2020-1-19.
 */
@Repository
public class SurveyAssetInfoDao {

    @Autowired
    private SurveyAssetInfoMapper mapper;

    public boolean updateSurveyAssetInfo(SurveyAssetInfo oo, boolean updateNull) {
        return updateNull ? mapper.updateByPrimaryKey(oo) == 1 : mapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public boolean deleteSurveyAssetInfoById(Integer id) {
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public void deleteSurveyAssetInfoByIds(List<Integer> ids) {
        SurveyAssetInfoExample example = new SurveyAssetInfoExample();
        example.createCriteria().andIdIn(ids);
        mapper.deleteByExample(example);
    }

    public SurveyAssetInfo getSurveyAssetInfoById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public boolean saveSurveyAssetInfo(SurveyAssetInfo oo) {
        return mapper.insertSelective(oo) == 1;
    }

    public List<SurveyAssetInfo> getSurveyAssetInfoByIds(List<Integer> ids) {
        SurveyAssetInfoExample example = new SurveyAssetInfoExample();
        example.createCriteria().andIdIn(ids);
        return mapper.selectByExample(example);
    }

    public List<SurveyAssetInfo> getSurveyAssetInfoListByExample(SurveyAssetInfo oo) {
        SurveyAssetInfoExample example = new SurveyAssetInfoExample();
        SurveyAssetInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        MybatisUtils.convertObj2Criteria(oo, criteria);
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }

}
