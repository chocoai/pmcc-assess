package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.FuniHousesProperty;
import com.copower.pmcc.assess.dal.entity.FuniHousesPropertyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FuniHousesPropertyMapper {
    int countByExample(FuniHousesPropertyExample example);

    int deleteByExample(FuniHousesPropertyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FuniHousesProperty record);

    int insertSelective(FuniHousesProperty record);

    List<FuniHousesProperty> selectByExample(FuniHousesPropertyExample example);

    FuniHousesProperty selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FuniHousesProperty record, @Param("example") FuniHousesPropertyExample example);

    int updateByExample(@Param("record") FuniHousesProperty record, @Param("example") FuniHousesPropertyExample example);

    int updateByPrimaryKeySelective(FuniHousesProperty record);

    int updateByPrimaryKey(FuniHousesProperty record);
}