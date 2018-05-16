package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.SchemeAreaGroupRecord;
import com.copower.pmcc.assess.dal.entity.SchemeAreaGroupRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchemeAreaGroupRecordMapper {
    int countByExample(SchemeAreaGroupRecordExample example);

    int deleteByExample(SchemeAreaGroupRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeAreaGroupRecord record);

    int insertSelective(SchemeAreaGroupRecord record);

    List<SchemeAreaGroupRecord> selectByExample(SchemeAreaGroupRecordExample example);

    SchemeAreaGroupRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeAreaGroupRecord record, @Param("example") SchemeAreaGroupRecordExample example);

    int updateByExample(@Param("record") SchemeAreaGroupRecord record, @Param("example") SchemeAreaGroupRecordExample example);

    int updateByPrimaryKeySelective(SchemeAreaGroupRecord record);

    int updateByPrimaryKey(SchemeAreaGroupRecord record);
}