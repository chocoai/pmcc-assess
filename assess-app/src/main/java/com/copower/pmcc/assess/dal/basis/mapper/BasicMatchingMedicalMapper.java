package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingMedical;
import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingMedicalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicMatchingMedicalMapper {
    int countByExample(BasicMatchingMedicalExample example);

    int deleteByExample(BasicMatchingMedicalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicMatchingMedical record);

    int insertSelective(BasicMatchingMedical record);

    List<BasicMatchingMedical> selectByExample(BasicMatchingMedicalExample example);

    BasicMatchingMedical selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicMatchingMedical record, @Param("example") BasicMatchingMedicalExample example);

    int updateByExample(@Param("record") BasicMatchingMedical record, @Param("example") BasicMatchingMedicalExample example);

    int updateByPrimaryKeySelective(BasicMatchingMedical record);

    int updateByPrimaryKey(BasicMatchingMedical record);
}