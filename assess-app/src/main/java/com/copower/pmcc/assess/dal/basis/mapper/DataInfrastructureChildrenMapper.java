package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureChildren;
import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureChildrenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataInfrastructureChildrenMapper {
    int countByExample(DataInfrastructureChildrenExample example);

    int deleteByExample(DataInfrastructureChildrenExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataInfrastructureChildren record);

    int insertSelective(DataInfrastructureChildren record);

    List<DataInfrastructureChildren> selectByExample(DataInfrastructureChildrenExample example);

    DataInfrastructureChildren selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataInfrastructureChildren record, @Param("example") DataInfrastructureChildrenExample example);

    int updateByExample(@Param("record") DataInfrastructureChildren record, @Param("example") DataInfrastructureChildrenExample example);

    int updateByPrimaryKeySelective(DataInfrastructureChildren record);

    int updateByPrimaryKey(DataInfrastructureChildren record);
}