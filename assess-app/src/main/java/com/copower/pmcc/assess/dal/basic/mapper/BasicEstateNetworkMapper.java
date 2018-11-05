package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicEstateNetwork;
import com.copower.pmcc.assess.dal.basic.entity.BasicEstateNetworkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicEstateNetworkMapper {
    int countByExample(BasicEstateNetworkExample example);

    int deleteByExample(BasicEstateNetworkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicEstateNetwork record);

    int insertSelective(BasicEstateNetwork record);

    List<BasicEstateNetwork> selectByExample(BasicEstateNetworkExample example);

    BasicEstateNetwork selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicEstateNetwork record, @Param("example") BasicEstateNetworkExample example);

    int updateByExample(@Param("record") BasicEstateNetwork record, @Param("example") BasicEstateNetworkExample example);

    int updateByPrimaryKeySelective(BasicEstateNetwork record);

    int updateByPrimaryKey(BasicEstateNetwork record);
}