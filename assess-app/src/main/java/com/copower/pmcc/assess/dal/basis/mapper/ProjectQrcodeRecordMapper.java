package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectQrcodeRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectQrcodeRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectQrcodeRecordMapper {
    long countByExample(ProjectQrcodeRecordExample example);

    int deleteByExample(ProjectQrcodeRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectQrcodeRecord record);

    int insertSelective(ProjectQrcodeRecord record);

    List<ProjectQrcodeRecord> selectByExampleWithBLOBs(ProjectQrcodeRecordExample example);

    List<ProjectQrcodeRecord> selectByExample(ProjectQrcodeRecordExample example);

    ProjectQrcodeRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectQrcodeRecord record, @Param("example") ProjectQrcodeRecordExample example);

    int updateByExampleWithBLOBs(@Param("record") ProjectQrcodeRecord record, @Param("example") ProjectQrcodeRecordExample example);

    int updateByExample(@Param("record") ProjectQrcodeRecord record, @Param("example") ProjectQrcodeRecordExample example);

    int updateByPrimaryKeySelective(ProjectQrcodeRecord record);

    int updateByPrimaryKeyWithBLOBs(ProjectQrcodeRecord record);

    int updateByPrimaryKey(ProjectQrcodeRecord record);
}