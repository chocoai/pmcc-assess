package com.copower.pmcc.assess.dal.basis.custom.mapper;

import com.copower.pmcc.assess.dal.basis.custom.entity.CustomProjectNumberRecord;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomSurveyExamineTask;
import com.copower.pmcc.assess.dal.basis.entity.ProjectNumberRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CustomProjectNumberRecordMapper {
    //查询任务
    List<CustomProjectNumberRecord> getAllProjectNumberRecord(@Param("projectName") String projectName, @Param("reportType") Integer reportType, @Param("numberValue") String numberValue);//查询
}
