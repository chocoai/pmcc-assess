package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesMating;
import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesMatingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseFuniHousesMatingMapper {
    int countByExample(CaseFuniHousesMatingExample example);

    int deleteByExample(CaseFuniHousesMatingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseFuniHousesMating record);

    int insertSelective(CaseFuniHousesMating record);

    List<CaseFuniHousesMating> selectByExample(CaseFuniHousesMatingExample example);

    CaseFuniHousesMating selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseFuniHousesMating record, @Param("example") CaseFuniHousesMatingExample example);

    int updateByExample(@Param("record") CaseFuniHousesMating record, @Param("example") CaseFuniHousesMatingExample example);

    int updateByPrimaryKeySelective(CaseFuniHousesMating record);

    int updateByPrimaryKey(CaseFuniHousesMating record);
}