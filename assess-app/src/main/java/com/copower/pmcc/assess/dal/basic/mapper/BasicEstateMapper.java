package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicEstate;
import com.copower.pmcc.assess.dal.basic.entity.BasicEstateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicEstateMapper {
    int countByExample(BasicEstateExample example);

    int deleteByExample(BasicEstateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicEstate record);

    int insertSelective(BasicEstate record);

    List<BasicEstate> selectByExample(BasicEstateExample example);

    BasicEstate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicEstate record, @Param("example") BasicEstateExample example);

    int updateByExample(@Param("record") BasicEstate record, @Param("example") BasicEstateExample example);

    int updateByPrimaryKeySelective(BasicEstate record);

    int updateByPrimaryKey(BasicEstate record);
}