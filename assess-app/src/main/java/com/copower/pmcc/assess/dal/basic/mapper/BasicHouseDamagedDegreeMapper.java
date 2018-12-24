package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseDamagedDegree;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseDamagedDegreeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicHouseDamagedDegreeMapper {
    int countByExample(BasicHouseDamagedDegreeExample example);

    int deleteByExample(BasicHouseDamagedDegreeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicHouseDamagedDegree record);

    int insertSelective(BasicHouseDamagedDegree record);

    List<BasicHouseDamagedDegree> selectByExample(BasicHouseDamagedDegreeExample example);

    BasicHouseDamagedDegree selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicHouseDamagedDegree record, @Param("example") BasicHouseDamagedDegreeExample example);

    int updateByExample(@Param("record") BasicHouseDamagedDegree record, @Param("example") BasicHouseDamagedDegreeExample example);

    int updateByPrimaryKeySelective(BasicHouseDamagedDegree record);

    int updateByPrimaryKey(BasicHouseDamagedDegree record);
}