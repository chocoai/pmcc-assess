package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdCostApproachTaxes;
import com.copower.pmcc.assess.dal.basis.entity.MdCostApproachTaxesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdCostApproachTaxesMapper {
    int countByExample(MdCostApproachTaxesExample example);

    int deleteByExample(MdCostApproachTaxesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdCostApproachTaxes record);

    int insertSelective(MdCostApproachTaxes record);

    List<MdCostApproachTaxes> selectByExample(MdCostApproachTaxesExample example);

    MdCostApproachTaxes selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdCostApproachTaxes record, @Param("example") MdCostApproachTaxesExample example);

    int updateByExample(@Param("record") MdCostApproachTaxes record, @Param("example") MdCostApproachTaxesExample example);

    int updateByPrimaryKeySelective(MdCostApproachTaxes record);

    int updateByPrimaryKey(MdCostApproachTaxes record);
}