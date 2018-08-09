package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdCostConstruction;
import com.copower.pmcc.assess.dal.basis.entity.MdCostConstructionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdCostConstructionMapper {
    int countByExample(MdCostConstructionExample example);

    int deleteByExample(MdCostConstructionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdCostConstruction record);

    int insertSelective(MdCostConstruction record);

    List<MdCostConstruction> selectByExample(MdCostConstructionExample example);

    MdCostConstruction selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdCostConstruction record, @Param("example") MdCostConstructionExample example);

    int updateByExample(@Param("record") MdCostConstruction record, @Param("example") MdCostConstructionExample example);

    int updateByPrimaryKeySelective(MdCostConstruction record);

    int updateByPrimaryKey(MdCostConstruction record);
}