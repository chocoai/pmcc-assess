package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingOutfit;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingOutfitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineBuildingOutfitMapper {
    int countByExample(ExamineBuildingOutfitExample example);

    int deleteByExample(ExamineBuildingOutfitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineBuildingOutfit record);

    int insertSelective(ExamineBuildingOutfit record);

    List<ExamineBuildingOutfit> selectByExample(ExamineBuildingOutfitExample example);

    ExamineBuildingOutfit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineBuildingOutfit record, @Param("example") ExamineBuildingOutfitExample example);

    int updateByExample(@Param("record") ExamineBuildingOutfit record, @Param("example") ExamineBuildingOutfitExample example);

    int updateByPrimaryKeySelective(ExamineBuildingOutfit record);

    int updateByPrimaryKey(ExamineBuildingOutfit record);
}