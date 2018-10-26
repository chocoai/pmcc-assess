package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceItem;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchemeSurePriceItemMapper {
    int countByExample(SchemeSurePriceItemExample example);

    int deleteByExample(SchemeSurePriceItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeSurePriceItem record);

    int insertSelective(SchemeSurePriceItem record);

    List<SchemeSurePriceItem> selectByExample(SchemeSurePriceItemExample example);

    SchemeSurePriceItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeSurePriceItem record, @Param("example") SchemeSurePriceItemExample example);

    int updateByExample(@Param("record") SchemeSurePriceItem record, @Param("example") SchemeSurePriceItemExample example);

    int updateByPrimaryKeySelective(SchemeSurePriceItem record);

    int updateByPrimaryKey(SchemeSurePriceItem record);
}