package com.copower.pmcc.assess.dal.basis.custom.mapper;

import com.copower.pmcc.assess.dal.basis.custom.entity.CustomSurveyExamineTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CustomSurveyExamineTaskMapper {
    //查询任务
    List<CustomSurveyExamineTask> getCustomExamineTaskList(@Param("planDetailsId") Integer planDetailsId, @Param("userAccount") String userAccount);//查询
}
