package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicMatchingEnvironment;
import com.copower.pmcc.assess.dal.basic.entity.BasicMatchingEnvironmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicMatchingEnvironmentMapper {
    int countByExample(BasicMatchingEnvironmentExample example);

    int deleteByExample(BasicMatchingEnvironmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicMatchingEnvironment record);

    int insertSelective(BasicMatchingEnvironment record);

    List<BasicMatchingEnvironment> selectByExample(BasicMatchingEnvironmentExample example);

    BasicMatchingEnvironment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicMatchingEnvironment record, @Param("example") BasicMatchingEnvironmentExample example);

    int updateByExample(@Param("record") BasicMatchingEnvironment record, @Param("example") BasicMatchingEnvironmentExample example);

    int updateByPrimaryKeySelective(BasicMatchingEnvironment record);

    int updateByPrimaryKey(BasicMatchingEnvironment record);
}