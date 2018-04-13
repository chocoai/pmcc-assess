package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.BaseFormModuleField;
import com.copower.pmcc.assess.dal.entity.BaseFormModuleFieldExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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