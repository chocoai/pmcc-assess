package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.BaseParameter;
import com.copower.pmcc.assess.dal.entity.BaseParameterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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