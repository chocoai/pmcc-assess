package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxCommission;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxCommissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectXlxCommissionMapper {
    int countByExample(ProjectXlxCommissionExample example);

    int deleteByExample(ProjectXlxCommissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectXlxCommission record);

    int insertSelective(ProjectXlxCommission record);

    List<ProjectXlxCommission> selectByExample(ProjectXlxCommissionExample example);

    ProjectXlxCommission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectXlxCommission record, @Param("example") ProjectXlxCommissionExample example);

    int updateByExample(@Param("record") ProjectXlxCommission record, @Param("example") ProjectXlxCommissionExample example);

    int updateByPrimaryKeySelective(ProjectXlxCommission record);

    int updateByPrimaryKey(ProjectXlxCommission record);
}