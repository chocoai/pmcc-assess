package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdCostApproach;
import com.copower.pmcc.assess.dal.basis.entity.MdCostApproachExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdCostApproachMapper {
    int countByExample(MdCostApproachExample example);

    int deleteByExample(MdCostApproachExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdCostApproach record);

    int insertSelective(MdCostApproach record);

    List<MdCostApproach> selectByExample(MdCostApproachExample example);

    MdCostApproach selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdCostApproach record, @Param("example") MdCostApproachExample example);

    int updateByExample(@Param("record") MdCostApproach record, @Param("example") MdCostApproachExample example);

    int updateByPrimaryKeySelective(MdCostApproach record);

    int updateByPrimaryKey(MdCostApproach record);
}