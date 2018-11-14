package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicEstateTagging;
import com.copower.pmcc.assess.dal.basic.entity.BasicEstateTaggingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicEstateTaggingMapper {
    int countByExample(BasicEstateTaggingExample example);

    int deleteByExample(BasicEstateTaggingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicEstateTagging record);

    int insertSelective(BasicEstateTagging record);

    List<BasicEstateTagging> selectByExample(BasicEstateTaggingExample example);

    BasicEstateTagging selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicEstateTagging record, @Param("example") BasicEstateTaggingExample example);

    int updateByExample(@Param("record") BasicEstateTagging record, @Param("example") BasicEstateTaggingExample example);

    int updateByPrimaryKeySelective(BasicEstateTagging record);

    int updateByPrimaryKey(BasicEstateTagging record);
}