package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseNetInfoRecord;
import com.copower.pmcc.assess.dal.cases.entity.CaseNetInfoRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseNetInfoRecordMapper {
    int countByExample(CaseNetInfoRecordExample example);

    int deleteByExample(CaseNetInfoRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseNetInfoRecord record);

    int insertSelective(CaseNetInfoRecord record);

    List<CaseNetInfoRecord> selectByExample(CaseNetInfoRecordExample example);

    CaseNetInfoRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseNetInfoRecord record, @Param("example") CaseNetInfoRecordExample example);

    int updateByExample(@Param("record") CaseNetInfoRecord record, @Param("example") CaseNetInfoRecordExample example);

    int updateByPrimaryKeySelective(CaseNetInfoRecord record);

    int updateByPrimaryKey(CaseNetInfoRecord record);
}