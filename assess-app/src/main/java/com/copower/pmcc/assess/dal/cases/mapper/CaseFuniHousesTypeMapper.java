package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesType;
import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseFuniHousesTypeMapper {
    int countByExample(CaseFuniHousesTypeExample example);

    int deleteByExample(CaseFuniHousesTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseFuniHousesType record);

    int insertSelective(CaseFuniHousesType record);

    List<CaseFuniHousesType> selectByExample(CaseFuniHousesTypeExample example);

    CaseFuniHousesType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseFuniHousesType record, @Param("example") CaseFuniHousesTypeExample example);

    int updateByExample(@Param("record") CaseFuniHousesType record, @Param("example") CaseFuniHousesTypeExample example);

    int updateByPrimaryKeySelective(CaseFuniHousesType record);

    int updateByPrimaryKey(CaseFuniHousesType record);
}