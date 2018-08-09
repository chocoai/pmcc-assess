package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdCost;
import com.copower.pmcc.assess.dal.basis.entity.MdCostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdCostMapper {
    int countByExample(MdCostExample example);

    int deleteByExample(MdCostExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdCost record);

    int insertSelective(MdCost record);

    List<MdCost> selectByExample(MdCostExample example);

    MdCost selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdCost record, @Param("example") MdCostExample example);

    int updateByExample(@Param("record") MdCost record, @Param("example") MdCostExample example);

    int updateByPrimaryKeySelective(MdCost record);

    int updateByPrimaryKey(MdCost record);
}