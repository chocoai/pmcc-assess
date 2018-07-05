package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.InitiateUnitInformation;
import com.copower.pmcc.assess.dal.basis.entity.InitiateUnitInformationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InitiateUnitInformationMapper {
    int countByExample(InitiateUnitInformationExample example);

    int deleteByExample(InitiateUnitInformationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InitiateUnitInformation record);

    int insertSelective(InitiateUnitInformation record);

    List<InitiateUnitInformation> selectByExample(InitiateUnitInformationExample example);

    InitiateUnitInformation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InitiateUnitInformation record, @Param("example") InitiateUnitInformationExample example);

    int updateByExample(@Param("record") InitiateUnitInformation record, @Param("example") InitiateUnitInformationExample example);

    int updateByPrimaryKeySelective(InitiateUnitInformation record);

    int updateByPrimaryKey(InitiateUnitInformation record);
}