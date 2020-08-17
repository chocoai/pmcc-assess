package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceFactor;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceFactorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchemeSurePriceFactorMapper {
    long countByExample(SchemeSurePriceFactorExample example);

    int deleteByExample(SchemeSurePriceFactorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeSurePriceFactor record);

    int insertSelective(@Param("record") SchemeSurePriceFactor record, @Param("selective") SchemeSurePriceFactor.Column ... selective);

    List<SchemeSurePriceFactor> selectByExample(SchemeSurePriceFactorExample example);

    SchemeSurePriceFactor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeSurePriceFactor record, @Param("example") SchemeSurePriceFactorExample example, @Param("selective") SchemeSurePriceFactor.Column ... selective);

    int updateByExample(@Param("record") SchemeSurePriceFactor record, @Param("example") SchemeSurePriceFactorExample example);

    int updateByPrimaryKeySelective(@Param("record") SchemeSurePriceFactor record, @Param("selective") SchemeSurePriceFactor.Column ... selective);

    int updateByPrimaryKey(SchemeSurePriceFactor record);

    int batchInsert(@Param("list") List<SchemeSurePriceFactor> list);

    int batchInsertSelective(@Param("list") List<SchemeSurePriceFactor> list, @Param("selective") SchemeSurePriceFactor.Column ... selective);
}