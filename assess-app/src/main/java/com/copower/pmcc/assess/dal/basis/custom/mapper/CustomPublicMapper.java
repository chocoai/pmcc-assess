package com.copower.pmcc.assess.dal.basis.custom.mapper;

import com.copower.pmcc.assess.dal.basis.custom.entity.CustomSurveyEstateTimes;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomSurveyExamineTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CustomPublicMapper {
    //查询楼盘被查勘的次数
    List<CustomSurveyEstateTimes> getSurveyEstateTimesList(@Param("userAccount") String userAccount, @Param("projectPhaseId") Integer projectPhaseId);//查询
}
