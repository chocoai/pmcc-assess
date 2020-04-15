package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicUnitStairs;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnitStairsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicUnitStairsMapper {
    int countByExample(BasicUnitStairsExample example);

    int deleteByExample(BasicUnitStairsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicUnitStairs record);

    int insertSelective(BasicUnitStairs record);

    List<BasicUnitStairs> selectByExample(BasicUnitStairsExample example);

    BasicUnitStairs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicUnitStairs record, @Param("example") BasicUnitStairsExample example);

    int updateByExample(@Param("record") BasicUnitStairs record, @Param("example") BasicUnitStairsExample example);

    int updateByPrimaryKeySelective(BasicUnitStairs record);

    int updateByPrimaryKey(BasicUnitStairs record);
}