package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.Infrastructure;
import com.copower.pmcc.assess.dal.entity.InfrastructureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InfrastructureMapper {
    int countByExample(InfrastructureExample example);

    int deleteByExample(InfrastructureExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Infrastructure record);

    int insertSelective(Infrastructure record);

    List<Infrastructure> selectByExample(InfrastructureExample example);

    Infrastructure selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Infrastructure record, @Param("example") InfrastructureExample example);

    int updateByExample(@Param("record") Infrastructure record, @Param("example") InfrastructureExample example);

    int updateByPrimaryKeySelective(Infrastructure record);

    int updateByPrimaryKey(Infrastructure record);
}