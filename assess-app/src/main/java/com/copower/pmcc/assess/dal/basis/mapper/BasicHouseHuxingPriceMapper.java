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

    int insertSelective(@Param("record") BasicHouseHuxingPrice record, @Param("selective") BasicHouseHuxingPrice.Column ... selective);

    List<BasicHouseHuxingPrice> selectByExampleWithBLOBs(BasicHouseHuxingPriceExample example);

    List<BasicHouseHuxingPrice> selectByExample(BasicHouseHuxingPriceExample example);

    BasicHouseHuxingPrice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicHouseHuxingPrice record, @Param("example") BasicHouseHuxingPriceExample example, @Param("selective") BasicHouseHuxingPrice.Column ... selective);

    int updateByExampleWithBLOBs(@Param("record") BasicHouseHuxingPrice record, @Param("example") BasicHouseHuxingPriceExample example);

    int updateByExample(@Param("record") BasicHouseHuxingPrice record, @Param("example") BasicHouseHuxingPriceExample example);

    int updateByPrimaryKeySelective(@Param("record") BasicHouseHuxingPrice record, @Param("selective") BasicHouseHuxingPrice.Column ... selective);

    int updateByPrimaryKeyWithBLOBs(BasicHouseHuxingPrice record);

    int updateByPrimaryKey(BasicHouseHuxingPrice record);

    int batchInsert(@Param("list") List<BasicHouseHuxingPrice> list);

    int batchInsertSelective(@Param("list") List<BasicHouseHuxingPrice> list, @Param("selective") BasicHouseHuxingPrice.Column ... selective);
}