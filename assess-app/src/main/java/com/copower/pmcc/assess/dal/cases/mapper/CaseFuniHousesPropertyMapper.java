package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesProperty;
import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesPropertyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseFuniHousesPropertyMapper {
    int countByExample(CaseFuniHousesPropertyExample example);

    int deleteByExample(CaseFuniHousesPropertyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseFuniHousesProperty record);

    int insertSelective(CaseFuniHousesProperty record);

    List<CaseFuniHousesProperty> selectByExample(CaseFuniHousesPropertyExample example);

    CaseFuniHousesProperty selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseFuniHousesProperty record, @Param("example") CaseFuniHousesPropertyExample example);

    int updateByExample(@Param("record") CaseFuniHousesProperty record, @Param("example") CaseFuniHousesPropertyExample example);

    int updateByPrimaryKeySelective(CaseFuniHousesProperty record);

    int updateByPrimaryKey(CaseFuniHousesProperty record);
}