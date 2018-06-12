package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.FuniHousesMating;
import com.copower.pmcc.assess.dal.entity.FuniHousesMatingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FuniHousesMatingMapper {
    int countByExample(FuniHousesMatingExample example);

    int deleteByExample(FuniHousesMatingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FuniHousesMating record);

    int insertSelective(FuniHousesMating record);

    List<FuniHousesMating> selectByExample(FuniHousesMatingExample example);

    FuniHousesMating selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FuniHousesMating record, @Param("example") FuniHousesMatingExample example);

    int updateByExample(@Param("record") FuniHousesMating record, @Param("example") FuniHousesMatingExample example);

    int updateByPrimaryKeySelective(FuniHousesMating record);

    int updateByPrimaryKey(FuniHousesMating record);
}