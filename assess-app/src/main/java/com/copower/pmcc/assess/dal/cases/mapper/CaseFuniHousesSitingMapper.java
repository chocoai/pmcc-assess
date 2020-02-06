package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesSiting;
import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesSitingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseFuniHousesSitingMapper {
    int countByExample(CaseFuniHousesSitingExample example);

    int deleteByExample(CaseFuniHousesSitingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseFuniHousesSiting record);

    int insertSelective(CaseFuniHousesSiting record);

    List<CaseFuniHousesSiting> selectByExample(CaseFuniHousesSitingExample example);

    CaseFuniHousesSiting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseFuniHousesSiting record, @Param("example") CaseFuniHousesSitingExample example);

    int updateByExample(@Param("record") CaseFuniHousesSiting record, @Param("example") CaseFuniHousesSitingExample example);

    int updateByPrimaryKeySelective(CaseFuniHousesSiting record);

    int updateByPrimaryKey(CaseFuniHousesSiting record);
}