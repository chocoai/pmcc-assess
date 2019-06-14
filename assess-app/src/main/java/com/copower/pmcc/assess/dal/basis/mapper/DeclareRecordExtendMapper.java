package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRecordExtend;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecordExtendExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareRecordExtendMapper {
    int countByExample(DeclareRecordExtendExample example);

    int deleteByExample(DeclareRecordExtendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareRecordExtend record);

    int insertSelective(DeclareRecordExtend record);

    List<DeclareRecordExtend> selectByExample(DeclareRecordExtendExample example);

    DeclareRecordExtend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareRecordExtend record, @Param("example") DeclareRecordExtendExample example);

    int updateByExample(@Param("record") DeclareRecordExtend record, @Param("example") DeclareRecordExtendExample example);

    int updateByPrimaryKeySelective(DeclareRecordExtend record);

    int updateByPrimaryKey(DeclareRecordExtend record);
}