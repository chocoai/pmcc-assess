package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectNumberRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectNumberRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectNumberRecordMapper {
    int countByExample(ProjectNumberRecordExample example);

    int deleteByExample(ProjectNumberRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectNumberRecord record);

    int insertSelective(ProjectNumberRecord record);

    List<ProjectNumberRecord> selectByExample(ProjectNumberRecordExample example);

    ProjectNumberRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectNumberRecord record, @Param("example") ProjectNumberRecordExample example);

    int updateByExample(@Param("record") ProjectNumberRecord record, @Param("example") ProjectNumberRecordExample example);

    int updateByPrimaryKeySelective(ProjectNumberRecord record);

    int updateByPrimaryKey(ProjectNumberRecord record);
}