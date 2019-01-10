package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataCustomerField;
import com.copower.pmcc.assess.dal.basis.entity.DataCustomerFieldExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataCustomerFieldMapper {
    int countByExample(DataCustomerFieldExample example);

    int deleteByExample(DataCustomerFieldExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataCustomerField record);

    int insertSelective(DataCustomerField record);

    List<DataCustomerField> selectByExample(DataCustomerFieldExample example);

    DataCustomerField selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataCustomerField record, @Param("example") DataCustomerFieldExample example);

    int updateByExample(@Param("record") DataCustomerField record, @Param("example") DataCustomerFieldExample example);

    int updateByPrimaryKeySelective(DataCustomerField record);

    int updateByPrimaryKey(DataCustomerField record);
}