package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BaseFormModule;
import com.copower.pmcc.assess.dal.basis.entity.BaseFormModuleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseFormModuleMapper {
    int countByExample(BaseFormModuleExample example);

    int deleteByExample(BaseFormModuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseFormModule record);

    int insertSelective(BaseFormModule record);

    List<BaseFormModule> selectByExample(BaseFormModuleExample example);

    BaseFormModule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseFormModule record, @Param("example") BaseFormModuleExample example);

    int updateByExample(@Param("record") BaseFormModule record, @Param("example") BaseFormModuleExample example);

    int updateByPrimaryKeySelective(BaseFormModule record);

    int updateByPrimaryKey(BaseFormModule record);
}