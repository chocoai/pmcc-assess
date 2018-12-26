package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseDamagedDegreeDetail;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseDamagedDegreeDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicHouseDamagedDegreeDetailMapper {
    int countByExample(BasicHouseDamagedDegreeDetailExample example);

    int deleteByExample(BasicHouseDamagedDegreeDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicHouseDamagedDegreeDetail record);

    int insertSelective(BasicHouseDamagedDegreeDetail record);

    List<BasicHouseDamagedDegreeDetail> selectByExample(BasicHouseDamagedDegreeDetailExample example);

    BasicHouseDamagedDegreeDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicHouseDamagedDegreeDetail record, @Param("example") BasicHouseDamagedDegreeDetailExample example);

    int updateByExample(@Param("record") BasicHouseDamagedDegreeDetail record, @Param("example") BasicHouseDamagedDegreeDetailExample example);

    int updateByPrimaryKeySelective(BasicHouseDamagedDegreeDetail record);

    int updateByPrimaryKey(BasicHouseDamagedDegreeDetail record);
}