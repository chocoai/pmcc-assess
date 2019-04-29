package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataLandApproximationMethodSetting;
import com.copower.pmcc.assess.dal.basis.entity.DataLandApproximationMethodSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataLandApproximationMethodSettingMapper {
    int countByExample(DataLandApproximationMethodSettingExample example);

    int deleteByExample(DataLandApproximationMethodSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataLandApproximationMethodSetting record);

    int insertSelective(DataLandApproximationMethodSetting record);

    List<DataLandApproximationMethodSetting> selectByExample(DataLandApproximationMethodSettingExample example);

    DataLandApproximationMethodSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataLandApproximationMethodSetting record, @Param("example") DataLandApproximationMethodSettingExample example);

    int updateByExample(@Param("record") DataLandApproximationMethodSetting record, @Param("example") DataLandApproximationMethodSettingExample example);

    int updateByPrimaryKeySelective(DataLandApproximationMethodSetting record);

    int updateByPrimaryKey(DataLandApproximationMethodSetting record);
}