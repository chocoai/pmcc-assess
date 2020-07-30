package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectSpotCheckItemScore;
import com.copower.pmcc.assess.dal.basis.entity.ProjectSpotCheckItemScoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectSpotCheckItemScoreMapper {
    long countByExample(ProjectSpotCheckItemScoreExample example);

    int deleteByExample(ProjectSpotCheckItemScoreExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectSpotCheckItemScore record);

    int insertSelective(ProjectSpotCheckItemScore record);

    List<ProjectSpotCheckItemScore> selectByExampleWithBLOBs(ProjectSpotCheckItemScoreExample example);

    List<ProjectSpotCheckItemScore> selectByExample(ProjectSpotCheckItemScoreExample example);

    ProjectSpotCheckItemScore selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectSpotCheckItemScore record, @Param("example") ProjectSpotCheckItemScoreExample example);

    int updateByExampleWithBLOBs(@Param("record") ProjectSpotCheckItemScore record, @Param("example") ProjectSpotCheckItemScoreExample example);

    int updateByExample(@Param("record") ProjectSpotCheckItemScore record, @Param("example") ProjectSpotCheckItemScoreExample example);

    int updateByPrimaryKeySelective(ProjectSpotCheckItemScore record);

    int updateByPrimaryKeyWithBLOBs(ProjectSpotCheckItemScore record);

    int updateByPrimaryKey(ProjectSpotCheckItemScore record);
}