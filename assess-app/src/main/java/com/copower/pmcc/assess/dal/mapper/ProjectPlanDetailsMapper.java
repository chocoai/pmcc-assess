package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetailsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectPlanDetailsMapper {
    int countByExample(ProjectPlanDetailsExample example);

    int deleteByExample(ProjectPlanDetailsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectPlanDetails record);

    int insertSelective(ProjectPlanDetails record);

    List<ProjectPlanDetails> selectByExample(ProjectPlanDetailsExample example);

    ProjectPlanDetails selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectPlanDetails record, @Param("example") ProjectPlanDetailsExample example);

    int updateByExample(@Param("record") ProjectPlanDetails record, @Param("example") ProjectPlanDetailsExample example);

    int updateByPrimaryKeySelective(ProjectPlanDetails record);

    int updateByPrimaryKey(ProjectPlanDetails record);
}