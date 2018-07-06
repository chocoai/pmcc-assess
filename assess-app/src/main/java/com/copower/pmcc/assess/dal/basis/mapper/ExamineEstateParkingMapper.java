package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateParking;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateParkingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineEstateParkingMapper {
    int countByExample(ExamineEstateParkingExample example);

    int deleteByExample(ExamineEstateParkingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineEstateParking record);

    int insertSelective(ExamineEstateParking record);

    List<ExamineEstateParking> selectByExample(ExamineEstateParkingExample example);

    ExamineEstateParking selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineEstateParking record, @Param("example") ExamineEstateParkingExample example);

    int updateByExample(@Param("record") ExamineEstateParking record, @Param("example") ExamineEstateParkingExample example);

    int updateByPrimaryKeySelective(ExamineEstateParking record);

    int updateByPrimaryKey(ExamineEstateParking record);
}