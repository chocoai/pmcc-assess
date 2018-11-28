package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataImgTwoDimensional;
import com.copower.pmcc.assess.dal.basis.entity.DataImgTwoDimensionalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataImgTwoDimensionalMapper {
    int countByExample(DataImgTwoDimensionalExample example);

    int deleteByExample(DataImgTwoDimensionalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataImgTwoDimensional record);

    int insertSelective(DataImgTwoDimensional record);

    List<DataImgTwoDimensional> selectByExample(DataImgTwoDimensionalExample example);

    DataImgTwoDimensional selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataImgTwoDimensional record, @Param("example") DataImgTwoDimensionalExample example);

    int updateByExample(@Param("record") DataImgTwoDimensional record, @Param("example") DataImgTwoDimensionalExample example);

    int updateByPrimaryKeySelective(DataImgTwoDimensional record);

    int updateByPrimaryKey(DataImgTwoDimensional record);
}