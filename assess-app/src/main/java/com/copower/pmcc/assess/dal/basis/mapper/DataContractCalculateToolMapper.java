package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataContractCalculateTool;
import com.copower.pmcc.assess.dal.basis.entity.DataContractCalculateToolExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataContractCalculateToolMapper {
    int countByExample(DataContractCalculateToolExample example);

    int deleteByExample(DataContractCalculateToolExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataContractCalculateTool record);

    int insertSelective(DataContractCalculateTool record);

    List<DataContractCalculateTool> selectByExample(DataContractCalculateToolExample example);

    DataContractCalculateTool selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataContractCalculateTool record, @Param("example") DataContractCalculateToolExample example);

    int updateByExample(@Param("record") DataContractCalculateTool record, @Param("example") DataContractCalculateToolExample example);

    int updateByPrimaryKeySelective(DataContractCalculateTool record);

    int updateByPrimaryKey(DataContractCalculateTool record);
}