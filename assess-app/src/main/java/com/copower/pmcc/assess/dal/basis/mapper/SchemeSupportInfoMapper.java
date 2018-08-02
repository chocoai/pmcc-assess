package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SchemeSupportInfo;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSupportInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchemeSupportInfoMapper {
    int countByExample(SchemeSupportInfoExample example);

    int deleteByExample(SchemeSupportInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeSupportInfo record);

    int insertSelective(SchemeSupportInfo record);

    List<SchemeSupportInfo> selectByExample(SchemeSupportInfoExample example);

    SchemeSupportInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeSupportInfo record, @Param("example") SchemeSupportInfoExample example);

    int updateByExample(@Param("record") SchemeSupportInfo record, @Param("example") SchemeSupportInfoExample example);

    int updateByPrimaryKeySelective(SchemeSupportInfo record);

    int updateByPrimaryKey(SchemeSupportInfo record);
}