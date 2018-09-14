package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SurveySceneExplore;
import com.copower.pmcc.assess.dal.basis.entity.SurveySceneExploreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SurveySceneExploreMapper {
    int countByExample(SurveySceneExploreExample example);

    int deleteByExample(SurveySceneExploreExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SurveySceneExplore record);

    int insertSelective(SurveySceneExplore record);

    List<SurveySceneExplore> selectByExample(SurveySceneExploreExample example);

    SurveySceneExplore selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SurveySceneExplore record, @Param("example") SurveySceneExploreExample example);

    int updateByExample(@Param("record") SurveySceneExplore record, @Param("example") SurveySceneExploreExample example);

    int updateByPrimaryKeySelective(SurveySceneExplore record);

    int updateByPrimaryKey(SurveySceneExplore record);
}