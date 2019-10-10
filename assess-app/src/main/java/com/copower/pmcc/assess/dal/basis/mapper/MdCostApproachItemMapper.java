package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdCostApproachItem;
import com.copower.pmcc.assess.dal.basis.entity.MdCostApproachItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdCostApproachItemMapper {
    int countByExample(MdCostApproachItemExample example);

    int deleteByExample(MdCostApproachItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdCostApproachItem record);

    int insertSelective(MdCostApproachItem record);

    List<MdCostApproachItem> selectByExample(MdCostApproachItemExample example);

    MdCostApproachItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdCostApproachItem record, @Param("example") MdCostApproachItemExample example);

    int updateByExample(@Param("record") MdCostApproachItem record, @Param("example") MdCostApproachItemExample example);

    int updateByPrimaryKeySelective(MdCostApproachItem record);

    int updateByPrimaryKey(MdCostApproachItem record);
}