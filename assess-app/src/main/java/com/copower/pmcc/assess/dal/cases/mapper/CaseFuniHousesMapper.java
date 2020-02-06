package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHouses;
import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseFuniHousesMapper {
    int countByExample(CaseFuniHousesExample example);

    int deleteByExample(CaseFuniHousesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseFuniHouses record);

    int insertSelective(CaseFuniHouses record);

    List<CaseFuniHouses> selectByExample(CaseFuniHousesExample example);

    CaseFuniHouses selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseFuniHouses record, @Param("example") CaseFuniHousesExample example);

    int updateByExample(@Param("record") CaseFuniHouses record, @Param("example") CaseFuniHousesExample example);

    int updateByPrimaryKeySelective(CaseFuniHouses record);

    int updateByPrimaryKey(CaseFuniHouses record);
}