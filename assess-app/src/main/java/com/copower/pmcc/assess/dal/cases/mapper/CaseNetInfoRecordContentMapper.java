package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseNetInfoRecordContent;
import com.copower.pmcc.assess.dal.cases.entity.CaseNetInfoRecordContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseNetInfoRecordContentMapper {
    int countByExample(CaseNetInfoRecordContentExample example);

    int deleteByExample(CaseNetInfoRecordContentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseNetInfoRecordContent record);

    int insertSelective(CaseNetInfoRecordContent record);

    List<CaseNetInfoRecordContent> selectByExampleWithBLOBs(CaseNetInfoRecordContentExample example);

    List<CaseNetInfoRecordContent> selectByExample(CaseNetInfoRecordContentExample example);

    CaseNetInfoRecordContent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseNetInfoRecordContent record, @Param("example") CaseNetInfoRecordContentExample example);

    int updateByExampleWithBLOBs(@Param("record") CaseNetInfoRecordContent record, @Param("example") CaseNetInfoRecordContentExample example);

    int updateByExample(@Param("record") CaseNetInfoRecordContent record, @Param("example") CaseNetInfoRecordContentExample example);

    int updateByPrimaryKeySelective(CaseNetInfoRecordContent record);

    int updateByPrimaryKeyWithBLOBs(CaseNetInfoRecordContent record);

    int updateByPrimaryKey(CaseNetInfoRecordContent record);
}