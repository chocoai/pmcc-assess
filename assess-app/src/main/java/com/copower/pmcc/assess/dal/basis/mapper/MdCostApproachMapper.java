package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdCostApproach;
import com.copower.pmcc.assess.dal.basis.entity.MdCostApproachExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdCostApproachMapper {
    long countByExample(MdCostApproachExample example);

    int deleteByExample(MdCostApproachExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdCostApproach record);

    int insertSelective(@Param("record") MdCostApproach record, @Param("selective") MdCostApproach.Column ... selective);

    List<MdCostApproach> selectByExampleWithBLOBs(MdCostApproachExample example);

    List<MdCostApproach> selectByExample(MdCostApproachExample example);

    MdCostApproach selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdCostApproach record, @Param("example") MdCostApproachExample example, @Param("selective") MdCostApproach.Column ... selective);

    int updateByExampleWithBLOBs(@Param("record") MdCostApproach record, @Param("example") MdCostApproachExample example);

    int updateByExample(@Param("record") MdCostApproach record, @Param("example") MdCostApproachExample example);

    int updateByPrimaryKeySelective(@Param("record") MdCostApproach record, @Param("selective") MdCostApproach.Column ... selective);

    int updateByPrimaryKeyWithBLOBs(MdCostApproach record);

    int updateByPrimaryKey(MdCostApproach record);

    int batchInsert(@Param("list") List<MdCostApproach> list);

    int batchInsertSelective(@Param("list") List<MdCostApproach> list, @Param("selective") MdCostApproach.Column ... selective);
}