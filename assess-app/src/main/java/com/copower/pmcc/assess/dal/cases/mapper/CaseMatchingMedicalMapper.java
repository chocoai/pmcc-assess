package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingMedical;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingMedicalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseMatchingMedicalMapper {
    int countByExample(CaseMatchingMedicalExample example);

    int deleteByExample(CaseMatchingMedicalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseMatchingMedical record);

    int insertSelective(CaseMatchingMedical record);

    List<CaseMatchingMedical> selectByExample(CaseMatchingMedicalExample example);

    CaseMatchingMedical selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseMatchingMedical record, @Param("example") CaseMatchingMedicalExample example);

    int updateByExample(@Param("record") CaseMatchingMedical record, @Param("example") CaseMatchingMedicalExample example);

    int updateByPrimaryKeySelective(CaseMatchingMedical record);

    int updateByPrimaryKey(CaseMatchingMedical record);
}