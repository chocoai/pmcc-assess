package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.FuniHousesType;
import com.copower.pmcc.assess.dal.entity.FuniHousesTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FuniHousesTypeMapper {
    int countByExample(FuniHousesTypeExample example);

    int deleteByExample(FuniHousesTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FuniHousesType record);

    int insertSelective(FuniHousesType record);

    List<FuniHousesType> selectByExample(FuniHousesTypeExample example);

    FuniHousesType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FuniHousesType record, @Param("example") FuniHousesTypeExample example);

    int updateByExample(@Param("record") FuniHousesType record, @Param("example") FuniHousesTypeExample example);

    int updateByPrimaryKeySelective(FuniHousesType record);

    int updateByPrimaryKey(FuniHousesType record);
}