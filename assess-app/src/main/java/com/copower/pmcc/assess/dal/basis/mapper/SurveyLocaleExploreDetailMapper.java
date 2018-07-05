package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SurveyLocaleExploreDetail;
import com.copower.pmcc.assess.dal.basis.entity.SurveyLocaleExploreDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SurveyLocaleExploreDetailMapper {
    int countByExample(SurveyLocaleExploreDetailExample example);

    int deleteByExample(SurveyLocaleExploreDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SurveyLocaleExploreDetail record);

    int insertSelective(SurveyLocaleExploreDetail record);

    List<SurveyLocaleExploreDetail> selectByExample(SurveyLocaleExploreDetailExample example);

    SurveyLocaleExploreDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SurveyLocaleExploreDetail record, @Param("example") SurveyLocaleExploreDetailExample example);

    int updateByExample(@Param("record") SurveyLocaleExploreDetail record, @Param("example") SurveyLocaleExploreDetailExample example);

    int updateByPrimaryKeySelective(SurveyLocaleExploreDetail record);

    int updateByPrimaryKey(SurveyLocaleExploreDetail record);
}