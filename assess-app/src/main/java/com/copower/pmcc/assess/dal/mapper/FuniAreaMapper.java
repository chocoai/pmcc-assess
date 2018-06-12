package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.FuniArea;
import com.copower.pmcc.assess.dal.entity.FuniAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FuniAreaMapper {
    int countByExample(FuniAreaExample example);

    int deleteByExample(FuniAreaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FuniArea record);

    int insertSelective(FuniArea record);

    List<FuniArea> selectByExample(FuniAreaExample example);

    FuniArea selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FuniArea record, @Param("example") FuniAreaExample example);

    int updateByExample(@Param("record") FuniArea record, @Param("example") FuniAreaExample example);

    int updateByPrimaryKeySelective(FuniArea record);

    int updateByPrimaryKey(FuniArea record);
}