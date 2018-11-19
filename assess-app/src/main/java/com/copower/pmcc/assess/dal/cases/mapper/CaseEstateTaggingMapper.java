package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstateTagging;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateTaggingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseEstateTaggingMapper {
    int countByExample(CaseEstateTaggingExample example);

    int deleteByExample(CaseEstateTaggingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseEstateTagging record);

    int insertSelective(CaseEstateTagging record);

    List<CaseEstateTagging> selectByExample(CaseEstateTaggingExample example);

    CaseEstateTagging selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseEstateTagging record, @Param("example") CaseEstateTaggingExample example);

    int updateByExample(@Param("record") CaseEstateTagging record, @Param("example") CaseEstateTaggingExample example);

    int updateByPrimaryKeySelective(CaseEstateTagging record);

    int updateByPrimaryKey(CaseEstateTagging record);
}