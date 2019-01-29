package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ToolRewardRate;
import com.copower.pmcc.assess.dal.basis.entity.ToolRewardRateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ToolRewardRateMapper {
    int countByExample(ToolRewardRateExample example);

    int deleteByExample(ToolRewardRateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ToolRewardRate record);

    int insertSelective(ToolRewardRate record);

    List<ToolRewardRate> selectByExample(ToolRewardRateExample example);

    ToolRewardRate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ToolRewardRate record, @Param("example") ToolRewardRateExample example);

    int updateByExample(@Param("record") ToolRewardRate record, @Param("example") ToolRewardRateExample example);

    int updateByPrimaryKeySelective(ToolRewardRate record);

    int updateByPrimaryKey(ToolRewardRate record);
}