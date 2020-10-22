package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseFaceStreet;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseFaceStreetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicHouseFaceStreetMapper {
    long countByExample(BasicHouseFaceStreetExample example);

    int deleteByExample(BasicHouseFaceStreetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicHouseFaceStreet record);

    int insertSelective(@Param("record") BasicHouseFaceStreet record, @Param("selective") BasicHouseFaceStreet.Column ... selective);

    List<BasicHouseFaceStreet> selectByExample(BasicHouseFaceStreetExample example);

    BasicHouseFaceStreet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicHouseFaceStreet record, @Param("example") BasicHouseFaceStreetExample example, @Param("selective") BasicHouseFaceStreet.Column ... selective);

    int updateByExample(@Param("record") BasicHouseFaceStreet record, @Param("example") BasicHouseFaceStreetExample example);

    int updateByPrimaryKeySelective(@Param("record") BasicHouseFaceStreet record, @Param("selective") BasicHouseFaceStreet.Column ... selective);

    int updateByPrimaryKey(BasicHouseFaceStreet record);

    int batchInsert(@Param("list") List<BasicHouseFaceStreet> list);

    int batchInsertSelective(@Param("list") List<BasicHouseFaceStreet> list, @Param("selective") BasicHouseFaceStreet.Column ... selective);
}