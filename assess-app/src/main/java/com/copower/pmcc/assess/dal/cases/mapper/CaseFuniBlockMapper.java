package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseFuniBlock;
import com.copower.pmcc.assess.dal.cases.entity.CaseFuniBlockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseFuniBlockMapper {
    int countByExample(CaseFuniBlockExample example);

    int deleteByExample(CaseFuniBlockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseFuniBlock record);

    int insertSelective(CaseFuniBlock record);

    List<CaseFuniBlock> selectByExample(CaseFuniBlockExample example);

    CaseFuniBlock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseFuniBlock record, @Param("example") CaseFuniBlockExample example);

    int updateByExample(@Param("record") CaseFuniBlock record, @Param("example") CaseFuniBlockExample example);

    int updateByPrimaryKeySelective(CaseFuniBlock record);

    int updateByPrimaryKey(CaseFuniBlock record);
}