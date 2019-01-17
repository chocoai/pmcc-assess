package com.copower.pmcc.assess.dal.basis.dao.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyExamineInfo;
import com.copower.pmcc.assess.dal.basis.entity.SurveyExamineInfoExample;
import com.copower.pmcc.assess.dal.basis.mapper.SurveyExamineInfoMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zly on 2018/5/15.
 */
@Repository
public class SurveyExamineInfoDao {
    @Autowired
    private SurveyExamineInfoMapper surveyExamineInfoMapper;

    public boolean update(SurveyExamineInfo surveyExamineInfo) {
        int i = surveyExamineInfoMapper.updateByPrimaryKeySelective(surveyExamineInfo);
        return i > 0;
    }

    public boolean save(SurveyExamineInfo surveyExamineInfo) {
        int i = surveyExamineInfoMapper.insertSelective(surveyExamineInfo);
        return i > 0;
    }

    public boolean delete(Integer id) {
        int i = surveyExamineInfoMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public SurveyExamineInfo getExamineInfoByPlanDetailsId(Integer planDetailsId){
        SurveyExamineInfoExample example = new SurveyExamineInfoExample();
        example.createCriteria().andPlanDetailsIdEqualTo(planDetailsId);
        example.setOrderByClause(" id ASC");
        List<SurveyExamineInfo> surveyExamineInfos = surveyExamineInfoMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(surveyExamineInfos)){
            return surveyExamineInfos.get(0);
        }
        return null;
    }

    public List<SurveyExamineInfo> getExamineInfoByDeclareId(Integer declareRecordId) {
        SurveyExamineInfoExample example = new SurveyExamineInfoExample();
        example.createCriteria().andDeclareRecordIdEqualTo(declareRecordId);
        return surveyExamineInfoMapper.selectByExample(example);
    }
}
