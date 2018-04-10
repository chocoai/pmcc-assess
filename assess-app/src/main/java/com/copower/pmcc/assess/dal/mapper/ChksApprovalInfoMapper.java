package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.ChksApprovalInfo;
import com.copower.pmcc.assess.dal.entity.ChksApprovalInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChksApprovalInfoMapper {
    int countByExample(ChksApprovalInfoExample example);

    int deleteByExample(ChksApprovalInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ChksApprovalInfo record);

    int insertSelective(ChksApprovalInfo record);

    List<ChksApprovalInfo> selectByExample(ChksApprovalInfoExample example);

    ChksApprovalInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ChksApprovalInfo record, @Param("example") ChksApprovalInfoExample example);

    int updateByExample(@Param("record") ChksApprovalInfo record, @Param("example") ChksApprovalInfoExample example);

    int updateByPrimaryKeySelective(ChksApprovalInfo record);

    int updateByPrimaryKey(ChksApprovalInfo record);
}