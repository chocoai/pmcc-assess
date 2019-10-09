package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ToolResidueRatio;
import com.copower.pmcc.assess.dal.basis.entity.ToolResidueRatioExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ToolResidueRatioMapper {
    int countByExample(ToolResidueRatioExample example);

    int deleteByExample(ToolResidueRatioExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ToolResidueRatio record);

    int insertSelective(ToolResidueRatio record);

    List<ToolResidueRatio> selectByExampleWithBLOBs(ToolResidueRatioExample example);

    List<ToolResidueRatio> selectByExample(ToolResidueRatioExample example);

    ToolResidueRatio selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ToolResidueRatio record, @Param("example") ToolResidueRatioExample example);

    int updateByExampleWithBLOBs(@Param("record") ToolResidueRatio record, @Param("example") ToolResidueRatioExample example);

    int updateByExample(@Param("record") ToolResidueRatio record, @Param("example") ToolResidueRatioExample example);

    int updateByPrimaryKeySelective(ToolResidueRatio record);

    int updateByPrimaryKeyWithBLOBs(ToolResidueRatio record);

    int updateByPrimaryKey(ToolResidueRatio record);
}