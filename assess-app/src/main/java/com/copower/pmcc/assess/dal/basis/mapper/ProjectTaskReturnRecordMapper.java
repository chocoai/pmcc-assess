package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectTaskReturnRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectTaskReturnRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectTaskReturnRecordMapper {
    long countByExample(ProjectTaskReturnRecordExample example);

    int deleteByExample(ProjectTaskReturnRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectTaskReturnRecord record);

    int insertSelective(ProjectTaskReturnRecord record);

    List<ProjectTaskReturnRecord> selectByExample(ProjectTaskReturnRecordExample example);

    ProjectTaskReturnRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectTaskReturnRecord record, @Param("example") ProjectTaskReturnRecordExample example);

    int updateByExample(@Param("record") ProjectTaskReturnRecord record, @Param("example") ProjectTaskReturnRecordExample example);

    int updateByPrimaryKeySelective(ProjectTaskReturnRecord record);

    int updateByPrimaryKey(ProjectTaskReturnRecord record);
}