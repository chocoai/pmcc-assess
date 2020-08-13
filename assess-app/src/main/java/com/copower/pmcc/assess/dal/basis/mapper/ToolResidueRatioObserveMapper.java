package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ToolResidueRatioObserve;
import com.copower.pmcc.assess.dal.basis.entity.ToolResidueRatioObserveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ToolResidueRatioObserveMapper {
    long countByExample(ToolResidueRatioObserveExample example);

    int deleteByExample(ToolResidueRatioObserveExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ToolResidueRatioObserve record);

    int insertSelective(@Param("record") ToolResidueRatioObserve record, @Param("selective") ToolResidueRatioObserve.Column ... selective);

    List<ToolResidueRatioObserve> selectByExample(ToolResidueRatioObserveExample example);

    ToolResidueRatioObserve selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ToolResidueRatioObserve record, @Param("example") ToolResidueRatioObserveExample example, @Param("selective") ToolResidueRatioObserve.Column ... selective);

    int updateByExample(@Param("record") ToolResidueRatioObserve record, @Param("example") ToolResidueRatioObserveExample example);

    int updateByPrimaryKeySelective(@Param("record") ToolResidueRatioObserve record, @Param("selective") ToolResidueRatioObserve.Column ... selective);

    int updateByPrimaryKey(ToolResidueRatioObserve record);

    int batchInsert(@Param("list") List<ToolResidueRatioObserve> list);

    int batchInsertSelective(@Param("list") List<ToolResidueRatioObserve> list, @Param("selective") ToolResidueRatioObserve.Column ... selective);
}