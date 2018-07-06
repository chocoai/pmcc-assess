package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineEstate;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineEstateMapper {
    int countByExample(ExamineEstateExample example);

    int deleteByExample(ExamineEstateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineEstate record);

    int insertSelective(ExamineEstate record);

    List<ExamineEstate> selectByExample(ExamineEstateExample example);

    ExamineEstate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineEstate record, @Param("example") ExamineEstateExample example);

    int updateByExample(@Param("record") ExamineEstate record, @Param("example") ExamineEstateExample example);

    int updateByPrimaryKeySelective(ExamineEstate record);

    int updateByPrimaryKey(ExamineEstate record);
}