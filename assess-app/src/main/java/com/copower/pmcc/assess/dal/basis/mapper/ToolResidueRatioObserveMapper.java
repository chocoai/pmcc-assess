package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ToolResidueRatioObserve;
import com.copower.pmcc.assess.dal.basis.entity.ToolResidueRatioObserveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ToolResidueRatioObserveMapper {
    int countByExample(ToolResidueRatioObserveExample example);

    int deleteByExample(ToolResidueRatioObserveExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ToolResidueRatioObserve record);

    int insertSelective(ToolResidueRatioObserve record);

    List<ToolResidueRatioObserve> selectByExample(ToolResidueRatioObserveExample example);

    ToolResidueRatioObserve selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ToolResidueRatioObserve record, @Param("example") ToolResidueRatioObserveExample example);

    int updateByExample(@Param("record") ToolResidueRatioObserve record, @Param("example") ToolResidueRatioObserveExample example);

    int updateByPrimaryKeySelective(ToolResidueRatioObserve record);

    int updateByPrimaryKey(ToolResidueRatioObserve record);
}