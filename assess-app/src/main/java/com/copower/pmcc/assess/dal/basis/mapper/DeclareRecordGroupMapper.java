package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRecordGroup;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecordGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareRecordGroupMapper {
    int countByExample(DeclareRecordGroupExample example);

    int deleteByExample(DeclareRecordGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareRecordGroup record);

    int insertSelective(DeclareRecordGroup record);

    List<DeclareRecordGroup> selectByExample(DeclareRecordGroupExample example);

    DeclareRecordGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareRecordGroup record, @Param("example") DeclareRecordGroupExample example);

    int updateByExample(@Param("record") DeclareRecordGroup record, @Param("example") DeclareRecordGroupExample example);

    int updateByPrimaryKeySelective(DeclareRecordGroup record);

    int updateByPrimaryKey(DeclareRecordGroup record);
}