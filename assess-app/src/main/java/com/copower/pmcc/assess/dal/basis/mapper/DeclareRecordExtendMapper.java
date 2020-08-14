package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRecordExtend;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecordExtendExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareRecordExtendMapper {
    long countByExample(DeclareRecordExtendExample example);

    int deleteByExample(DeclareRecordExtendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareRecordExtend record);

    int insertSelective(@Param("record") DeclareRecordExtend record, @Param("selective") DeclareRecordExtend.Column ... selective);

    List<DeclareRecordExtend> selectByExample(DeclareRecordExtendExample example);

    DeclareRecordExtend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareRecordExtend record, @Param("example") DeclareRecordExtendExample example, @Param("selective") DeclareRecordExtend.Column ... selective);

    int updateByExample(@Param("record") DeclareRecordExtend record, @Param("example") DeclareRecordExtendExample example);

    int updateByPrimaryKeySelective(@Param("record") DeclareRecordExtend record, @Param("selective") DeclareRecordExtend.Column ... selective);

    int updateByPrimaryKey(DeclareRecordExtend record);

    int batchInsert(@Param("list") List<DeclareRecordExtend> list);

    int batchInsertSelective(@Param("list") List<DeclareRecordExtend> list, @Param("selective") DeclareRecordExtend.Column ... selective);
}