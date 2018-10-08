package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataSetUseField;
import com.copower.pmcc.assess.dal.basis.entity.DataSetUseFieldExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataSetUseFieldMapper {
    int countByExample(DataSetUseFieldExample example);

    int deleteByExample(DataSetUseFieldExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataSetUseField record);

    int insertSelective(DataSetUseField record);

    List<DataSetUseField> selectByExample(DataSetUseFieldExample example);

    DataSetUseField selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataSetUseField record, @Param("example") DataSetUseFieldExample example);

    int updateByExample(@Param("record") DataSetUseField record, @Param("example") DataSetUseFieldExample example);

    int updateByPrimaryKeySelective(DataSetUseField record);

    int updateByPrimaryKey(DataSetUseField record);
}