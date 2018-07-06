package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstateParking;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateParkingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseEstateParkingMapper {
    int countByExample(CaseEstateParkingExample example);

    int deleteByExample(CaseEstateParkingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseEstateParking record);

    int insertSelective(CaseEstateParking record);

    List<CaseEstateParking> selectByExample(CaseEstateParkingExample example);

    CaseEstateParking selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseEstateParking record, @Param("example") CaseEstateParkingExample example);

    int updateByExample(@Param("record") CaseEstateParking record, @Param("example") CaseEstateParkingExample example);

    int updateByPrimaryKeySelective(CaseEstateParking record);

    int updateByPrimaryKey(CaseEstateParking record);
}