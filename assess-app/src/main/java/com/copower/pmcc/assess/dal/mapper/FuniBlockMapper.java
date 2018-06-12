package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.FuniBlock;
import com.copower.pmcc.assess.dal.entity.FuniBlockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FuniBlockMapper {
    int countByExample(FuniBlockExample example);

    int deleteByExample(FuniBlockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FuniBlock record);

    int insertSelective(FuniBlock record);

    List<FuniBlock> selectByExample(FuniBlockExample example);

    FuniBlock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FuniBlock record, @Param("example") FuniBlockExample example);

    int updateByExample(@Param("record") FuniBlock record, @Param("example") FuniBlockExample example);

    int updateByPrimaryKeySelective(FuniBlock record);

    int updateByPrimaryKey(FuniBlock record);
}