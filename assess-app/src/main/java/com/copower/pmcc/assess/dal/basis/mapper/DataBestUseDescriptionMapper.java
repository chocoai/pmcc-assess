package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataBestUseDescription;
import com.copower.pmcc.assess.dal.basis.entity.DataBestUseDescriptionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataBestUseDescriptionMapper {
    int countByExample(DataBestUseDescriptionExample example);

    int deleteByExample(DataBestUseDescriptionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataBestUseDescription record);

    int insertSelective(DataBestUseDescription record);

    List<DataBestUseDescription> selectByExample(DataBestUseDescriptionExample example);

    DataBestUseDescription selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataBestUseDescription record, @Param("example") DataBestUseDescriptionExample example);

    int updateByExample(@Param("record") DataBestUseDescription record, @Param("example") DataBestUseDescriptionExample example);

    int updateByPrimaryKeySelective(DataBestUseDescription record);

    int updateByPrimaryKey(DataBestUseDescription record);
}