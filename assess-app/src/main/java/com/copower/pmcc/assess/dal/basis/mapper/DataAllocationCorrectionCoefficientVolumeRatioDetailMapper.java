package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataAllocationCorrectionCoefficientVolumeRatioDetail;
import com.copower.pmcc.assess.dal.basis.entity.DataAllocationCorrectionCoefficientVolumeRatioDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataAllocationCorrectionCoefficientVolumeRatioDetailMapper {
    int countByExample(DataAllocationCorrectionCoefficientVolumeRatioDetailExample example);

    int deleteByExample(DataAllocationCorrectionCoefficientVolumeRatioDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataAllocationCorrectionCoefficientVolumeRatioDetail record);

    int insertSelective(DataAllocationCorrectionCoefficientVolumeRatioDetail record);

    List<DataAllocationCorrectionCoefficientVolumeRatioDetail> selectByExample(DataAllocationCorrectionCoefficientVolumeRatioDetailExample example);

    DataAllocationCorrectionCoefficientVolumeRatioDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataAllocationCorrectionCoefficientVolumeRatioDetail record, @Param("example") DataAllocationCorrectionCoefficientVolumeRatioDetailExample example);

    int updateByExample(@Param("record") DataAllocationCorrectionCoefficientVolumeRatioDetail record, @Param("example") DataAllocationCorrectionCoefficientVolumeRatioDetailExample example);

    int updateByPrimaryKeySelective(DataAllocationCorrectionCoefficientVolumeRatioDetail record);

    int updateByPrimaryKey(DataAllocationCorrectionCoefficientVolumeRatioDetail record);
}