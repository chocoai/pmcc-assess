package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseBlockArea;
import com.copower.pmcc.assess.dal.cases.entity.CaseBlockAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseBlockAreaMapper {
    int countByExample(CaseBlockAreaExample example);

    int deleteByExample(CaseBlockAreaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseBlockArea record);

    int insertSelective(CaseBlockArea record);

    List<CaseBlockArea> selectByExample(CaseBlockAreaExample example);

    CaseBlockArea selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseBlockArea record, @Param("example") CaseBlockAreaExample example);

    int updateByExample(@Param("record") CaseBlockArea record, @Param("example") CaseBlockAreaExample example);

    int updateByPrimaryKeySelective(CaseBlockArea record);

    int updateByPrimaryKey(CaseBlockArea record);
}