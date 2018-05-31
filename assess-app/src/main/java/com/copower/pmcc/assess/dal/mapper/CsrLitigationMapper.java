package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.CsrLitigation;
import com.copower.pmcc.assess.dal.entity.CsrLitigationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CsrLitigationMapper {
    int countByExample(CsrLitigationExample example);

    int deleteByExample(CsrLitigationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CsrLitigation record);

    int insertSelective(CsrLitigation record);

    List<CsrLitigation> selectByExample(CsrLitigationExample example);

    CsrLitigation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CsrLitigation record, @Param("example") CsrLitigationExample example);

    int updateByExample(@Param("record") CsrLitigation record, @Param("example") CsrLitigationExample example);

    int updateByPrimaryKeySelective(CsrLitigation record);

    int updateByPrimaryKey(CsrLitigation record);
}