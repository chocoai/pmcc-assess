package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.FuniHousesBuildUnit;
import com.copower.pmcc.assess.dal.basis.entity.FuniHousesBuildUnitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FuniHousesBuildUnitMapper {
    int countByExample(FuniHousesBuildUnitExample example);

    int deleteByExample(FuniHousesBuildUnitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FuniHousesBuildUnit record);

    int insertSelective(FuniHousesBuildUnit record);

    List<FuniHousesBuildUnit> selectByExample(FuniHousesBuildUnitExample example);

    FuniHousesBuildUnit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FuniHousesBuildUnit record, @Param("example") FuniHousesBuildUnitExample example);

    int updateByExample(@Param("record") FuniHousesBuildUnit record, @Param("example") FuniHousesBuildUnitExample example);

    int updateByPrimaryKeySelective(FuniHousesBuildUnit record);

    int updateByPrimaryKey(FuniHousesBuildUnit record);
}