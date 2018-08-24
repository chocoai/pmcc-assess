package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdDevelopment;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdDevelopmentMapper {
    int countByExample(MdDevelopmentExample example);

    int deleteByExample(MdDevelopmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdDevelopment record);

    int insertSelective(MdDevelopment record);

    List<MdDevelopment> selectByExample(MdDevelopmentExample example);

    MdDevelopment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdDevelopment record, @Param("example") MdDevelopmentExample example);

    int updateByExample(@Param("record") MdDevelopment record, @Param("example") MdDevelopmentExample example);

    int updateByPrimaryKeySelective(MdDevelopment record);

    int updateByPrimaryKey(MdDevelopment record);
}