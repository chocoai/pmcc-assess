package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SchemeCertAdjustmentFactor;
import com.copower.pmcc.assess.dal.basis.entity.SchemeCertAdjustmentFactorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchemeCertAdjustmentFactorMapper {
    int countByExample(SchemeCertAdjustmentFactorExample example);

    int deleteByExample(SchemeCertAdjustmentFactorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeCertAdjustmentFactor record);

    int insertSelective(SchemeCertAdjustmentFactor record);

    List<SchemeCertAdjustmentFactor> selectByExample(SchemeCertAdjustmentFactorExample example);

    SchemeCertAdjustmentFactor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeCertAdjustmentFactor record, @Param("example") SchemeCertAdjustmentFactorExample example);

    int updateByExample(@Param("record") SchemeCertAdjustmentFactor record, @Param("example") SchemeCertAdjustmentFactorExample example);

    int updateByPrimaryKeySelective(SchemeCertAdjustmentFactor record);

    int updateByPrimaryKey(SchemeCertAdjustmentFactor record);
}