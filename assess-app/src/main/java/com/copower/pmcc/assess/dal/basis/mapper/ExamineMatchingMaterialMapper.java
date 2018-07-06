package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingMaterial;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingMaterialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineMatchingMaterialMapper {
    int countByExample(ExamineMatchingMaterialExample example);

    int deleteByExample(ExamineMatchingMaterialExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineMatchingMaterial record);

    int insertSelective(ExamineMatchingMaterial record);

    List<ExamineMatchingMaterial> selectByExample(ExamineMatchingMaterialExample example);

    ExamineMatchingMaterial selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineMatchingMaterial record, @Param("example") ExamineMatchingMaterialExample example);

    int updateByExample(@Param("record") ExamineMatchingMaterial record, @Param("example") ExamineMatchingMaterialExample example);

    int updateByPrimaryKeySelective(ExamineMatchingMaterial record);

    int updateByPrimaryKey(ExamineMatchingMaterial record);
}