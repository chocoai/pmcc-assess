package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BaseReplaceRecord;
import com.copower.pmcc.assess.dal.basis.entity.BaseReplaceRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseReplaceRecordMapper {
    int countByExample(BaseReplaceRecordExample example);

    int deleteByExample(BaseReplaceRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseReplaceRecord record);

    int insertSelective(BaseReplaceRecord record);

    List<BaseReplaceRecord> selectByExample(BaseReplaceRecordExample example);

    BaseReplaceRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseReplaceRecord record, @Param("example") BaseReplaceRecordExample example);

    int updateByExample(@Param("record") BaseReplaceRecord record, @Param("example") BaseReplaceRecordExample example);

    int updateByPrimaryKeySelective(BaseReplaceRecord record);

    int updateByPrimaryKey(BaseReplaceRecord record);
}