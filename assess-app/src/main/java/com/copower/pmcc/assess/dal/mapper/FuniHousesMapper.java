package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.FuniHouses;
import com.copower.pmcc.assess.dal.entity.FuniHousesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FuniHousesMapper {
    int countByExample(FuniHousesExample example);

    int deleteByExample(FuniHousesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FuniHouses record);

    int insertSelective(FuniHouses record);

    List<FuniHouses> selectByExample(FuniHousesExample example);

    FuniHouses selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FuniHouses record, @Param("example") FuniHousesExample example);

    int updateByExample(@Param("record") FuniHouses record, @Param("example") FuniHousesExample example);

    int updateByPrimaryKeySelective(FuniHouses record);

    int updateByPrimaryKey(FuniHouses record);
}