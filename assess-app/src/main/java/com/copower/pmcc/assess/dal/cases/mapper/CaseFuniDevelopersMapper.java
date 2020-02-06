package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseFuniDevelopers;
import com.copower.pmcc.assess.dal.cases.entity.CaseFuniDevelopersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseFuniDevelopersMapper {
    int countByExample(CaseFuniDevelopersExample example);

    int deleteByExample(CaseFuniDevelopersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseFuniDevelopers record);

    int insertSelective(CaseFuniDevelopers record);

    List<CaseFuniDevelopers> selectByExample(CaseFuniDevelopersExample example);

    CaseFuniDevelopers selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseFuniDevelopers record, @Param("example") CaseFuniDevelopersExample example);

    int updateByExample(@Param("record") CaseFuniDevelopers record, @Param("example") CaseFuniDevelopersExample example);

    int updateByPrimaryKeySelective(CaseFuniDevelopers record);

    int updateByPrimaryKey(CaseFuniDevelopers record);
}