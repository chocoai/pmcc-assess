package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareRecordMapper {
    long countByExample(DeclareRecordExample example);

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