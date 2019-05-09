package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SurveyExaminePurenessLand;
import com.copower.pmcc.assess.dal.basis.entity.SurveyExaminePurenessLandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SurveyExaminePurenessLandMapper {
    int countByExample(SurveyExaminePurenessLandExample example);

    int deleteByExample(SurveyExaminePurenessLandExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SurveyExaminePurenessLand record);

    int insertSelective(SurveyExaminePurenessLand record);

    List<SurveyExaminePurenessLand> selectByExample(SurveyExaminePurenessLandExample example);

    SurveyExaminePurenessLand selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SurveyExaminePurenessLand record, @Param("example") SurveyExaminePurenessLandExample example);

    int updateByExample(@Param("record") SurveyExaminePurenessLand record, @Param("example") SurveyExaminePurenessLandExample example);

    int updateByPrimaryKeySelective(SurveyExaminePurenessLand record);

    int updateByPrimaryKey(SurveyExaminePurenessLand record);
}