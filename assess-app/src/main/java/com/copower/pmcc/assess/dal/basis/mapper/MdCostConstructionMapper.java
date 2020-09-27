package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdCostConstruction;
import com.copower.pmcc.assess.dal.basis.entity.MdCostConstructionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdCostConstructionMapper {
    long countByExample(MdCostConstructionExample example);

    int deleteByExample(MdCostConstructionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdCostConstruction record);

    int insertSelective(@Param("record") MdCostConstruction record, @Param("selective") MdCostConstruction.Column ... selective);

    List<MdCostConstruction> selectByExampleWithBLOBs(MdCostConstructionExample example);

    List<MdCostConstruction> selectByExample(MdCostConstructionExample example);

    MdCostConstruction selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdCostConstruction record, @Param("example") MdCostConstructionExample example, @Param("selective") MdCostConstruction.Column ... selective);

    int updateByExampleWithBLOBs(@Param("record") MdCostConstruction record, @Param("example") MdCostConstructionExample example);

    int updateByExample(@Param("record") MdCostConstruction record, @Param("example") MdCostConstructionExample example);

    int updateByPrimaryKeySelective(@Param("record") MdCostConstruction record, @Param("selective") MdCostConstruction.Column ... selective);

    int updateByPrimaryKeyWithBLOBs(MdCostConstruction record);

    int updateByPrimaryKey(MdCostConstruction record);

    int batchInsert(@Param("list") List<MdCostConstruction> list);

    int batchInsertSelective(@Param("list") List<MdCostConstruction> list, @Param("selective") MdCostConstruction.Column ... selective);
}