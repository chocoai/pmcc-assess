package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdCostApproachItem;
import com.copower.pmcc.assess.dal.basis.entity.MdCostApproachItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdCostApproachItemMapper {
    long countByExample(MdCostApproachItemExample example);

    int deleteByExample(MdCostApproachItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdCostApproachItem record);

    int insertSelective(@Param("record") MdCostApproachItem record, @Param("selective") MdCostApproachItem.Column ... selective);

    List<MdCostApproachItem> selectByExample(MdCostApproachItemExample example);

    MdCostApproachItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdCostApproachItem record, @Param("example") MdCostApproachItemExample example, @Param("selective") MdCostApproachItem.Column ... selective);

    int updateByExample(@Param("record") MdCostApproachItem record, @Param("example") MdCostApproachItemExample example);

    int updateByPrimaryKeySelective(@Param("record") MdCostApproachItem record, @Param("selective") MdCostApproachItem.Column ... selective);

    int updateByPrimaryKey(MdCostApproachItem record);

    int batchInsert(@Param("list") List<MdCostApproachItem> list);

    int batchInsertSelective(@Param("list") List<MdCostApproachItem> list, @Param("selective") MdCostApproachItem.Column ... selective);
}