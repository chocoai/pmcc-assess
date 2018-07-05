package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.CsrProjectInfoGroup;
import com.copower.pmcc.assess.dal.basis.entity.CsrProjectInfoGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CsrProjectInfoGroupMapper {
    int countByExample(CsrProjectInfoGroupExample example);

    int deleteByExample(CsrProjectInfoGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CsrProjectInfoGroup record);

    int insertSelective(CsrProjectInfoGroup record);

    List<CsrProjectInfoGroup> selectByExample(CsrProjectInfoGroupExample example);

    CsrProjectInfoGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CsrProjectInfoGroup record, @Param("example") CsrProjectInfoGroupExample example);

    int updateByExample(@Param("record") CsrProjectInfoGroup record, @Param("example") CsrProjectInfoGroupExample example);

    int updateByPrimaryKeySelective(CsrProjectInfoGroup record);

    int updateByPrimaryKey(CsrProjectInfoGroup record);
}