package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataQualification;
import com.copower.pmcc.assess.dal.basis.entity.DataQualificationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataQualificationMapper {
    int countByExample(DataQualificationExample example);

    int deleteByExample(DataQualificationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataQualification record);

    int insertSelective(DataQualification record);

    List<DataQualification> selectByExample(DataQualificationExample example);

    DataQualification selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataQualification record, @Param("example") DataQualificationExample example);

    int updateByExample(@Param("record") DataQualification record, @Param("example") DataQualificationExample example);

    int updateByPrimaryKeySelective(DataQualification record);

    int updateByPrimaryKey(DataQualification record);
}