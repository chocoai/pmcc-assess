package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTradingSell;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTradingSellExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicHouseTradingSellMapper {
    long countByExample(BasicHouseTradingSellExample example);

    int deleteByExample(BasicHouseTradingSellExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicHouseTradingSell record);

    int insertSelective(BasicHouseTradingSell record);

    List<BasicHouseTradingSell> selectByExample(BasicHouseTradingSellExample example);

    BasicHouseTradingSell selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicHouseTradingSell record, @Param("example") BasicHouseTradingSellExample example);

    int updateByExample(@Param("record") BasicHouseTradingSell record, @Param("example") BasicHouseTradingSellExample example);

    int updateByPrimaryKeySelective(BasicHouseTradingSell record);

    int updateByPrimaryKey(BasicHouseTradingSell record);
}