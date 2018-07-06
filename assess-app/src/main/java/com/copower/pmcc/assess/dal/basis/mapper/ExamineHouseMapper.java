package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouse;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineHouseMapper {
    int countByExample(ExamineHouseExample example);

    int deleteByExample(ExamineHouseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineHouse record);

    int insertSelective(ExamineHouse record);

    List<ExamineHouse> selectByExample(ExamineHouseExample example);

    ExamineHouse selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineHouse record, @Param("example") ExamineHouseExample example);

    int updateByExample(@Param("record") ExamineHouse record, @Param("example") ExamineHouseExample example);

    int updateByPrimaryKeySelective(ExamineHouse record);

    int updateByPrimaryKey(ExamineHouse record);
}