package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataSetUseFieldItem;
import com.copower.pmcc.assess.dal.basis.entity.DataSetUseFieldItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataSetUseFieldItemMapper {
    int countByExample(DataSetUseFieldItemExample example);

    int deleteByExample(DataSetUseFieldItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataSetUseFieldItem record);

    int insertSelective(DataSetUseFieldItem record);

    List<DataSetUseFieldItem> selectByExample(DataSetUseFieldItemExample example);

    DataSetUseFieldItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataSetUseFieldItem record, @Param("example") DataSetUseFieldItemExample example);

    int updateByExample(@Param("record") DataSetUseFieldItem record, @Param("example") DataSetUseFieldItemExample example);

    int updateByPrimaryKeySelective(DataSetUseFieldItem record);

    int updateByPrimaryKey(DataSetUseFieldItem record);
}