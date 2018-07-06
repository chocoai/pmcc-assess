package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseDeveloper;
import com.copower.pmcc.assess.dal.cases.entity.CaseDeveloperExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseDeveloperMapper {
    int countByExample(CaseDeveloperExample example);

    int deleteByExample(CaseDeveloperExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseDeveloper record);

    int insertSelective(CaseDeveloper record);

    List<CaseDeveloper> selectByExample(CaseDeveloperExample example);

    CaseDeveloper selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseDeveloper record, @Param("example") CaseDeveloperExample example);

    int updateByExample(@Param("record") CaseDeveloper record, @Param("example") CaseDeveloperExample example);

    int updateByPrimaryKeySelective(CaseDeveloper record);

    int updateByPrimaryKey(CaseDeveloper record);
}