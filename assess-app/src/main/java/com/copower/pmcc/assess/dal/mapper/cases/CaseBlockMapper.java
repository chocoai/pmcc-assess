package com.copower.pmcc.assess.dal.mapper.cases;

import com.copower.pmcc.assess.dal.entity.cases.CaseBlock;
import com.copower.pmcc.assess.dal.entity.cases.CaseBlockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseBlockMapper {
    int countByExample(CaseBlockExample example);

    int deleteByExample(CaseBlockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseBlock record);

    int insertSelective(CaseBlock record);

    List<CaseBlock> selectByExample(CaseBlockExample example);

    CaseBlock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseBlock record, @Param("example") CaseBlockExample example);

    int updateByExample(@Param("record") CaseBlock record, @Param("example") CaseBlockExample example);

    int updateByPrimaryKeySelective(CaseBlock record);

    int updateByPrimaryKey(CaseBlock record);
}