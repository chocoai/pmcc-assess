package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.BaseFormModule;
import com.copower.pmcc.assess.dal.entity.BaseFormModuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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