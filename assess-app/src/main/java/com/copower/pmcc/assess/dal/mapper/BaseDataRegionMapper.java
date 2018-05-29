package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.BaseDataRegion;
import com.copower.pmcc.assess.dal.entity.BaseDataRegionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDataRegionMapper {
    int countByExample(BaseDataRegionExample example);

    int deleteByExample(BaseDataRegionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseDataRegion record);

    int insertSelective(BaseDataRegion record);

    List<BaseDataRegion> selectByExample(BaseDataRegionExample example);

    BaseDataRegion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseDataRegion record, @Param("example") BaseDataRegionExample example);

    int updateByExample(@Param("record") BaseDataRegion record, @Param("example") BaseDataRegionExample example);

    int updateByPrimaryKeySelective(BaseDataRegion record);

    int updateByPrimaryKey(BaseDataRegion record);
}