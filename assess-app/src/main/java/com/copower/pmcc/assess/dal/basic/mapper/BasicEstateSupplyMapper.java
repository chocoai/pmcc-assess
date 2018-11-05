package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicEstateSupply;
import com.copower.pmcc.assess.dal.basic.entity.BasicEstateSupplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicEstateSupplyMapper {
    int countByExample(BasicEstateSupplyExample example);

    int deleteByExample(BasicEstateSupplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicEstateSupply record);

    int insertSelective(BasicEstateSupply record);

    List<BasicEstateSupply> selectByExample(BasicEstateSupplyExample example);

    BasicEstateSupply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicEstateSupply record, @Param("example") BasicEstateSupplyExample example);

    int updateByExample(@Param("record") BasicEstateSupply record, @Param("example") BasicEstateSupplyExample example);

    int updateByPrimaryKeySelective(BasicEstateSupply record);

    int updateByPrimaryKey(BasicEstateSupply record);
}