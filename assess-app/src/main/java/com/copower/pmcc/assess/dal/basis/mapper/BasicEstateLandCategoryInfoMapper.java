package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandCategoryInfo;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandCategoryInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicEstateLandCategoryInfoMapper {
    int countByExample(BasicEstateLandCategoryInfoExample example);

    int deleteByExample(BasicEstateLandCategoryInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicEstateLandCategoryInfo record);

    int insertSelective(BasicEstateLandCategoryInfo record);

    List<BasicEstateLandCategoryInfo> selectByExample(BasicEstateLandCategoryInfoExample example);

    BasicEstateLandCategoryInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicEstateLandCategoryInfo record, @Param("example") BasicEstateLandCategoryInfoExample example);

    int updateByExample(@Param("record") BasicEstateLandCategoryInfo record, @Param("example") BasicEstateLandCategoryInfoExample example);

    int updateByPrimaryKeySelective(BasicEstateLandCategoryInfo record);

    int updateByPrimaryKey(BasicEstateLandCategoryInfo record);
}