package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentInfrastructureChildren;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentInfrastructureChildrenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdDevelopmentInfrastructureChildrenMapper {
    int countByExample(MdDevelopmentInfrastructureChildrenExample example);

    int deleteByExample(MdDevelopmentInfrastructureChildrenExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdDevelopmentInfrastructureChildren record);

    int insertSelective(MdDevelopmentInfrastructureChildren record);

    List<MdDevelopmentInfrastructureChildren> selectByExample(MdDevelopmentInfrastructureChildrenExample example);

    MdDevelopmentInfrastructureChildren selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdDevelopmentInfrastructureChildren record, @Param("example") MdDevelopmentInfrastructureChildrenExample example);

    int updateByExample(@Param("record") MdDevelopmentInfrastructureChildren record, @Param("example") MdDevelopmentInfrastructureChildrenExample example);

    int updateByPrimaryKeySelective(MdDevelopmentInfrastructureChildren record);

    int updateByPrimaryKey(MdDevelopmentInfrastructureChildren record);
}