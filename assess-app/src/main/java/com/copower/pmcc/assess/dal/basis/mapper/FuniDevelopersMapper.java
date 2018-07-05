package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.FuniDevelopers;
import com.copower.pmcc.assess.dal.basis.entity.FuniDevelopersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FuniDevelopersMapper {
    int countByExample(FuniDevelopersExample example);

    int deleteByExample(FuniDevelopersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FuniDevelopers record);

    int insertSelective(FuniDevelopers record);

    List<FuniDevelopers> selectByExample(FuniDevelopersExample example);

    FuniDevelopers selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FuniDevelopers record, @Param("example") FuniDevelopersExample example);

    int updateByExample(@Param("record") FuniDevelopers record, @Param("example") FuniDevelopersExample example);

    int updateByPrimaryKeySelective(FuniDevelopers record);

    int updateByPrimaryKey(FuniDevelopers record);
}