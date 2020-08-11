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

    int insertSelective(@Param("record") BasicApplyBatchDetail record, @Param("selective") BasicApplyBatchDetail.Column ... selective);

    List<BasicApplyBatchDetail> selectByExample(BasicApplyBatchDetailExample example);

    BasicApplyBatchDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicApplyBatchDetail record, @Param("example") BasicApplyBatchDetailExample example, @Param("selective") BasicApplyBatchDetail.Column ... selective);

    int updateByExample(@Param("record") BasicApplyBatchDetail record, @Param("example") BasicApplyBatchDetailExample example);

    int updateByPrimaryKeySelective(@Param("record") BasicApplyBatchDetail record, @Param("selective") BasicApplyBatchDetail.Column ... selective);

    int updateByPrimaryKey(BasicApplyBatchDetail record);

    int batchInsert(@Param("list") List<BasicApplyBatchDetail> list);

    int batchInsertSelective(@Param("list") List<BasicApplyBatchDetail> list, @Param("selective") BasicApplyBatchDetail.Column ... selective);
}