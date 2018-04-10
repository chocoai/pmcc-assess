package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.ChksApprovalAssess;
import com.copower.pmcc.assess.dal.entity.ChksApprovalAssessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChksApprovalAssessMapper {
    int countByExample(ChksApprovalAssessExample example);

    int deleteByExample(ChksApprovalAssessExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ChksApprovalAssess record);

    int insertSelective(ChksApprovalAssess record);

    List<ChksApprovalAssess> selectByExample(ChksApprovalAssessExample example);

    ChksApprovalAssess selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ChksApprovalAssess record, @Param("example") ChksApprovalAssessExample example);

    int updateByExample(@Param("record") ChksApprovalAssess record, @Param("example") ChksApprovalAssessExample example);

    int updateByPrimaryKeySelective(ChksApprovalAssess record);

    int updateByPrimaryKey(ChksApprovalAssess record);
}