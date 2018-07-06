package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingMedical;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingMedicalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineMatchingMedicalMapper {
    int countByExample(ExamineMatchingMedicalExample example);

    int deleteByExample(ExamineMatchingMedicalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineMatchingMedical record);

    int insertSelective(ExamineMatchingMedical record);

    List<ExamineMatchingMedical> selectByExample(ExamineMatchingMedicalExample example);

    ExamineMatchingMedical selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineMatchingMedical record, @Param("example") ExamineMatchingMedicalExample example);

    int updateByExample(@Param("record") ExamineMatchingMedical record, @Param("example") ExamineMatchingMedicalExample example);

    int updateByPrimaryKeySelective(ExamineMatchingMedical record);

    int updateByPrimaryKey(ExamineMatchingMedical record);
}