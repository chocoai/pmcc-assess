package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceRecord;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchemeSurePriceRecordMapper {
    int countByExample(SchemeSurePriceRecordExample example);

    int deleteByExample(SchemeSurePriceRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeSurePriceRecord record);

    int insertSelective(SchemeSurePriceRecord record);

    List<SchemeSurePriceRecord> selectByExample(SchemeSurePriceRecordExample example);

    SchemeSurePriceRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeSurePriceRecord record, @Param("example") SchemeSurePriceRecordExample example);

    int updateByExample(@Param("record") SchemeSurePriceRecord record, @Param("example") SchemeSurePriceRecordExample example);

    int updateByPrimaryKeySelective(SchemeSurePriceRecord record);

    int updateByPrimaryKey(SchemeSurePriceRecord record);
}