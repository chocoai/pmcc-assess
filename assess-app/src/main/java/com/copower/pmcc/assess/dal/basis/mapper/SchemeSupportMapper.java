package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SchemeSupport;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSupportExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchemeSupportMapper {
    int countByExample(SchemeSupportExample example);

    int deleteByExample(SchemeSupportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeSupport record);

    int insertSelective(SchemeSupport record);

    List<SchemeSupport> selectByExample(SchemeSupportExample example);

    SchemeSupport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeSupport record, @Param("example") SchemeSupportExample example);

    int updateByExample(@Param("record") SchemeSupport record, @Param("example") SchemeSupportExample example);

    int updateByPrimaryKeySelective(SchemeSupport record);

    int updateByPrimaryKey(SchemeSupport record);
}