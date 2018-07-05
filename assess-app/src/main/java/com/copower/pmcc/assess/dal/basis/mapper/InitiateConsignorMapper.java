package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.InitiateConsignor;
import com.copower.pmcc.assess.dal.basis.entity.InitiateConsignorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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