package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdCostApproachTaxes;
import com.copower.pmcc.assess.dal.basis.entity.MdCostApproachTaxesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdCostApproachTaxesMapper {
    long countByExample(MdCostApproachTaxesExample example);

    int deleteByExample(MdCostApproachTaxesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdCostApproachTaxes record);

    int insertSelective(@Param("record") MdCostApproachTaxes record, @Param("selective") MdCostApproachTaxes.Column ... selective);

    List<MdCostApproachTaxes> selectByExample(MdCostApproachTaxesExample example);

    MdCostApproachTaxes selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdCostApproachTaxes record, @Param("example") MdCostApproachTaxesExample example, @Param("selective") MdCostApproachTaxes.Column ... selective);

    int updateByExample(@Param("record") MdCostApproachTaxes record, @Param("example") MdCostApproachTaxesExample example);

    int updateByPrimaryKeySelective(@Param("record") MdCostApproachTaxes record, @Param("selective") MdCostApproachTaxes.Column ... selective);

    int updateByPrimaryKey(MdCostApproachTaxes record);

    int batchInsert(@Param("list") List<MdCostApproachTaxes> list);

    int batchInsertSelective(@Param("list") List<MdCostApproachTaxes> list, @Param("selective") MdCostApproachTaxes.Column ... selective);
}