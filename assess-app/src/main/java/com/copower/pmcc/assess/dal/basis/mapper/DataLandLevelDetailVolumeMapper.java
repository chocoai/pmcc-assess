package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetailVolume;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetailVolumeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataLandLevelDetailVolumeMapper {
    int countByExample(DataLandLevelDetailVolumeExample example);

    int deleteByExample(DataLandLevelDetailVolumeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataLandLevelDetailVolume record);

    int insertSelective(DataLandLevelDetailVolume record);

    List<DataLandLevelDetailVolume> selectByExample(DataLandLevelDetailVolumeExample example);

    DataLandLevelDetailVolume selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataLandLevelDetailVolume record, @Param("example") DataLandLevelDetailVolumeExample example);

    int updateByExample(@Param("record") DataLandLevelDetailVolume record, @Param("example") DataLandLevelDetailVolumeExample example);

    int updateByPrimaryKeySelective(DataLandLevelDetailVolume record);

    int updateByPrimaryKey(DataLandLevelDetailVolume record);
}