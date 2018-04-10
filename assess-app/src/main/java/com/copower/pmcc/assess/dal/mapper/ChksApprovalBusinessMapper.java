package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.ChksApprovalBusiness;
import com.copower.pmcc.assess.dal.entity.ChksApprovalBusinessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChksApprovalBusinessMapper {
    int countByExample(ChksApprovalBusinessExample example);

    int deleteByExample(ChksApprovalBusinessExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ChksApprovalBusiness record);

    int insertSelective(ChksApprovalBusiness record);

    List<ChksApprovalBusiness> selectByExample(ChksApprovalBusinessExample example);

    ChksApprovalBusiness selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ChksApprovalBusiness record, @Param("example") ChksApprovalBusinessExample example);

    int updateByExample(@Param("record") ChksApprovalBusiness record, @Param("example") ChksApprovalBusinessExample example);

    int updateByPrimaryKeySelective(ChksApprovalBusiness record);

    int updateByPrimaryKey(ChksApprovalBusiness record);
}