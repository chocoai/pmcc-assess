package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectLandAchievementGroup;
import com.copower.pmcc.assess.dal.basis.entity.ProjectLandAchievementGroupExample;
import com.copower.pmcc.assess.dal.basis.entity.ProjectLandAchievementGroupWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectLandAchievementGroupMapper {
    long countByExample(ProjectLandAchievementGroupExample example);

    int deleteByExample(ProjectLandAchievementGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectLandAchievementGroupWithBLOBs record);

    int insertSelective(@Param("record") ProjectLandAchievementGroupWithBLOBs record, @Param("selective") ProjectLandAchievementGroupWithBLOBs.Column ... selective);

    List<ProjectLandAchievementGroupWithBLOBs> selectByExampleWithBLOBs(ProjectLandAchievementGroupExample example);

    List<ProjectLandAchievementGroup> selectByExample(ProjectLandAchievementGroupExample example);

    ProjectLandAchievementGroupWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectLandAchievementGroupWithBLOBs record, @Param("example") ProjectLandAchievementGroupExample example, @Param("selective") ProjectLandAchievementGroupWithBLOBs.Column ... selective);

    int updateByExampleWithBLOBs(@Param("record") ProjectLandAchievementGroupWithBLOBs record, @Param("example") ProjectLandAchievementGroupExample example);

    int updateByExample(@Param("record") ProjectLandAchievementGroup record, @Param("example") ProjectLandAchievementGroupExample example);

    int updateByPrimaryKeySelective(@Param("record") ProjectLandAchievementGroupWithBLOBs record, @Param("selective") ProjectLandAchievementGroupWithBLOBs.Column ... selective);

    int updateByPrimaryKeyWithBLOBs(ProjectLandAchievementGroupWithBLOBs record);

    int updateByPrimaryKey(ProjectLandAchievementGroup record);

    int batchInsert(@Param("list") List<ProjectLandAchievementGroupWithBLOBs> list);

    int batchInsertSelective(@Param("list") List<ProjectLandAchievementGroupWithBLOBs> list, @Param("selective") ProjectLandAchievementGroupWithBLOBs.Column ... selective);
}