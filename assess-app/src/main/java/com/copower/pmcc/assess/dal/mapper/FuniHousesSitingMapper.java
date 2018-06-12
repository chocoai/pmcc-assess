package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.FuniHousesSiting;
import com.copower.pmcc.assess.dal.entity.FuniHousesSitingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FuniHousesSitingMapper {
    int countByExample(FuniHousesSitingExample example);

    int deleteByExample(FuniHousesSitingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FuniHousesSiting record);

    int insertSelective(FuniHousesSiting record);

    List<FuniHousesSiting> selectByExample(FuniHousesSitingExample example);

    FuniHousesSiting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FuniHousesSiting record, @Param("example") FuniHousesSitingExample example);

    int updateByExample(@Param("record") FuniHousesSiting record, @Param("example") FuniHousesSitingExample example);

    int updateByPrimaryKeySelective(FuniHousesSiting record);

    int updateByPrimaryKey(FuniHousesSiting record);
}