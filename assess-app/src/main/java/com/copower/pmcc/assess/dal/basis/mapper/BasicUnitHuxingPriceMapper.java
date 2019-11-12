package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicUnitHuxingPrice;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnitHuxingPriceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicUnitHuxingPriceMapper {
    int countByExample(BasicUnitHuxingPriceExample example);

    int deleteByExample(BasicUnitHuxingPriceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicUnitHuxingPrice record);

    int insertSelective(BasicUnitHuxingPrice record);

    List<BasicUnitHuxingPrice> selectByExample(BasicUnitHuxingPriceExample example);

    BasicUnitHuxingPrice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicUnitHuxingPrice record, @Param("example") BasicUnitHuxingPriceExample example);

    int updateByExample(@Param("record") BasicUnitHuxingPrice record, @Param("example") BasicUnitHuxingPriceExample example);

    int updateByPrimaryKeySelective(BasicUnitHuxingPrice record);

    int updateByPrimaryKey(BasicUnitHuxingPrice record);
}