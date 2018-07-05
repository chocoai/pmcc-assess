package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.CsrProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.CsrProjectInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CsrProjectInfoMapper {
    int countByExample(CsrProjectInfoExample example);

    int deleteByExample(CsrProjectInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CsrProjectInfo record);

    int insertSelective(CsrProjectInfo record);

    List<CsrProjectInfo> selectByExample(CsrProjectInfoExample example);

    CsrProjectInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CsrProjectInfo record, @Param("example") CsrProjectInfoExample example);

    int updateByExample(@Param("record") CsrProjectInfo record, @Param("example") CsrProjectInfoExample example);

    int updateByPrimaryKeySelective(CsrProjectInfo record);

    int updateByPrimaryKey(CsrProjectInfo record);
}