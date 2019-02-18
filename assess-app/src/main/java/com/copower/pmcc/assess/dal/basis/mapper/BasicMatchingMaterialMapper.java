package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingMaterial;
import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingMaterialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicMatchingMaterialMapper {
    int countByExample(BasicMatchingMaterialExample example);

    int deleteByExample(BasicMatchingMaterialExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicMatchingMaterial record);

    int insertSelective(BasicMatchingMaterial record);

    List<BasicMatchingMaterial> selectByExample(BasicMatchingMaterialExample example);

    BasicMatchingMaterial selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicMatchingMaterial record, @Param("example") BasicMatchingMaterialExample example);

    int updateByExample(@Param("record") BasicMatchingMaterial record, @Param("example") BasicMatchingMaterialExample example);

    int updateByPrimaryKeySelective(BasicMatchingMaterial record);

    int updateByPrimaryKey(BasicMatchingMaterial record);
}