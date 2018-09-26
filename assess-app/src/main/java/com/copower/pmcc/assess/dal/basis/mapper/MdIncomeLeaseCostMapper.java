package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeLeaseCost;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeLeaseCostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdIncomeLeaseCostMapper {
    int countByExample(MdIncomeLeaseCostExample example);

    int deleteByExample(MdIncomeLeaseCostExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdIncomeLeaseCost record);

    int insertSelective(MdIncomeLeaseCost record);

    List<MdIncomeLeaseCost> selectByExample(MdIncomeLeaseCostExample example);

    MdIncomeLeaseCost selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdIncomeLeaseCost record, @Param("example") MdIncomeLeaseCostExample example);

    int updateByExample(@Param("record") MdIncomeLeaseCost record, @Param("example") MdIncomeLeaseCostExample example);

    int updateByPrimaryKeySelective(MdIncomeLeaseCost record);

    int updateByPrimaryKey(MdIncomeLeaseCost record);
}