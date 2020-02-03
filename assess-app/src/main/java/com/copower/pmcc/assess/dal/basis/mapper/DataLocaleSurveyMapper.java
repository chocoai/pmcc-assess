package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataLocaleSurvey;
import com.copower.pmcc.assess.dal.basis.entity.DataLocaleSurveyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataLocaleSurveyMapper {
    int countByExample(DataLocaleSurveyExample example);

    int deleteByExample(DataLocaleSurveyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataLocaleSurvey record);

    int insertSelective(DataLocaleSurvey record);

    List<DataLocaleSurvey> selectByExample(DataLocaleSurveyExample example);

    DataLocaleSurvey selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataLocaleSurvey record, @Param("example") DataLocaleSurveyExample example);

    int updateByExample(@Param("record") DataLocaleSurvey record, @Param("example") DataLocaleSurveyExample example);

    int updateByPrimaryKeySelective(DataLocaleSurvey record);

    int updateByPrimaryKey(DataLocaleSurvey record);
}