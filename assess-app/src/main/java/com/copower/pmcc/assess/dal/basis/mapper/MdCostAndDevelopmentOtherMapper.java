package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdCostAndDevelopmentOther;
import com.copower.pmcc.assess.dal.basis.entity.MdCostAndDevelopmentOtherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdCostAndDevelopmentOtherMapper {
    int countByExample(MdCostAndDevelopmentOtherExample example);

    int deleteByExample(MdCostAndDevelopmentOtherExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdCostAndDevelopmentOther record);

    int insertSelective(MdCostAndDevelopmentOther record);

    List<MdCostAndDevelopmentOther> selectByExample(MdCostAndDevelopmentOtherExample example);

    MdCostAndDevelopmentOther selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdCostAndDevelopmentOther record, @Param("example") MdCostAndDevelopmentOtherExample example);

    int updateByExample(@Param("record") MdCostAndDevelopmentOther record, @Param("example") MdCostAndDevelopmentOtherExample example);

    int updateByPrimaryKeySelective(MdCostAndDevelopmentOther record);

    int updateByPrimaryKey(MdCostAndDevelopmentOther record);
}