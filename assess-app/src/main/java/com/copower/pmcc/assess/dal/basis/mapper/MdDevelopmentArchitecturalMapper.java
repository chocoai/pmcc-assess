package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentArchitectural;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentArchitecturalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdDevelopmentArchitecturalMapper {
    int countByExample(MdDevelopmentArchitecturalExample example);

    int deleteByExample(MdDevelopmentArchitecturalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdDevelopmentArchitectural record);

    int insertSelective(MdDevelopmentArchitectural record);

    List<MdDevelopmentArchitectural> selectByExample(MdDevelopmentArchitecturalExample example);

    MdDevelopmentArchitectural selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdDevelopmentArchitectural record, @Param("example") MdDevelopmentArchitecturalExample example);

    int updateByExample(@Param("record") MdDevelopmentArchitectural record, @Param("example") MdDevelopmentArchitecturalExample example);

    int updateByPrimaryKeySelective(MdDevelopmentArchitectural record);

    int updateByPrimaryKey(MdDevelopmentArchitectural record);
}