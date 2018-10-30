package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingOutfit;
import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingOutfitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicBuildingOutfitMapper {
    int countByExample(BasicBuildingOutfitExample example);

    int deleteByExample(BasicBuildingOutfitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicBuildingOutfit record);

    int insertSelective(BasicBuildingOutfit record);

    List<BasicBuildingOutfit> selectByExample(BasicBuildingOutfitExample example);

    BasicBuildingOutfit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicBuildingOutfit record, @Param("example") BasicBuildingOutfitExample example);

    int updateByExample(@Param("record") BasicBuildingOutfit record, @Param("example") BasicBuildingOutfitExample example);

    int updateByPrimaryKeySelective(BasicBuildingOutfit record);

    int updateByPrimaryKey(BasicBuildingOutfit record);
}