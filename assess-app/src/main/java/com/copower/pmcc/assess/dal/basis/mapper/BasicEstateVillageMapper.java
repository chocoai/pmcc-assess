package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateVillage;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateVillageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicEstateVillageMapper {
    int countByExample(BasicEstateVillageExample example);

    int deleteByExample(BasicEstateVillageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicEstateVillage record);

    int insertSelective(BasicEstateVillage record);

    List<BasicEstateVillage> selectByExample(BasicEstateVillageExample example);

    BasicEstateVillage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicEstateVillage record, @Param("example") BasicEstateVillageExample example);

    int updateByExample(@Param("record") BasicEstateVillage record, @Param("example") BasicEstateVillageExample example);

    int updateByPrimaryKeySelective(BasicEstateVillage record);

    int updateByPrimaryKey(BasicEstateVillage record);
}