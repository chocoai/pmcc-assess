package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePrice;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchemeSurePriceMapper {
    long countByExample(SchemeSurePriceExample example);

    int deleteByExample(SchemeSurePriceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeSurePrice record);

    int insertSelective(SchemeSurePrice record);

    List<SchemeSurePrice> selectByExample(SchemeSurePriceExample example);

    SchemeSurePrice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeSurePrice record, @Param("example") SchemeSurePriceExample example);

    int updateByExample(@Param("record") SchemeSurePrice record, @Param("example") SchemeSurePriceExample example);

    int updateByPrimaryKeySelective(SchemeSurePrice record);

    int updateByPrimaryKey(SchemeSurePrice record);
}