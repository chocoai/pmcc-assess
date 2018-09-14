package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeSelfSupportCost;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeSelfSupportCostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdIncomeSelfSupportCostMapper {
    int countByExample(MdIncomeSelfSupportCostExample example);

    int deleteByExample(MdIncomeSelfSupportCostExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdIncomeSelfSupportCost record);

    int insertSelective(MdIncomeSelfSupportCost record);

    List<MdIncomeSelfSupportCost> selectByExample(MdIncomeSelfSupportCostExample example);

    MdIncomeSelfSupportCost selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdIncomeSelfSupportCost record, @Param("example") MdIncomeSelfSupportCostExample example);

    int updateByExample(@Param("record") MdIncomeSelfSupportCost record, @Param("example") MdIncomeSelfSupportCostExample example);

    int updateByPrimaryKeySelective(MdIncomeSelfSupportCost record);

    int updateByPrimaryKey(MdIncomeSelfSupportCost record);
}