package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.ChksApprovalAssessDetails;
import com.copower.pmcc.assess.dal.entity.ChksApprovalAssessDetailsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChksApprovalAssessDetailsMapper {
    int countByExample(ChksApprovalAssessDetailsExample example);

    int deleteByExample(ChksApprovalAssessDetailsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ChksApprovalAssessDetails record);

    int insertSelective(ChksApprovalAssessDetails record);

    List<ChksApprovalAssessDetails> selectByExample(ChksApprovalAssessDetailsExample example);

    ChksApprovalAssessDetails selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ChksApprovalAssessDetails record, @Param("example") ChksApprovalAssessDetailsExample example);

    int updateByExample(@Param("record") ChksApprovalAssessDetails record, @Param("example") ChksApprovalAssessDetailsExample example);

    int updateByPrimaryKeySelective(ChksApprovalAssessDetails record);

    int updateByPrimaryKey(ChksApprovalAssessDetails record);
}