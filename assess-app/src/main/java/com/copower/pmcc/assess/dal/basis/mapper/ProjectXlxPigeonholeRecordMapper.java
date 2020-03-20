package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxPigeonholeRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxPigeonholeRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectXlxPigeonholeRecordMapper {
    int countByExample(ProjectXlxPigeonholeRecordExample example);

    int deleteByExample(ProjectXlxPigeonholeRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectXlxPigeonholeRecord record);

    int insertSelective(ProjectXlxPigeonholeRecord record);

    List<ProjectXlxPigeonholeRecord> selectByExample(ProjectXlxPigeonholeRecordExample example);

    ProjectXlxPigeonholeRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectXlxPigeonholeRecord record, @Param("example") ProjectXlxPigeonholeRecordExample example);

    int updateByExample(@Param("record") ProjectXlxPigeonholeRecord record, @Param("example") ProjectXlxPigeonholeRecordExample example);

    int updateByPrimaryKeySelective(ProjectXlxPigeonholeRecord record);

    int updateByPrimaryKey(ProjectXlxPigeonholeRecord record);
}