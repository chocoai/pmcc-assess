package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseHuxingPrice;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseHuxingPriceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicHouseHuxingPriceMapper {
    long countByExample(BasicHouseHuxingPriceExample example);

    int deleteByExample(BasicHouseHuxingPriceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicHouseHuxingPrice record);

    int insertSelective(BasicHouseHuxingPrice record);

    List<BasicHouseHuxingPrice> selectByExampleWithBLOBs(BasicHouseHuxingPriceExample example);

    List<BasicHouseHuxingPrice> selectByExample(BasicHouseHuxingPriceExample example);

    BasicHouseHuxingPrice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicHouseHuxingPrice record, @Param("example") BasicHouseHuxingPriceExample example);

    int updateByExampleWithBLOBs(@Param("record") BasicHouseHuxingPrice record, @Param("example") BasicHouseHuxingPriceExample example);

    int updateByExample(@Param("record") BasicHouseHuxingPrice record, @Param("example") BasicHouseHuxingPriceExample example);

    int updateByPrimaryKeySelective(BasicHouseHuxingPrice record);

    int updateByPrimaryKeyWithBLOBs(BasicHouseHuxingPrice record);

    int updateByPrimaryKey(BasicHouseHuxingPrice record);
}