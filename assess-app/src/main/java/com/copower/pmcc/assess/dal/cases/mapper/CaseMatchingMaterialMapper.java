package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingMaterial;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingMaterialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseMatchingMaterialMapper {
    int countByExample(CaseMatchingMaterialExample example);

    int deleteByExample(CaseMatchingMaterialExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseMatchingMaterial record);

    int insertSelective(CaseMatchingMaterial record);

    List<CaseMatchingMaterial> selectByExample(CaseMatchingMaterialExample example);

    CaseMatchingMaterial selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseMatchingMaterial record, @Param("example") CaseMatchingMaterialExample example);

    int updateByExample(@Param("record") CaseMatchingMaterial record, @Param("example") CaseMatchingMaterialExample example);

    int updateByPrimaryKeySelective(CaseMatchingMaterial record);

    int updateByPrimaryKey(CaseMatchingMaterial record);
}