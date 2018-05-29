package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.DeclareInfo;
import com.copower.pmcc.assess.dal.entity.DeclareInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeclareInfoMapper {
    int countByExample(DeclareInfoExample example);

    int deleteByExample(DeclareInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareInfo record);

    int insertSelective(DeclareInfo record);

    List<DeclareInfo> selectByExample(DeclareInfoExample example);

    DeclareInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareInfo record, @Param("example") DeclareInfoExample example);

    int updateByExample(@Param("record") DeclareInfo record, @Param("example") DeclareInfoExample example);

    int updateByPrimaryKeySelective(DeclareInfo record);

    int updateByPrimaryKey(DeclareInfo record);
}