package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesBuildUnit;
import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesBuildUnitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseFuniHousesBuildUnitMapper {
    int countByExample(CaseFuniHousesBuildUnitExample example);

    int deleteByExample(CaseFuniHousesBuildUnitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseFuniHousesBuildUnit record);

    int insertSelective(CaseFuniHousesBuildUnit record);

    List<CaseFuniHousesBuildUnit> selectByExample(CaseFuniHousesBuildUnitExample example);

    CaseFuniHousesBuildUnit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseFuniHousesBuildUnit record, @Param("example") CaseFuniHousesBuildUnitExample example);

    int updateByExample(@Param("record") CaseFuniHousesBuildUnit record, @Param("example") CaseFuniHousesBuildUnitExample example);

    int updateByPrimaryKeySelective(CaseFuniHousesBuildUnit record);

    int updateByPrimaryKey(CaseFuniHousesBuildUnit record);
}