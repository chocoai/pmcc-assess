package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataCsrFieldRelation;
import com.copower.pmcc.assess.dal.basis.entity.DataCsrFieldRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataCsrFieldRelationMapper {
    int countByExample(DataCsrFieldRelationExample example);

    int deleteByExample(DataCsrFieldRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataCsrFieldRelation record);

    int insertSelective(DataCsrFieldRelation record);

    List<DataCsrFieldRelation> selectByExample(DataCsrFieldRelationExample example);

    DataCsrFieldRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataCsrFieldRelation record, @Param("example") DataCsrFieldRelationExample example);

    int updateByExample(@Param("record") DataCsrFieldRelation record, @Param("example") DataCsrFieldRelationExample example);

    int updateByPrimaryKeySelective(DataCsrFieldRelation record);

    int updateByPrimaryKey(DataCsrFieldRelation record);
}