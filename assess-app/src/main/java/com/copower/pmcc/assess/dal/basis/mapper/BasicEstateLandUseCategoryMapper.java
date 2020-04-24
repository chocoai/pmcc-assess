package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandUseCategory;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandUseCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicEstateLandUseCategoryMapper {
    int countByExample(BasicEstateLandUseCategoryExample example);

    int deleteByExample(BasicEstateLandUseCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicEstateLandUseCategory record);

    int insertSelective(BasicEstateLandUseCategory record);

    List<BasicEstateLandUseCategory> selectByExample(BasicEstateLandUseCategoryExample example);

    BasicEstateLandUseCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicEstateLandUseCategory record, @Param("example") BasicEstateLandUseCategoryExample example);

    int updateByExample(@Param("record") BasicEstateLandUseCategory record, @Param("example") BasicEstateLandUseCategoryExample example);

    int updateByPrimaryKeySelective(BasicEstateLandUseCategory record);

    int updateByPrimaryKey(BasicEstateLandUseCategory record);
}