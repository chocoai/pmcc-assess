package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicMatchingTraffic;
import com.copower.pmcc.assess.dal.basic.entity.BasicMatchingTrafficExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicMatchingTrafficMapper {
    int countByExample(BasicMatchingTrafficExample example);

    int deleteByExample(BasicMatchingTrafficExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicMatchingTraffic record);

    int insertSelective(BasicMatchingTraffic record);

    List<BasicMatchingTraffic> selectByExample(BasicMatchingTrafficExample example);

    BasicMatchingTraffic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicMatchingTraffic record, @Param("example") BasicMatchingTrafficExample example);

    int updateByExample(@Param("record") BasicMatchingTraffic record, @Param("example") BasicMatchingTrafficExample example);

    int updateByPrimaryKeySelective(BasicMatchingTraffic record);

    int updateByPrimaryKey(BasicMatchingTraffic record);
}