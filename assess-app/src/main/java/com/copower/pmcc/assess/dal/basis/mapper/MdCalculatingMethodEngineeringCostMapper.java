package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdCalculatingMethodEngineeringCost;
import com.copower.pmcc.assess.dal.basis.entity.MdCalculatingMethodEngineeringCostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdCalculatingMethodEngineeringCostMapper {
    int countByExample(MdCalculatingMethodEngineeringCostExample example);

    int deleteByExample(MdCalculatingMethodEngineeringCostExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdCalculatingMethodEngineeringCost record);

    int insertSelective(MdCalculatingMethodEngineeringCost record);

    List<MdCalculatingMethodEngineeringCost> selectByExample(MdCalculatingMethodEngineeringCostExample example);

    MdCalculatingMethodEngineeringCost selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdCalculatingMethodEngineeringCost record, @Param("example") MdCalculatingMethodEngineeringCostExample example);

    int updateByExample(@Param("record") MdCalculatingMethodEngineeringCost record, @Param("example") MdCalculatingMethodEngineeringCostExample example);

    int updateByPrimaryKeySelective(MdCalculatingMethodEngineeringCost record);

    int updateByPrimaryKey(MdCalculatingMethodEngineeringCost record);
}