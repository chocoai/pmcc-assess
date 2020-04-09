package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateStreetInfo;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateStreetInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicEstateStreetInfoMapper {
    int countByExample(BasicEstateStreetInfoExample example);

    int deleteByExample(BasicEstateStreetInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicEstateStreetInfo record);

    int insertSelective(BasicEstateStreetInfo record);

    List<BasicEstateStreetInfo> selectByExample(BasicEstateStreetInfoExample example);

    BasicEstateStreetInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicEstateStreetInfo record, @Param("example") BasicEstateStreetInfoExample example);

    int updateByExample(@Param("record") BasicEstateStreetInfo record, @Param("example") BasicEstateStreetInfoExample example);

    int updateByPrimaryKeySelective(BasicEstateStreetInfo record);

    int updateByPrimaryKey(BasicEstateStreetInfo record);
}