package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchDetail;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicApplyBatchDetailMapper {
    long countByExample(BasicApplyBatchDetailExample example);

    int deleteByExample(BasicApplyBatchDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicApplyBatchDetail record);

    int insertSelective(BasicApplyBatchDetail record);

    List<BasicApplyBatchDetail> selectByExample(BasicApplyBatchDetailExample example);

    BasicApplyBatchDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicApplyBatchDetail record, @Param("example") BasicApplyBatchDetailExample example);

    int updateByExample(@Param("record") BasicApplyBatchDetail record, @Param("example") BasicApplyBatchDetailExample example);

    int updateByPrimaryKeySelective(BasicApplyBatchDetail record);

    int updateByPrimaryKey(BasicApplyBatchDetail record);
}