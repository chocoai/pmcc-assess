package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BaseParameter;
import com.copower.pmcc.assess.dal.basis.entity.BaseParameterExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseParameterMapper {
    int countByExample(BaseParameterExample example);

    int deleteByExample(BaseParameterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseParameter record);

    int insertSelective(BaseParameter record);

    List<BaseParameter> selectByExample(BaseParameterExample example);

    BaseParameter selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseParameter record, @Param("example") BaseParameterExample example);

    int updateByExample(@Param("record") BaseParameter record, @Param("example") BaseParameterExample example);

    int updateByPrimaryKeySelective(BaseParameter record);

    int updateByPrimaryKey(BaseParameter record);
}