package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatch;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicApplyBatchMapper {
    long countByExample(BasicApplyBatchExample example);

    int deleteByExample(BasicApplyBatchExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicApplyBatch record);

    int insertSelective(BasicApplyBatch record);

    List<BasicApplyBatch> selectByExample(BasicApplyBatchExample example);

    BasicApplyBatch selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicApplyBatch record, @Param("example") BasicApplyBatchExample example);

    int updateByExample(@Param("record") BasicApplyBatch record, @Param("example") BasicApplyBatchExample example);

    int updateByPrimaryKeySelective(BasicApplyBatch record);

    int updateByPrimaryKey(BasicApplyBatch record);
}