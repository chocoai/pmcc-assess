package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxPigeonholeConfig;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxPigeonholeConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectXlxPigeonholeConfigMapper {
    int countByExample(ProjectXlxPigeonholeConfigExample example);

    int deleteByExample(ProjectXlxPigeonholeConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectXlxPigeonholeConfig record);

    int insertSelective(ProjectXlxPigeonholeConfig record);

    List<ProjectXlxPigeonholeConfig> selectByExample(ProjectXlxPigeonholeConfigExample example);

    ProjectXlxPigeonholeConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectXlxPigeonholeConfig record, @Param("example") ProjectXlxPigeonholeConfigExample example);

    int updateByExample(@Param("record") ProjectXlxPigeonholeConfig record, @Param("example") ProjectXlxPigeonholeConfigExample example);

    int updateByPrimaryKeySelective(ProjectXlxPigeonholeConfig record);

    int updateByPrimaryKey(ProjectXlxPigeonholeConfig record);
}