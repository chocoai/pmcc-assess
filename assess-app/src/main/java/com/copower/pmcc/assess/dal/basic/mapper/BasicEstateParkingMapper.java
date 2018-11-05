package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicEstateParking;
import com.copower.pmcc.assess.dal.basic.entity.BasicEstateParkingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicEstateParkingMapper {
    int countByExample(BasicEstateParkingExample example);

    int deleteByExample(BasicEstateParkingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicEstateParking record);

    int insertSelective(BasicEstateParking record);

    List<BasicEstateParking> selectByExample(BasicEstateParkingExample example);

    BasicEstateParking selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicEstateParking record, @Param("example") BasicEstateParkingExample example);

    int updateByExample(@Param("record") BasicEstateParking record, @Param("example") BasicEstateParkingExample example);

    int updateByPrimaryKeySelective(BasicEstateParking record);

    int updateByPrimaryKey(BasicEstateParking record);
}