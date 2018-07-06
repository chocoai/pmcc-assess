package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingOutfit;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingOutfitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseBuildingOutfitMapper {
    int countByExample(CaseBuildingOutfitExample example);

    int deleteByExample(CaseBuildingOutfitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseBuildingOutfit record);

    int insertSelective(CaseBuildingOutfit record);

    List<CaseBuildingOutfit> selectByExample(CaseBuildingOutfitExample example);

    CaseBuildingOutfit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseBuildingOutfit record, @Param("example") CaseBuildingOutfitExample example);

    int updateByExample(@Param("record") CaseBuildingOutfit record, @Param("example") CaseBuildingOutfitExample example);

    int updateByPrimaryKeySelective(CaseBuildingOutfit record);

    int updateByPrimaryKey(CaseBuildingOutfit record);
}