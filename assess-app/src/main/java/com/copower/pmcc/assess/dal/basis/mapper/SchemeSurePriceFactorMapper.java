package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceFactor;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceFactorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchemeSurePriceFactorMapper {
    int countByExample(SchemeSurePriceFactorExample example);

    int deleteByExample(SchemeSurePriceFactorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeSurePriceFactor record);

    int insertSelective(SchemeSurePriceFactor record);

    List<SchemeSurePriceFactor> selectByExample(SchemeSurePriceFactorExample example);

    SchemeSurePriceFactor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeSurePriceFactor record, @Param("example") SchemeSurePriceFactorExample example);

    int updateByExample(@Param("record") SchemeSurePriceFactor record, @Param("example") SchemeSurePriceFactorExample example);

    int updateByPrimaryKeySelective(SchemeSurePriceFactor record);

    int updateByPrimaryKey(SchemeSurePriceFactor record);
}