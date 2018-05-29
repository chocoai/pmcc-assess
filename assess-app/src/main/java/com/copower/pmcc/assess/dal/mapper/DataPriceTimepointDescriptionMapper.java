package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.DataPriceTimepointDescription;
import com.copower.pmcc.assess.dal.entity.DataPriceTimepointDescriptionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataPriceTimepointDescriptionMapper {
    int countByExample(DataPriceTimepointDescriptionExample example);

    int deleteByExample(DataPriceTimepointDescriptionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataPriceTimepointDescription record);

    int insertSelective(DataPriceTimepointDescription record);

    List<DataPriceTimepointDescription> selectByExample(DataPriceTimepointDescriptionExample example);

    DataPriceTimepointDescription selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataPriceTimepointDescription record, @Param("example") DataPriceTimepointDescriptionExample example);

    int updateByExample(@Param("record") DataPriceTimepointDescription record, @Param("example") DataPriceTimepointDescriptionExample example);

    int updateByPrimaryKeySelective(DataPriceTimepointDescription record);

    int updateByPrimaryKey(DataPriceTimepointDescription record);
}