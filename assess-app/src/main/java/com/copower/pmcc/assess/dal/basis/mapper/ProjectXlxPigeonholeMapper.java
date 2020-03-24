package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxPigeonhole;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxPigeonholeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectXlxPigeonholeMapper {
    int countByExample(ProjectXlxPigeonholeExample example);

    int deleteByExample(ProjectXlxPigeonholeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectXlxPigeonhole record);

    int insertSelective(ProjectXlxPigeonhole record);

    List<ProjectXlxPigeonhole> selectByExample(ProjectXlxPigeonholeExample example);

    ProjectXlxPigeonhole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectXlxPigeonhole record, @Param("example") ProjectXlxPigeonholeExample example);

    int updateByExample(@Param("record") ProjectXlxPigeonhole record, @Param("example") ProjectXlxPigeonholeExample example);

    int updateByPrimaryKeySelective(ProjectXlxPigeonhole record);

    int updateByPrimaryKey(ProjectXlxPigeonhole record);
}