package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.BaseProcess;
import com.copower.pmcc.assess.dal.entity.BaseProcessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseProcessMapper {
    int countByExample(BaseProcessExample example);

    int deleteByExample(BaseProcessExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseProcess record);

    int insertSelective(BaseProcess record);

    List<BaseProcess> selectByExample(BaseProcessExample example);

    BaseProcess selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseProcess record, @Param("example") BaseProcessExample example);

    int updateByExample(@Param("record") BaseProcess record, @Param("example") BaseProcessExample example);

    int updateByPrimaryKeySelective(BaseProcess record);

    int updateByPrimaryKey(BaseProcess record);
}