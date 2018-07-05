package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.CsrFieldRelation;
import com.copower.pmcc.assess.dal.basis.entity.CsrFieldRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CsrFieldRelationMapper {
    int countByExample(CsrFieldRelationExample example);

    int deleteByExample(CsrFieldRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CsrFieldRelation record);

    int insertSelective(CsrFieldRelation record);

    List<CsrFieldRelation> selectByExample(CsrFieldRelationExample example);

    CsrFieldRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CsrFieldRelation record, @Param("example") CsrFieldRelationExample example);

    int updateByExample(@Param("record") CsrFieldRelation record, @Param("example") CsrFieldRelationExample example);

    int updateByPrimaryKeySelective(CsrFieldRelation record);

    int updateByPrimaryKey(CsrFieldRelation record);
}