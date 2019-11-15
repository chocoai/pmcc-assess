package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetailAchievement;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetailAchievementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataLandLevelDetailAchievementMapper {
    int countByExample(DataLandLevelDetailAchievementExample example);

    int deleteByExample(DataLandLevelDetailAchievementExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataLandLevelDetailAchievement record);

    int insertSelective(DataLandLevelDetailAchievement record);

    List<DataLandLevelDetailAchievement> selectByExample(DataLandLevelDetailAchievementExample example);

    DataLandLevelDetailAchievement selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataLandLevelDetailAchievement record, @Param("example") DataLandLevelDetailAchievementExample example);

    int updateByExample(@Param("record") DataLandLevelDetailAchievement record, @Param("example") DataLandLevelDetailAchievementExample example);

    int updateByPrimaryKeySelective(DataLandLevelDetailAchievement record);

    int updateByPrimaryKey(DataLandLevelDetailAchievement record);
}