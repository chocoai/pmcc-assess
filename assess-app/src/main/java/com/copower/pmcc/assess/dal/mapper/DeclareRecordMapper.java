package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.entity.DeclareRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeclareRecordMapper {
    int countByExample(DeclareRecordExample example);

    int deleteByExample(DeclareRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareRecord record);

    int insertSelective(DeclareRecord record);

    List<DeclareRecord> selectByExample(DeclareRecordExample example);

    DeclareRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareRecord record, @Param("example") DeclareRecordExample example);

    int updateByExample(@Param("record") DeclareRecord record, @Param("example") DeclareRecordExample example);

    int updateByPrimaryKeySelective(DeclareRecord record);

    int updateByPrimaryKey(DeclareRecord record);
}