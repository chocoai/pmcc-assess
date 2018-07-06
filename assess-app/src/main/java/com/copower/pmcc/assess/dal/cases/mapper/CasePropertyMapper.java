package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseProperty;
import com.copower.pmcc.assess.dal.cases.entity.CasePropertyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CasePropertyMapper {
    int countByExample(CasePropertyExample example);

    int deleteByExample(CasePropertyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseProperty record);

    int insertSelective(CaseProperty record);

    List<CaseProperty> selectByExample(CasePropertyExample example);

    CaseProperty selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseProperty record, @Param("example") CasePropertyExample example);

    int updateByExample(@Param("record") CaseProperty record, @Param("example") CasePropertyExample example);

    int updateByPrimaryKeySelective(CaseProperty record);

    int updateByPrimaryKey(CaseProperty record);
}