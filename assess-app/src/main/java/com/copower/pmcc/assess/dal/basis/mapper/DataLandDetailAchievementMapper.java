package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataLandDetailAchievement;
import com.copower.pmcc.assess.dal.basis.entity.DataLandDetailAchievementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataLandDetailAchievementMapper {
    int countByExample(DataLandDetailAchievementExample example);

    int deleteByExample(DataLandDetailAchievementExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataLandDetailAchievement record);

    int insertSelective(DataLandDetailAchievement record);

    List<DataLandDetailAchievement> selectByExample(DataLandDetailAchievementExample example);

    DataLandDetailAchievement selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataLandDetailAchievement record, @Param("example") DataLandDetailAchievementExample example);

    int updateByExample(@Param("record") DataLandDetailAchievement record, @Param("example") DataLandDetailAchievementExample example);

    int updateByPrimaryKeySelective(DataLandDetailAchievement record);

    int updateByPrimaryKey(DataLandDetailAchievement record);
}