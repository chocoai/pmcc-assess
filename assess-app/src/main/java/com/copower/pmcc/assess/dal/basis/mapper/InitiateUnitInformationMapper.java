package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.InitiateUnitInformation;
import com.copower.pmcc.assess.dal.basis.entity.InitiateUnitInformationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InitiateUnitInformationMapper {
    long countByExample(InitiateUnitInformationExample example);

    int deleteByExample(InitiateUnitInformationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InitiateUnitInformation record);

    int insertSelective(@Param("record") InitiateUnitInformation record, @Param("selective") InitiateUnitInformation.Column ... selective);

    List<InitiateUnitInformation> selectByExample(InitiateUnitInformationExample example);

    InitiateUnitInformation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InitiateUnitInformation record, @Param("example") InitiateUnitInformationExample example, @Param("selective") InitiateUnitInformation.Column ... selective);

    int updateByExample(@Param("record") InitiateUnitInformation record, @Param("example") InitiateUnitInformationExample example);

    int updateByPrimaryKeySelective(@Param("record") InitiateUnitInformation record, @Param("selective") InitiateUnitInformation.Column ... selective);

    int updateByPrimaryKey(InitiateUnitInformation record);

    int batchInsert(@Param("list") List<InitiateUnitInformation> list);

    int batchInsertSelective(@Param("list") List<InitiateUnitInformation> list, @Param("selective") InitiateUnitInformation.Column ... selective);
}