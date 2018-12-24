package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataDamagedDegree;
import com.copower.pmcc.assess.dal.basis.entity.DataDamagedDegreeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataDamagedDegreeMapper {
    int countByExample(DataDamagedDegreeExample example);

    int deleteByExample(DataDamagedDegreeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataDamagedDegree record);

    int insertSelective(DataDamagedDegree record);

    List<DataDamagedDegree> selectByExample(DataDamagedDegreeExample example);

    DataDamagedDegree selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataDamagedDegree record, @Param("example") DataDamagedDegreeExample example);

    int updateByExample(@Param("record") DataDamagedDegree record, @Param("example") DataDamagedDegreeExample example);

    int updateByPrimaryKeySelective(DataDamagedDegree record);

    int updateByPrimaryKey(DataDamagedDegree record);
}