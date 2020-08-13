package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentInfrastructureChildren;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentInfrastructureChildrenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdDevelopmentInfrastructureChildrenMapper {
    long countByExample(MdDevelopmentInfrastructureChildrenExample example);

    int deleteByExample(MdDevelopmentInfrastructureChildrenExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdDevelopmentInfrastructureChildren record);

    int insertSelective(@Param("record") MdDevelopmentInfrastructureChildren record, @Param("selective") MdDevelopmentInfrastructureChildren.Column ... selective);

    List<MdDevelopmentInfrastructureChildren> selectByExample(MdDevelopmentInfrastructureChildrenExample example);

    MdDevelopmentInfrastructureChildren selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdDevelopmentInfrastructureChildren record, @Param("example") MdDevelopmentInfrastructureChildrenExample example, @Param("selective") MdDevelopmentInfrastructureChildren.Column ... selective);

    int updateByExample(@Param("record") MdDevelopmentInfrastructureChildren record, @Param("example") MdDevelopmentInfrastructureChildrenExample example);

    int updateByPrimaryKeySelective(@Param("record") MdDevelopmentInfrastructureChildren record, @Param("selective") MdDevelopmentInfrastructureChildren.Column ... selective);

    int updateByPrimaryKey(MdDevelopmentInfrastructureChildren record);

    int batchInsert(@Param("list") List<MdDevelopmentInfrastructureChildren> list);

    int batchInsertSelective(@Param("list") List<MdDevelopmentInfrastructureChildren> list, @Param("selective") MdDevelopmentInfrastructureChildren.Column ... selective);
}