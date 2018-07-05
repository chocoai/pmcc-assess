package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BaseFormModuleField;
import com.copower.pmcc.assess.dal.basis.entity.BaseFormModuleFieldExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseFormModuleFieldMapper {
    int countByExample(BaseFormModuleFieldExample example);

    int deleteByExample(BaseFormModuleFieldExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseFormModuleField record);

    int insertSelective(BaseFormModuleField record);

    List<BaseFormModuleField> selectByExample(BaseFormModuleFieldExample example);

    BaseFormModuleField selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseFormModuleField record, @Param("example") BaseFormModuleFieldExample example);

    int updateByExample(@Param("record") BaseFormModuleField record, @Param("example") BaseFormModuleFieldExample example);

    int updateByPrimaryKeySelective(BaseFormModuleField record);

    int updateByPrimaryKey(BaseFormModuleField record);
}