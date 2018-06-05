package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.CsrBorrowerEntering;
import com.copower.pmcc.assess.dal.entity.CsrBorrowerEnteringExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CsrBorrowerEnteringMapper {
    int countByExample(CsrBorrowerEnteringExample example);

    int deleteByExample(CsrBorrowerEnteringExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CsrBorrowerEntering record);

    int insertSelective(CsrBorrowerEntering record);

    List<CsrBorrowerEntering> selectByExample(CsrBorrowerEnteringExample example);

    CsrBorrowerEntering selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CsrBorrowerEntering record, @Param("example") CsrBorrowerEnteringExample example);

    int updateByExample(@Param("record") CsrBorrowerEntering record, @Param("example") CsrBorrowerEnteringExample example);

    int updateByPrimaryKeySelective(CsrBorrowerEntering record);

    int updateByPrimaryKey(CsrBorrowerEntering record);
}