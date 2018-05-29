package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.InitiateConsignor;
import com.copower.pmcc.assess.dal.entity.InitiateConsignorExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InitiateConsignorMapper {
    int countByExample(InitiateConsignorExample example);

    int deleteByExample(InitiateConsignorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InitiateConsignor record);

    int insertSelective(InitiateConsignor record);

    List<InitiateConsignor> selectByExample(InitiateConsignorExample example);

    InitiateConsignor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InitiateConsignor record, @Param("example") InitiateConsignorExample example);

    int updateByExample(@Param("record") InitiateConsignor record, @Param("example") InitiateConsignorExample example);

    int updateByPrimaryKeySelective(InitiateConsignor record);

    int updateByPrimaryKey(InitiateConsignor record);
}