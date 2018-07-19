package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SurveyExamineInfo;
import com.copower.pmcc.assess.dal.basis.entity.SurveyExamineInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SurveyExamineInfoMapper {
    int countByExample(SurveyExamineInfoExample example);

    int deleteByExample(SurveyExamineInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SurveyExamineInfo record);

    int insertSelective(SurveyExamineInfo record);

    List<SurveyExamineInfo> selectByExample(SurveyExamineInfoExample example);

    SurveyExamineInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SurveyExamineInfo record, @Param("example") SurveyExamineInfoExample example);

    int updateByExample(@Param("record") SurveyExamineInfo record, @Param("example") SurveyExamineInfoExample example);

    int updateByPrimaryKeySelective(SurveyExamineInfo record);

    int updateByPrimaryKey(SurveyExamineInfo record);
}