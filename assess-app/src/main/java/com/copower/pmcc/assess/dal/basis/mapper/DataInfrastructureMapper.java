package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructure;
import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataInfrastructureMapper {
    int countByExample(DataInfrastructureExample example);

    int deleteByExample(DataInfrastructureExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataInfrastructure record);

    int insertSelective(DataInfrastructure record);

    List<DataInfrastructure> selectByExample(DataInfrastructureExample example);

    DataInfrastructure selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataInfrastructure record, @Param("example") DataInfrastructureExample example);

    int updateByExample(@Param("record") DataInfrastructure record, @Param("example") DataInfrastructureExample example);

    int updateByPrimaryKeySelective(DataInfrastructure record);

    int updateByPrimaryKey(DataInfrastructure record);
}