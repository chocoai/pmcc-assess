package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataAllocationCorrectionCoefficientVolumeRatio;
import com.copower.pmcc.assess.dal.basis.entity.DataAllocationCorrectionCoefficientVolumeRatioExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataAllocationCorrectionCoefficientVolumeRatioMapper {
    int countByExample(DataAllocationCorrectionCoefficientVolumeRatioExample example);

    int deleteByExample(DataAllocationCorrectionCoefficientVolumeRatioExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataAllocationCorrectionCoefficientVolumeRatio record);

    int insertSelective(DataAllocationCorrectionCoefficientVolumeRatio record);

    List<DataAllocationCorrectionCoefficientVolumeRatio> selectByExample(DataAllocationCorrectionCoefficientVolumeRatioExample example);

    DataAllocationCorrectionCoefficientVolumeRatio selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataAllocationCorrectionCoefficientVolumeRatio record, @Param("example") DataAllocationCorrectionCoefficientVolumeRatioExample example);

    int updateByExample(@Param("record") DataAllocationCorrectionCoefficientVolumeRatio record, @Param("example") DataAllocationCorrectionCoefficientVolumeRatioExample example);

    int updateByPrimaryKeySelective(DataAllocationCorrectionCoefficientVolumeRatio record);

    int updateByPrimaryKey(DataAllocationCorrectionCoefficientVolumeRatio record);
}