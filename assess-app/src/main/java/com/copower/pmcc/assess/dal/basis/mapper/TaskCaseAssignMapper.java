package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.TaskCaseAssign;
import com.copower.pmcc.assess.dal.basis.entity.TaskCaseAssignExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskCaseAssignMapper {
    int countByExample(TaskCaseAssignExample example);

    int deleteByExample(TaskCaseAssignExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaskCaseAssign record);

    int insertSelective(TaskCaseAssign record);

    List<TaskCaseAssign> selectByExample(TaskCaseAssignExample example);

    TaskCaseAssign selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaskCaseAssign record, @Param("example") TaskCaseAssignExample example);

    int updateByExample(@Param("record") TaskCaseAssign record, @Param("example") TaskCaseAssignExample example);

    int updateByPrimaryKeySelective(TaskCaseAssign record);

    int updateByPrimaryKey(TaskCaseAssign record);
}