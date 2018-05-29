package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.InitiateBasicInformation;
import com.copower.pmcc.assess.dal.entity.InitiateBasicInformationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InitiateBasicInformationMapper {
    int countByExample(InitiateBasicInformationExample example);

    int deleteByExample(InitiateBasicInformationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InitiateBasicInformation record);

    int insertSelective(InitiateBasicInformation record);

    List<InitiateBasicInformation> selectByExample(InitiateBasicInformationExample example);

    InitiateBasicInformation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InitiateBasicInformation record, @Param("example") InitiateBasicInformationExample example);

    int updateByExample(@Param("record") InitiateBasicInformation record, @Param("example") InitiateBasicInformationExample example);

    int updateByPrimaryKeySelective(InitiateBasicInformation record);

    int updateByPrimaryKey(InitiateBasicInformation record);
}