package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicUnitHuxing;
import com.copower.pmcc.assess.dal.basic.entity.BasicUnitHuxingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicUnitHuxingMapper {
    int countByExample(BasicUnitHuxingExample example);

    int deleteByExample(BasicUnitHuxingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicUnitHuxing record);

    int insertSelective(BasicUnitHuxing record);

    List<BasicUnitHuxing> selectByExample(BasicUnitHuxingExample example);

    BasicUnitHuxing selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicUnitHuxing record, @Param("example") BasicUnitHuxingExample example);

    int updateByExample(@Param("record") BasicUnitHuxing record, @Param("example") BasicUnitHuxingExample example);

    int updateByPrimaryKeySelective(BasicUnitHuxing record);

    int updateByPrimaryKey(BasicUnitHuxing record);
}